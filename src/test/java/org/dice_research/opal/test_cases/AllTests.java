package org.dice_research.opal.test_cases;

import org.dice_research.opal.test_cases.tests.GetModelTest;
import org.dice_research.opal.test_cases.tests.ListSetsTest;
import org.dice_research.opal.test_cases.tests.ListTestsTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({

		GetModelTest.class,

		ListSetsTest.class,

		ListTestsTest.class })

public class AllTests {
}