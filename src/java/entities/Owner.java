/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import enums.Role;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.json.JsonArray;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NIYI
 */
@Entity
@XmlRootElement
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Role role;

    private String surname;

    private String firstname;

    private String othername;

    private String email;

    private String password;

    private String phoneNo;

    private String homeAddress;

    private String lagosAddress;

    private String otherAddress;

    @Lob
    private String missionStatement;

    private String profilePixPath;

    private String linkedInLink;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Award> awards;

    @OneToMany
    private List<Skill> skills;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(cascade = CascadeType.ALL)
    private List<School> schools;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Referee> referees;

    public Owner() {
        this.role = Role.USER;
        this.linkedInLink = "";
        this.homeAddress = "";
        this.lagosAddress = "";
        this.otherAddress = "";
        this.missionStatement = "";
        this.profilePixPath = "";
        this.skills = new LinkedList<>();
        this.awards = new LinkedList<>();
        this.experiences = new LinkedList<>();
        this.schools = new LinkedList<>();
        this.projects = new LinkedList<>();
        this.referees = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getLagosAddress() {
        return lagosAddress;
    }

    public void setLagosAddress(String lagosAddress) {
        this.lagosAddress = lagosAddress;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }

    public String getMissionStatement() {
        return missionStatement;
    }

    public void setMissionStatement(String missionStatement) {
        this.missionStatement = missionStatement;
    }

    public String getProfilePixPath() {
        return profilePixPath;
    }

    public void setProfilePixPath(String profilePixPath) {
        this.profilePixPath = profilePixPath;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @XmlTransient
    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    @XmlTransient
    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    @XmlTransient
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @XmlTransient
    public List<Referee> getReferees() {
        return referees;
    }

    public void setReferees(List<Referee> referees) {
        this.referees = referees;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLinkedInLink() {
        return linkedInLink;
    }

    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.role);
        hash = 61 * hash + Objects.hashCode(this.surname);
        hash = 61 * hash + Objects.hashCode(this.firstname);
        hash = 61 * hash + Objects.hashCode(this.othername);
        hash = 61 * hash + Objects.hashCode(this.email);
        hash = 61 * hash + Objects.hashCode(this.password);
        hash = 61 * hash + Objects.hashCode(this.phoneNo);
        hash = 61 * hash + Objects.hashCode(this.homeAddress);
        hash = 61 * hash + Objects.hashCode(this.lagosAddress);
        hash = 61 * hash + Objects.hashCode(this.otherAddress);
        hash = 61 * hash + Objects.hashCode(this.missionStatement);
        hash = 61 * hash + Objects.hashCode(this.profilePixPath);
        hash = 61 * hash + Objects.hashCode(this.skills);
        hash = 61 * hash + Objects.hashCode(this.experiences);
        hash = 61 * hash + Objects.hashCode(this.schools);
        hash = 61 * hash + Objects.hashCode(this.projects);
        hash = 61 * hash + Objects.hashCode(this.referees);
        hash = 61 * hash + Objects.hashCode(this.linkedInLink);
        hash = 61 * hash + Objects.hashCode(this.awards);
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
        final Owner other = (Owner) obj;
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.othername, other.othername)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.phoneNo, other.phoneNo)) {
            return false;
        }
        if (!Objects.equals(this.homeAddress, other.homeAddress)) {
            return false;
        }
        if (!Objects.equals(this.lagosAddress, other.lagosAddress)) {
            return false;
        }
        if (!Objects.equals(this.otherAddress, other.otherAddress)) {
            return false;
        }
        if (!Objects.equals(this.missionStatement, other.missionStatement)) {
            return false;
        }
        if (!Objects.equals(this.profilePixPath, other.profilePixPath)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.role != other.role) {
            return false;
        }
        if (!Objects.equals(this.skills, other.skills)) {
            return false;
        }
        if (!Objects.equals(this.experiences, other.experiences)) {
            return false;
        }
        if (!Objects.equals(this.schools, other.schools)) {
            return false;
        }
        if (!Objects.equals(this.projects, other.projects)) {
            return false;
        }
        if (!Objects.equals(this.referees, other.referees)) {
            return false;
        }
        if (!Objects.equals(this.linkedInLink, other.linkedInLink)) {
            return false;
        }
        if (!Objects.equals(this.awards, other.awards)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "surname:" + surname + ", firstname:" + firstname + ", othername:" + othername + ", email:" + email + ", phoneNo:" + phoneNo + ", homeAddress:" + homeAddress + ", lagosAddress:" + lagosAddress + ", otherAddress:" + otherAddress + ", missionStatement:" + missionStatement + ", profilePixPath:" + profilePixPath + ", linkedInLink:" + linkedInLink + '}';
    }

}
