package com.uesc.lif.i2ot.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class OntologyManager {
	/**
     * The I2oTontology namespace used to reference the ontology components.
     */
    public static final String NAMESPACE_I2OTOLOGY = "http://www.semanticweb.org/I2oT/ontology#";
    private static String filePath = "/home/levy/Documents/Protege-5.2.0/projects/i2otology.owl";

    private static InputStream inputStream = null;
    private static InputStreamReader inputStreamReader = null;
    private static OutputStream outputStream = null;
    private static OutputStreamWriter outputStreamWriter = null;
    //private static final OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
    
	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		OntologyManager.filePath = filePath;
	}

	public static InputStream getInputStream() {
		return inputStream;
	}

	public static void setInputStream(InputStream inputStream) {
		OntologyManager.inputStream = inputStream;
	}

	public static InputStreamReader getInputStreamReader() {
		return inputStreamReader;
	}

	public static void setInputStreamReader(InputStreamReader inputStreamReader) {
		OntologyManager.inputStreamReader = inputStreamReader;
	}

	public static OutputStream getOutputStream() {
		return outputStream;
	}

	public static void setOutputStream(OutputStream outputStream) {
		OntologyManager.outputStream = outputStream;
	}

	public static OutputStreamWriter getOutputStreamWriter() {
		return outputStreamWriter;
	}

	public static void setOutputStreamWriter(OutputStreamWriter outputStreamWriter) {
		OntologyManager.outputStreamWriter = outputStreamWriter;
	}

	public static String getNamespaceI2otology() {
		return NAMESPACE_I2OTOLOGY;
	}

	public static OntModel getOntmodel() {
		return ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
	}

	/**
     * Opens a RDF/XML file to write using the static variable 'filePath' from this class.
     * 
     * @throws java.io.FileNotFoundException
     */
    public static void openOutputRDFFile() throws IOException{
    	//Now the file is opened as output
        outputStream = new FileOutputStream(filePath);        
        outputStreamWriter = new OutputStreamWriter(outputStream);
    }
    
    /**
     * Closes the RDF/XML file opened.
     * 
     * @throws java.io.IOException
     */    
    public static void closeOutputRDFFile() throws IOException{
        outputStream.close();
        outputStreamWriter.close();
        outputStream = null;
        outputStreamWriter = null;
        
    }
    
    /**
     * Opens a RDF/XML file to read using the static variable 'filePath' from this class.
     */
    public static void openInputRDFFile(){
        //Creating an input for the rdf file
        inputStream = FileManager.get().open(filePath);
        //Creating a inputStreamReader from the inputStream
        inputStreamReader = new InputStreamReader(inputStream);        
        
        //Using the inputStreamReader to configurate the default model
        //OntologyManager.ontModel.read(inputStreamReader, "RDF/XML");
    }
    
    /**
     * Closes the RDF/XML file opened.
     * @throws java.io.IOException
     */
    public static void closeInputRDFFile() throws IOException{        
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        inputStreamReader = null;
    }
}
