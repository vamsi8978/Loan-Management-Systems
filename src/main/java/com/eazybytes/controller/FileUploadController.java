package com.eazybytes.controller;
/*
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.eazybytes.service.FileUploadService;
import com.eazybytes.service.CustomerFileUploadService;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private CustomerFileUploadService customerFileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileUploadService.uploadFile(file);

            // Insert record into customer_file_uploads table
           // int customerId = getCustomerId(); // Get customer ID here
           // String fileExtension = getFileExtension(file);
            //String fileName = file.getOriginalFilename();
           // customerFileUploadService.logFileUpload(customerId, fileExtension, fileName);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
}
*/
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.eazybytes.service.FileUploadService;
import com.eazybytes.service.CustomerFileUploadService;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private CustomerFileUploadService customerFileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(@RequestParam("file") MultipartFile[] files) {
        try {
            System.out.println("Received " + files.length + " files");

            List<String> uploadedFileNames = new ArrayList<>();

            for (MultipartFile file : files) {
                System.out.println("Processing file: " + file.getOriginalFilename());
                fileUploadService.uploadFile(file);
                uploadedFileNames.add(file.getOriginalFilename());

                // Uncomment the following lines if you want to insert records into the customer_file_uploads table for each uploaded file
                // int customerId = getCustomerId(); // Get customer ID here
                // String fileExtension = getFileExtension(file);
                // String fileName = file.getOriginalFilename();
                // customerFileUploadService.logFileUpload(customerId, fileExtension, fileName);
            }

            String responseMessage = "Uploaded files: " + String.join(", ", uploadedFileNames);
            System.out.println(responseMessage);
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files");
        }
    }
}
