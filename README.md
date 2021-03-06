# OPAL JUnit test cases

This component provides test cases. The single tests are separated into sets. Take a view at the [available test cases](src/main/resources/org/dice_research/opal/test_cases/sets/).


## Test case sources

-  [OpalGraph 2019-06-24](https://hobbitdata.informatik.uni-leipzig.de/OPAL/OpalGraph/2019-06-24/)
-  [EDP 2019-12-17](https://hobbitdata.informatik.uni-leipzig.de/OPAL/SourceGraphs/edp-2019-12-17/) (data.10.nt in edp-de-2019-12-17.tar.gz)
-  [EDP 2020-06-06](https://hobbitdata.informatik.uni-leipzig.de/OPAL/processed_datasets/europeandataportal/) (edp_06-06-2020.tar.gz)


## Request for additional test case

If you need an additional dataset, please create a [new issue](https://github.com/projekt-opal/test-cases/issues/new/choose).


## Examples

```java
import org.dice_research.opal.test_cases.OpalTestCases;
import org.dice_research.opal.test_cases.TestCase;
// ...

// Print all available test-sets.
SortedSet<String> testSets = OpalTestCases.listTestSets();
System.out.println("Sets: " + testSets);

// Print all available test-cases for a test-set
SortedSet<String> testCases = OpalTestCases.listTestCases(testSets.first());
System.out.println("Test-cases for " + testSets.first() + ": " + testCases);
		
// Get a test-case. Print the model-size and dataset-URI.
TestCase testCase = OpalTestCases.getTestCase(testSets.first(), testCases.first());
System.out.println("Test case model: " + testCase.getModel().size());
System.out.println("Test case dataset-URI: " + testCase.getDatasetUri());
```


## Usage with Apache Maven

Add the following lines to your `pom.xml` configuration file:

	<dependencies>
		<dependency>
			<groupId>org.dice-research.opal</groupId>
			<artifactId>test-cases</artifactId>
			<version>[1,2)</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
	
	<repositories>
		<repository>
			<id>maven.aksw.internal</id>
			<name>AKSW Repository</name>
			<url>http://maven.aksw.org/archiva/repository/internal</url>
		</repository>
		<repository>
			<id>maven.aksw.snapshots</id>
			<name>AKSW Snapshot Repository</name>
			<url>http://maven.aksw.org/archiva/repository/snapshots</url>
		</repository>
	</repositories>

Available versions are listed at [maven.aksw.org](https://maven.aksw.org/archiva/#advancedsearch~internal/org.dice-research.opal~test-cases~~~~~30).


## Credits

[Data Science Group (DICE)](https://dice-research.org/) at [Paderborn University](https://www.uni-paderborn.de/)

This work has been supported by the German Federal Ministry of Transport and Digital Infrastructure (BMVI) in the project [Open Data Portal Germany (OPAL)](http://projekt-opal.de/) (funding code 19F2028A).
