package nl.shanelab.multiblock;

public enum MultiBlockPatternFacing {
	NORTH,
	EAST,
	SOUTH,
	WEST,
	CARDINAL;
	
	/**
	 * Get the pattern facing by index
	 * 
	 * 0: NORTH,
	 * 1: EAST,
	 * 2: SOUTH,
	 * 3: WEST,
	 * default: CARDINAL
	 * 
	 * @param i Facing index
	 * @return PatternFacing Returns a pattern facing by the given index
	 */
	public static MultiBlockPatternFacing getById(int i) {
		MultiBlockPatternFacing facing = null;
		
		switch(i) {
			case 0: facing = MultiBlockPatternFacing.NORTH; break;
			case 1: facing = MultiBlockPatternFacing.EAST; break;
			case 2: facing = MultiBlockPatternFacing.SOUTH; break;
			case 3: facing = MultiBlockPatternFacing.WEST; break;
			default: facing = MultiBlockPatternFacing.CARDINAL;
		}
		
		return facing;
	}
}
