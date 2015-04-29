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

		// for(int i = 0 ; i<20 ; i++){
		// System.out.println("result for "+i+" items : \t"+computeTheBestSquareRepartition(i));
		// }
		System.out.println(computeTheBestSquareRepartition(12));
	}

	/**
	 * This method compute the best possible repartition of n elements in a square
	 * Doesn't works!!
	 * 
	 * @return The representative list of the square, the index is the line and the value is the number of elements it contains
	 */
	public static List<Integer> computeTheBestSquareRepartition(int initialNumberOfProjects) {
		List<Integer> matrix = new ArrayList<Integer>();
		// System.out.println("Hello");
		// int initialNumberOfProjects = 7;
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

	// public static List<Integer> computeTheBestSquareRepartition(int initialNumberOfProjects) {
	// List<Integer> matrix = new ArrayList<Integer>();
	// // // System.out.println("Hello");
	// // // int initialNumberOfProjects = 7;
	// int nbProjectsRemaining = initialNumberOfProjects;
	// int nbColumn = 0;
	// int nbLinesRemaining;
	// int nbColumnTotal;
	// int nbLinesTotal;
	// //
	// int i;
	// // int projectsRemains = initialNumberOfProjects;
	//
	// // // If I can set remains projects on one line, I set these and I quit the loop
	// // projectsRemains = initialNumberOfProjects - sum(matrix);
	// // if (projectsRemains <= nbColumn) {
	// // matrix.add(new Integer(projectsRemains));
	// // break;
	// // }
	// // Look for the number of columns
	// i = 0;
	// while (initialNumberOfProjects > i * i) {
	// i++;
	//
	// }
	// nbColumnTotal = i;
	//
	// // Deduct the number of lines
	// // if(initialNumberOfProjects > 2*nbColumn){
	// // nbLines = nbColumn;
	// // }else{
	// // nbLines = nbColumn-1;
	// // }
	// // nbLines = (initialNumberOfProjects > 2 * nbColumn) ? (nbLines = nbColumn) : (nbLines = nbColumn - 1);
	// nbLinesTotal = nbColumnTotal - ((initialNumberOfProjects > 2 * nbColumnTotal) ? 0 : 1);
	// nbLinesRemaining = nbLinesTotal;
	// // int projectsForThisLine = nbColumn;
	// do {
	// // nbProjects -= i;
	// if((nbProjectsRemaining/nbLinesRemaining)==nbColumnTotal){
	// matrix.add(new Integer(nbLinesTotal));
	// nbProjectsRemaining -= nbColumn;
	// nbLinesRemaining--;
	// }else{
	// matrix.add(new Integer(nbLinesTotal-1));
	// nbProjectsRemaining -= nbColumn-1;
	// nbLinesRemaining--;
	// }
	//
	//
	// } while (initialNumberOfProjects - sum(matrix) != 0 && nbLinesRemaining != 0);
	// //
	// // // System.out.println(matrix);
	// return matrix;
	// }

	private static int sum(List<Integer> l) {
		int sum = 0;
		for (Integer i : l) {
			sum += i.intValue();
		}
		return sum;
	}
}
