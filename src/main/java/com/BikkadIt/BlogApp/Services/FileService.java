package com.BikkadIt.BlogApp.Services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	
	
	InputStream getresource(String path , String fileName) throws FileNotFoundException;

	String uploadImage(String path, MultipartFile image) throws IOException;
	
	

}
