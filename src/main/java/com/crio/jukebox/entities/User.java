package com.crio.jukebox.entities;

public class User extends BaseEntity {

    private String name;
    private UserStatus status;


    public User(String userName) {
        this.name = userName;
    }

    public User(String id, String userName) {
        this.id = id;
        this.name = userName;
    }

    public User(String id, String name, UserStatus status) {
        this(name, status);
        this.id = id;
    }

    public User(String name, UserStatus status) {
        this.name = name;
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public UserStatus getStatus() {
        return status;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.id + " " + name;
    }

}

