package nl.shanelab.multiblock;

import org.bukkit.DyeColor;
import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;

import nl.shanelab.multiblock.validators.CauldronValidator;
import nl.shanelab.multiblock.validators.DyeColorValidator;
import nl.shanelab.multiblock.validators.GrassSpeciesValidator;
import nl.shanelab.multiblock.validators.SlabValidator;
import nl.shanelab.multiblock.validators.TreeSpeciesValidator;
import nl.shanelab.multiblock.validators.WoodSlabValidator;

public enum SpecificMaterial implements IMaterial {
	
	/*WOOL_WHITE(Material.WOOL, new IMaterialValidator() {
		   public boolean validate(Block block) {
		        return false;
		   }
		}),*/
	WOOL_WHITE(Material.WOOL, new DyeColorValidator(DyeColor.WHITE)),
	WOOL_ORANGE(Material.WOOL, new DyeColorValidator(DyeColor.ORANGE)),
	WOOL_MAGENTA(Material.WOOL, new DyeColorValidator(DyeColor.MAGENTA)),
	WOOL_LIGHT_BLUE(Material.WOOL, new DyeColorValidator(DyeColor.LIGHT_BLUE)),
	WOOL_YELLOW(Material.WOOL, new DyeColorValidator(DyeColor.YELLOW)),
	WOOL_LIME_GREEN(Material.WOOL, new DyeColorValidator(DyeColor.LIME)),
	WOOL_PINK(Material.WOOL, new DyeColorValidator(DyeColor.PINK)),
	WOOL_GRAY(Material.WOOL, new DyeColorValidator(DyeColor.GRAY)),
	WOOL_LIGHT_GRAY(Material.WOOL, new DyeColorValidator(DyeColor.SILVER)),
	WOOL_CYAN(Material.WOOL, new DyeColorValidator(DyeColor.CYAN)),
	WOOL_PURPLE(Material.WOOL, new DyeColorValidator(DyeColor.PURPLE)),
	WOOL_BLUE(Material.WOOL, new DyeColorValidator(DyeColor.BLUE)),
	WOOL_BROWN(Material.WOOL, new DyeColorValidator(DyeColor.BROWN)),
	WOOL_GREEN(Material.WOOL, new DyeColorValidator(DyeColor.GREEN)),
	WOOL_RED(Material.WOOL, new DyeColorValidator(DyeColor.RED)),
	WOOL_BLACK(Material.WOOL, new DyeColorValidator(DyeColor.BLACK)),

	CARPET_WHITE(Material.CARPET, new DyeColorValidator(DyeColor.WHITE, Material.CARPET)),
	CARPET_ORANGE(Material.CARPET, new DyeColorValidator(DyeColor.ORANGE, Material.CARPET)),
	CARPET_MAGENTA(Material.CARPET, new DyeColorValidator(DyeColor.MAGENTA, Material.CARPET)),
	CARPET_LIGHT_BLUE(Material.CARPET, new DyeColorValidator(DyeColor.LIGHT_BLUE, Material.CARPET)),
	CARPET_YELLOW(Material.CARPET, new DyeColorValidator(DyeColor.YELLOW, Material.CARPET)),
	CARPET_LIME_GREEN(Material.CARPET, new DyeColorValidator(DyeColor.LIME, Material.CARPET)),
	CARPET_PINK(Material.CARPET, new DyeColorValidator(DyeColor.PINK, Material.CARPET)),
	CARPET_GRAY(Material.CARPET, new DyeColorValidator(DyeColor.GRAY, Material.CARPET)),
	CARPET_LIGHT_GRAY(Material.CARPET, new DyeColorValidator(DyeColor.SILVER, Material.CARPET)),
	CARPET_CYAN(Material.CARPET, new DyeColorValidator(DyeColor.CYAN, Material.CARPET)),
	CARPET_PURPLE(Material.CARPET, new DyeColorValidator(DyeColor.PURPLE, Material.CARPET)),
	CARPET_BLUE(Material.CARPET, new DyeColorValidator(DyeColor.BLUE, Material.CARPET)),
	CARPET_BROWN(Material.CARPET, new DyeColorValidator(DyeColor.BROWN, Material.CARPET)),
	CARPET_GREEN(Material.CARPET, new DyeColorValidator(DyeColor.GREEN, Material.CARPET)),
	CARPET_RED(Material.CARPET, new DyeColorValidator(DyeColor.RED, Material.CARPET)),
	CARPET_BLACK(Material.CARPET, new DyeColorValidator(DyeColor.BLACK, Material.CARPET)),

