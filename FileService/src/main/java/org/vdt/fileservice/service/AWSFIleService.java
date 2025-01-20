package org.vdt.fileservice.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.multipart.MultipartFile;

@DubboService
public class AWSFIleService{
    public String saveFile(MultipartFile file, String directory) {
        return "heheheheh";
    }
}
