package com.ohathaway.store;

import com.ohathaway.model.entity.User;

import java.util.List;

public interface UserRepoStore {

    public User saveUser(User user);

     public List<User> findByEmail(String email);

     public List<User> findUserById(int id);
}
