package com.example.demo.service;


import com.example.demo.exception.FileNotFoundException;
import com.example.demo.exception.FolderNotFoundException;
import com.example.demo.model.Folder;
import com.example.demo.repository.FolderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FolderService {

    private FolderRepository folderRepository;
    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }
    public Folder getFolderByName(String name) {
        return folderRepository.findByFolderName(name).
                orElseThrow(()->new FileNotFoundException("Folder not found"));
    }
    public Folder getFolderById(long id) {
        return folderRepository.findById(id).
                orElseThrow(()->new FolderNotFoundException("Folder not found"));
    }
    public Folder addFolder(Folder folder) {
        return folderRepository.save(folder);
    }
    public Folder updateFolder(Folder folder) {
        return folderRepository.save(folder);
    }
    public void deleteFolder(Long id) {
        folderRepository.deleteById(id);
    }
}
