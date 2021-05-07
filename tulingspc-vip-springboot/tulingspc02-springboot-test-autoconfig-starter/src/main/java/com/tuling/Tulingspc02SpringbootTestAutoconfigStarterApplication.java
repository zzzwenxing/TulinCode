package com.tuling;

import com.tuling.core.ITulingRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Tulingspc02SpringbootTestAutoconfigStarterApplication {

	@Autowired
	private ITulingRedis tulingRedis;

	public static void main(String[] args) {
		SpringApplication.run(Tulingspc02SpringbootTestAutoconfigStarterApplication.class, args);
	}

	@RequestMapping("/testAutoCfgStater")
	public String testAutoCfgStarter() {
		tulingRedis.set("smsbslz","司马是不是老贼");
		return tulingRedis.get("smsbslz");
	}

}
