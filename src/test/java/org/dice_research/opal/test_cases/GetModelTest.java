package org.dice_research.opal.test_cases;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.jena.rdf.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class GetModelTest {

	@Test
	public void test() throws URISyntaxException, IOException {
		Model model = OpalTestCases.getModel("opal-2019-06-24", "edp-corine-iceland");
		Assert.assertTrue(!model.isEmpty());
	}

}