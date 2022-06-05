package com.jeanponomarev.speakout.repository;

import com.jeanponomarev.speakout.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
