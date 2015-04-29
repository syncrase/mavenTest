package app.maths;

import java.util.ArrayList;
import java.util.List;

public class Bases {

	public static double add(double a, double b) {
		if (a < -10000 | a > 10000 | b > 10000 | b < -10000) {
			throw new IllegalArgumentException("|param| must be smaller than 10000");
		}
		return a + b;
	}

	public static void main(String[] args) {
		computeTheBestSquareRepartition(9);
	}

	/**
	 * This method compute the best possible repartition of n elements in a square
	 * 
	 * @return The representative list of the square, the index is the line and the value is the number of elements it contains
	 */
	public static List<Integer> computeTheBestSquareRepartition(int initialNumberOfProjects) {
		List<Integer> matrix = new ArrayList<Integer>();
		// System.out.println("Hello");
//		int initialNumberOfProjects = 7;
		int nbProjects = initialNumberOfProjects;
		int nbColumn = 0;
		// int nbLines;

		int i;
		int projectsRemains = initialNumberOfProjects;
		do {
			// If I can set remains projects on one line, I set these and I quit the loop
			projectsRemains = initialNumberOfProjects - sum(matrix);
			if (projectsRemains <= nbColumn) {
				matrix.add(new Integer(projectsRemains));
				break;
			}
			i = 0;
			while (nbProjects > i * i) {
				i++;

			}
			if (nbColumn == 0) {
				nbColumn = i;
			}
			nbProjects -= i;
			matrix.add(new Integer(i));
		} while (true);

		// System.out.println(matrix);
		return matrix;
	}

	private static int sum(List<Integer> l) {
		int sum = 0;
		for (Integer i : l) {
			sum += i.intValue();
		}
		return sum;
	}
}
