package com.transkript.reportcard.data.entity;

import com.transkript.reportcard.data.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "username", nullable = false, unique = true) private String username;
    @Column(name = "password", nullable = false) private String password;
    @Column(name = "first_name") private String firstName;
    @Column(name = "last_name") private String lastName;
    @Column(name = "phone") private String phone;
    @Column(name = "address") private String address;

    @ElementCollection
    @Column(name = "user_role")
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "owner_id"))
    private List<Role> roles = new ArrayList<>();

}
