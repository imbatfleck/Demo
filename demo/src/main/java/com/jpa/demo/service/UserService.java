package com.jpa.demo.service;

import java.util.List;
import java.util.Optional;

import com.jpa.demo.model.User;

public interface UserService {
	public void createUser(User user);
    public List<User> getUser();
    public Optional<User> findById(long id);
    public User update(User user, long l);
    public void deleteUserById(long id);
    public User updatePartially(User user, long id);

}
