package id.co.prismalink.ISO8583.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import id.co.prismalink.ISO8583.res.PurchaseReq;
import id.co.prismalink.ISO8583.res.ResponsesISO;
import id.co.prismalink.ISO8583.service.NetworkManagementService;
import id.co.prismalink.ISO8583.service.PurchaseService;
@RestController
public class PurchaseController {
	
	private static final String DATE_FORMAT_BIT_7 = "MMddHHmmss";
    private static final String DATE_FORMAT_BIT_12 = "HHmmss";
    private static final String DATE_FORMAT_BIT_13 = "MMdd";
    private static final String DATE_FORMAT_BIT_14 = "YYMM";
    
    @RequestMapping(value="/purchase-with-otp", method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public  Map<String,String> purchaseWithOtp(@RequestBody PurchaseReq req) throws Exception  {
		 
		
		
		LocalDateTime sekarang = LocalDateTime.now();
		req.setBit3("000000");
		req.setBit7(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_7)));
		req.setBit12(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_12)));
		req.setBit13(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_13)));
		
		PurchaseService pcIso= new PurchaseService();
		pcIso.buildRequestISOMessage(req);
		 
		ResponsesISO pcRes=pcIso.buildResponseISOMessage(req);
		NetworkManagementService nmIso= new NetworkManagementService();
		Map<String,String> res=nmIso.unpackISO8583(pcRes.getRawMessage());
		 
	
		 
		return res;
	}
    
    @RequestMapping(value="/purchase-without-otp", method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public Map<String,String> otpGenerate(@RequestBody PurchaseReq req) throws Exception  {
		 
		
		
		LocalDateTime sekarang = LocalDateTime.now();
		req.setBit3("000001");
		req.setBit7(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_7)));
		req.setBit12(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_12)));
		req.setBit13(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_13)));
		
		PurchaseService pcIso= new PurchaseService();
		pcIso.buildRequestISOMessage(req);
		 
		ResponsesISO pcRes=pcIso.buildResponseISOMessage(req);
		NetworkManagementService nmIso= new NetworkManagementService();
		Map<String,String> res=nmIso.unpackISO8583(pcRes.getRawMessage());
		
		 
	
		 
		return res;
	}
}
