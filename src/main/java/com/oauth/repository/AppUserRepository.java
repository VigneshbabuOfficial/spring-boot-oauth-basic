package com.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oauth.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
}
