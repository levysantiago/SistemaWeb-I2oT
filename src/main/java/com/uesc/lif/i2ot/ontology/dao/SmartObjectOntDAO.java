package com.uesc.lif.i2ot.ontology.dao;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.SomeValuesFromRestriction;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import com.uesc.lif.i2ot.util.OntologyManager;

public class SmartObjectOntDAO {
	
	private OntModel ontModel;
	
	private boolean openReadConnection() {
		try {
			OntologyManager.openInputRDFFile();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean closeReadConnection() {
		try {
	    	OntologyManager.closeInputRDFFile();
	    	return true;
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}
	
	private boolean openWriteConnection() {
		try {
			OntologyManager.openOutputRDFFile();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean closeWriteConnection() {
		try {
			OntologyManager.closeOutputRDFFile();
			return true;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void beginTransaction() {
		this.ontModel = OntologyManager.getOntmodel();
		//It is necessary to open and close the file as input to update the ontModel class
    	this.openReadConnection();
    	ontModel.read(OntologyManager.getInputStreamReader(), "RDF/XML");
	}
	
	public void rollbackTransaction() {
		if(OntologyManager.getInputStream() != null) {
    		this.closeReadConnection();
    	}
	}
	
	public void endTransaction() {
		if(OntologyManager.getInputStream() != null) {
			this.closeReadConnection();
		}
		if(OntologyManager.getOutputStream() != null) {
			this.closeWriteConnection();
		}
	}
	
	public boolean commitChanges(){
    	if(OntologyManager.getInputStream() != null) {
    		this.closeReadConnection();
    	}else {
    		return false;
    	}
    	if(OntologyManager.getOutputStream() != null) {
    		return false;
    	}
    	//Inserting new informations inside the file
        this.openWriteConnection();
        ontModel.write(new OutputStreamWriter(OntologyManager.getOutputStream()));
        this.closeWriteConnection();
        return true;
    }
	
	public Individual createIndividual(String name, String strType){
        if(OntologyManager.getInputStream() != null && OntologyManager.getInputStreamReader() != null){
            Resource type = ontModel.createResource(OntologyManager.getNamespaceI2otology() + strType);
            Individual individual = ontModel.createIndividual(OntologyManager.getNamespaceI2otology() + name, type);
            return individual;
        }else{
            return null;
        }
    }
	
	private Individual getIndividual(Long smartObjID){
        try{
            return ontModel.getIndividual(OntologyManager.getNamespaceI2otology() + smartObjID);
        }catch(Exception e){
            return null;
        }
    }
    
    public boolean setType(Individual individual, String type){
        if(OntologyManager.getInputStream() != null && OntologyManager.getInputStreamReader() != null){
            OntClass classType = ontModel.getOntClass(OntologyManager.getNamespaceI2otology() + type);
            individual.setRDFType(classType);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean setLocation(Long smartObjID, String strLocation){
        if(OntologyManager.getInputStream() != null && OntologyManager.getInputStreamReader() != null){    
        	Individual individual = this.getIndividual(smartObjID);
            Property pro = ontModel.getProperty(OntologyManager.getNamespaceI2otology() + "hasLocation");
            Individual location = ontModel.getIndividual(OntologyManager.getNamespaceI2otology() + strLocation);
            individual.setPropertyValue(pro, location);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean setDefaultPerson(Individual individual, String defaultClassPerson){
        if(OntologyManager.getInputStream() != null && OntologyManager.getInputStreamReader() != null){            
            Property pro = ontModel.getProperty(OntologyManager.getNamespaceI2otology() + "canBeWith");
            OntClass person = ontModel.getOntClass(OntologyManager.getNamespaceI2otology() + defaultClassPerson);
            SomeValuesFromRestriction restr = ontModel.createSomeValuesFromRestriction(null, pro, person);
            individual.addRDFType(restr);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean setDefaultPlace(Individual individual, String defaultClassPlace){
        if(OntologyManager.getInputStream() != null && OntologyManager.getInputStreamReader() != null){            
            Property pro = ontModel.getProperty(OntologyManager.getNamespaceI2otology() + "canBeIn");
            OntClass location = ontModel.getOntClass(OntologyManager.getNamespaceI2otology() + defaultClassPlace);
            SomeValuesFromRestriction restr = ontModel.createSomeValuesFromRestriction(null, pro, location);
            individual.addRDFType(restr);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean existsIndividual(Long smartObjID) {
    	try {    		
    		Individual individual = this.getIndividual(smartObjID);
    		individual.getLocalName();
    		return true;
    	}catch(Exception e) {
    		return false;
    	}
    }
        
    /**
     * Returns if some device is of an allowed material (simple) or not (dangerous).
     * 
     * @param smartObjID The smart object id of database.
     * @return <code>true</code> if the instance is of an allowed material.<br>
     *         <code>false</code> if it's not or if the individual doesn't exists.<br>
     */
    public boolean allowedMaterial(Long smartObjID){
        return individualIsTypeOf(smartObjID, "AllowedMaterial");
    }
    
    /**
     * Returns if some device is in an allowed place or not.
     * 
     * @param smartObjID The smart object id of database.
     * @return <code>true</code> if the instance is located in an allowed place.<br>
     *         <code>false</code> if it's not or if the individual doesn't exists.<br>
     */
    public boolean allowedPlace(Long smartObjID){
        return individualIsTypeOf(smartObjID, "AllowedPlace");
    }
    
    /**
     * Returns if some device is with an allowed person or not.
     * 
     * @param smartObjID The smart object id of database.
     * @return <code>true</code> if the instance is with an allowed person.<br>
     *         <code>false</code> if it's not or if the individual doesn't exists.<br>
     */
    public boolean allowedPerson(Long smartObjID){
        return individualIsTypeOf(smartObjID, "AllowedPerson");
    }
    
    /**
     * Returns true if some device is of some type (class).
     * 
     * @param smartObjID The smart object id of database.
     * @param type An specific class name
     * @return <code>true</code> if the instanceName is instance of the type given;<br>
     *         <code>false</code> if it's not an instance of the type given or the individual doesn't exists.<br>
     */
    public boolean individualIsTypeOf(Long smartObjID, String type){
        //An individual from ontology
        Individual individual;
        //The return value
        boolean rightPlace = false;
        
        try{
        	individual = this.getIndividual(smartObjID);
            
            if(individual.hasOntClass(OntologyManager.getNamespaceI2otology() + type)) {
            	rightPlace = true;
            }
        }catch(Exception e){
            rightPlace = false;
        }
        
        return rightPlace;
    }
    
    /**
     * Define a rfid code to some device.
     * @param smartObjID The smart object id.
     * @param code The rfid code for the device.
     * @return <code>true</code> If everything went ok.<br>
     *         <code>false</code> If the individual wasn't found.
     */
    public boolean setDeviceCode(Long smartObjID, String code){
        //The return value
        boolean result = true;
        Individual individual;
        
        if(OntologyManager.getInputStream() != null && OntologyManager.getInputStreamReader() != null){
            try{
            	individual = getIndividual(smartObjID);
                DatatypeProperty dataProp = ontModel.getDatatypeProperty(OntologyManager.getNamespaceI2otology() + "deviceTag");
                Literal literalCode = ontModel.createLiteral(code);
                individual.setPropertyValue(dataProp, literalCode);
            }catch(Exception e){
            	//Individual not found
            	//e.printStackTrace();
            	this.rollbackTransaction();
                result = false;
            }            
        }
        
        return result;
    }
    
    /**
     * Returns the device's code registered.
     * @param smartObjID The smart object id.
     * @return  The device's code.<br>
     *          <code>null</code> If the individual doesn't exists or doesn't
     *          have code.
     */
    public String getDeviceCode(Long smartObjID){
        //An individual from ontology
        Individual individual;
        //The return code of the device
        String code;
        
        try{
        	OntologyManager.openInputRDFFile();
            
            individual = ontModel.getIndividual(OntologyManager.getNamespaceI2otology() + smartObjID);
            DatatypeProperty dataProp = ontModel.getDatatypeProperty(OntologyManager.getNamespaceI2otology() + "deviceTag");
            code = individual.getPropertyValue(dataProp).toString();
            
            try{                
            	OntologyManager.closeInputRDFFile();
            }catch (IOException ex) {
                Logger.getLogger(SmartObjectOntDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch(Exception e){
        	e.printStackTrace();
            code = null;
        }
        
        return code;
    }
    
    /**
     * Returns the current RDF/OWL file path configured previously.
     * @return The current RDF/OWL file path.
     */
    /*public String getFilePath() {
        return OntologyManager.getFilePath();
    }*/
    
    /**
     * Configures the RDF/OWL file path.
     * @param filePath The new file path.
     */
    /*public void setFilePath(String filePath) {
    	OntologyManager.setFilePath(filePath);
    }*/
}
