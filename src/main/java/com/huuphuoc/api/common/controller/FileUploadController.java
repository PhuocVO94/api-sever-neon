package com.huuphuoc.api.common.controller;


import com.huuphuoc.api.common.service.CloudUpdateFileService;
import com.huuphuoc.api.common.utils.ResponseUtility;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/upload")
public class FileUploadController {
    public  final CloudUpdateFileService cloudUpdateFileService;
    public  final  ResponseUtility responseUtility;

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object uploadFile( @RequestParam("file") MultipartFile file) throws IOException {

        try {
            Map resultUrl = cloudUpdateFileService.uploadFile(file);

            String imgString =(String) resultUrl.get("secure_url");


            return responseUtility.Get(imgString,HttpStatus.OK);


        } catch (Exception e) {

            return responseUtility.Get(e.getMessage(),HttpStatus.BAD_REQUEST);
        }




    }


}
