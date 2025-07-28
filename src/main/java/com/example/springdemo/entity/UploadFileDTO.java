package com.example.springdemo.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadFileDTO {
    private List<MultipartFile> files;
}
