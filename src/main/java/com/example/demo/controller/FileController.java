package com.example.demo.controller;

import com.example.demo.model.File;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.FileService;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<File>> getAllFiles(){
        List<File> fileList = fileService.getAllFiles();
        return new ResponseEntity<>(fileList, HttpStatus.OK);
    }
    @GetMapping("/get/fileId/{id}")
    public ResponseEntity<File> getFileByName(@PathVariable("id") long id){
        File file= fileService.getFileById(id);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }
    @GetMapping("/get/fileName/{fileName}")
    public ResponseEntity<File> getFileByName(@PathVariable("fileName") String fileName){
        File file= fileService.getFileByFilename(fileName);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<File> addFile(@RequestBody File file){
        fileService.addFile(file);
        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<File> updateAddress(@RequestBody File file){
        fileService.updateFile(file);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<File> deleteFileById(@PathVariable("id") Long id){
        fileService.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
