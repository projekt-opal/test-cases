package org.dice_research.opal.test_cases.tests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.dice_research.opal.test_cases.OpalTestCases;
import org.dice_research.opal.test_cases.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class GetModelTest {

	@Test
	public void test() throws URISyntaxException, IOException {

		TestCase testCase = OpalTestCases.getTestCase("opal-2019-06-24", "edp-corine-iceland");
		Assert.assertTrue(testCase.getDatasetUri() != null);
		Assert.assertTrue(testCase.getModel() != null);

		testCase = OpalTestCases.getTestCase("opal-2019-06-24", "mcloud-moers-innenstadt");
		Assert.assertTrue(testCase.getDatasetUri() != null);
		Assert.assertTrue(testCase.getModel() != null);
	}

}