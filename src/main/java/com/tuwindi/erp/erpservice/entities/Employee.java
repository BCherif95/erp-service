package com.tuwindi.erp.erpservice.entities;

import com.tuwindi.erp.erpservice.entities.parents.AuditableParent;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Employee extends AuditableParent<Employee> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastname;
    @Column(name = "firstname", length = 100, unique = true)
    private String firstname;
    @OneToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;
    @Column(name = "email", length = 100, unique = true)
    private String email;
    private String telephone;
    private String address;
    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
}
