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
@Table(name = "demand")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Demand extends AuditableParent<Demand> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Lob
    @Column(name = "description", columnDefinition = "text", unique = true)
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    private String description;
    @Column(name = "demand_state")
    @Enumerated(EnumType.STRING)
    private Enumeration.DEMAND_STATE demandState = Enumeration.DEMAND_STATE.APPROVED;
    @ManyToOne
    @JoinColumn(name = "create_by")
    private User createBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "demand_date")
    private Date demandDate = new Date();
    /*@OneToMany(mappedBy = "demand")
    private List<DemandFile> files;*/
    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    private Budget budget;
}
