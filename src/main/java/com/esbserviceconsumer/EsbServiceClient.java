package com.esbserviceconsumer;
import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.esbbank.gbo.xml.schemas.v1_0.ESBServiceReply;
import com.esbbank.gbo.xml.schemas.v1_0.ESBServiceRequest;
import com.esbbank.gbo.xml.schemas.v1_0.ObjectFactory;

public class EsbServiceClient extends WebServiceGatewaySupport{
	private static final Logger log = LoggerFactory.getLogger(EsbServiceClient.class);
	protected ESBServiceRequest request;
	protected String Amount;
	protected String commitmentTerm;
	protected String vRimNo;
	protected String vURL;
	protected String vPORT;
	protected String vServiceName;

	public String getvPORT() {
		return vPORT;
	}
	public void setvPORT(String vPORT) {
		this.vPORT = vPORT;
	}
	public String getvServiceName() {
		return vServiceName;
	}
	public void setvServiceName(String vServiceName) {
		this.vServiceName = vServiceName;
	}
	public String getvURL() {
		return vURL;
	}
	public void setvURL(String vURL) {
		this.vURL = vURL;
	}
	public JAXBElement<ESBServiceReply> response() {
		ObjectFactory obj = new ObjectFactory();
		ESBServiceRequest request =new ESBServiceRequest();
		
		request.setClientChannel("CASH");
		request.setServiceName("LIMIT.MAINTENANCE");
		request.setUserId("CASH");
		request.setPassword("XXXX");
		request.setReferenceNum("545MAR0903202222");
		request.setData("<![CDATA[<tns:maintainCommitmentRequest xsi:schemaLocation=\"urn:esbbank.com/gbo/xml/schemas/v1_0/LimitMaintenance.xsd \" xmlns:tns=\"urn:esbbank.com/gbo/xml/schemas/v1_0/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"+
       "<eAI_HEADER>"
        +"<serviceName>LIMIT.MAINTENANCE</serviceName>"
        +"<serviceType>SYNC</serviceType>"
        +"<serviceVersion>1.1</serviceVersion>"
        +"<client>ADIB</client>"
        +"<clientChannel>CASH</clientChannel>"
        +"<msgChannel>MQ</msgChannel>"
        +"<requestorLanguage>E</requestorLanguage>"
        +"<securityInfo>"
          +" <authentication>"
            +"  <UserId>CASH</UserId>"
              +"<Password>XXXXXXXXXXXX</Password>"
           +"</authentication>"
        +"</securityInfo>"
        +"<returnCode>0000</returnCode>"
    +" </eAI_HEADER>"
     +"<eAI_BODY>"
       +" <referenceNum>545MAR0903202222</referenceNum>"
        +"<branchCode>1</branchCode>"
        +"<maintainCommitment>"
         +"  <operation>C</operation>"
          +" <commitmentType>CM</commitmentType>"
           +"<classCode>111</classCode>"
          +" <branchNo>1</branchNo>"
           +"<description>Test Algo2</description>"
           +"<amount>"+this.Amount+"</amount>"
           +"<commitmentTerm>"+this.commitmentTerm+"</commitmentTerm>"
           +"<commitmentTermUnit>Year(s)</commitmentTermUnit>"
           +"<rimNo>"+this.vRimNo+"</rimNo>"
          +" <advanceFlag>Y</advanceFlag>"
        +"</maintainCommitment>"
        +"<requestTime>11111111111111111111</requestTime>"
     +"</eAI_BODY>"
  +"</tns:maintainCommitmentRequest>]]>");
		//1235613
		JAXBElement<ESBServiceRequest> req = obj.createEsbServiceRequest(request);
	/*	JAXBElement<ESBServiceReply> response = (JAXBElement<ESBServiceReply>) getWebServiceTemplate()
				.marshalSendAndReceive("http://"+this.vURL+":"+this.vPORT+"/ESBServices/ESBService_1", req,
				new SoapActionCallback("urn:esbbank.com/gbo/xml/schemas/v1_0/ESBService_1"));
	*/	
		if(this.vURL =="" || this.vURL==null) {
			this.vURL="localhost";
		}
		if(this.vPORT =="" || this.vPORT==null) {
			this.vPORT="8080";
		}
		if(this.vServiceName =="" || this.vServiceName==null) {
			this.vServiceName="ESBServices";
		}
		JAXBElement<ESBServiceReply> response = (JAXBElement<ESBServiceReply>) getWebServiceTemplate()
				.marshalSendAndReceive("http://"+this.vURL+":"+this.vPORT+"/"+this.vServiceName+"/ESBService_1", req,
				new SoapActionCallback("urn:esbbank.com/gbo/xml/schemas/v1_0/ESBService_1"));
		//log.info("RESPONSE: " + response.getValue().getData());
		return response;
		
	}
	public String getCommitmentTerm() {
		return commitmentTerm;
	}

	public void setCommitmentTerm(String commitmentTerm) {
		this.commitmentTerm = commitmentTerm;
	}

	public String getvRimNo() {
		return vRimNo;
	}

	public void setvRimNo(String vRimNo) {
		this.vRimNo = vRimNo;
	}

	
	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}
}
