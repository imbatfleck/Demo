package com.jpa.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.jpa.demo.model.User;
import com.jpa.demo.service.UserService;

@RestController
@RequestMapping(value={"/user"})
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id).get();
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value="/create")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User "+user.getName());
        userService.createUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value="/get")
    public List<User> getAllUser() {
        List<User> tasks=userService.getUser();
        return tasks;

    }

    @PutMapping(value="/update")
    public ResponseEntity<String> updateUser(@RequestBody User currentUser)
    {
        System.out.println("sd");
        User user = userService.findById(currentUser.getId()).get();
        if (user==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        userService.update(currentUser, currentUser.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id){
        User user = userService.findById(id).get();
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<User> updateUserPartially(@PathVariable("id") long id, @RequestBody User currentUser){
        User user = userService.findById(id).get();
        if(user ==null){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        User usr = userService.updatePartially(currentUser, id);
        return new ResponseEntity<User>(usr, HttpStatus.OK);
    }

}
