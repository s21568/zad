package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String fileName;
    private long size;

    @OneToMany
    private List<Folder> optionalFolders; // (eg. photos, text files, top secret, etc.)

    /*
    @ElementCollection
    private List<String> optionalFiles; // (eg. photos, text files, top secret, etc.)
     */
    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;

}
