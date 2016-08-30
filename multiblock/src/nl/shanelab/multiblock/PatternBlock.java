package nl.shanelab.multiblock;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.util.Vector;

/**
 * Block pattern for the multiblock structure
 * 
 * NORTH: 	negative on Z-axis
 * EAST: 	positive on X-axis
 * SOUTH: 	positive on Z-axis
 * WEST: 	negative on X-axis
 * 
 * @author ShaneCraft
 *
 */
public final class PatternBlock {

	private final Material blockMaterial;
	
	private final int relativeX, relativeY, relativeZ;
	
	public PatternBlock(@Nonnull Material blockMaterial, @Nonnull Vector relativeVec) {
		this(blockMaterial, relativeVec.getBlockX(), relativeVec.getBlockY(), relativeVec.getBlockZ());
	}
	
	public PatternBlock(@Nonnull Material blockMaterial, int relativeX, int relativeY, int relativeZ) {
		if (!blockMaterial.isBlock()) {
			throw new IllegalArgumentException(String.format("The given blockMaterial %s is not a valid block material.", blockMaterial.toString()));
		}
		
		this.blockMaterial = blockMaterial;
		
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		this.relativeZ = relativeZ;
	}
	
	public final Material getMaterial() {
		return blockMaterial;
	}
	
	public final int getX() {
		return relativeX;
	}
	
	public final int getY() {
		return relativeY;
	}
	
	public final int getZ() {
		return relativeZ;
	}
	
	public final Vector getLocation() {
		return new Vector(relativeX, relativeY, relativeZ);
	}
	
	public PatternBlock rotate90() {
		return rotate(90);
	}
	
	public PatternBlock rotate180() {
		return rotate(180);
	}
	
	public PatternBlock rotate270() {
		return rotate(270);
	}
	
	private PatternBlock rotate(int n) {
		n = n > 360 ? 0 : n;
		
		double rad = Math.toRadians((double) n);
		
		double rx = (relativeX * Math.cos(rad)) - (relativeZ * Math.sin(rad));
	    double rz = (relativeX * Math.sin(rad)) + (relativeZ * Math.cos(rad));
	    
	    int x = (int) Math.round(rx);
	    int z = (int) Math.round(rz);
		
		return new PatternBlock(blockMaterial, new Vector(x, relativeY, z));
	}
}
