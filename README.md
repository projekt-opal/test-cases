# OPAL JUnit test cases

This component provides test cases. The single tests are separated into sets.

## Example: Get all sets

```java
SortedSet<String> listSets() throws URISyntaxException, IOException {
	return org.dice_research.opal.test_cases.OpalTestCases.listSets();
}
```

## Example: Get all tests of a set

```java
Map<String, String> getSets(String setId) throws URISyntaxException, IOException  {
	return org.dice_research.opal.test_cases.OpalTestCases.listTests(setId);
}
```

## Example: Get a test model

```java
private Model getTestModel(String setId, String testId) throws IOException {
	return org.dice_research.opal.test_cases.OpalTestCases.getModel(setId, testId);
}
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
