package nl.shanelab.multiblock;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public enum MultiBlockFactory {

	INSTANCE;
	
	private final Map<PluginMultiBlock, Material> pluginMultiBlockCores;
	
	private MultiBlockFactory() {
		this.pluginMultiBlockCores = new HashMap<>();
	}
	
	/**
	 * Register a MultiBlock structure with a valid plugin.
	 * MultiBlock structures have to be registered in the onEnable state of the plugin
	 * 
	 * @param plugin
	 * @param clazz
	 * @param params
	 */
	public <T extends IMultiBlock> void register(Plugin plugin, Class<T> clazz) {
		if (pluginMultiBlockCores.isEmpty() || !hasPluginMultiBlock(plugin, clazz)) {
			Object raw = null;
			
			try {
				raw = clazz.getConstructor().newInstance();
				
				PluginMultiBlock pluginMultiBlock = new PluginMultiBlock(plugin, (IMultiBlock) raw);
				
				pluginMultiBlockCores.put(pluginMultiBlock, pluginMultiBlock.getCoreMaterial());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("There already is a multiblock structure registered for this class: " + clazz.getSimpleName());
		}
	}
	
	private <T extends IMultiBlock> boolean hasPluginMultiBlock(Plugin plugin, Class<T> clazz) {
		boolean flag = false;
		
		for (PluginMultiBlock pluginMultiBlock : pluginMultiBlockCores.keySet()) {
			if (!flag) {
				flag = pluginMultiBlock.getPlugin().equals(plugin) && pluginMultiBlock.getMultiBlock().getClass().equals(clazz);
			}
		}
		
		return flag;
	}
	
	private void activate(Plugin plugin, Block block, Player player, MultiBlockActivationType activationType) {
		if (block != null && player != null) {
			activate(plugin, block.getType(), block.getLocation(), player, activationType);
		}
	}
	
	private void activate(Plugin plugin, Material material, Location location, Player player, MultiBlockActivationType activationType) {
		if (location != null && player != null) {
			List<IMultiBlock> multiBlocks = getRegisteredMultiBlocks(plugin, material);
			
			if (multiBlocks != null) {
				boolean flag = false;
				for (IMultiBlock multiBlock : multiBlocks) {
					if (!flag) {
						MultiBlockPattern pattern = multiBlock.getMultiBlockPattern();
						
						if (pattern != null && pattern.isMultiBlock(location)) {
							flag = true;
							
							multiBlock.onActivate(plugin, location, player, activationType);
						}
					}
				}
			}
		}
	}
	
	private List<IMultiBlock> getRegisteredMultiBlocks(Plugin plugin, Material materialCore) {
		List<IMultiBlock> multiBlocks = new ArrayList<>();
		
		for (Entry<PluginMultiBlock, Material> entry : pluginMultiBlockCores.entrySet()) {
			PluginMultiBlock pluginMultiBlock = entry.getKey();
			
			if (pluginMultiBlock.getPlugin().equals(plugin) && entry.getValue() == materialCore) {
				multiBlocks.add(pluginMultiBlock.getMultiBlock());
			}
		}
		
		return multiBlocks;
	}
	
	protected static class PluginMultiBlock implements Listener {
		
		private final Plugin plugin;
		
		private final IMultiBlock multiBlock;
		
		public PluginMultiBlock(Plugin plugin, IMultiBlock multiBlock) {
			this.plugin = plugin;
			this.multiBlock = multiBlock;

			plugin.getServer().getPluginManager().registerEvents(this, plugin);
		}
		
		public Plugin getPlugin() {
			return plugin;
		}
		
		public IMultiBlock getMultiBlock() {
			return multiBlock;
		}
		
		public Material getCoreMaterial() {
			MultiBlockPattern pattern = multiBlock.getMultiBlockPattern();
			
			return pattern != null ? pattern.getCoreMaterial() : null;
		}

		@EventHandler
		public void onBlockPlaceEvent(BlockPlaceEvent event) {
			INSTANCE.activate(plugin, event.getBlock(), event.getPlayer(), MultiBlockActivationType.CORE_PLACED);
		}
		
		@EventHandler
		public void onPlayerInteractEvent(PlayerInteractEvent event) {
			Block block = event.getClickedBlock();
			
			if (block != null && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				INSTANCE.activate(plugin, block, event.getPlayer(), MultiBlockActivationType.CORE_INTERACTED);
			}
		}
	}
}
