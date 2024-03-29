package com.tool.RecruitXpert.Entities;

import com.tool.RecruitXpert.Enums.Role;
import com.tool.RecruitXpert.Enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
    // this user show's the user login part
    // make sure to do @Column(nullable = false) for all field

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;

    String firstName;

    String mobileNo;

    @Column(unique = true, nullable = false)
    String email;

    String password;

    int experienceInYears;
    int relevantExp;
    String highestQualification;
    ArrayList<String> skillset;
    String currentOrg;
    String currentJobTitle;
    String location;
    String city;
    String zipCode;

    // last active date : this will update when user able to sign in
    @UpdateTimestamp
    Date lastActiveDate;

    // this field is only access by admin cause we're passing this para to dtos so user can't able to access it
    @Enumerated(EnumType.STRING)
    private Status status; // approved | denied | deactivate

    Role userRole;

    // this is recruiter - review - resume feature :

    boolean isReviewed; // if this is true then show on recruiter's portal like this person selected.
//  then if it's reviewed then get the details of that recruiter id

    // for that we've to do many-many [hwo to map]

    @OneToMany(mappedBy = "userToResume", cascade = CascadeType.ALL)
    private List<ResumeEntity> resumeList = new ArrayList<>();

    // one user - many jobs
    @OneToMany(mappedBy = "userJobs", cascade = CascadeType.ALL)
    private List<Jobs> jobsList = new ArrayList<>();
}
