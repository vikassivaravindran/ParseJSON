package com.accenture.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@RestController
public class JacksonAPI {
	
	@RequestMapping(value = "/getJSON", method = RequestMethod.GET , produces="application/json")
	public Object readJsonFile(String args[]) throws IOException {
		File f = new File("C:\\Users\\vikas.sivaravindran\\Desktop\\youtube.json");
		String path = "$.widget.image.name";
		String value = "Vikas S R A";
		DocumentContext context = JsonPath.parse(f);
		context.set(path,value);
		
		ObjectMapper mapper = new ObjectMapper();
		Object jsonValue = mapper.readValue(context.jsonString(),Object.class);
		String Data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonValue);
		FileOutputStream fs = new FileOutputStream(f);
		fs.write(Data.getBytes());
		fs.close();
		Object response=mapper.readValue(f, Object.class); // readValue converts Json to Java Object 
		return  response;
	}
	
}
