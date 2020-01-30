package org.dice_research.opal.test_cases.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.SortedSet;

import org.dice_research.opal.test_cases.OpalTestCases;
import org.junit.Assert;
import org.junit.Test;

public class ListTestsTest {

	@Test
	public void test() throws URISyntaxException, IOException {
		SortedSet<String> testCases = OpalTestCases.listTestCases("opal-2019-06-24");
		Assert.assertTrue(testCases.contains("edp-corine-iceland"));

		testCases = OpalTestCases.listTestCases("edp-2019-12-17");
		Assert.assertTrue(testCases.contains("mittenwalde"));
	}

}