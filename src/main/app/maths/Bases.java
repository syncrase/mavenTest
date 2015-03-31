package app.maths;

public class Bases {

	public static double add(double a, double b) {
		if (a < -10000 | a > 10000 | b > 10000 | b < -10000) {
			throw new IllegalArgumentException("|param| must be smaller than 10000");
		}
		return a + b;
	}
}
