package com.example.demo.model.RoleAppUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAppUser {
    @Id
    private long appUserId;
    @Id
    private long roleId;

}
