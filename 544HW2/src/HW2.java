import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Zelun Jiang
 *
 */
public class HW2 {

	//the graphic file
	public static String file = "src/graph.txt";
	
	//The dampening factor beta
	public static double beta = 0.85;
	
	//The degree for the SQRE 
	public static double degree= 0.00001;

	
	/**
	 * @param file
	 * @return the row number of the Matrix
	 * @throws FileNotFoundException
	 */
	public static int rowNum(String file) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(file));
		int lineNum = 0;
		while (sc.hasNextLine()) {
			sc.nextLine();
			lineNum++;
		}
		int rowNum = (int) Math.sqrt(lineNum);
		sc.close();
		// System.out.println(rowNum);
		return rowNum;
	}

	/**
	 * @param file
	 * @param rowNum
	 * @return the transition matrix
	 * @throws FileNotFoundException
	 */
	public static double[][] MatrixM(String file, int rowNum) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(file));

		double[][] originalMatrix = new double[rowNum][rowNum];
		while (sc.hasNextLine()) {
			List<String> helper = Arrays.asList(sc.nextLine().split(","));

			int row = Integer.parseInt(helper.get(1));
			int column = Integer.parseInt(helper.get(2));
			int value = Integer.parseInt(helper.get(0));

			// System.out.println("row "+row+" col "+column+" value "+value);
			originalMatrix[row - 1][column - 1] = value;
		}

		for (int k = 0; k < originalMatrix.length; k++) {
			int sum = 0;
			for (int j = 0; j < originalMatrix[k].length; j++) {
				sum += originalMatrix[k][j];
			}
			if (sum == 0) {
				sum = 1;
			}
			for (int j = 0; j < originalMatrix[k].length; j++) {
				originalMatrix[k][j] = originalMatrix[k][j] / sum;
				// System.out.print(originalMatrix[k][j] + " ");
			}
			// System.out.println();

		}

		double[][] MatrixM = new double[rowNum][rowNum];

		for (int k = 0; k < originalMatrix.length; k++) {

			for (int j = 0; j < originalMatrix[k].length; j++) {

				MatrixM[j][k] = originalMatrix[k][j] * beta;
			}

		}
		sc.close();
		return MatrixM;
	}

	/**
	 * @param rowNum
	 * @return the vector matrix (1-beta) by n
	 */
	public static double[][] vector(int rowNum) {
		double[][] vector = new double[rowNum][1];
		for (int i = 0; i < vector.length; i++) {
			vector[i][0] = (double) (1 - beta) / rowNum;
		}
		for (int k = 0; k < vector.length; k++) {

			for (int j = 0; j < vector[k].length; j++) {

				// System.out.print(vector[k][j] + " ");
			}
			// System.out.println();
		}
		// System.out.println();

		return vector;
	}

	/**
	 * @param rowNum
	 * @return the first matrix of the iteration
	 */
	public static double[][] firstMatrix(int rowNum) {
		double[][] first = new double[rowNum][1];
		for (int i = 0; i < first.length; i++) {
			first[i][0] = (double) (1.0 / rowNum);
		}

		for (int k = 0; k < first.length; k++) {

			for (int j = 0; j < first[k].length; j++) {

				// System.out.print(first[k][j] + " ");
			}
			// System.out.println();
		}
		// System.out.println();
		return first;
	}

	/**
	 * @param m1
	 * @param m2
	 * @return multiply two matrixes
	 */
	public static double[][] multiplyMatrix(double[][] m1, double[][] m2) {
		int i, j, k;
		// rows and columns for each matrix
		int rowsA = m1.length;
		int colsA = m1[0].length;
		int colsB = m2[0].length;
		double[][] myMatrixC = new double[rowsA][colsB];
		// start across rows of A
		for (i = 0; i < rowsA; i++) {
			// work across cols of B
			for (j = 0; j < colsB; j++) {
				// now complete the addition and multiplication
				for (k = 0; k < colsA; k++) {
					myMatrixC[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}

		return myMatrixC;
	}

	/**
	 * @param m1
	 * @param m2
	 * @return add matrix m1 and m2
	 */
	public static double[][] addMatrix(double[][] m1, double[][] m2) {
		int rows = m1.length;
		double[][] result = new double[rows][1];
		for (int i = 0; i < rows; i++) {
			result[i][0] = m1[i][0] + m2[i][0];
		}

		return result;
	}

	/**
	 * @param m1
	 * @param m2
	 * @return if the two matrixes are converged
	 */
	public static boolean converged(double[][] m1, double[][] m2) {
		boolean converged = true;
		int rows = m1.length;
		for (int i = 0; i < rows; i++) {
			if (SQRE(m1[i][0], m2[i][0], degree) == false) {
				converged = false;
			}
		}

		return converged;
	}

	/**
	 * @param n1
	 * @param n2
	 * @param degree
	 * @return the square root of the difference of two doubles 
	 * and see if it is less than a degree, such as 0.0001
	 */
	public static boolean SQRE(double n1, double n2, double degree) {
		double sqre = Math.sqrt(Math.pow(n1 - n2, 2));
		if (sqre <= degree) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		int rowNum = rowNum(file);

		double[][] first = firstMatrix(rowNum);
		double[][] vector = vector(rowNum);

		double[][] MatrixM = MatrixM(file, rowNum);
		for (int k = 0; k < MatrixM.length; k++) {

			for (int j = 0; j < MatrixM[k].length; j++) {

				System.out.print(MatrixM[k][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

		for (int k = 0; k < first.length; k++) {

			for (int j = 0; j < first[k].length; j++) {

				System.out.print(first[k][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

		double[][] result = addMatrix(multiplyMatrix(MatrixM, first), vector);

		int iteration = 1;
		while (!converged(first, result)) {
			iteration++;
			first = result;
			result = addMatrix(multiplyMatrix(MatrixM, result), vector);
		}

		for (int k = 0; k < result.length; k++) {

			for (int j = 0; j < result[k].length; j++) {

				System.out.print(result[k][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("The iteration number = "+iteration);
	}

}
