package com.project.shared_calender.common.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.exception.ResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Util {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    @Value("${cloud.aws.s3.public-domain}")
    private String publicDomain;
    private final AmazonS3 client;


    public String uploadFile(MultipartFile multipartFile){
        SimpleDateFormat directoryDateFormat = new SimpleDateFormat("yyyyMMdd", new Locale("kr", "KR"));
        SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", new Locale("kr", "KR"));
        String directoryPath = directoryDateFormat.format(new Date());
        String filePrefix = fileDateFormat.format(new Date());
        String filePath = directoryPath +
                "/" +
                filePrefix +
                multipartFile.getOriginalFilename();
        return sendFileToImageStorage(multipartFile, filePath);
    }

    private String sendFileToImageStorage(MultipartFile file, String filePath){

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        try(InputStream inputStream = file.getInputStream()){
            client.putObject(new PutObjectRequest(bucketName,filePath, inputStream, objectMetadata));
        } catch (IOException e){
            throw new ResponseException(ResponseCode.INVALID_REQUEST);
        }
        return publicDomain + filePath;
    }


}
