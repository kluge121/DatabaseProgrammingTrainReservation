package com.project.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @Column(name = "user_id", length = 15)
    private
    String id;

    @Column(nullable = false, length = 15)
    private
    String password;

    @Column(nullable = false, length = 10)
    private
    String name;

    @OneToMany(mappedBy = "user")
    private Set<Reserve> reserves = new HashSet<Reserve>(0);

    public User() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {

        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setReserves(Set<Reserve> reserves) {
        this.reserves = reserves;
    }

    public Set<Reserve> getReserves() {

        return reserves;
    }

    public User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}

