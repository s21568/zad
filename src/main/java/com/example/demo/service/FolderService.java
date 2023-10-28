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
    private FileService fileService;

    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    public Folder getFolderByName(String name) {
        return folderRepository.findByFolderName(name)
                .orElseThrow(() -> new FileNotFoundException("Folder not found"));
    }

    public Folder getFolderById(long id) {
        return folderRepository.findById(id)
                .orElseThrow(() -> new FolderNotFoundException("Folder not found"));
    }

    public Folder addFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    public Folder updateFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    public Long deleteFolder(Long id) {
        folderRepository.deleteById(id);
        return id;
    }

    public Folder addFileToFolder(String folderName, String fileName) {
        Folder folder = getFolderByName(folderName);
        folder.addFileToFolder(fileService.getFileByFilename(fileName));
        return updateFolder(folder);
    }

    public Folder addChildFolderToFolder(String parentFolderName, String childFolderName) {
        Folder parentFolder = getFolderByName(parentFolderName);
        Folder childFolder = getFolderByName(childFolderName);
        parentFolder.addChildFolder(childFolder);
        return updateFolder(parentFolder);
    }

    public Folder addParentFolderToFolder(String childFolderName, String parentFolderName) {
        Folder childFolder = getFolderByName(childFolderName);
        Folder parentFolder = getFolderByName(parentFolderName);
        childFolder.addParentFolder(parentFolder);
        return updateFolder(childFolder);
    }
}
