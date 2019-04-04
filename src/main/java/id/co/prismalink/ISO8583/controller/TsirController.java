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
import id.co.prismalink.ISO8583.res.TransactionStatusInquiryReq;
import id.co.prismalink.ISO8583.service.NetworkManagementService;
import id.co.prismalink.ISO8583.service.PurchaseService;
import id.co.prismalink.ISO8583.service.TransactionStatusInquiryService;

@RestController
public class TsirController {
	
	private static final String DATE_FORMAT_BIT_7 = "MMddHHmmss";
    private static final String DATE_FORMAT_BIT_12 = "HHmmss";
    private static final String DATE_FORMAT_BIT_13 = "MMdd";
    private static final String DATE_FORMAT_BIT_14 = "YYMM";
    
    @RequestMapping(value="/transaction-status-inquiry", method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public Map<String,String> transactionStatus(@RequestBody TransactionStatusInquiryReq req) throws Exception  {
		 
		
		
		LocalDateTime sekarang = LocalDateTime.now();
		req.setBit3("700000");
		req.setBit7(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_7)));
		req.setBit12(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_12)));
		req.setBit13(sekarang.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BIT_13)));
		
		TransactionStatusInquiryService tsiIso= new TransactionStatusInquiryService();
		tsiIso.buildRequestISOMessage(req);
		 
		ResponsesISO tsiRes=tsiIso.buildResponseISOMessage(req);
		NetworkManagementService nmIso= new NetworkManagementService();
		Map<String,String> res=nmIso.unpackISO8583(tsiRes.getRawMessage());
		 
	
		 
		return res;
	}
}
