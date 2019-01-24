package io.spring.microservices.javarestclient;

import io.spring.microservices.javarestclient.model.StatusResponse;
import io.spring.microservices.javarestclient.services.SSRestClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaRestClientApplicationTests {

	@Autowired
	private SSRestClientService ssRestClientService;



	@Test
	public void contextLoads() {
	}

	@Test
	public void ssRequestServiceTest(){
		Map<String, String> headers = new HashMap<>();
		headers.put("unit", Integer.toString(164));
		headers.put("key", "1b8ec7a9acda6737cbd036870dd017a1");
		StatusResponse statusResponse = ssRestClientService.getStatusResponse("page=1", "https://api-upsim-get.herokuapp.com/SS/listAll", headers);

		System.out.println("Test Request...");
		System.out.println(statusResponse.toString());
	}

}

