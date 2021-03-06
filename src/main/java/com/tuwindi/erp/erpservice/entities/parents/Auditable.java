package com.tuwindi.erp.erpservice.entities.parents;

import com.tuwindi.erp.erpservice.entities.User;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@XmlRootElement
@MappedSuperclass
public abstract class Auditable<T> extends AuditableParent<Auditable> implements Serializable {
    @ManyToOne
    @JoinColumn(name = "create_by")
    private User createBy;
    @ManyToOne
    @JoinColumn(name = "update_by")
    private User updateBy;

}
