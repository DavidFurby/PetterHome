package com.backend.backend.Auth;

import java.util.Collection;
import java.util.Optional;

import com.backend.backend.DAO.Users.ApplicationUserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

	private final ApplicationUserDao applicationUserDao;

	@Autowired
	public ApplicationUserService( ApplicationUserDao applicationUserDao) {
		this.applicationUserDao = applicationUserDao;
	}


	public Collection<ApplicationUser> getUsers() {
		return applicationUserDao.getUsers();
	}

	public ApplicationUser register(ApplicationUser applicationUser) {
		return applicationUserDao.register(applicationUser);
	}

	public Optional<ApplicationUser> getCurrentUser() {
		return applicationUserDao.getCurrentUser();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
