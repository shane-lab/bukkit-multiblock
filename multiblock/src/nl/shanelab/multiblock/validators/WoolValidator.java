package nl.shanelab.multiblock.validators;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.Wool;

import nl.shanelab.multiblock.IMaterialValidator;

public class WoolValidator implements IMaterialValidator {
	
	private final DyeColor dyeColor;
	
	public WoolValidator(DyeColor dyeColor) {
		this.dyeColor = dyeColor;
	}

	@Override
	public boolean validateBlock(Block block) {
		if (block == null || block.getType() != Material.WOOL){
			return false;
		}
		
		return ((Wool)block.getState().getData()).getColor() == dyeColor;
	}
}
