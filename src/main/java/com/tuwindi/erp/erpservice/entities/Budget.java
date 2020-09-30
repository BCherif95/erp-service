package com.tuwindi.erp.erpservice.entities;

import com.tuwindi.erp.erpservice.entities.parents.AuditableParent;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "budget")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Budget extends AuditableParent<Budget> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @Column(name = "title", columnDefinition = "text", unique = true)
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    private String title;
    private Double amount;
    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;
    /*@OneToMany(mappedBy = "budget")
    private List<BudgetLine> budgetLines;*/
}
