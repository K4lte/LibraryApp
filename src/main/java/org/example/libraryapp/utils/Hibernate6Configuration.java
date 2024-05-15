package org.example.libraryapp.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ImportResource({"classpath:hibernate6Configuration.xml"})
public class Hibernate6Configuration {

}
