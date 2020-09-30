package com.gympro.app.base.db.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "REQUEST_ID", nullable = false)
    private String requestId;

    @Column(name = "CREATED_DATETIME")
    private LocalDateTime createdDatetime;

    @Column(name = "UPDATED_DATETIME")
    private LocalDateTime updatedDatetime;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "IS_ENABLED", nullable = false)
    private Boolean isEnabled;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;
    
    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime == null ? LocalDateTime.now() : createdDatetime;
    }

    public void setUpdatedDatetime(LocalDateTime updatedDatetime) {
        this.updatedDatetime = updatedDatetime == null ? LocalDateTime.now() : updatedDatetime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getRequestId() {
        return requestId;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public LocalDateTime getUpdatedDatetime() {
        return updatedDatetime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity))
            return false;

        BaseEntity other = (BaseEntity) o;

        if (id.equals(other.getId())) {
            return true;
        }

        // equivalence by id
        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }
}
