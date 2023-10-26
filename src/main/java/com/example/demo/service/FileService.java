package com.example.demo.service;

import com.example.demo.model.File;
import org.springframework.stereotype.Service;
import com.example.demo.repository.FileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private FileRepository fileRepository;

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public Optional<File> getFileByFilename(String fileName) {
        return fileRepository.findByFileName(fileName);
    }

    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
