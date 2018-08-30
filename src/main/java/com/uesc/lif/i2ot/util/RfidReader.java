package com.uesc.lif.i2ot.util;

import com.phidgets.Phidget;
import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.OutputChangeEvent;
import com.phidgets.event.OutputChangeListener;
import com.phidgets.event.TagGainEvent;
import com.phidgets.event.TagGainListener;
import com.phidgets.event.TagLossEvent;
import com.phidgets.event.TagLossListener;

public class RfidReader {
	private RFIDPhidget rfid;
	private String tag;
	private boolean ligado = false;
	
	/*DESLIGA A ANTENA E LED DO LEITOR RFID*/
    public void desligaRFID()throws Exception{
    	try {
            System.out.print("closing...");
            rfid.setAntennaOn(false);
            rfid.setLEDOn(false);
            rfid.close();
            rfid = null;
            System.out.println(" ok");
            System.out.println("wait for finalization...");
            System.gc();
            ligado = false;
            System.out.println("Done!");
            System.out.println("\n__________________________________________________________\n");
    	}catch(PhidgetException e) {
    		System.out.println("O dispositivo já está desligado!");
    	}
    }//fim_desligaRFID
    
    /*LIGA A ANTENA E LED DO LEITOR RFID E IMPLEMENTA OS EVENTOS DE LEITURA DE CÓDIGO*/
    public void ligaRFID()throws Exception{
        if(ligado){
            System.out.println("Ligado");
        }else{
            rfid = new RFIDPhidget();
                        
            System.out.println(Phidget.getLibraryVersion());
            
            //EVENTOS
            rfid.addAttachListener(new AttachListener() {
                @Override
                public void attached(AttachEvent ae)
                {
                    try
                    {
                        ((RFIDPhidget)ae.getSource()).setAntennaOn(true);
                        ((RFIDPhidget)ae.getSource()).setLEDOn(true);
                    }
                    catch (PhidgetException ex) { }
                    System.out.println("attachment of " + ae);
                }
            });
            rfid.addDetachListener(new DetachListener() {
                @Override
                public void detached(DetachEvent ae) {
                    System.out.println("detachment of " + ae);
                }
            });
            rfid.addErrorListener(new ErrorListener() {
                @Override
                public void error(ErrorEvent ee) {
                    System.out.println("error event for " + ee);
                }
            });
            
            //EVENTO DE LEITURA DA TAG
            rfid.addTagGainListener(new TagGainListener()
            {
                @Override
                public void tagGained(TagGainEvent oe)
                {
                    tag = oe.getValue();
                    System.out.println("Tag gained: "+tag);
                }
            });
            
            rfid.addTagLossListener(new TagLossListener()
            {
                @Override
                public void tagLost(TagLossEvent oe)
                {
                    System.out.println(oe);
                }
            });
            rfid.addOutputChangeListener(new OutputChangeListener()
            {
                @Override
                public void outputChanged(OutputChangeEvent oe)
                {
                    System.out.println(oe);
                }
            });
            //fim_EVENTOS
            
            //Utilizando os eventos para ligar o leitor
            rfid.openAny();
            //System.out.println("waiting for RFID attachment...");
            rfid.waitForAttachment(1000);
            
            //System.out.println("Serial: " + rfid.getSerialNumber());
            //System.out.println("Outputs: " + rfid.getOutputCount());

            //How to write a tag:
            //rfid.write("A TAG!!", RFIDPhidget.PHIDGET_RFID_PROTOCOL_PHIDGETS, false); 

            //System.out.println("Outputting events.  Input to stop.");
        }
        
    }//fim_ligaRFID
}
