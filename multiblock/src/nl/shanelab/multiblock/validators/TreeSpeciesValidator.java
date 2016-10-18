package nl.shanelab.multiblock.validators;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.material.Wood;

import nl.shanelab.multiblock.IMaterialValidator;

public class TreeSpeciesValidator implements IMaterialValidator {
	
	private final TreeSpecies species;
	
	private final Material material;
	
	public TreeSpeciesValidator(TreeSpecies species) {
		this(species, Material.WOOD);
	}
	
	public TreeSpeciesValidator(TreeSpecies species, Material material) {
		this.species = species;
		this.material = material;
	}

	@Override
	public boolean validateBlock(Block block) {
		if (block == null || block.getType() != material) {
			return false;
		}
		
		return ((Wood)block.getState().getData()).getSpecies() == species;
	}
}
