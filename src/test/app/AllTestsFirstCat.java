package app;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import app.printing.InFileTest;


@RunWith(Categories.class)
@IncludeCategory(FirstCat.class)
@ExcludeCategory(SecondCat.class)
@SuiteClasses({ InFileTest.class })
public class AllTestsFirstCat {

}
