package org.gl.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "createdBy", "updatedAt", "updatedBy"},
        allowGetters = true
)

public abstract class AuditModel implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String Created_By;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "modified_by", nullable = false)
    private String Modified_By;
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

	public String getCreated_By() {
		return Created_By;
	}

	public String getModified_By() {
		return Modified_By;
	}

	public void setCreated_By(String created_By) {
		Created_By = created_By;
	}

	public void setModified_By(String modified_By) {
		Modified_By = modified_By;
	}
     
}
