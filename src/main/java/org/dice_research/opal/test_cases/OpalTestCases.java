package org.dice_research.opal.test_cases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.dice_research.opal.common.utilities.ModelSerialization;

/**
 * Provides test cases. Single tests are separated into sets.
 * 
 * Use {@link #listTestSets()} to get all set identifiers.
 * 
 * Use {@link #listTestCases(String)} to get all test-identifiers (for a
 * set-identifier).
 * 
 * Use {@link #getTestCase(String, String)} to get a test case (for the related
 * set-identifer and test-identifier).
 * 
 * @author Adrian Wilke
 */
public abstract class OpalTestCases {

	protected static final String TEST_CASES_DIRECTORY = "/org/dice_research/opal/test_cases/sets";
	// Maybe has to be changed for IDE tests on M$-systems
	protected static final String FILE_SEPARATOR = "/";

	/**
	 * Lists available test sets.
	 */
	public static SortedSet<String> listTestSets() throws URISyntaxException, IOException {
		URI uri = OpalTestCases.class.getResource(TEST_CASES_DIRECTORY).toURI();
		// Handle 2 cases: JAR and IDE, see https://stackoverflow.com/a/28057735
		if (uri.getScheme().equals("jar")) {
			try (FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap())) {
				// Called in this way to be able to close FileSystem afterwards
				return listFiles(fileSystem.getPath(TEST_CASES_DIRECTORY));
			}
		} else {
			return listFiles(Paths.get(uri));
		}
	}

	/**
	 * Lists available test cases of a set.
	 */
	public static SortedSet<String> listTestCases(String setId) throws URISyntaxException, IOException {
		URI uri = OpalTestCases.class.getResource(TEST_CASES_DIRECTORY + FILE_SEPARATOR + setId).toURI();
		// Handle 2 cases: JAR and IDE, see https://stackoverflow.com/a/28057735
		SortedSet<String> filenames = new TreeSet<>();
		if (uri.getScheme().equals("jar")) {
			try (FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap())) {
				// Called in this way to be able to close FileSystem afterwards
				filenames = listFiles(fileSystem.getPath(TEST_CASES_DIRECTORY + FILE_SEPARATOR + setId));
			}
		} else {
			filenames = listFiles(Paths.get(uri));
		}

		SortedSet<String> testIds = new TreeSet<>();
		for (String filename : filenames) {
			if (filename.endsWith(".ttl")) {
				testIds.add(filename.substring(0, filename.length() - 4));
			} else if (filename.endsWith(".nt")) {
				testIds.add(filename.substring(0, filename.length() - 3));
			}
		}
		return testIds;
	}

	/**
	 * Returns a test-case for a test in a set.
	 */
	public static TestCase getTestCase(String setId, String testId) throws IOException {
		TestCase testCase = new TestCase();
		String id = TEST_CASES_DIRECTORY + FILE_SEPARATOR + setId + FILE_SEPARATOR + testId;

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(OpalTestCases.class.getResourceAsStream(id + ".txt")))) {
			testCase.setDatasetUri(br.lines().collect(Collectors.joining(System.lineSeparator())));
		}

		// TTL
		try (InputStream inputStream = OpalTestCases.class.getResourceAsStream(id + ".ttl")) {
			if (inputStream != null) {
				byte[] byteArray = new byte[inputStream.available()];
				inputStream.read(byteArray);
				testCase.setModel(ModelSerialization.deserialize(byteArray));
			}
		}

		// NT
		if (testCase.getModel() == null) {
			try (InputStream inputStream = OpalTestCases.class.getResourceAsStream(id + ".nt")) {
				if (inputStream != null) {
					byte[] byteArray = new byte[inputStream.available()];
					inputStream.read(byteArray);
					testCase.setModel(ModelSerialization.deserialize(byteArray, null, "N-TRIPLES"));
				}
			}
		}

		return testCase;
	}

	public static List<TestCase> getAllTestCases() throws URISyntaxException, IOException {
		List<TestCase> testCases = new LinkedList<>();
		for (String testSetId : listTestSets()) {
			for (String testCaseId : listTestCases(testSetId)) {
				testCases.add(getTestCase(testSetId, testCaseId));
			}
		}
		return testCases;
	}

	protected static SortedSet<String> listFiles(Path path) throws IOException {
		SortedSet<String> sets = new TreeSet<>();
		try (Stream<Path> walk = Files.walk(path, 1)) {
			for (Iterator<Path> iterator = walk.iterator(); iterator.hasNext();) {
				Path itPath = iterator.next();
				if (itPath.equals(path)) {
					continue;
				}
				String string = itPath.toString();
				if (string.endsWith(FILE_SEPARATOR)) {
					string = string.substring(0, string.length() - 1);
				}
				sets.add(string.substring(string.lastIndexOf(FILE_SEPARATOR) + FILE_SEPARATOR.length()));
			}
		}
		return sets;
	}

	/**
	 * Used to test loading of resources in jar file.
	 */
	public static void main(String[] args) throws Exception {
		SortedSet<String> testSets = OpalTestCases.listTestSets();
		System.out.println(" Sets: " + testSets);

		SortedSet<String> testCases = OpalTestCases.listTestCases(testSets.first());
		System.out.println(" Test-cases for " + testSets.first() + ": " + testCases);

		TestCase testCase = OpalTestCases.getTestCase(testSets.first(), testCases.first());
		System.out.println(" Test case model: " + testCase.getModel().size());
		System.out.println(" Test case dataset-URI: " + testCase.getDatasetUri());
	}

}