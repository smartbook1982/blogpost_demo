package com.blog.post.demo;

import com.blog.post.demo.processor.BlogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Component
	class BlogPostDemoStarter {

		@Autowired
		BlogManager manager;

		@PostConstruct public void startApp ()
		{
			manager.start();
		}
	}

}
