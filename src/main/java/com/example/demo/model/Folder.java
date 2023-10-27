package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String folderName;

    @OneToMany(mappedBy = "folder")
    private List<File> files;

    @JsonIgnore
    @OneToMany(mappedBy = "parentFolder")
    private List<Folder> childrenFolders;

    @ManyToOne
    @JoinColumn(name = "parent_folder_id")
    private Folder parentFolder;

    public void addFileToFolder(File file) {
        if (files.contains(file)) {
            throw new IllegalArgumentException("Ten plik już należy do tego folderu.");
        }

        if (file.getFolder() != null && !file.getFolder().equals(this)) {
            throw new IllegalArgumentException("Ten plik jest już przypisany do innego folderu.");
        }

        if (!files.contains(file)) {
            files.add(file); // Dodaj plik do listy plików w folderze
            file.setFolder(this); // Ustaw plikowi ten folder
        }
    }



    public void addParentFolder(Folder parentFolder) {
        if (parentFolder.equals(this)) {
            throw new IllegalArgumentException("Nie można dodać samego siebie jako folderu nadrzędnego.");
        }

        if (parentFolder.isDescendantOf(this)) {
            throw new IllegalArgumentException("Nie można utworzyć cyklicznego poddrzewa.");
        }

        this.setParentFolder(parentFolder);
    }

    public void addChildFolder(Folder childFolder) {
        if (childFolder.equals(this)) {
            throw new IllegalArgumentException("Nie można dodać samego siebie jako folderu podrzędnego.");
        }

        if (childFolder.isAncestorOf(this)) {
            throw new IllegalArgumentException("Nie można utworzyć cyklicznego poddrzewa.");
        }

        if (!childrenFolders.contains(childFolder)) {
            childrenFolders.add(childFolder);
            childFolder.setParentFolder(this);
        }
    }

    private boolean isAncestorOf(Folder potentialDescendant) {
        Folder current = potentialDescendant.getParentFolder();
        while (current != null) {
            if (current.equals(this)) {
                return true;
            }
            current = current.getParentFolder();
        }
        return false;
    }

    private boolean isDescendantOf(Folder potentialAncestor) {
        return potentialAncestor.isAncestorOf(this);
    }



}
