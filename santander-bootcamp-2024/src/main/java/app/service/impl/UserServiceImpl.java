package app.service.impl;

import app.domain.model.User;
import app.domain.repository.IUserRepository;
import app.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User newUser) {
        if(newUser.getId() != null && userRepository.existsById(newUser.getId())){
            throw new IllegalArgumentException("This User ID already exists.");
        }

        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User user) {
        if(user == null || user.getId() != null && userRepository.existsById(user.getId())){
            throw new IllegalArgumentException("This user was not found.");
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User userToDelete = findById(id);
        if(userToDelete == null){
            throw new IllegalArgumentException("This user was not found.");
        }
        userRepository.delete(userToDelete);
    }
}
