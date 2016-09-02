package nl.shanelab.multiblock;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

/**
 * Block pattern for the multiblock structure
 * 
 * @author ShaneCraft
 *
 */
public class PatternBlock extends PatternObject {

	private final Material blockMaterial;
	
	public PatternBlock(@Nonnull Material blockMaterial, int x, int y, int z) {
		this(blockMaterial, new Vector(x, y, z));
	}
	
	public PatternBlock(@Nonnull Material blockMaterial, Vector relativeVec) {
		super(relativeVec);
		
		if (!blockMaterial.isBlock()) {
			throw new IllegalArgumentException(String.format("The given blockMaterial %s is not a valid block material.", blockMaterial.toString()));
		}
		
		this.blockMaterial = blockMaterial;
	}

	@Override
	protected PatternObject createRotatedClone(Vector vector) {
		return new PatternBlock(blockMaterial, vector);
	}

	@Override
	public boolean isValid(Location location) {
		return location.getBlock() != null && location.getBlock().getType() == blockMaterial;
	}
	
	public Material getMaterial() {
		return blockMaterial;
	}
}
