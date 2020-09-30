package com.gympro.app.organization.domain;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MailObject {
    @Email
    @NotNull
    @Size(min = 1, message = "Please, set an email address to send the message to it")
    private String to;
    private String subject;
    private String text;
    private Map<String, String> props = new HashMap<>();

    
    public Map<String, String> getProps() {
		return props;
	}

	public void setProps(Map<String, String> props) {
		this.props = props;
	}

	public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
