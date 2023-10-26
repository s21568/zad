package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService fileService;


}
