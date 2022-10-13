package com.example.kafka.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.kafka.service.Producer;

@RestController
@RequestMapping("/test")
public class DemoController {
	
	@Autowired
	Producer producer;
	@PostMapping("/uploadFile")
	public ResponseEntity<String> test(@RequestParam("file") MultipartFile myFile) throws IOException
	{
		String response="";
		try {
			response=producer.sendMessageToTopic(myFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(response,HttpStatus.OK);

	}

}
