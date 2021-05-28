package engine.graphics.math.geometry;

/**
 * Represents the draw type of entities. There are 3 options available: Wireframe drawing, filling the entire polygon,
 * filling the polygon and also drawing its edges.
 */
public enum DrawType {
	WIREFRAME_DRAW(0),
	FILL(1),
	FILL_N_HIGHLIGHT(2);

	int drawOrdinal;
	DrawType(int ord) {
		this.drawOrdinal = ord;
	}

	/**
	 *
	 * @param ord The number of the draw type
	 * @return A draw type
	 * @see Polygon3D
	 */
	public static DrawType ordToDrawType(int ord) {
		int modDrawType = ord % 3;

		// unneeded but used to avoid compiler warnings/errors
		switch (modDrawType) {
			case 0:
				return WIREFRAME_DRAW;
			case 1:
				return FILL;
			case 2:
				return FILL_N_HIGHLIGHT;
			default:
				return null;
		}

	}
}
