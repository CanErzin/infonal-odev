package com.erzin.core.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * Java Based Configuration Class For Spring.
 * This class also imports bean definitions from
 * applicationContext.xml for neccessary definitions of MongoDb.
 *
 */
@Configuration
@ComponentScan(basePackages = "com.erzin.core")
@ImportResource({"classpath:applicationContext.xml"})
public class ApplicationConfig {

}
