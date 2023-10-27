package com.example.demo.repository;


import com.example.demo.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    Optional<Folder> findByFolderName(String folderName);
}
