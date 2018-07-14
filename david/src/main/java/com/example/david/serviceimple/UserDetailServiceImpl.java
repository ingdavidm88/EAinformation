package com.example.david.serviceimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.david.model.Role;
import com.example.david.model.User;
import com.example.david.model.UserDetail;
import com.example.david.repository.UserDetailRepository;
import com.example.david.repository.UserRepository;
import com.example.david.service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private UserDetailRepository userDetailRepository;
    
    @Autowired
    private UserRepository userRepository;
    

	@Override
	@Transactional
	public UserDetail findByUserDetail(Integer idUserDetail) {
		return userDetailRepository.findOne(idUserDetail);
	}

    @Override
    @Transactional
    public UserDetail saveUserDetail(UserDetail userDetail){
        
    	userDetail = userDetailRepository.save(userDetail);
        if(userDetail != null) {
        	User user = new User();
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
			String hashedPassword = passwordEncoder.encode(userDetail.getPassword());
        	
        	user.setUserDetail(userDetail);
        	user.setUserName(userDetail.getUserName());
        	user.setPassword(hashedPassword);
        	user.setEnabled(true);
        	user.setRole(new Role(new Integer("2")));
        	userRepository.save(user);
        }

        return userDetail;
    }
}