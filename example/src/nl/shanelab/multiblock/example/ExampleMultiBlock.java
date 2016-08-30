package nl.shanelab.multiblock.example;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import nl.shanelab.multiblock.IMultiBlock;
import nl.shanelab.multiblock.MultiBlockActivationType;
import nl.shanelab.multiblock.MultiBlockPattern;
import nl.shanelab.multiblock.PatternBlock;
import nl.shanelab.multiblock.PatternEntity;

public class ExampleMultiBlock implements IMultiBlock {
	
	@Override
	public void onActivate(Plugin plugin, Location location, Player player, MultiBlockActivationType activationType) {
		if (activationType == MultiBlockActivationType.CORE_PLACED) {
			// when all pattern blocks are present and the last block placed
			// is the core material block of the multiblock pattern, this block will run
			
			player.sendMessage(ChatColor.GREEN + "Example multiblock constructed");	
		} else if (activationType == MultiBlockActivationType.CORE_INTERACTED) {
			// when all pattern blocks, including the core material, are present
			// and the user interacts with the core material block 
			
			player.sendMessage(ChatColor.RED + "Interacted with example multiblock");
			
			// Optionally you could check for a certain item to be used
			if (player.getInventory().getItemInMainHand().getType() == Material.LEVER) {
				// e.g. load custom gui
			}
		}
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern() {
		return new MultiBlockPattern(Material.WOOL, // optional parameter [patternFacing: {default: CARDINAL} ]
			// the cardinal parameter allows to check the pattern in any direction 
			// or strict the pattern to face a certain one
			
			// pattern objects are relative to the core block (WOOL Material)
			// NORTH: 	negative on Z-axis
			// EAST: 	positive on X-axis
			// SOUTH: 	positive on Z-axis
			// WEST: 	negative on X-axis
			new PatternBlock(Material.STONE, -2, 0, -2),
			new PatternBlock(Material.BRICK, 2, 0, -2),
			new PatternBlock(Material.WOOD, 2, 0, 2),
			new PatternBlock(Material.COAL_BLOCK, -2, 0, 2),
			// patterns even support entities
			new PatternEntity(EntityType.CHICKEN, -2, 1, -2) 
		);
	}
}
