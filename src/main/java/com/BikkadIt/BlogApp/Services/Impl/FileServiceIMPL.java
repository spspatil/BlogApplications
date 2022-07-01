package com.BikkadIt.BlogApp.Services.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.BikkadIt.BlogApp.Services.FileService;

@Service
public class FileServiceIMPL implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		String name=  file.getOriginalFilename();
		
		String randomId =UUID.randomUUID().toString();
		
		String fileName1=randomId.concat(name.substring(name.lastIndexOf(".")));
		
		
		String filePath=path + File.separator +fileName1;
		
		
		
		File f1=new File(path);
		
		if(!f1.exists()) {
			f1.mkdir();
		}
		
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName1;
		
	}

	@Override
	public InputStream getresource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		String fullPath= path +File.separator +fileName;
		
		InputStream is= new FileInputStream(fullPath);
		
		return is;
	}

}
