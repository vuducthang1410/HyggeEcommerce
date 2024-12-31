package org.vdt.hyggeecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HyggeEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyggeEcommerceApplication.class, args);
    }

}
