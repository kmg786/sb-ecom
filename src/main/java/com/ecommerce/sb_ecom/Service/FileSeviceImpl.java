package com.ecommerce.sb_ecom.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileSeviceImpl implements FileService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

//        file name of current/original file
        String originalFileName = file.getOriginalFilename();
//        generate unique file name using UUID
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFileName
                .substring(originalFileName.lastIndexOf('.')));
        String filePath = path + File.separator + fileName;
//        check if path exists and create
        File folder = new File(path);
        if (!folder.exists())
            folder.mkdir();
//        upload to server
        Files.copy(file.getInputStream(), Paths.get(filePath));
//        return filename
        return fileName;
    }

}
