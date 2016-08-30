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
	
	private final PatternBlock[] blocks;
	
	// private final boolean cardinal;
	
	private final PatternFacing patternFacing;
	
	public MultiBlockPattern(@Nonnull Material coreMaterial, PatternBlock ... blockPatterns) {
		this(coreMaterial, PatternFacing.CARDINAL, blockPatterns);
	}
	
	public MultiBlockPattern(@Nonnull Material coreMaterial, PatternFacing patternFacing, PatternBlock ... blocks) {
		if (!coreMaterial.isBlock()) {
			throw new IllegalArgumentException(String.format("The given coreMaterial %s is not a valid block material.", coreMaterial.toString()));
		}
		
		this.coreMaterial = coreMaterial;
		this.patternFacing = patternFacing;
		this.blocks = patternFacing == PatternFacing.CARDINAL ? blocks : rotatePatterns(blocks, patternFacing);
	}
	
	public Material getCoreMaterial() {
		return coreMaterial;
	}
	
	public boolean isMultiBlock(Location location) {
		if (location.getBlock().getType() == coreMaterial) {
			// check for pattern
			
			//System.out.println("MultiBlock on " + location.toVector().toString());
			
			return patternFacing == PatternFacing.CARDINAL ? checkCardinalPattern(location, blocks) : checkPattern(location, blocks); 
		}
		
		return false;
	}
	
	private boolean checkCardinalPattern(Location startLocation, PatternBlock[] patternBlocks) {
		boolean flag = false;
		
		PatternBlock[] pattern = null;
		for (int i = 0; i < 4; i++) {
			if (!flag) {
				// set initial pattern or rotate the previous one by 90 degrees
				pattern = i == 0 ? patternBlocks : rotatePatterns(pattern, PatternFacing.EAST);

				flag = checkPattern(startLocation, pattern);
			}
		}
		
		return flag;
	}
	
	private PatternBlock[] rotatePatterns(PatternBlock[] patternBlocks, PatternFacing patternFacing) {
		if (patternFacing != PatternFacing.NORTH || patternFacing != PatternFacing.CARDINAL) {
			PatternBlock[] newPatternBlocks = new PatternBlock[patternBlocks.length];
			
			int i = 0;
			for (PatternBlock pattern : patternBlocks) {
				switch (patternFacing) {
					case EAST: newPatternBlocks[i] = pattern.rotate90(); break;
					case SOUTH: newPatternBlocks[i] = pattern.rotate180(); break;
					case WEST: newPatternBlocks[i] = pattern.rotate270(); break;
					default: break;
				}
				
				i++;
			}
			
			return newPatternBlocks;
		}
		
		return patternBlocks;
	}
	
	private boolean checkPattern(Location startLocation, PatternBlock[] patternBlocks) {
		if (patternBlocks != null && patternBlocks.length > 0) {
			//System.out.println("-> has pattern blocks");
			boolean flag = true;
			
			//int i = 0;
			for (PatternBlock pattern : patternBlocks) {
				if (flag) {

					//System.out.println("iteration " + i);
					Location location = startLocation.add(pattern.getLocation());
					if (location.getBlock() == null || location.getBlock().getType() != pattern.getMaterial()) {
						flag = false;
						//System.out.println("-> no pattern match");
						
						/*if (location.getBlock() != null) {
							System.out.println("on " + location.toString());
							System.out.print(" found: " + location.getBlock().getType() + ", should be: " + pattern.getMaterial());
						}*/
					}
					
					startLocation.subtract(pattern.getLocation());
				}
				
				//i++;
			}
			
			return flag;
		}

		return true;
	}
}
