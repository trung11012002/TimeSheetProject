package com.timesheet.timesheetproject.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    void init();
    String store(MultipartFile file,String username);

    public Resource loadAsResource(String filename);
    void deleteAll();
//    void delete(Long id);
}
