package com.backend.backend.Auth;

import java.util.List;
import java.util.Optional;

import com.backend.backend.DAO.Users.ApplicationUserDao;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import static com.backend.backend.Security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        // TODO Auto-generated method stub
        return getApplicationUser().stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUser() {
        List<ApplicationUser> applicationUser = Lists.newArrayList(
                new ApplicationUser("anna", passwordEncoder.encode("password"), USER.getGrantedAuthority(), true, true,
                        true, true),
                new ApplicationUser("linda", passwordEncoder.encode("password"), ADMIN.getGrantedAuthority(), true,
                        true, true, true),
                new ApplicationUser("tom", passwordEncoder.encode("password"), ADMINTRAINEE.getGrantedAuthority(), true,
                        true, true, true));
        return applicationUser;
    }
}
