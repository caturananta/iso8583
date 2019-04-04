package id.co.prismalink.ISO8583.res;

public class NetworkManagementRes {

	private String messageTypeId;
	private String processingCode;
	private String transmissionDateAndTime;
	private String systemTraceAuditNumber;
	private String networkInternationalIdentifier;
	private String responseCode;
	
	public String getMessageTypeId() {
		return messageTypeId;
	}
	public void setMessageTypeId(String messageTypeId) {
		this.messageTypeId = messageTypeId;
	}
	public String getProcessingCode() {
		return processingCode;
	}
	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}
	public String getTransmissionDateAndTime() {
		return transmissionDateAndTime;
	}
	public void setTransmissionDateAndTime(String transmissionDateAndTime) {
		this.transmissionDateAndTime = transmissionDateAndTime;
	}
	public String getSystemTraceAuditNumber() {
		return systemTraceAuditNumber;
	}
	public void setSystemTraceAuditNumber(String systemTraceAuditNumber) {
		this.systemTraceAuditNumber = systemTraceAuditNumber;
	}
	public String getNetworkInternationalIdentifier() {
		return networkInternationalIdentifier;
	}
	public void setNetworkInternationalIdentifier(String networkInternationalIdentifier) {
		this.networkInternationalIdentifier = networkInternationalIdentifier;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	
}
