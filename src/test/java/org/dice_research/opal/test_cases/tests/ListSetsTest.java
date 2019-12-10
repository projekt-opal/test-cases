package org.dice_research.opal.test_cases.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.SortedSet;

import org.dice_research.opal.test_cases.OpalTestCases;
import org.junit.Assert;
import org.junit.Test;

public class ListSetsTest {

	/**
	 * Used for README.md
	 */
	SortedSet<String> listSets() throws URISyntaxException, IOException {
		SortedSet<String> testSets = OpalTestCases.listSets();
		return testSets;
	}

	@Test
	public void test() throws URISyntaxException, IOException {
		SortedSet<String> sets = listSets();
		Assert.assertTrue(sets.contains("opal-2019-06-24"));
	}

}