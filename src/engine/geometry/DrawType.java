package engine.geometry;

public enum DrawType {
	WIREFRAME_DRAW(0),
	FILL(1),
	FILL_N_HIGHLIGHT(2);

	int drawOrdinal;
	DrawType(int ord) {
		this.drawOrdinal = ord;
	}

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
