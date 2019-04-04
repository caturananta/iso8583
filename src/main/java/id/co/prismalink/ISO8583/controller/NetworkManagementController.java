package id.co.prismalink.ISO8583.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import id.co.prismalink.ISO8583.res.NetworkManagementReq;
import id.co.prismalink.ISO8583.res.ResponsesISO;
import id.co.prismalink.ISO8583.service.NetworkManagementService;


@RestController
public class NetworkManagementController {
	
	private static final String DATE_FORMAT_BIT_7 = "MMddHHmmss";
    private static final String DATE_FORMAT_BIT_12 = "HHmmss";
    private static final String DATE_FORMAT_BIT_13 = "MMdd";
    
	
	@RequestMapping(value="/network-management", method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public Map<String,String> otpGenerate(@RequestBody NetworkManagementReq req) throws Exception  {
		 
		
		
		LocalDateTime sekarang = LocalDateTime.now();
		req.setBit7(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_7)));
		
		
		NetworkManagementService nmIso= new NetworkManagementService();
		nmIso.buildRequestISOMessage(req);
		
		ResponsesISO nmRes=nmIso.buildResponseISOMessage(req);
		
		Map<String,String> res=nmIso.unpackISO8583(nmRes.getRawMessage());
	
		 
		return res;
	}
}
