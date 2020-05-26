package org.dice_research.opal.test_cases.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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

	@Test
	public void testNt() throws URISyntaxException, IOException {
		TestCase testCase = OpalTestCases.getTestCase("edp-2019-12-17", "mittenwalde");
		Assert.assertTrue(testCase.getDatasetUri() != null);
		Assert.assertTrue(testCase.getModel() != null);
	}

	@Test
	public void testAll() throws URISyntaxException, IOException {
		List<TestCase> testCases = OpalTestCases.getAllTestCases();
		for (TestCase testCase : testCases) {
			Assert.assertTrue(testCase.getDatasetUri() != null);
			Assert.assertFalse(testCase.getDatasetUri().trim().isEmpty());
			Assert.assertTrue(testCase.getModel() != null);
			Assert.assertTrue(testCase.getModel().size() > 0);
		}
		System.out.println("Checked test cases: " + testCases.size() + " (" + this.getClass() + ")");
	}

}