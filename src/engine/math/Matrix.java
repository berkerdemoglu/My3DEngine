package engine.math;

import java.util.Arrays;

public class Matrix {
	public final double[][] matrix;

	public Matrix(int rows, int columns) {
		matrix = new double[rows][columns];
	}

	public Matrix(double[][] matrix) {
		this.matrix = matrix.clone();
	}

	public Matrix scale(double s) {
		double[][] newMatrixArray = new double[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				newMatrixArray[i][j] = s * matrix[i][j];
			}
		}

		return new Matrix(newMatrixArray);
	}

	public static Matrix multiplyMatrices(Matrix m1, Matrix m2) {
		if (m1.matrix[0].length != m2.matrix.length) {
			// Check that the two matrices can be multiplied, and if not, return null
			return null;
		}

		double[][] newMatrixArray = new double[m1.matrix.length][m2.matrix[0].length];

		for (int i = 0; i < newMatrixArray.length; i++) {
			for (int j = 0; j < newMatrixArray[i].length; j++) {
				newMatrixArray[i][j] = multiplyCells(m1.matrix, m2.matrix, i, j);
			}
		}

		return new Matrix(newMatrixArray);
	}

	private static double multiplyCells(double[][] a1, double[][] a2, int i, int j) {
		double cell = 0;
		for (int k = 0; k < a2.length; k++) {
			cell += a1[i][k] * a2[k][j];
		}
		return cell;
	}

	@Override
	public String toString() {
		return Arrays.deepToString(matrix);
	}
}
