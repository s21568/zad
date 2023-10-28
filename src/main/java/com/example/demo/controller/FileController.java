package com.example.demo.controller;

import com.example.demo.model.File;
import com.example.demo.service.FileService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public File addFile(@RequestBody File file) {
        return fileService.addFile(file);
    }

    @PutMapping("/update")
    public File updateFile(@RequestBody File file) {
        return fileService.updateFile(file);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public Long deleteFileById(@PathVariable("id") Long id) {
        return fileService.deleteFile(id);
    }

    @PutMapping("/update/{fileName}/folder/{folderName}")
    public File addFolderToFile(@PathVariable("fileName") String fileName,
                                @PathVariable("folderName") String folderName) {
        return fileService.addFolderToFile(fileName, folderName);
    }


}
