package org.dice_research.opal.test_cases;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.SortedSet;

import org.junit.Assert;
import org.junit.Test;

public class ListSetsTest {

	@Test
	public void test() throws URISyntaxException, IOException {
		SortedSet<String> sets = OpalTestCases.listSets();
		Assert.assertTrue(sets.contains("opal-2019-06-24"));
	}

}