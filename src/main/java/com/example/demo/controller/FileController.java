package com.example.demo.controller;

import com.example.demo.model.File;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.FileService;

import java.util.List;

@RestController
@RequestMapping("/api/file")
@AllArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/getAll") //only for testing :)
    public ResponseEntity<List<File>> getAllFiles() {
        return new ResponseEntity<>(fileService.getAllFiles(), HttpStatus.OK);
    }

    @GetMapping("/get/fileId/{id}")
    public ResponseEntity<File> getFileByName(@PathVariable("id") long id) {
        return new ResponseEntity<>(fileService.getFileById(id), HttpStatus.OK);
    }

    @GetMapping("/get/fileName/{fileName}")
    public ResponseEntity<File> getFileByName(@PathVariable("fileName") String fileName) {
        return new ResponseEntity<>(fileService.getFileByFilename(fileName), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<File> addFile(@RequestBody File file) {
        return new ResponseEntity<>(fileService.addFile(file), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<File> updateFile(@RequestBody File file) {
        return new ResponseEntity<>(fileService.updateFile(file), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Long> deleteFileById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(fileService.deleteFile(id), HttpStatus.OK);
    }
}
