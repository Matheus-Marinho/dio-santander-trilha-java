package app.service;

import app.domain.model.User;

import java.util.List;

public interface IUserService {

    User findById(Long id);

    List<User> findAll();

    User createUser(User newUser);

    User updateUser(User user);

    void deleteUserById(Long id);
}
