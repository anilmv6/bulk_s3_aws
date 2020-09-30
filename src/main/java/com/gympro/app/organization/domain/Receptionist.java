package com.gympro.app.organization.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@DiscriminatorValue("receptionist")
public class Receptionist extends BaseEmployee {

    @Column(name = "receptionist_type")
    @ColumnDefault("'receptionist'")
    private String receptionistType;

    @OneToMany(
            mappedBy = "managedBy",
            fetch = FetchType.LAZY
    )
    private Set<Customer> managedLeads = new HashSet<>();

    public void addLead(Customer lead) {
        managedLeads.add(lead);
        lead.setManagedBy(this);
    }

    public void removeLead(Customer lead) {
        managedLeads.remove(lead);
        lead.setManagedBy(null);
    }
}
