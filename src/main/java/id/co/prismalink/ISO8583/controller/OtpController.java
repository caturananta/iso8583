package id.co.prismalink.ISO8583.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import id.co.prismalink.ISO8583.res.OtpReq;
import id.co.prismalink.ISO8583.res.ResponsesISO;
import id.co.prismalink.ISO8583.service.NetworkManagementService;
import id.co.prismalink.ISO8583.service.OtpService;

@RestController
public class OtpController {
	private static final String DATE_FORMAT_BIT_7 = "MMddHHmmss";
    private static final String DATE_FORMAT_BIT_12 = "HHmmss";
    private static final String DATE_FORMAT_BIT_13 = "MMdd";
    private static final String DATE_FORMAT_BIT_14 = "YYMM";
    
    @RequestMapping(value="/otp-generate", method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public Map<String,String> otpGenerate(@RequestBody OtpReq req) throws Exception  {
		 
		
		
		LocalDateTime sekarang = LocalDateTime.now();
		req.setBit3("600000");
		req.setBit7(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_7)));
		req.setBit12(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_12)));
		req.setBit13(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_13)));
		
		OtpService ogIso= new OtpService();
		ogIso.buildRequestISOMessage(req);
		 
		ResponsesISO ogRes=ogIso.buildResponseMaskISOMessage(req);
		NetworkManagementService nmIso= new NetworkManagementService();
		Map<String,String> res=nmIso.unpackISO8583(ogRes.getRawMessage());
	
		 
		return res;
	}
    
    @RequestMapping(value="/otp-validate/card-registration", method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public Map<String,String> otpValidateCardBinding(@RequestBody OtpReq req) throws Exception  {
		 
		
		
		LocalDateTime sekarang = LocalDateTime.now();
		req.setBit3("610000");
		req.setBit7(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_7)));
		req.setBit12(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_12)));
		req.setBit13(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_13)));
		
		OtpService ogIso= new OtpService();
		ogIso.buildRequestISOMessage(req);
		 
		ResponsesISO ogRes=ogIso.buildResponseISOMessage(req);
		NetworkManagementService nmIso= new NetworkManagementService();
		Map<String,String> res=nmIso.unpackISO8583(ogRes.getRawMessage());
	
		 
	
		 
		return res;
	}
    @RequestMapping(value="/otp-validate/transaction", method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public Map<String,String> otpValidateTransaction(@RequestBody OtpReq req) throws Exception  {
		 
		
		
		LocalDateTime sekarang = LocalDateTime.now();
		req.setBit3("620000");
		req.setBit7(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_7)));
		req.setBit12(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_12)));
		req.setBit13(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_13)));
		
		OtpService ogIso= new OtpService();
		ogIso.buildRequestISOMessage(req);
		
		ResponsesISO ogRes=ogIso.buildResponseISOMessage(req);
		NetworkManagementService nmIso= new NetworkManagementService();
		Map<String,String> res=nmIso.unpackISO8583(ogRes.getRawMessage());
	
		 
	
		 
		return res;
	}
}
