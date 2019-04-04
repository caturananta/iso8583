package id.co.prismalink.ISO8583.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

import id.co.prismalink.ISO8583.res.NetworkManagementReq;
import id.co.prismalink.ISO8583.res.ResponsesISO;

public class NetworkManagementService {

	 public String buildRequestISOMessage(NetworkManagementReq req) throws Exception {
	        try {
	        
	            InputStream is = getClass().getResourceAsStream("/fields.xml");
	            GenericPackager packager = new GenericPackager(is);
	            System.out.println("incoming");
	            ISOMsg isoMsg = new ISOMsg();
	            isoMsg.setPackager(packager);
	            isoMsg.setMTI("0800");

	            
	            isoMsg.set(3, req.getBit3());
	            isoMsg.set(7, req.getBit7());
	            isoMsg.set(11,req.getBit11());
	
	            isoMsg.set(24,req.getBit24());
	           
	            printISOMessage(isoMsg);

	            byte[] result = isoMsg.pack();
	            
	            return new String(result);
	        } catch (ISOException e) {
	            throw new Exception(e);
	        }
	    }
	 
	 
	 public ResponsesISO buildResponseISOMessage(NetworkManagementReq req) throws Exception {
	        try {
	          
	        	
	            InputStream is = getClass().getResourceAsStream("/fields.xml");
	            GenericPackager packager = new GenericPackager(is);
	            
	            System.out.println("Outgoing");
	            ISOMsg isoMsg = new ISOMsg();
	            isoMsg.setPackager(packager);
	            isoMsg.setMTI("0810");

	            
	            isoMsg.set(3, req.getBit3());
	            isoMsg.set(7, req.getBit7());
	            isoMsg.set(11,req.getBit11());
	
	            isoMsg.set(24,req.getBit24());
	            isoMsg.set(39,"00");
	           
	            printISOMessage(isoMsg);

	            byte[] result = isoMsg.pack();
	            
	            ResponsesISO nmRes=new ResponsesISO();
	            nmRes.setRawMessage(new String(result));
	            nmRes.setResponCode("00");
	            return nmRes ;
	        } catch (ISOException e) {
	            throw new Exception(e);
	        }
	    }
	 
	 

	    public void printISOMessage(ISOMsg isoMsg) {
	        try {
	            System.out.printf("MTI = %s%n", isoMsg.getMTI());
	            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
	                if (isoMsg.hasField(i)) {
	                    System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
	                }
	            }
	        } catch (ISOException e) {
	            e.printStackTrace();
	        }
	    }
	    

	    
	    public Map<String,String> unpackISO8583(String msg) throws ISOException {
	
	    	   GenericPackager packager = new GenericPackager("src/main/resources/fields.xml");
	    	   
	           // Setting packager
	           ISOMsg isoMsg = new ISOMsg();
	           isoMsg.setPackager(packager);
	    
	           // this is ISO8583 Message that we will Unpack
	           String isoMessage = msg;
	    
	           // first, we must convert ISO8583 Message String to byte[]
	           byte[] bIsoMessage = new byte[isoMessage.length()];
	           for (int i = 0; i < bIsoMessage.length; i++) {
	               bIsoMessage[i] = (byte) (int) isoMessage.charAt(i);
	           }
	    
	           // second, we unpack the message
	           isoMsg.unpack(bIsoMessage);     
	           Map<String,String> map=new HashMap<String,String>();   
	           // last, print the unpacked ISO8583
	           map.put("RawMessage", isoMessage);
	           map.put("MTI",isoMsg.getMTI());
	           System.out.println("MTI22="+isoMsg.getMTI() );  
	        	
	           for(int i=1; i<=isoMsg.getMaxField(); i++){
	               if(isoMsg.hasField(i)) {
	            	   map.put("bit"+i,isoMsg.getString(i));
	                   System.out.println(i+"="+isoMsg.getString(i));
	               } 
	               
	            }
			return map;
	       }
	

	    }

