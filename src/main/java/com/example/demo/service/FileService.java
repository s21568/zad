package com.example.demo.service;

import com.example.demo.exception.FileNotFoundException;
import com.example.demo.model.File;
import com.example.demo.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final FolderService folderService;

    @Transactional
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    } //only for testing :)

    @Transactional
    public File getFileById(long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
    }

    @Transactional
    public File getFileByFilename(String fileName) {
        return fileRepository.findByFileName(fileName)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
    }

    @Transactional
    public File addFile(File file) {
        return fileRepository.save(file);
    }

    @Transactional
    public File updateFile(File file) {
        return fileRepository.save(file);
    }

    @Transactional
    public Long deleteFile(Long id) {
        fileRepository.deleteById(id);
        return id;
    }

    @Transactional
    public File addFolderToFile(String fileName, String folderName) {
        File file = getFileByFilename(fileName);
        file.addFolderToList(folderService.getFolderByName(folderName));
        return updateFile(file);
    }
}
