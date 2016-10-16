package nl.shanelab.multiblock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

	private final IMaterial coreMaterial;
	
	private final PatternObject[] objects;
	
	private final MultiBlockPatternFacing patternFacing;
	
	private MultiBlockPatternFacing lastPatternFacing;
	
	public MultiBlockPattern(@Nonnull Material coreMaterialType, PatternObject ... objects) {
		this(coreMaterialType, MultiBlockPatternFacing.CARDINAL, objects);
	}
	
	public MultiBlockPattern(@Nonnull Material coreMaterialType, MultiBlockPatternFacing patternFacing, PatternObject ... objects) {
		if (!coreMaterialType.isBlock()) {
			throw new IllegalArgumentException(String.format("The given coreMaterial %s is not a valid block material.", coreMaterialType.toString()));
		}
		
		this.coreMaterial = new MaterialWrapper(coreMaterialType);
		this.patternFacing = patternFacing;
		this.objects = patternFacing == MultiBlockPatternFacing.CARDINAL ? objects : rotatePatterns(objects, patternFacing);
	}
	
	public MultiBlockPattern(@Nonnull IMaterial coreMaterial, PatternObject ... objects) {
		this(coreMaterial, MultiBlockPatternFacing.CARDINAL, objects);
	}
	
	public MultiBlockPattern(@Nonnull IMaterial coreMaterial, MultiBlockPatternFacing patternFacing, PatternObject ... objects) {
		if (!coreMaterial.getType().isBlock()) {
			throw new IllegalArgumentException(String.format("The given coreMaterial %s is not a valid block material.", coreMaterial.toString()));
		}
		
		this.coreMaterial = coreMaterial;
		this.patternFacing = patternFacing;
		this.objects = patternFacing == MultiBlockPatternFacing.CARDINAL ? objects : rotatePatterns(objects, patternFacing);
	}
	
	public List<PatternObject> getPatternObjects() {
		return (List<PatternObject>) Collections.unmodifiableList(Arrays.asList(objects));
	}
	
	public IMaterial getCoreMaterial() {
		return coreMaterial;
	}
	
	public MultiBlockPatternFacing getLastPatternFacing() {
		return patternFacing == MultiBlockPatternFacing.CARDINAL ? lastPatternFacing : patternFacing;
	}
	
	public boolean isMultiBlock(Location location) {
		if (coreMaterial.isValidBlock(location.getBlock())) {
			// check for pattern
			
			lastPatternFacing = null;
			
			return patternFacing == MultiBlockPatternFacing.CARDINAL ? checkCardinalPattern(location, objects) : checkPattern(location, objects); 
		}
		
		return false;
	}
	
	private boolean checkCardinalPattern(Location startLocation, PatternObject[] patternObjects) {
		boolean flag = false;
		
		PatternObject[] pattern = null;
		for (int i = 0; i < 4; i++) {
			if (!flag) {
				// set initial pattern or rotate the previous one by 90 degrees
				pattern = i == 0 ? patternObjects : rotatePatterns(pattern, MultiBlockPatternFacing.EAST);
				
				lastPatternFacing = MultiBlockPatternFacing.getById(i);

				flag = checkPattern(startLocation, pattern);
			}
		}
		
		return flag;
	}
	
	private PatternObject[] rotatePatterns(PatternObject[] patternObjects, MultiBlockPatternFacing patternFacing) {
		if (patternFacing != MultiBlockPatternFacing.NORTH || patternFacing != MultiBlockPatternFacing.CARDINAL) {
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
