package org.dice_research.opal.test_cases;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.SortedSet;

import org.junit.Assert;
import org.junit.Test;

public class ListTestsTest {

	@Test
	public void test() throws URISyntaxException, IOException {
		SortedSet<String> tests = OpalTestCases.listTests("opal-2019-06-24");
		Assert.assertTrue(tests.contains("edp-corine-iceland"));
	}

}