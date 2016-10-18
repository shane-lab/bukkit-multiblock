package nl.shanelab.multiblock.validators;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.Step;

import nl.shanelab.multiblock.IMaterialValidator;

public class SlabValidator implements IMaterialValidator {
	
	private final Material material;
	
	private final Material stepType;
	
	private final boolean up;
	
	private boolean sided;
	
	public SlabValidator(Material material) {
		this(material, false);
		
		this.sided = false;
	}
	
	public SlabValidator(Material material, Material alternative) {
		this(material, false, alternative);
		
		this.sided = false;
	}
	
	public SlabValidator(Material material, boolean up) {
		this(material, up, Material.STEP);
	}
	
	public SlabValidator(Material material, boolean up, Material alternative) {
		this.material = material;
		this.stepType = alternative;
		this.up = up;
		
		this.sided = true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean validateBlock(Block block) {
		if (block == null || block.getType() != stepType){
			return false;
		}
		
		if (stepType == Material.STEP) {
			Step step = (Step) block.getState().getData();
			
			return step.getMaterial() == material && (!sided || (sided && step.isInverted() == up));
		} else {
			// 0 = down
			// 8 = top
			byte data = block.getData();
			return block.getState().getType() == material && (!sided || (sided && ((up && data == 8) || (!up && data == 0))));
		}
	}
}