package com.tuwindi.erp.erpservice.entities;

import com.tuwindi.erp.erpservice.entities.parents.AuditableParent;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "job")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Job extends AuditableParent<Job> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Lob
    @Column(name = "job_role", columnDefinition = "text", unique = true)
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    private String jobRole;
    @Lob
    @Column(name = "description", columnDefinition = "text", unique = true)
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    private String description;
    @Lob
    @Column(name = "job_duty", columnDefinition = "text", unique = true)
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    private String jobDuty;
}
