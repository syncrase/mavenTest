package app.maths;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import app.FirstCat;
import app.SecondCat;

public class BasesTest2 {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	@Category(FirstCat.class)
	public void testAdd() {
		System.out.println("testAdd");
		exception.expect(IllegalArgumentException.class);
		Bases.add(1, 10001);
		// throw new IllegalArgumentException("|param| must be smaller than 10000");
	}

	// ***********************************************************************************

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	@Category(SecondCat.class)
	public void testUsingTempFolder() throws IOException {
		System.out.println("testUsingTempFolder");
		File createdFolder = folder.newFolder("newfolder");
		File createdFile = folder.newFile("myfilefile.txt");
		assertTrue(createdFile.exists());
		assertTrue(createdFolder.exists());
	}

}
