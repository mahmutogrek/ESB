package com.esbserviceconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;



@Configuration
public class EsbServiceConfiguration {
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.esbbank.gbo.xml.schemas.v1_0");
		return marshaller;
	}

	@Bean
	public EsbServiceClient esbClient(Jaxb2Marshaller marshaller) {
		EsbServiceClient client = new EsbServiceClient();
		client.setDefaultUri("http://localhost:8080/ESBServices");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
