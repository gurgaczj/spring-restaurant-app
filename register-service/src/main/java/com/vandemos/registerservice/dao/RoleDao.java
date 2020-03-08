package com.vandemos.registerservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vandemos.registerservice.model.RoleEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "role")
@Data
public class RoleDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @Column(name = "rolename")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleEnum roleEnum;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<UserDao> user;

    public RoleDao(){}
}
