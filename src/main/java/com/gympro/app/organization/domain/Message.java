package com.gympro.app.organization.domain;

import com.gympro.app.base.db.domain.BaseEntity;
import com.gympro.app.base.db.domain.CompanyEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "org_message")
public class Message extends CompanyEntity {

    @Lob
    @Column(name = "message_content")
    private byte[] messageContent;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Message(Long id) {
        super(id);
    }

    public Message() {
    }
}
