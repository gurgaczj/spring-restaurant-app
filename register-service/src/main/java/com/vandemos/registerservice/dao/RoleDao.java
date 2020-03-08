package com.vandemos.registerservice.dao;

import com.vandemos.registerservice.model.RoleEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RoleDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    public RoleDao(){}
}
