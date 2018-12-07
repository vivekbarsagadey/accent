package com.whizit.accent.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity extends MasterBaseEntity{

	private static final long serialVersionUID = 1963086204985183449L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE", updatable = false)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="SERVER_SYNC", columnDefinition="bit(1) Default 0")
	private boolean isServerSync;
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@PreUpdate
    public void setPreUpdateEntity() {
		this.updatedDate = new Date();
	}
	
	@PrePersist
    public void setPrePersistEntity() {
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.isServerSync = false;
	}
}
