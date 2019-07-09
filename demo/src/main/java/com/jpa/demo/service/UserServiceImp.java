package com.jpa.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.demo.model.User;
import com.jpa.demo.repository.UserRepository;

@Service
@Transactional
public class UserServiceImp implements UserService{
	
		@Autowired
	    UserRepository userRepository;

	    public void createUser(User user) {
	        // TODO Auto-generated method stub
	        userRepository.save(user);
	    }

	    public List<User> getUser() {
	        // TODO Auto-generated method stub
	        return (List<User>) userRepository.findAll();
	    }

	    public Optional<User> findById(long id) {
	        // TODO Auto-generated method stub
	        return userRepository.findById(id);
	    }

	    public User update(User user, long l) {
	        // TODO Auto-generated method stub
	        return userRepository.save(user);
	    }

	    public void deleteUserById(long id) {
	        // TODO Auto-generated method stub
	        userRepository.deleteById(id);
	    }

	    public User updatePartially(User user, long id) {
	        // TODO Auto-generated method stub
	        Optional<User> usr = findById(id);
	        User user2=usr.get();
	        user2.setCountry(user.getCountry());
	        return userRepository.save(user2);
	    }
	

}
