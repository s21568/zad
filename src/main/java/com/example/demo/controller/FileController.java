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
    public List<File> getAllFiles() {
        return fileService.getAllFiles();
    }

    @GetMapping("/get/fileId/{id}")
    public File getFileByName(@PathVariable("id") long id) {
        return fileService.getFileById(id);
    }

    @GetMapping("/get/fileName/{fileName}")
    public File getFileByName(@PathVariable("fileName") String fileName) {
        return fileService.getFileByFilename(fileName);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public File addFile(@RequestBody File file) {
        return fileService.addFile(file);
    }

    @PutMapping("/update")
    public File updateFile(@RequestBody File file) {
        return fileService.updateFile(file);
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteFileById(@PathVariable("id") Long id) {
        return fileService.deleteFile(id);
    }
}
