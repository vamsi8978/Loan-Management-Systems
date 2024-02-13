package com.eazybytes.service;

import com.eazybytes.controller.FileUploadController;
import com.eazybytes.model.FileEntity;
import com.eazybytes.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

import java.io.IOException;

@Service
public class FileUploadService {

    private final FileRepository fileRepository;


    public FileUploadService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

   /* @Transactional
    public Boolean uploadFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileRepository.save(fileEntity);
        return true;
    }*/
    @Transactional
    public Boolean uploadFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setData(file.getBytes());

        // Set the upload date and time
        fileEntity.setUploadDateTime(new Date());

        fileRepository.save(fileEntity);
        return true;
    }

}