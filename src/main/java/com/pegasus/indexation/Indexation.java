package com.pegasus.indexation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author hongyu.ma
 * 
 * <pre>Spring Boot main entrée, @SpringBootApplication,
 *  Scanning :
 *  @Controller
 *  @Service
 *  @Repository
 *  @Conponent
 *  </pre>
 */
@SpringBootApplication
public class Indexation {
    public static void main( String[] args ){
        SpringApplication.run(Indexation.class, args);
    }
}

