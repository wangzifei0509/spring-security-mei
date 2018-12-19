package com.wang.web.controller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/24 20:40
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping
    public String upload(MultipartFile file){
        System.out.println(file.getOriginalFilename());
        String fileName = RandomStringUtils.randomNumeric(8);
        File dest = new File("d://"+fileName+".txt");
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @GetMapping("/{id}")
    public void upload(@PathVariable String id, HttpServletResponse resp) {
        try (
                FileInputStream inputStream = new FileInputStream(new File("d://" + id + ".txt"));
                OutputStream outputStream = resp.getOutputStream();
        ) {
            resp.setContentType("application/x-download");
            resp.addHeader("Content-Disposition", "attachment;filename=" + id + ".txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
