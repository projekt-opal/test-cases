package org.dice_research.opal.test_cases;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.apache.jena.rdf.model.Model;
import org.dice_research.opal.common.utilities.ModelSerialization;

/**
 * Provides test cases. The single tests are separated into sets.
 * 
 * Use {@link #listSets()} to get all set identifiers.
 * 
 * Use {@link #listTests(String)} to get all test identifiers.
 * 
 * Use {@link #getModel(String, String)} to specify a set and a test, and get
 * the related test model.
 * 
 * @author Adrian Wilke
 */
public abstract class OpalTestCases {

	protected static final String TEST_CASES_DIRECTORY = "/org/dice_research/opal/test_cases/sets";
	protected static final String FILE_SEPARATOR = File.separator;

	public static SortedSet<String> listSets() throws URISyntaxException, IOException {
		URI uri = OpalTestCases.class.getResource(TEST_CASES_DIRECTORY).toURI();
		// Handle 2 cases: JAR and IDE
		// See https://stackoverflow.com/a/28057735
		if (uri.getScheme().equals("jar")) {
			try (FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap())) {
				// Called in this way to be able to close FileSystem afterwards
				return listFiles(fileSystem.getPath(TEST_CASES_DIRECTORY));
			}
		} else {
			return listFiles(Paths.get(uri));
		}
	}

	public static Map<String, String> listTests(String set) throws URISyntaxException, IOException {
		URI uri = OpalTestCases.class.getResource(TEST_CASES_DIRECTORY + FILE_SEPARATOR + set).toURI();
		// Handle 2 cases: JAR and IDE
		// See https://stackoverflow.com/a/28057735
		SortedSet<String> filenames = new TreeSet<>();
		if (uri.getScheme().equals("jar")) {
			try (FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap())) {
				// Called in this way to be able to close FileSystem afterwards
				filenames = listFiles(fileSystem.getPath(TEST_CASES_DIRECTORY + FILE_SEPARATOR + set));
			}
		} else {
			filenames = listFiles(Paths.get(uri));
		}

		// Collect test IDs and dataset URIs
		Map<String, String> testIds = new HashMap<>();
		String testId = null;
		for (String filename : filenames) {
			// Filenames are sorted, ttl before txt
			if (filename.endsWith(".ttl")) {
				testId = filename.substring(0, filename.length() - 4);
			} else if (filename.endsWith(".txt")) {
				String path = TEST_CASES_DIRECTORY + FILE_SEPARATOR + set + FILE_SEPARATOR + filename;
				testIds.put(testId, IOUtils
						.toString(OpalTestCases.class.getResourceAsStream(path), StandardCharsets.UTF_8.name()).trim());
				testId = null;
			}
		}
		return testIds;
	}

	public static Model getModel(String set, String test) throws IOException {
		try (InputStream inputStream = OpalTestCases.class
				.getResourceAsStream(TEST_CASES_DIRECTORY + FILE_SEPARATOR + set + FILE_SEPARATOR + test + ".ttl")) {
			byte[] byteArray = new byte[inputStream.available()];
			inputStream.read(byteArray);
			return ModelSerialization.deserialize(byteArray);
		}
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

	public static void main(String[] args) throws Exception {
		// Used to test resources in jar.

		SortedSet<String> sets = OpalTestCases.listSets();
		System.out.println("Sets: " + sets);

		Map<String, String> tests = OpalTestCases.listTests(sets.first());
		System.out.println("Tests: " + tests.keySet());
		System.out.println("URIs: " + tests.values());

		Model model = OpalTestCases.getModel(sets.first(), tests.keySet().iterator().next());
		System.out.println(model);
	}
}