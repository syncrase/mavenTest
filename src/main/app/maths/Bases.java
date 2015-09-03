package app.maths;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Bases {

	public static double add(double a, double b) {
		if (a < -10000 | a > 10000 | b > 10000 | b < -10000) {
			throw new IllegalArgumentException("|param| must be smaller than 10000");
		}
		return a + b;
	}

	public static void main(String[] args) {

		// for(int i = 0 ; i<30 ; i++){
		// // System.out.println("result for "+i+" items : \t"+computeTheBestSquareRepartition(i));
		// System.out.println("result for "+i+" items : \t"+getViewsPerRow(i));
		// }
		// System.out.println(computeTheBestSquareRepartition(12));

		// int margin = 5;
		// double d = ((100 - 2 * margin) / (double)100);
		// System.out.println(d);

		convertMilliToEquivalentDuration(new BigDecimal("1432826021000"));
		// for(int i : convertMilliToTime(1432911970)){
		// System.out.println(i);
		// }
	}

	/**
	 * 
	 * @param timeInMilli
	 * @return A map with the equivalent duration, keys are : days, hours, minutes and seconds
	 */
	private static Map<?, ?> convertMilliToEquivalentDuration(double timeInMilli) {
        Map<String, Double> equivalentDuration = new TreeMap<String, Double>();

        // Compute seconds
        double seconds = timeInMilli / 1000;
        double minutes = seconds / 60;
        double minutesIntegerPart = ((int) seconds / 60);
        double decimales = minutes - minutesIntegerPart;
        equivalentDuration.put("seconds", new BigDecimal(decimales * 60).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue());

        // Compute minutes
        double hours = minutesIntegerPart / 60;
        double hoursIntegerPart = ((int) minutesIntegerPart / 60);
        decimales = hours - hoursIntegerPart;
        equivalentDuration.put("minutes", new BigDecimal(decimales * 60).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue());

        // Compute hours
        double days = hoursIntegerPart / 24;
        double daysIntegerPart = ((int) hoursIntegerPart / 24);
        decimales = days - daysIntegerPart;
        equivalentDuration.put("hours", new BigDecimal(decimales * 24).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue());
        equivalentDuration.put("days", new BigDecimal(daysIntegerPart).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue());

        return equivalentDuration;
	}
	
	/**
	 * 
	 * @param timeInMilli
	 * @return A map with the equivalent duration, keys are : days, hours, minutes and seconds
	 */
	private static Map<?, ?> convertMilliToEquivalentDuration(BigDecimal timeInMilli) {
        Map<String, String> equivalentDuration = new TreeMap<String, String>();

        // Compute seconds
        BigDecimal[] seconds = timeInMilli.divideAndRemainder(new BigDecimal("1000"));
        BigDecimal[] minutes = seconds[0].divideAndRemainder(new BigDecimal("60"));

        // Compute minutes
        BigDecimal[] hours = minutes[0].divideAndRemainder(new BigDecimal("60"));

        // Compute hours
        BigDecimal[] days = hours[0].divideAndRemainder(new BigDecimal("24"));
        
        equivalentDuration.put("days", days[0].toString());
        equivalentDuration.put("hours", days[1].toString());
        equivalentDuration.put("minutes", hours[1].toString());
        equivalentDuration.put("seconds", minutes[1].toString());

        return equivalentDuration;
	}

	/**
	 * This method compute the best possible repartition of n elements in a square Doesn't works!!
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

	public static int getViewsPerRow(int totalViews) {
		int i = 0;
		while (totalViews > i * i) {
			i++;
		}
		return i;
	}
}
