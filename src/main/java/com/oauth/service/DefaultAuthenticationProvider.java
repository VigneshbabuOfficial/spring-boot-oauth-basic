package com.oauth.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.oauth.entity.AppUser;
import com.oauth.repository.AppUserRepository;

public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private AppUserRepository appUserRepository;
    
    
    public DefaultAuthenticationProvider(AppUserRepository appUserRepository) {
		super();
		this.appUserRepository = appUserRepository;
	}

	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<AppUser> appUser = appUserRepository.findById(authentication.getName());
        if(appUser.isPresent()) {
            AppUser user = appUser.get();
            String username = authentication.getName();
            String password = (String)authentication.getCredentials();
            if(username.equalsIgnoreCase(user.getUsername()) &&
                password.equalsIgnoreCase(user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority(user.getRoles()))
                );
            }
        }

        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
