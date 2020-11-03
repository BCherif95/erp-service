package com.tuwindi.erp.erpservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuwindi.erp.erpservice.entities.parents.AuditableParent;
import com.tuwindi.erp.erpservice.utils.Enumeration;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "spend")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Spend extends AuditableParent<Spend> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String label;
    private Double amount;
    private String pictures;
    @Enumerated(EnumType.STRING)
    private Enumeration.SPEND_STATE state = Enumeration.SPEND_STATE.AWAITING_APPROVAL;
    @ManyToOne
    @JoinColumn(name = "budget_line_id")
    private BudgetLine budgetLine;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();
//    @ManyToOne
//    @JoinColumn(name = "employee_id")
//    private Employee employee;
    @OneToMany(mappedBy = "spend")
    private List<Justificative> justificatives;
}
