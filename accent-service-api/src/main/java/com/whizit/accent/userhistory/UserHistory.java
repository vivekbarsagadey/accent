package com.whizit.accent.userhistory;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_USER_HISTORY")
@Data
@Access(AccessType.PROPERTY)
public class UserHistory {

}
