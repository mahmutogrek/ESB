package com.esbserviceconsumer;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.esbbank.gbo.xml.schemas.v1_0.ESBServiceReply;

@SpringBootApplication
public class EsbServiceConsumerApplication {
	private static final Logger log = LoggerFactory.getLogger(EsbServiceConsumerApplication.class);
	public static String Response;

	public static void main(String[] args) {
		SpringApplication.run(EsbServiceConsumerApplication.class, args);
		log.info("RESPONSE: " + Response);

	}

	@Bean
	CommandLineRunner lookup(EsbServiceClient quoteClient) {
		return args -> {
			String vRimNo = "1235613";
			String vCommitmentTerm = "1";
			String vAmount = "100";
			if (args.length == 1) {
				quoteClient.setvURL(args[0]);
			} else if (args.length == 2) {
				quoteClient.setvPORT(args[1]);
			} else if (args.length == 3) {
				quoteClient.setvServiceName(args[2]);
			} else if (args.length == 4) {
				vAmount = args[3];
			} else if (args.length == 5) {
				vCommitmentTerm = args[4];
			} else if (args.length == 6) {
				vRimNo = args[5];
			}
			quoteClient.setvRimNo(vRimNo);
			quoteClient.setCommitmentTerm(vCommitmentTerm);
			quoteClient.setAmount(vAmount);
			JAXBElement<ESBServiceReply> response = quoteClient.response();
			this.Response = response.getValue().getData();
		};

	}
}
