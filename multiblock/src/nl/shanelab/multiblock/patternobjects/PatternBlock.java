package nl.shanelab.multiblock.patternobjects;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import nl.shanelab.multiblock.IMaterial;
import nl.shanelab.multiblock.MaterialWrapper;
import nl.shanelab.multiblock.PatternObject;

/**
 * Block pattern for the multiblock structure
 * 
 * @author ShaneCraft
 *
 */
public class PatternBlock extends PatternObject {

	private final IMaterial material;
	
	public PatternBlock(@Nonnull Material blockMaterial, int x, int y, int z) {
		this(blockMaterial, new Vector(x, y, z));
	}
	
	public PatternBlock(@Nonnull Material blockMaterial, Vector relativeVec) {
		super(relativeVec);
		
		if (!blockMaterial.isBlock()) {
			throw new IllegalArgumentException(String.format("The given blockMaterial %s is not a valid block material.", blockMaterial.toString()));
		}
		
		this.material = new MaterialWrapper(blockMaterial);
	}
	
	public PatternBlock(@Nonnull IMaterial material, int x, int y, int z) {
		this(material, new Vector(x, y, z));
	}
	
	public PatternBlock(@Nonnull IMaterial material, Vector relativeVec) {
		super(relativeVec);
		
		if (!material.getType().isBlock()) {
			throw new IllegalArgumentException(String.format("The given blockMaterial %s is not a valid block material.", material.getType().toString()));
		}
		
		this.material = material;
	}

	@Override
	protected PatternObject createRotatedClone(Vector vector) {
		return new PatternBlock(material, vector);
	}

	@Override
	public boolean isValid(Location location) {
		return material.isValidBlock(location.getBlock());
	}
	
	public Material getMaterial() {
		return material.getType();
	}
}
