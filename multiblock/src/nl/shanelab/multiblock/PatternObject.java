package nl.shanelab.multiblock;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Pattern for the multiblock structure
 * 
 * NORTH: 	negative on Z-axis
 * EAST: 	positive on X-axis
 * SOUTH: 	positive on Z-axis
 * WEST: 	negative on X-axis
 * 
 * @author ShaneCraft
 *
 */
public abstract class PatternObject {
	
	private final int relativeX, relativeY, relativeZ;
	
	public PatternObject(@Nonnull Vector relativeVec) {
		this.relativeX = relativeVec.getBlockX();
		this.relativeY = relativeVec.getBlockY();
		this.relativeZ = relativeVec.getBlockZ();
	}
	
	protected abstract PatternObject createRotatedClone(Vector vector);
	
	public abstract boolean isValid(Location location);
	
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
	
	public PatternObject rotate90() {
		return rotate(90);
	}
	
	public PatternObject rotate180() {
		return rotate(180);
	}
	
	public PatternObject rotate270() {
		return rotate(270);
	}
	
	private PatternObject rotate(int n) {
		n = n > 360 ? 0 : n;
		
		double rad = Math.toRadians((double) n);
		
		double rx = (relativeX * Math.cos(rad)) - (relativeZ * Math.sin(rad));
	    double rz = (relativeX * Math.sin(rad)) + (relativeZ * Math.cos(rad));
	    
	    int x = (int) Math.round(rx);
	    int z = (int) Math.round(rz);
		
		return createRotatedClone(new Vector(x, relativeY, z));
	}
}
