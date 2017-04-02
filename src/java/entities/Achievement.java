/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NIYI
 */
@Entity
@XmlRootElement
public class Achievement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private LocalDate localDate;

//    private List<String> imagePaths;
//
//    private List<String> videoPaths;

//    private List<String> links;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<String> getImagePaths() {
//        return imagePaths;
//    }
//
//    public void setImagePaths(List<String> imagePaths) {
//        this.imagePaths = imagePaths;
//    }

//    public List<String> getVideoPaths() {
//        return videoPaths;
//    }
//
//    public void setVideoPaths(List<String> videoPaths) {
//        this.videoPaths = videoPaths;
//    }

//    public List<String> getLinks() {
//        return links;
//    }
//
//    public void setLinks(List<String> links) {
//        this.links = links;
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.description);
//        hash = 73 * hash + Objects.hashCode(this.imagePaths);
//        hash = 73 * hash + Objects.hashCode(this.videoPaths);
//        hash = 73 * hash + Objects.hashCode(this.links);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Achievement other = (Achievement) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
//        if (!Objects.equals(this.imagePaths, other.imagePaths)) {
//            return false;
//        }
//        if (!Objects.equals(this.videoPaths, other.videoPaths)) {
//            return false;
//        }
//        if (!Objects.equals(this.links, other.links)) {
//            return false;
//        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "Achievement{" + "id=" + id + ", description=" + description + ", imagePaths=" + imagePaths + ", videoPaths=" + videoPaths + ", links=" + links + '}';
//    }

}
