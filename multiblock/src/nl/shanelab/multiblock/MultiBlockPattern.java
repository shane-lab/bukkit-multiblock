package nl.shanelab.multiblock;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * The multi block pattern wrapper class, 
 * all pattern block locations are relative to the core block.
 * 
 * Works in all cardinal directions (North, East, South, West).
 * 
 * @author ShaneCraft
 *
 */
public final class MultiBlockPattern {

	private final Material coreMaterial;
	
	private final PatternObject[] objects;
	
	private final PatternFacing patternFacing;
	
	private PatternFacing lastPatternFacing;
	
	public MultiBlockPattern(@Nonnull Material coreMaterial, PatternObject ... objects) {
		this(coreMaterial, PatternFacing.CARDINAL, objects);
	}
	
	public MultiBlockPattern(@Nonnull Material coreMaterial, PatternFacing patternFacing, PatternObject ... objects) {
		if (!coreMaterial.isBlock()) {
			throw new IllegalArgumentException(String.format("The given coreMaterial %s is not a valid block material.", coreMaterial.toString()));
		}
		
		this.coreMaterial = coreMaterial;
		this.patternFacing = patternFacing;
		this.objects = patternFacing == PatternFacing.CARDINAL ? objects : rotatePatterns(objects, patternFacing);
	}
	
	public Material getCoreMaterial() {
		return coreMaterial;
	}
	
	public PatternFacing getLastPatternFacing() {
		return patternFacing == PatternFacing.CARDINAL ? lastPatternFacing : patternFacing;
	}
	
	public boolean isMultiBlock(Location location) {
		if (location.getBlock().getType() == coreMaterial) {
			// check for pattern
			
			lastPatternFacing = null;
			
			return patternFacing == PatternFacing.CARDINAL ? checkCardinalPattern(location, objects) : checkPattern(location, objects); 
		}
		
		return false;
	}
	
	private boolean checkCardinalPattern(Location startLocation, PatternObject[] patternObjects) {
		boolean flag = false;
		
		PatternObject[] pattern = null;
		for (int i = 0; i < 4; i++) {
			if (!flag) {
				// set initial pattern or rotate the previous one by 90 degrees
				pattern = i == 0 ? patternObjects : rotatePatterns(pattern, PatternFacing.EAST);
				
				lastPatternFacing = PatternFacing.getById(i);

				flag = checkPattern(startLocation, pattern);
			}
		}
		
		return flag;
	}
	
	private PatternObject[] rotatePatterns(PatternObject[] patternObjects, PatternFacing patternFacing) {
		if (patternFacing != PatternFacing.NORTH || patternFacing != PatternFacing.CARDINAL) {
			PatternObject[] newPatternObjects = new PatternObject[patternObjects.length];
			
			int i = 0;
			for (PatternObject pattern : patternObjects) {
				switch (patternFacing) {
					case EAST: newPatternObjects[i] = pattern.rotate90(); break;
					case SOUTH: newPatternObjects[i] = pattern.rotate180(); break;
					case WEST: newPatternObjects[i] = pattern.rotate270(); break;
					default: break;
				}
				
				i++;
			}
			
			return newPatternObjects;
		}
		
		return patternObjects;
	}
	
	private boolean checkPattern(Location startLocation, PatternObject[] patternObjects) {
		if (patternObjects != null && patternObjects.length > 0) {
			boolean flag = true;
			
			
			for (PatternObject pattern : patternObjects) {
				if (flag) {

					Location location = startLocation.add(pattern.getLocation());
					if (location.getBlock() == null || !pattern.isValid(location)) {
						flag = false;
					}
					
					startLocation.subtract(pattern.getLocation());
				}
			}
			
			return flag;
		}

		return true;
	}
}
