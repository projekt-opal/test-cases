package org.dice_research.opal.test_cases.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.dice_research.opal.test_cases.OpalTestCases;
import org.junit.Assert;
import org.junit.Test;

public class ListTestsTest {

	/**
	 * Used for README.md
	 */
	Map<String, String> getSets(String testSetId) throws URISyntaxException, IOException {
		Map<String, String> tests = OpalTestCases.listTests(testSetId);
		return tests;
	}

	@Test
	public void test() throws URISyntaxException, IOException {
		Map<String, String> tests = getSets("opal-2019-06-24");
		Assert.assertTrue(tests.containsKey("edp-corine-iceland"));
		Assert.assertTrue(tests.get("edp-corine-iceland").equals(
				"http://projekt-opal.de/dataset/https___europeandataportal_eu_set_data__3dff988d_59d2_415d_b2da_818e8ef31117_"));
	}

}