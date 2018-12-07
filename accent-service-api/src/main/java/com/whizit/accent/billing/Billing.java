package com.whizit.accent.billing;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.whizit.accent.common.domain.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "TB_BILLING")
@Data
@Access(AccessType.PROPERTY)
public class Billing extends BaseEntity{

}
