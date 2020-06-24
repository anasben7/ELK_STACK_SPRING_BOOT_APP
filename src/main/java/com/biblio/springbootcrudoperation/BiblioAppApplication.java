package com.biblio.springbootcrudoperation;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories({"com.biblio.springbootcrudoperation.repository"})
@EnableElasticsearchRepositories({"com.biblio.springbootcrudoperation.elasticRepository"})

public class BiblioAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioAppApplication.class, args);
	}
	Runtime rt = Runtime.getRuntime();
	@Scheduled(fixedDelayString="PT5M")
	void logstach() throws InterruptedException, IOException{
		// Unix
		Process proc = rt.exec("/Users/pro/Downloads/logstash-7.8.0/bin/logstash -f /Users/pro/Documents/books.conf");
		//Windows
		// Process proc = rt.exec("cmd /c start C:/Users/Path/to/logstach.bat -f /path/to/books.conf");
		
		
	}
	
@Configuration
@EnableScheduling
@ConditionalOnProperty(name="scheduling.enabled",matchIfMissing=true)
 class SchedulingConfiguration {
	
}
	
	
	
	

}
