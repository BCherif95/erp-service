package com.tuwindi.erp.erpservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuwindi.erp.erpservice.entities.parents.AuditableParent;
import com.tuwindi.erp.erpservice.utils.Enumeration;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "line_partner")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class LinePartner extends AuditableParent<LinePartner> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount = 0D;
    @Column(name = "balance_before")
    private Double balanceBefore;
    @Column(name = "balance_after")
    private Double balanceAfter;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finance_date")
    private Date financeDate = new Date();
    @Column(name = "method_of_payment")
    @Enumerated(EnumType.STRING)
    private Enumeration.METHOD_OF_PAYMENT methodOfPayment;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private Enumeration.LINE_STATE lineState;
    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;
    @ManyToOne
    @JoinColumn(name = "line_id")
    private BudgetLine budgetLine;

}
