package nl.shanelab.multiblock.example.multiblocks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Cauldron;
import org.bukkit.plugin.Plugin;

import nl.shanelab.multiblock.IMultiBlock;
import nl.shanelab.multiblock.MultiBlockActivation;
import nl.shanelab.multiblock.MultiBlockActivationType;
import nl.shanelab.multiblock.MultiBlockPattern;
import nl.shanelab.multiblock.PatternBlock;
import nl.shanelab.multiblock.PatternEntity;
import nl.shanelab.multiblock.PatternItem;

public class ChickenSoupMultiBlock implements IMultiBlock {
	
	@Override
	public void onActivate(Plugin plugin, Location location, Player player, MultiBlockActivation activation) {
		if (activation.getType() == MultiBlockActivationType.CORE_INTERACTED) {
			if (player.getInventory().getItemInMainHand().getType() == Material.STICK) {
				if (location.getBlock().getType() == Material.CAULDRON) {
					Cauldron cauldron = (Cauldron) location.getBlock().getState().getData();
					
					if (cauldron.isFull()) {
						Entity chicken = getEntity(EntityType.CHICKEN, location);
						Entity carrot = getEntity(EntityType.DROPPED_ITEM, location);
						
						if (chicken != null && chicken instanceof Chicken) {
							chicken.setFireTicks(80);
							
							if (carrot != null && carrot instanceof Item && ((Item) carrot).getItemStack().getType() == Material.CARROT_ITEM) {
								carrot.remove();
								
								// clear cauldron water
								location.getBlock().setType(Material.CAULDRON);
								
								player.getInventory().addItem(buildChickenSoup());
								player.sendMessage(ChatColor.GREEN + "delicous chicken soup!");
								
								animate(plugin, location, player, (Chicken) chicken);
							}
						}
					} else {
						player.sendMessage(ChatColor.RED + "not enough boiling water!");
					}
				}
			}
		}
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern() {
		return new MultiBlockPattern(Material.CAULDRON,
			new PatternBlock(Material.FIRE, 0, -1, 0),
			// r1.1 adds support for entities
			new PatternEntity(EntityType.CHICKEN, 0, 0, 0),
			// r1.2 adds support for item (dropped item entities)
			new PatternItem(Material.CARROT_ITEM, 0, 0, 0) 
		);
	}
	
	private Entity getEntity(EntityType entityType, Location location) {
		List<Entity> entities = (List<Entity>) location.getWorld().getNearbyEntities(location, 1, 1, 1);
		
		Entity entity = null;
		for (Entity e : entities) {
			if (entity == null) {
				if (e.getType() == entityType) {
					entity = e;
				}
			}
		}
		
		return entity;
	}
	
	private ItemStack buildChickenSoup() {
		ItemStack stack = new ItemStack(Material.BEETROOT_SOUP);
		
		ItemMeta meta = stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.BLUE + "This absolutely, really is chicken soup");
		meta.setDisplayName(ChatColor.GOLD + "Chicken Soup");
		meta.setLore(lore);
		stack.setItemMeta(meta);
		
		return stack;
	}
	
	private void animate(Plugin plugin, Location location, Player player, Chicken chicken) {
		spawnParticle(Particle.FLAME, location, 150);
		
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				player.playSound(location, Sound.ENTITY_CHICKEN_DEATH, 1.0F, 1.0F);
				
				spawnParticle(Particle.SMOKE_LARGE, location, 50);
				
				chicken.remove();
			}
		}, 20 * 1);
	}
	
	private void spawnParticle(Particle particle, Location location, int size) {
		location.getWorld().spawnParticle(
				particle, 
				location.getX() + .5, 
				location.getY() + .5, 
				location.getZ() + .5, 
				size, 
				0, 
				1.0D, 
				0, 
				0.1D);
	}
}
