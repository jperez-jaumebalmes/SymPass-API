/*package net.jaumebalmes.dam.m13.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "main_folder_id");
    private Directory parent;
    @OneToMany(mappedBy = "parent")
    private List<Directory> children;
    @ManyToOne
}

 
*/