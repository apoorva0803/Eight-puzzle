package puzzle;

/**
 * For direction
 * @author apoorva
 *
 */
public enum Direction {

	UP (1),
	BOTTOM (2),
	LEFT (3),
	RIGHT (4);
	
	private int type;

	private Direction(int t) {
		type = t;
	}

	public int getType() {
		return type;
	}

	
}
