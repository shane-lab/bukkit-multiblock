package nl.shanelab.multiblock;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

/**
 * The multiblock factory, used to register custom multiblocks and detecting
 * their patterns through events with the corresponding plugin
 * 
 * @author ShaneCraft
 *
 */
public enum MultiBlockFactory {

	INSTANCE;
	
	private final Map<Plugin, PluginMultiBlockCollection> pluginMultiBlocks;
	
	private MultiBlockFactory() {
		this.pluginMultiBlocks = new HashMap<>();
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
		if (pluginMultiBlocks.isEmpty() || !hasPluginMultiBlock(plugin, clazz)) {
			Object raw = null;
			
			try {
				raw = clazz.getConstructor().newInstance();
				
				PluginMultiBlockCollection collection = pluginMultiBlocks.get(plugin);
				if (collection == null) {
					collection = new PluginMultiBlockCollection(plugin);
					
					pluginMultiBlocks.put(plugin, collection);
				}
				
				collection.register((IMultiBlock) raw);
				
				System.out.println("registered: " + clazz.getSimpleName());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("There already is a multiblock structure registered for this plugin in combination with this class: " + clazz.getSimpleName());
		}
	}
	
	private <T extends IMultiBlock> boolean hasPluginMultiBlock(Plugin plugin, Class<T> clazz) {
		boolean flag = false;
		
		PluginMultiBlockCollection collection = pluginMultiBlocks.get(plugin);
		if (collection != null) {
			for (IMultiBlock multiBlock : collection.getMultiBlocks()) {
				if (!flag) {
					flag = multiBlock.getClass().equals(clazz);
				}
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
			List<IMultiBlock> multiBlocks = getRegisteredPluginMultiBlocks(plugin, material);
			
			if (multiBlocks != null && !multiBlocks.isEmpty()) {
				boolean flag = false;
				for (IMultiBlock multiBlock : multiBlocks) {
					if (!flag) {
						MultiBlockPattern pattern = multiBlock.getMultiBlockPattern();
						
						// check pattern for requested core material
						if (pattern != null && pattern.isMultiBlock(location)) {
							flag = true;
							
							multiBlock.onActivate(plugin, location, player, new MultiBlockActivation(activationType, pattern.getLastPatternFacing()));
						}
					}
				}
			}
		}
	}
	
	private List<IMultiBlock> getRegisteredPluginMultiBlocks(Plugin plugin, Material materialCore) {
		List<IMultiBlock> multiBlocks = new ArrayList<>();
		
		PluginMultiBlockCollection collection = pluginMultiBlocks.get(plugin);
		if (collection != null) {
			for (IMultiBlock multiBlock : collection.getMultiBlocks()) {
				if (multiBlock.getMultiBlockPattern().getCoreMaterial() == materialCore) {
					multiBlocks.add(multiBlock);
				}
			}
		}
		
		return multiBlocks;
	}
	
	private static final class PluginMultiBlockCollection implements Listener {
		
		private final Plugin plugin;
		
		private List<IMultiBlock> multiBlocks;
		
		private Map<String, BigInteger> playerInteractCooldowns;
		
		public PluginMultiBlockCollection(Plugin plugin) {
			this.plugin = plugin;
			this.multiBlocks = new ArrayList<>();
			this.playerInteractCooldowns = new HashMap<>();
			
			plugin.getServer().getPluginManager().registerEvents(this, plugin);
		}
		
		public List<IMultiBlock> getMultiBlocks() {
			return Collections.unmodifiableList(multiBlocks);
		}
		
		public void register(IMultiBlock multiBlock) {
			if (!multiBlocks.contains(multiBlock)) {
				multiBlocks.add(multiBlock);
			}
		}
		
		@EventHandler
		public void onBlockPlaceEvent(BlockPlaceEvent event) {
			INSTANCE.activate(plugin, event.getBlock(), event.getPlayer(), MultiBlockActivationType.CORE_PLACED);
		}
		
		@EventHandler
		public void onPlayerInteractEvent(PlayerInteractEvent event) {
			Player player = event.getPlayer();
			
			long currentSysTime = System.currentTimeMillis();
			long lastSysTime = getLastSysTime(player);
			
			// roughly 1 second delay
			if (lastSysTime == 0 || currentSysTime > (lastSysTime + 1000)) {
				Block block = event.getClickedBlock();
				
				if (block != null && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					INSTANCE.activate(plugin, block, player, MultiBlockActivationType.CORE_INTERACTED);
				}
				
				setLastSysTime(player, currentSysTime);
			}
		}
		
		@EventHandler
		public void onPlayerQuitEvent(PlayerQuitEvent event) {
			String uuid = event.getPlayer().getUniqueId().toString();
			
			if (playerInteractCooldowns.containsKey(uuid)) {
				playerInteractCooldowns.remove(uuid);
			}
		}
		
		private long getLastSysTime(Player player) {
			long sysTime = 0;
			
			if (player != null) {
				String uuid = player.getUniqueId().toString();
				if (playerInteractCooldowns.containsKey(uuid)) {
					sysTime = playerInteractCooldowns.get(uuid).longValue();
				}
			}
			
			return sysTime;
		}
		
		private void setLastSysTime(Player player, long sysTime) {
			if (player != null) {
				String uuid = player.getUniqueId().toString();
				if (playerInteractCooldowns.containsKey(uuid)) {
					playerInteractCooldowns.remove(uuid);
				}
				
				playerInteractCooldowns.put(uuid, BigInteger.valueOf(sysTime));
			}
		}
	}
}
