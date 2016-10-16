package nl.shanelab.multiblock;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;

import nl.shanelab.multiblock.validators.WoolValidator;

public enum SpecificMaterial implements IMaterial {
	
	/*WOOL_WHITE(Material.WOOL, new IMaterialValidator() {
		   public boolean validate(Block block) {
		        return false;
		   }
		}),*/
	WOOL_WHITE(Material.WOOL, new WoolValidator(DyeColor.WHITE)),
	WOOL_ORANGE(Material.WOOL, new WoolValidator(DyeColor.ORANGE)),
	WOOL_MAGENTA(Material.WOOL, new WoolValidator(DyeColor.MAGENTA)),
	WOOL_LIGHT_BLUE(Material.WOOL, new WoolValidator(DyeColor.LIGHT_BLUE)),
	WOOL_YELLOW(Material.WOOL, new WoolValidator(DyeColor.YELLOW)),
	WOOL_LIME_GREEN(Material.WOOL, new WoolValidator(DyeColor.LIME)),
	WOOL_PINK(Material.WOOL, new WoolValidator(DyeColor.PINK)),
	WOOL_GRAY(Material.WOOL, new WoolValidator(DyeColor.GRAY)),
	WOOL_LIGHT_GRAY(Material.WOOL, new WoolValidator(DyeColor.SILVER)),
	WOOL_CYAN(Material.WOOL, new WoolValidator(DyeColor.CYAN)),
	WOOL_PURPLE(Material.WOOL, new WoolValidator(DyeColor.PURPLE)),
	WOOL_BLUE(Material.WOOL, new WoolValidator(DyeColor.BLUE)),
	WOOL_BROWN(Material.WOOL, new WoolValidator(DyeColor.BROWN)),
	WOOL_GREEN(Material.WOOL, new WoolValidator(DyeColor.GREEN)),
	WOOL_RED(Material.WOOL, new WoolValidator(DyeColor.RED)),
	WOOL_BLACK(Material.WOOL, new WoolValidator(DyeColor.BLACK));
	
	private final Material material;
	
	private final IMaterialValidator validator;
	
	private SpecificMaterial(Material material, IMaterialValidator validator) {
		this.material = material;
		this.validator = validator;
	}
	
	@Override
	public Material getType() {
		return material;
	}
	
	@Override
	public boolean isValidBlock(Block block) {
		return validator.validateBlock(block);
	}
}
