package com.tuwindi.erp.erpservice.entities;

import com.tuwindi.erp.erpservice.entities.parents.AuditableParent;
import com.tuwindi.erp.erpservice.utils.Enumeration;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "unity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Unity extends AuditableParent<Unity> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_unity")
    private Enumeration.TYPE_UNITY typeUnity;

}
