package com.huuphuoc.api.common.service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudUpdateFileService {

    private final Cloudinary cloudinary;

    public CloudUpdateFileService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    public Map uploadFile(MultipartFile file ) throws IOException {
        // The file is passed in bytes to the upload method.

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        return  uploadResult;
    }
}
