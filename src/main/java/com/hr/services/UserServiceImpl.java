package com.hr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.model.User;
import com.hr.model.UserModel;
import com.hr.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userrepo;

	@Override
	public List<User> getAllAccounts() {
List<User> userlist=userrepo.findAll();
		return userlist;
	}

	@Override
	public String forgotpasswrod(String id,String password) {
		Optional<User> user1=userrepo.findById(id);
		user1.get().setPassword(password);
		
		return "password updated successfully";
	}

	@Override
	public String delete(String id) {
		userrepo.deleteById(id);
		return "account deleted successfully";
	}

	@Override
	public String update(String id,UserModel user) {
		User user1=userrepo.findByUserId(id);
		user1.setCountry(user.getCountry());
		user1.setState(user.getState());
		user1.setMandal(user.getMandal());
		user1.setLocationName(user.getLocationName());
		user1.setDistrict(user.getDistrict());
		user1.setEmail(user.getEmail());
		user1.setPhoneNumber(user.getPhoneNumber());
		user1.setPincode(user.getPincode());
		userrepo.save(user1);
		return "updated profile";
	}

	@Override
	public User getByid(String id) {
		User u=userrepo.findByUserId(id);
		return u;
	}

}
