package com.example.springdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UploadResultDTO {
    private String uuid;
    private String fileName;
    private boolean img;
    public String getLink() {
        if (img) {
            return "s_"+uuid+"_"+fileName; //이미지인 경우 썸네일
        } else {
            return uuid+"_"+fileName;
        }
    }
}
