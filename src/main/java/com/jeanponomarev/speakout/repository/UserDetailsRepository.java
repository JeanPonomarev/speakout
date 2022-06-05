package com.jeanponomarev.speakout.repository;

import com.jeanponomarev.speakout.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