	HARDENED_CLAY_WHITE(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.WHITE, Material.STAINED_CLAY)),
	HARDENED_CLAY_ORANGE(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.ORANGE, Material.STAINED_CLAY)),
	HARDENED_CLAY_MAGENTA(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.MAGENTA, Material.STAINED_CLAY)),
	HARDENED_CLAY_LIGHT_BLUE(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.LIGHT_BLUE, Material.STAINED_CLAY)),
	HARDENED_CLAY_YELLOW(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.YELLOW, Material.STAINED_CLAY)),
	HARDENED_CLAY_LIME_GREEN(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.LIME, Material.STAINED_CLAY)),
	HARDENED_CLAY_PINK(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.PINK, Material.STAINED_CLAY)),
	HARDENED_CLAY_GRAY(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.GRAY, Material.STAINED_CLAY)),
	HARDENED_CLAY_LIGHT_GRAY(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.SILVER, Material.STAINED_CLAY)),
	HARDENED_CLAY_CYAN(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.CYAN, Material.STAINED_CLAY)),
	HARDENED_CLAY_PURPLE(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.PURPLE, Material.STAINED_CLAY)),
	HARDENED_CLAY_BLUE(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.BLUE, Material.STAINED_CLAY)),
	HARDENED_CLAY_BROWN(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.BROWN, Material.STAINED_CLAY)),
	HARDENED_CLAY_GREEN(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.GREEN, Material.STAINED_CLAY)),
	HARDENED_CLAY_RED(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.RED, Material.STAINED_CLAY)),
	HARDENED_CLAY_BLACK(Material.STAINED_CLAY, new DyeColorValidator(DyeColor.BLACK, Material.STAINED_CLAY)),

	STAINED_GLASS_WHITE(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.WHITE, Material.STAINED_GLASS)),
	STAINED_GLASS_ORANGE(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.ORANGE, Material.STAINED_GLASS)),
	STAINED_GLASS_MAGENTA(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.MAGENTA, Material.STAINED_GLASS)),
	STAINED_GLASS_LIGHT_BLUE(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.LIGHT_BLUE, Material.STAINED_GLASS)),
	STAINED_GLASS_YELLOW(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.YELLOW, Material.STAINED_GLASS)),
	STAINED_GLASS_LIME_GREEN(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.LIME, Material.STAINED_GLASS)),
	STAINED_GLASS_PINK(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.PINK, Material.STAINED_GLASS)),
	STAINED_GLASS_GRAY(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.GRAY, Material.STAINED_GLASS)),
	STAINED_GLASS_LIGHT_GRAY(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.SILVER, Material.STAINED_GLASS)),
	STAINED_GLASS_CYAN(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.CYAN, Material.STAINED_GLASS)),
	STAINED_GLASS_PURPLE(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.PURPLE, Material.STAINED_GLASS)),
	STAINED_GLASS_BLUE(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.BLUE, Material.STAINED_GLASS)),
	STAINED_GLASS_BROWN(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.BROWN, Material.STAINED_GLASS)),
	STAINED_GLASS_GREEN(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.GREEN, Material.STAINED_GLASS)),
	STAINED_GLASS_RED(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.RED, Material.STAINED_GLASS)),
	STAINED_GLASS_BLACK(Material.STAINED_GLASS, new DyeColorValidator(DyeColor.BLACK, Material.STAINED_GLASS)),

	STAINED_GLASS_PANE_WHITE(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.WHITE, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_ORANGE(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.ORANGE, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_MAGENTA(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.MAGENTA, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_LIGHT_BLUE(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.LIGHT_BLUE, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_YELLOW(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.YELLOW, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_LIME_GREEN(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.LIME, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_PINK(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.PINK, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_GRAY(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.GRAY, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_LIGHT_GRAY(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.SILVER, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_CYAN(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.CYAN, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_PURPLE(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.PURPLE, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_BLUE(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.BLUE, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_BROWN(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.BROWN, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_GREEN(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.GREEN, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_RED(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.RED, Material.STAINED_GLASS_PANE)),
	STAINED_GLASS_PANE_BLACK(Material.STAINED_GLASS_PANE, new DyeColorValidator(DyeColor.BLACK, Material.STAINED_GLASS_PANE)),
	
	CAULDRON_EMPTY(Material.CAULDRON, new CauldronValidator(0)),
	CAULDRON_USED_TWICE(Material.CAULDRON, new CauldronValidator(1)),
	CAULDRON_USED_ONCE(Material.CAULDRON, new CauldronValidator(2)),
	CAULDRON_FULL(Material.CAULDRON, new CauldronValidator(3)),
	
	TALL_GRASS(Material.LONG_GRASS, new GrassSpeciesValidator(GrassSpecies.NORMAL)),
	TALL_GRASS_FERN(Material.LONG_GRASS, new GrassSpeciesValidator(GrassSpecies.FERN_LIKE)),

	TALL_GRASS_STACKED(Material.DOUBLE_PLANT, new GrassSpeciesValidator(GrassSpecies.NORMAL, Material.DOUBLE_PLANT)),
	TALL_GRASS_FERN_STACKED(Material.DOUBLE_PLANT, new GrassSpeciesValidator(GrassSpecies.FERN_LIKE, Material.DOUBLE_PLANT)),
	
	WOOD_OAK(Material.WOOD, new TreeSpeciesValidator(TreeSpecies.GENERIC)),
	WOOD_BIRCH(Material.WOOD, new TreeSpeciesValidator(TreeSpecies.BIRCH)),
	WOOD_SPRUCE(Material.WOOD, new TreeSpeciesValidator(TreeSpecies.REDWOOD)),
	WOOD_JUNGLE(Material.WOOD, new TreeSpeciesValidator(TreeSpecies.JUNGLE)),
	WOOD_DARK_OAK(Material.WOOD, new TreeSpeciesValidator(TreeSpecies.DARK_OAK)),
	WOOD_ACACIA(Material.WOOD, new TreeSpeciesValidator(TreeSpecies.ACACIA)),
	
	LOG_OAK(Material.LOG_2, new TreeSpeciesValidator(TreeSpecies.GENERIC, Material.LOG_2)),
	LOG_BIRCH(Material.LOG_2, new TreeSpeciesValidator(TreeSpecies.BIRCH, Material.LOG_2)),
	LOG_SPRUCE(Material.LOG_2, new TreeSpeciesValidator(TreeSpecies.REDWOOD, Material.LOG_2)),
	LOG_JUNGLE(Material.LOG_2, new TreeSpeciesValidator(TreeSpecies.JUNGLE, Material.LOG_2)),
	LOG_DARK_OAK(Material.LOG_2, new TreeSpeciesValidator(TreeSpecies.DARK_OAK, Material.LOG_2)),
	LOG_ACACIA(Material.LOG_2, new TreeSpeciesValidator(TreeSpecies.ACACIA, Material.LOG_2)),
	
	LOG_OAK_GENERATED(Material.LOG, new TreeSpeciesValidator(TreeSpecies.GENERIC, Material.LOG)),
	LOG_BIRCH_GENERATED(Material.LOG, new TreeSpeciesValidator(TreeSpecies.BIRCH, Material.LOG)),
	LOG_SPRUCE_GENERATED(Material.LOG, new TreeSpeciesValidator(TreeSpecies.REDWOOD, Material.LOG)),
	LOG_JUNGLE_GENERATED(Material.LOG, new TreeSpeciesValidator(TreeSpecies.JUNGLE, Material.LOG)),
	LOG_DARK_OAK_GENERATED(Material.LOG, new TreeSpeciesValidator(TreeSpecies.DARK_OAK, Material.LOG)),
	LOG_ACACIA_GENERATED(Material.LOG, new TreeSpeciesValidator(TreeSpecies.ACACIA, Material.LOG)),
	
	LEAVES_OAK(Material.LEAVES_2, new TreeSpeciesValidator(TreeSpecies.GENERIC, Material.LEAVES_2)),
	LEAVES_BIRCH(Material.LEAVES_2, new TreeSpeciesValidator(TreeSpecies.BIRCH, Material.LEAVES_2)),
	LEAVES_SPRUCE(Material.LEAVES_2, new TreeSpeciesValidator(TreeSpecies.REDWOOD, Material.LEAVES_2)),
	LEAVES_JUNGLE(Material.LEAVES_2, new TreeSpeciesValidator(TreeSpecies.JUNGLE, Material.LEAVES_2)),
	LEAVES_DARK_OAK(Material.LEAVES_2, new TreeSpeciesValidator(TreeSpecies.DARK_OAK, Material.LEAVES_2)),
	LEAVES_ACACIA(Material.LEAVES_2, new TreeSpeciesValidator(TreeSpecies.ACACIA, Material.LEAVES_2)),
	
	LEAVES_OAK_GENERATED(Material.LEAVES, new TreeSpeciesValidator(TreeSpecies.GENERIC, Material.LEAVES)),
	LEAVES_BIRCH_GENERATED(Material.LEAVES, new TreeSpeciesValidator(TreeSpecies.BIRCH, Material.LEAVES)),
	LEAVES_SPRUCE_GENERATED(Material.LEAVES, new TreeSpeciesValidator(TreeSpecies.REDWOOD, Material.LEAVES)),
	LEAVES_JUNGLE_GENERATED(Material.LEAVES, new TreeSpeciesValidator(TreeSpecies.JUNGLE, Material.LEAVES)),
	LEAVES_DARK_OAK_GENERATED(Material.LEAVES, new TreeSpeciesValidator(TreeSpecies.DARK_OAK, Material.LEAVES)),
	LEAVES_ACACIA_GENERATED(Material.LEAVES, new TreeSpeciesValidator(TreeSpecies.ACACIA, Material.LEAVES)),
	
	SAPLING_OAK_GENERATED(Material.SAPLING, new TreeSpeciesValidator(TreeSpecies.GENERIC, Material.SAPLING)),
	SAPLING_BIRCH_GENERATED(Material.SAPLING, new TreeSpeciesValidator(TreeSpecies.BIRCH, Material.SAPLING)),
	SAPLING_SPRUCE_GENERATED(Material.SAPLING, new TreeSpeciesValidator(TreeSpecies.REDWOOD, Material.SAPLING)),
	SAPLING_JUNGLE_GENERATED(Material.SAPLING, new TreeSpeciesValidator(TreeSpecies.JUNGLE, Material.SAPLING)),
	SAPLING_DARK_OAK_GENERATED(Material.SAPLING, new TreeSpeciesValidator(TreeSpecies.DARK_OAK, Material.SAPLING)),
	SAPLING_ACACIA_GENERATED(Material.SAPLING, new TreeSpeciesValidator(TreeSpecies.ACACIA, Material.SAPLING)),

	WOOD_SLAB_OAK(Material.WOOD, new WoodSlabValidator(TreeSpecies.GENERIC)),
	WOOD_SLAB_OAK_DOWN(Material.WOOD, new WoodSlabValidator(TreeSpecies.GENERIC, false)),
	WOOD_SLAB_OAK_UP(Material.WOOD, new WoodSlabValidator(TreeSpecies.GENERIC, true)),

	WOOD_SLAB_BIRCH(Material.WOOD, new WoodSlabValidator(TreeSpecies.BIRCH)),
	WOOD_SLAB_BIRCH_DOWN(Material.WOOD, new WoodSlabValidator(TreeSpecies.BIRCH, false)),
	WOOD_SLAB_BIRCH_UP(Material.WOOD, new WoodSlabValidator(TreeSpecies.BIRCH, true)),

	WOOD_SLAB_SPRUCE(Material.WOOD, new WoodSlabValidator(TreeSpecies.REDWOOD)),
	WOOD_SLAB_SPRUCE_DOWN(Material.WOOD, new WoodSlabValidator(TreeSpecies.REDWOOD, false)),
	WOOD_SLAB_SPRUCE_UP(Material.WOOD, new WoodSlabValidator(TreeSpecies.REDWOOD, true)),

	WOOD_SLAB_JUNGLE(Material.WOOD, new WoodSlabValidator(TreeSpecies.JUNGLE)),
	WOOD_SLAB_JUNGLE_DOWN(Material.WOOD, new WoodSlabValidator(TreeSpecies.JUNGLE, false)),
	WOOD_SLAB_JUNGLE_UP(Material.WOOD, new WoodSlabValidator(TreeSpecies.JUNGLE, true)),

	WOOD_SLAB_DARK_OAK(Material.WOOD, new WoodSlabValidator(TreeSpecies.DARK_OAK)),
	WOOD_SLAB_DARK_OAK_DOWN(Material.WOOD, new WoodSlabValidator(TreeSpecies.DARK_OAK, false)),
	WOOD_SLAB_DARK_OAK_UP(Material.WOOD, new WoodSlabValidator(TreeSpecies.DARK_OAK, true)),

	WOOD_SLAB_ACACIA(Material.WOOD, new WoodSlabValidator(TreeSpecies.ACACIA)),
	WOOD_SLAB_ACACIA_DOWN(Material.WOOD, new WoodSlabValidator(TreeSpecies.ACACIA, false)),
	WOOD_SLAB_ACACIA_UP(Material.WOOD, new WoodSlabValidator(TreeSpecies.ACACIA, true)),
	
	SLAB_STONE(Material.STEP, new SlabValidator(Material.STONE)),
	SLAB_STONE_DOWN(Material.STEP, new SlabValidator(Material.STONE, false)),
	SLAB_STONE_UP(Material.STEP, new SlabValidator(Material.STONE, true)),
	
	SLAB_SANDSTONE(Material.STEP, new SlabValidator(Material.SANDSTONE)),
	SLAB_SANDSTONE_DOWN(Material.STEP, new SlabValidator(Material.SANDSTONE, false)),
	SLAB_SANDSTONE_UP(Material.STEP, new SlabValidator(Material.SANDSTONE, true)),
	
	SLAB_COBBLESTONE(Material.STEP, new SlabValidator(Material.COBBLESTONE)),
	SLAB_COBBLESTONE_DOWN(Material.STEP, new SlabValidator(Material.COBBLESTONE, false)),
	SLAB_COBBLESTONE_UP(Material.STEP, new SlabValidator(Material.COBBLESTONE, true)),
	
	SLAB_BRICK(Material.STEP, new SlabValidator(Material.BRICK)),
	SLAB_BRICK_DOWN(Material.STEP, new SlabValidator(Material.BRICK, false)),
	SLAB_BRICK_UP(Material.STEP, new SlabValidator(Material.BRICK, true)),
	
	SLAB_SMOOTH_BRICK(Material.STEP, new SlabValidator(Material.SMOOTH_BRICK)),
	SLAB_SMOOTH_BRICK_DOWN(Material.STEP, new SlabValidator(Material.SMOOTH_BRICK, false)),
	SLAB_SMOOTH_BRICK_UP(Material.STEP, new SlabValidator(Material.SMOOTH_BRICK, true)),
	
	SLAB_QUARTZ(Material.STEP, new SlabValidator(Material.QUARTZ)),
	SLAB_QUARTZ_DOWN(Material.STEP, new SlabValidator(Material.QUARTZ, false)),
	SLAB_QUARTZ_UP(Material.STEP, new SlabValidator(Material.QUARTZ, true)),
	
	SLAB_PURPUR(Material.PURPUR_SLAB, new SlabValidator(Material.PURPUR_SLAB, Material.PURPUR_SLAB)),
	SLAB_PURPUR_DOWN(Material.PURPUR_SLAB, new SlabValidator(Material.PURPUR_SLAB, false, Material.PURPUR_SLAB)),
	SLAB_PURPUR_UP(Material.PURPUR_SLAB, new SlabValidator(Material.PURPUR_SLAB, true, Material.PURPUR_SLAB));
	
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
