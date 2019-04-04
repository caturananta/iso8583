package id.co.prismalink.ISO8583.service;

import java.io.InputStream;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

import id.co.prismalink.ISO8583.res.OtpReq;
import id.co.prismalink.ISO8583.res.PurchaseReq;
import id.co.prismalink.ISO8583.res.ResponsesISO;

public class PurchaseService {

	
	public String buildRequestISOMessage(PurchaseReq req) throws Exception {
        try {
        
            InputStream is = getClass().getResourceAsStream("/fields.xml");
            GenericPackager packager = new GenericPackager(is);
            System.out.println("incoming");
           
            
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);
            isoMsg.setMTI("0200");

            isoMsg.set(2,req.getBit2());
            isoMsg.set(3,req.getBit3());
            isoMsg.set(4,req.getBit4());
            isoMsg.set(7,req.getBit7());
            isoMsg.set(11,req.getBit11());
            isoMsg.set(12,req.getBit12());
            isoMsg.set(13,req.getBit13());
            isoMsg.set(14,req.getBit14());
            isoMsg.set(18,req.getBit18());
            isoMsg.set(22,req.getBit22());
            isoMsg.set(24,req.getBit24());
            isoMsg.set(25,req.getBit25());
            isoMsg.set(35,req.getBit35());
            isoMsg.set(37,req.getBit37());
            isoMsg.set(41,req.getBit41());
            isoMsg.set(42,req.getBit42());
            isoMsg.set(43,req.getBit43());
            isoMsg.set(48,req.getBit48());
            isoMsg.set(49,req.getBit49());
            isoMsg.set(60,req.getBit60());
            isoMsg.set(62,req.getBit62());
            printISOMessage(isoMsg);

            byte[] result = isoMsg.pack();
            
            return new String(result);
        } catch (ISOException e) {
            throw new Exception(e);
        }
    }
	
	public ResponsesISO buildResponseISOMessage(PurchaseReq req) throws Exception {
        try {
          
        	
            InputStream is = getClass().getResourceAsStream("/fields.xml");
            GenericPackager packager = new GenericPackager(is);
            
            System.out.println("Outgoing");
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);
            isoMsg.setMTI("0210");

           
            isoMsg.set(3,req.getBit3());
            isoMsg.set(4,req.getBit4());
            isoMsg.set(7, req.getBit7());
            isoMsg.set(11, req.getBit11());
            isoMsg.set(12,req.getBit12());
            isoMsg.set(13,req.getBit13());
            isoMsg.set(24,req.getBit24());
            isoMsg.set(37,req.getBit37());
            isoMsg.set(38,"sa2657");
            isoMsg.set(39,"00");
            isoMsg.set(41,req.getBit41());
            
            isoMsg.set(48,"");
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
}
