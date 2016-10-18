package nl.shanelab.multiblock.validators;

import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.bukkit.block.Block;

import nl.shanelab.multiblock.IMaterialValidator;

public class GrassSpeciesValidator implements IMaterialValidator {
	
	private final GrassSpecies species;
	
	private final Material material;
	
	public GrassSpeciesValidator(GrassSpecies species) {
		this(species, Material.LONG_GRASS);
	}
	
	public GrassSpeciesValidator(GrassSpecies species, Material material) {
		this.species = species;
		this.material = material;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean validateBlock(Block block) {
		if (block == null || block.getType() != material) {
			return false;
		}
		
		byte data = block.getData();
		if (material == Material.DOUBLE_PLANT) {
			// block data LONG_GRASS
			// 0 = dead
			// 1 = normal
			// 2 = fern-like
			
			// block data DOUBLE_PLANT
			// 2 = normal taller
			// 3 = fern-like taller
			data = (byte) (data - 1); // subtract 1, and it matches the same type (dirty but w/e)
		}
		
		return GrassSpecies.getByData(data) == species;
	}
}
