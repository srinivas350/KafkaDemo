package com.example.kafka.service;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Producer {
	@Autowired
	KafkaTemplate<String, String> template;
	@Autowired
	ServletContext context; 

	public String sendMessageToTopic(MultipartFile myFile) throws IOException
	{
		context.setAttribute("fileName", myFile.getOriginalFilename());
		if (!myFile.isEmpty()) {
	        try {
	            byte[] bytes = myFile.getBytes();
	            String completeData = new String(bytes);
	            ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic-1",completeData);
	
	            //send data
	            template.send(record);
	            return "Data Sent to Kafka Topic"; 
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
		}
		return null;
	}

}
