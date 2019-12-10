# OPAL JUnit test cases

This component provides test cases. The single tests are separated into sets.

## Example: Get all test sets

```java
import org.dice_research.opal.test_cases.OpalTestCases;

SortedSet<String> testSets = OpalTestCases.listSets();

System.out.println("Test sets: " + testSets);
```

## Example: Get all tests of a set

```java
import org.dice_research.opal.test_cases.OpalTestCases;

// String testSetId = ...

Map<String, String> tests = OpalTestCases.listTests(testSetId);

for (String testId : tests.keySet()) {
	System.out.println("Test:    " + testId);
	System.out.println("Dataset: " + tests.get(testId));
}
```

## Example: Get a test model

```java
import org.dice_research.opal.test_cases.OpalTestCases;

// String testSetId = ...
// String testId = ...

Model model = OpalTestCases.getModel(testSetId, testId);
```



## Usage with Apache Maven

Add the following lines to your `pom.xml` configuration file:

	<dependencies>
		<dependency>
			<groupId>org.dice-research.opal</groupId>
			<artifactId>test-cases</artifactId>
			<version>[1,2)</version>
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
