package nl.shanelab.multiblock.validators;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.material.WoodenStep;

public class WoodSlabValidator extends TreeSpeciesValidator {

	private final boolean up;
	
	private boolean sided;
	
	public WoodSlabValidator(TreeSpecies species) {
		this(species, false);
		
		this.sided = false;
	}
	
	public WoodSlabValidator(TreeSpecies species, boolean up) {
		super(species, Material.WOOD_STEP);
		
		this.up = up;
		
		this.sided = true;
	}

	@Override
	public boolean validateBlock(Block block) {
		boolean flag = super.validateBlock(block);
		
		System.out.println("sided: " + sided);

		return flag && (!sided || (sided && ((WoodenStep)block.getState().getData()).isInverted() == up));
	}
}