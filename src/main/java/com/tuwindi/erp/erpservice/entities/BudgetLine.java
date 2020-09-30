package com.tuwindi.erp.erpservice.entities;

import com.tuwindi.erp.erpservice.entities.parents.AuditableParent;
import com.tuwindi.erp.erpservice.utils.Enumeration;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "budget_ligne")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class BudgetLine extends AuditableParent<BudgetLine> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @Column(name = "title", columnDefinition = "text", unique = true)
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    private String title;
    private int quantity1;
    private int quantity2;
    private int unitPrice;
    @ManyToOne
    @JoinColumn(name = "unity1_id")
    private Unity unity1;
    @ManyToOne
    @JoinColumn(name = "unity2_id")
    private Unity unity2;
    private Double total;
    private Double solde = 0D;
    private Double finance = 0D;
    private Double stayToFinance = 0D;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private Enumeration.LINE_STATE state = Enumeration.LINE_STATE.PENDING;
    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
   /* @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "ligne_partner",
            joinColumns = {
                    @JoinColumn(name = "ligne_id", referencedColumnName = "id")
            },
            inverseJoinColumns = @JoinColumn(name = "partner_id"))
    private Set<Partner> partners = new HashSet<>();*/
}
