package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.customexception.CustomAppException;
import com.example.demo.dao.DataHandlerDao;

@RestController
@RequestMapping(value = "file")
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Value("${file.upload-dir}")
	private String upload_folder;
	
	@Value("${file.file-name}")
	private String fileName;
	
	@Value("${file.file-name-extn}")
	private String fileNameExtn;
	
	@Autowired
	private DataHandlerDao dataHandlerDao;

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public void readTheNumberOfLinesFromAFile(@RequestParam(required = true, value = "file") MultipartFile inputFile) throws IOException {
		logger.info("Input file name:: " + inputFile.getOriginalFilename());
		logger.info("File Content Type:: " +inputFile.getContentType());
		try {
			saveUploadedFiles(inputFile);
			dataHandlerDao.saveTheFileToDataBase(inputFile);
			} 
		catch (CustomAppException e) {
			logger.error(e.getMessage());
		}

	}

	private void saveUploadedFiles(MultipartFile file) throws IOException, CustomAppException {

	
		Date date = new Date();
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-YY-HH-mm-SS");
		String fileNameAppender=formatter.format(date);
		logger.debug(fileNameAppender);
		byte[] bytes = file.getBytes();
		logger.info("file byte content:: "+bytes);
		Path path = Paths.get(upload_folder, fileName+"_"+fileNameAppender+fileNameExtn);
		Files.write(path, bytes);
	}

}
