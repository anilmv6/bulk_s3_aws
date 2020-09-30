package com.gympro.app.organization.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.gympro.app.base.db.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "org_sms_template")
@Setter
@Getter
public class SMSNotification extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -5926604589858460943L;
	
	@Column(name = "pos_id")
    private Long posId;
	@Column(name = "category")
    private String category;
	@Column(name = "type")
    private String type;
	@Column(name = "content")
    private String content;
	@Column(name = "customer_id")
	 private String customerId;
	
	
    
    
    public SMSNotification(Long id) {
        super(id);
    }

    public SMSNotification() {
    }
}
