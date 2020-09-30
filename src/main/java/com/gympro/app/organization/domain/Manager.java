package com.gympro.app.organization.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@DiscriminatorValue("manager")
public class Manager extends BaseEmployee {




    public Manager() {
    }

    public Manager(Long id) {
        super(id);
    }
}
