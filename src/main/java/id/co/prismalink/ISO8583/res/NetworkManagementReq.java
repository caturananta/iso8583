package id.co.prismalink.ISO8583.res;

public class NetworkManagementReq {
	
//	private String messageType;
//	private String processingCode;
//	private String transmissionDateAndTime;
//	private String systemTraceAuditNumber;
//	private String networkInternationalIdentifier;
	

	private String bit3;
	private String bit7;
	private String bit11;
	private String bit24;
	
	public String getBit3() {
		return bit3;
	}
	public void setBit3(String bit3) {
		this.bit3 = bit3;
	}
	public String getBit7() {
		return bit7;
	}
	public void setBit7(String bit7) {
		this.bit7 = bit7;
	}
	public String getBit11() {
		return bit11;
	}
	public void setBit11(String bit11) {
		this.bit11 = bit11;
	}
	public String getBit24() {
		return bit24;
	}
	public void setBit24(String bit24) {
		this.bit24 = bit24;
	}
	
	
}
