package com.example.springdemo.controller;

import com.example.springdemo.entity.UploadFileDTO;
import com.example.springdemo.entity.UploadResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Log4j2
public class upDownController {
    @Value("${upload.path}")
    private String uploadPath;
    @Operation(summary = "Upload POST", description = "POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes =
    MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
        log.info(uploadFileDTO);
        final List<UploadResultDTO> list = new ArrayList<>();

        if (uploadFileDTO.getFiles() != null) {
            uploadFileDTO.getFiles().forEach(file -> {
                String originalFilename = file.getOriginalFilename();
                log.info(originalFilename);

                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" + originalFilename);
                boolean image = false;
                try {
                    file.transferTo(savePath); //파일저장

                    if (Files.probeContentType(savePath).startsWith("image")) {
                        image = true;
                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalFilename);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                } catch (IOException e) {
                    log.info(e.getMessage());
                }
                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .fileName(originalFilename)
                        .img(image).build()
                );
            }); //end each
            return list;
        }// end if
        return null;
    }

    @Operation(summary = "파일 보여주기", description = "GET 방식으로 첨부파일 조회")
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFile(@PathVariable String fileName) {
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @Operation(summary = "파일 삭제하기", description = "DELETE 방식으로 파일 삭제")
    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName) {
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
        Map<String, Boolean> map = new HashMap<>();
        boolean removed = false;

        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete(); //원폰 파일 삭제

            if (contentType.startsWith("image")) {
                File thumbFile = new File(uploadPath+File.separator+"s_"+fileName);
                thumbFile.delete(); //썸네일 삭제
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        map.put("result", removed);
        return map;
    }
//    public String upload(UploadFileDTO uploadFileDTO) {
//        log.info(uploadFileDTO);
//        if (uploadFileDTO.getFiles() != null) {
//            uploadFileDTO.getFiles().forEach(file -> {
//                String originalFilename = file.getOriginalFilename();
//                log.info(originalFilename);
//
//                String uuid = UUID.randomUUID().toString();
//                Path savePath = Paths.get(uploadPath, uuid+"_"+originalFilename);
//                boolean image = false;
//                try {
//                    file.transferTo(savePath); // 파일 저장
//
//                    if (Files.probeContentType(savePath).startsWith("image")) {
//                        image = true;
//                        File thumbFile = new File(uploadPath, "s_" + uuid+"_"+originalFilename);
//                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200,200);
//                    }
//                } catch (IOException e) {
//                    log.info(e.getMessage());
//                }
//            });
//        }
//        return "파일 업로드";
//    }
}
