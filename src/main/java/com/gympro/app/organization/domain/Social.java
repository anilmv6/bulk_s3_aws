package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "org_social")
@Setter
@Getter
public class Social extends BaseEntity {

    @Column(name = "facebook") private String facebook;

    @Column(name = "twitter") private String twitter;

    @Column(name = "google") private String google;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Social(Long id) {
        super(id);
    }

    public Social() {
    }
}
