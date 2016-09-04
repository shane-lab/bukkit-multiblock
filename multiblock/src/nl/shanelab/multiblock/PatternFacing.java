package nl.shanelab.multiblock;

public enum PatternFacing {
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
	public static PatternFacing getById(int i) {
		PatternFacing facing = null;
		
		switch(i) {
			case 0: facing = PatternFacing.NORTH; break;
			case 1: facing = PatternFacing.EAST; break;
			case 2: facing = PatternFacing.SOUTH; break;
			case 3: facing = PatternFacing.WEST; break;
			default: facing = PatternFacing.CARDINAL;
		}
		
		return facing;
	}
}
