package com.example.kafka.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
	@Value("${fileUploadDir}")
	private String fileUploadDir;
	@Autowired
	ServletContext context; 
	
	@KafkaListener(topics="topic-1",groupId="mygroup")
	public void recieveMessageFromTopic(String myData) throws IOException
	{
		File newFile=new File(fileUploadDir+context.getAttribute("fileName"));
		if(newFile.length()==0)
		{
			BufferedWriter bout = new BufferedWriter(new FileWriter(newFile, true));
			bout.write(myData);
			bout.flush();
			bout.close();
		}
		
	}

}
