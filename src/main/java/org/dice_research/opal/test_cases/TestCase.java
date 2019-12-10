package org.dice_research.opal.test_cases;

import org.apache.jena.rdf.model.Model;

/**
 * Contains model and dataset-URI for a test.
 *
 * @author Adrian Wilke
 */
public class TestCase {

	protected String datasetUri;
	protected Model model;

	public String getDatasetUri() {
		return datasetUri;
	}

	public Model getModel() {
		return model;
	}

	public TestCase setDatasetUri(String datasetUri) {
		this.datasetUri = datasetUri;
		return this;
	}

	public TestCase setModel(Model model) {
		this.model = model;
		return this;
	}

	@Override
	public String toString() {
		return (datasetUri != null ? datasetUri : "null") + (model != null ? " (" + model.size() + ")" : "null");
	}

}