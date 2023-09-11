package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository{

    Map<String,User> userMap;
    Integer autoIncr=0;
    
    public UserRepository(){
        userMap=new HashMap<>();
    }
    
    public UserRepository(Map<String, User> userMap, Integer autoIncr) {
        this.userMap = userMap;
        this.autoIncr = autoIncr;
    }

    @Override
    public User save(User entity) {
        if(entity.getId()==null){

            autoIncr++;
            User user=new User(Integer.toString(autoIncr), entity.getName());
            userMap.put(user.getId(), user);
            return user;
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        
        return userMap.values().stream().findFirst().filter(t->t.getId().equals(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<User> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
