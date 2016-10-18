package nl.shanelab.multiblock.validators;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.Cauldron;

import nl.shanelab.multiblock.IMaterialValidator;

public class CauldronValidator implements IMaterialValidator {
	
	private final int stage;
	
	public CauldronValidator(int stage) {
		this.stage = stage;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean validateBlock(Block block) {
		if (block == null || block.getType() != Material.CAULDRON){
			return false;
		}
		
		Cauldron cauldron = (Cauldron)block.getState().getData();
		
		boolean flag = false;
		switch(stage) {
			case 0: flag = cauldron.isEmpty(); break;
			case 1: flag = isStage(cauldron, block.getData()); break;
			case 2: flag = isStage(cauldron, block.getData()); break;
			case 3: flag = cauldron.isFull(); break;
		}
		
		return flag;
	}
	
	private boolean isStage(Cauldron cauldron, byte data) {
		return !cauldron.isEmpty() && !cauldron.isFull() && data == stage;
	}

}
