package org.dice_research.opal.test_cases.tests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.jena.rdf.model.Model;
import org.dice_research.opal.test_cases.OpalTestCases;
import org.junit.Assert;
import org.junit.Test;

public class GetModelTest {

	/**
	 * Used for README.md
	 */
	Model getTestModel(String testSetId, String testId) throws IOException {
		Model model = OpalTestCases.getModel(testSetId, testId);
		return model;
	}

	@Test
	public void test() throws URISyntaxException, IOException {
		Model model = getTestModel("opal-2019-06-24", "edp-corine-iceland");
		Assert.assertTrue(!model.isEmpty());
	}

}