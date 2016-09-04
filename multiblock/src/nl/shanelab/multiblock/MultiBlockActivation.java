package nl.shanelab.multiblock;

/**
 * Used as callback on multiblock activation, 
 * specifies the activation type and the activated multiblock pattern facing
 * 
 * @author ShaneCraft
 *
 */
public final class MultiBlockActivation {
	
	private final MultiBlockActivationType activationType;
	
	private final PatternFacing patternFacing;
	
	public MultiBlockActivation(MultiBlockActivationType activationType, PatternFacing patternFacing) {
		this.activationType = activationType;
		this.patternFacing = patternFacing;
	}
	
	public MultiBlockActivationType getType() {
		return activationType;
	}
	
	public PatternFacing getFacing() {
		return patternFacing;
	}
}
