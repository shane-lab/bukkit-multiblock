package nl.shanelab.multiblock.example.multiblocks;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import nl.shanelab.multiblock.IMultiBlock;
import nl.shanelab.multiblock.MultiBlockActivation;
import nl.shanelab.multiblock.MultiBlockActivationType;
import nl.shanelab.multiblock.MultiBlockPattern;
import nl.shanelab.multiblock.SpecificMaterial;
import nl.shanelab.multiblock.patternobjects.PatternBlock;

public class ExampleMultiBlock implements IMultiBlock {
	
	@Override
	public void onActivate(Plugin plugin, Location location, Player player, MultiBlockActivation activation) {
		if (activation.getType() == MultiBlockActivationType.CORE_PLACED) {
			// when all pattern blocks are present and the last block placed
			// is the core material block of the multiblock pattern
			
			player.sendMessage(ChatColor.GREEN + "Example multiblock constructed");	
		} else if (activation.getType() == MultiBlockActivationType.CORE_INTERACTED) {
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
		return new MultiBlockPattern(SpecificMaterial.WOOL_ORANGE, // MultiBlockPatternFacing.EAST, optional parameter [patternFacing: {default: CARDINAL} ]
			// the patternFacing parameter allows the mutliblock to activate in any direction 
			// or strict the pattern of the multiblock to face a certain one
			
			// pattern objects are relative to the core block (WOOL Material)
			// NORTH: 	negative on Z-axis
			// EAST: 	positive on X-axis
			// SOUTH: 	positive on Z-axis
			// WEST: 	negative on X-axis
			new PatternBlock(Material.STONE, -2, 0, -2),
			new PatternBlock(Material.BRICK, 2, 0, -2),
			new PatternBlock(Material.WOOD, 2, 0, 2),
			new PatternBlock(Material.COAL_BLOCK, -2, 0, 2),
			new PatternBlock(SpecificMaterial.WOOL_WHITE, 0, 1, 0)
		);
	}
}
