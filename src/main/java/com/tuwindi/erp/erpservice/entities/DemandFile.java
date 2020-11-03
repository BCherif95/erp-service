package com.tuwindi.erp.erpservice.entities;

import com.tuwindi.erp.erpservice.entities.parents.AuditableParent;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "demand_file")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DemandFile extends AuditableParent<DemandFile> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private String description;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "demand_id", nullable = false)
    private Demand demand;
}
