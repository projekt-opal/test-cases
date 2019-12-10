package org.dice_research.opal.test_cases;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.jena.rdf.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class GetModelTest {

	private Model getTestModel(String setId, String testId) throws IOException {
		return org.dice_research.opal.test_cases.OpalTestCases.getModel(setId, testId);
	}

	@Test
	public void test() throws URISyntaxException, IOException {
		Model model = getTestModel("opal-2019-06-24", "edp-corine-iceland");
		Assert.assertTrue(!model.isEmpty());
	}

}