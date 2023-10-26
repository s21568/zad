package com.example.demo.service;

import com.example.demo.exception.FileNotFoundException;
import com.example.demo.model.File;
import com.example.demo.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    } //only for testing :)

    public File getFileById(long id) {
        return fileRepository.findById(id).
                orElseThrow(()->new FileNotFoundException("File not found"));
    }

    public File getFileByFilename(String fileName) {
        return fileRepository.findByFileName(fileName).
                orElseThrow(()->new FileNotFoundException("File not found"));
    }

    public File addFile(File file) {
        return fileRepository.save(file);
    }

    public File updateFile(File file) {
        return fileRepository.save(file);
    }

    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
