package id.co.prismalink.ISO8583;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class Iso8583Application {

	public static void main(String[] args) {
		SpringApplication.run(Iso8583Application.class, args);
	}

}
