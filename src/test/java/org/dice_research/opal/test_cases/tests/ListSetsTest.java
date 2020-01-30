package org.dice_research.opal.test_cases.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.SortedSet;

import org.dice_research.opal.test_cases.OpalTestCases;
import org.junit.Assert;
import org.junit.Test;

public class ListSetsTest {

	@Test
	public void test() throws URISyntaxException, IOException {
		SortedSet<String> sets = OpalTestCases.listTestSets();
		Assert.assertTrue(sets.contains("opal-2019-06-24"));
		Assert.assertTrue(sets.contains("edp-2019-12-17"));
	}

}