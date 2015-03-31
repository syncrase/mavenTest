package app.maths;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.FirstCat;

@RunWith(Parameterized.class)
public class BasesTest {

	private double[] myParameters;

	
	public BasesTest(double myParameter1, double myParameter2) {
		this.myParameters = new double[2];
		this.myParameters[0] = myParameter1;
		this.myParameters[1] = myParameter2;
	}

	// This class test is create as many time as there're param's array in the collection
	@Parameters
	public static Collection<Object[]> hummhumm() {
		Object[][] arrayOfParamCollection = new Object[][] { { 5, 6 }, { 12, 10 }, { 25.951, 3.14159 }, { 12.35, 34.12 } };
		return Arrays.asList(arrayOfParamCollection);
	}

	@Test
	@Category(FirstCat.class)
	public void testAdd() {
		System.out.println("param 1 : "+myParameters[0]+" & param2 :"+myParameters[1]);
		assertEquals(myParameters[0]+myParameters[1], Bases.add(myParameters[0], myParameters[1]),0);
	}

}
