package com.hr.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hr.model.User;
import com.hr.model.UserModel;

@Service
public interface UserService {
	List<User> getAllAccounts();
   String forgotpasswrod(String id,String password);
   String delete(String id);
   String update(String id,UserModel user);
	User getByid(String id);

}
