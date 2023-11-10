package com.ohathaway.store;

import com.ohathaway.model.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    @Test
    public void saveUser() {
        UserRepository userRepository = new UserRepository();
        User user = User.of("John Smith", "SmithJohn@gmail.com", "John");
        User user1 = userRepository.saveUser(user);
        assertEquals(user.getEmail(), user1.getEmail());
    }

    @Test
    public void findUserById() {
        UserRepository userRepository = new UserRepository();
        User user = User.of("John Smith", "SmithJohn@gmail.com", "John");
        User user1 = userRepository.saveUser(user);
       List<User> rslUser = userRepository.findUserById(1);
        assertEquals(user.getEmail(), rslUser.get(0).getEmail());
    }

    @Test
    public void findByEmail() {
        UserRepository userRepository = new UserRepository();
        User user = User.of("John Smith", "SmithJohn@gmail.com", "John");
        User user1 = userRepository.saveUser(user);
        List<User> rslUser = userRepository.findByEmail("SmithJohn@gmail.com");
        assertEquals(user.getEmail(), rslUser.get(0).getEmail());
    }
}