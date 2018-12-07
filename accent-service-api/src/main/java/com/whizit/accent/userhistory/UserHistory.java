package com.whizit.accent.userhistory;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.whizit.accent.common.domain.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "TB_USER_HISTORY")
@Data
@Access(AccessType.PROPERTY)
public class UserHistory extends BaseEntity{

}
