package com.example.demo.controller;

import com.example.demo.model.Folder;
import com.example.demo.service.FolderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folder")
@AllArgsConstructor
public class FolderController {

    private FolderService folderService;

    @GetMapping("/getAll") //only for testing :)
    public List<Folder> getAllFolders() {
        return folderService.getAllFolders();
    }

    @GetMapping("/get/folderId/{id}")
    public Folder getFolderByName(@PathVariable("id") long id) {
        return folderService.getFolderById(id);
    }

    @GetMapping("/get/folderName/{folderName}")
    public Folder getFileByName(@PathVariable("folderName") String folderName) {
        return folderService.getFolderByName(folderName);
    }

    @PostMapping("/add")
    public Folder addFolder(@RequestBody Folder folder) {
        return folderService.addFolder(folder);
    }

    @PutMapping("/update")
    public Folder updateFolder(@RequestBody Folder folder) {
        return folderService.updateFolder(folder);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public Long deleteFolderById(@PathVariable("id") Long id) {
        return folderService.deleteFolder(id);
    }

    @PutMapping("/update/{folderName}/file/{fileName}")
    public Folder addFileToFolder(@PathVariable("folderName") String folderName,
                                  @PathVariable("fileName") String fileName) {
        return folderService.addFileToFolder(folderName, fileName);
    }

    @PutMapping("/update/{parentFolderName}/childfolder/{childFolderName}")
    public Folder addChildFolderToFolder(@PathVariable("parentFolderName") String parentFolderName,
                                         @PathVariable("childFolderName") String childFolderName) {
        return folderService.addChildFolderToFolder(parentFolderName, childFolderName);
    }


    @PutMapping("/update/{childFolderName}/parentfolder/{parentFolderName}")
    public Folder addParentFolderToFolder(@PathVariable("childFolderName") String childFolderName,
                                          @PathVariable("parentFolderName") String parentFolderName) {
        return folderService.addParentFolderToFolder(childFolderName, parentFolderName);
    }
}