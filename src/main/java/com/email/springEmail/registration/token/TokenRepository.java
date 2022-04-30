package com.email.springEmail.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface TokenRepository extends JpaRepository<Token ,Long>{
Optional<Token> findByToken(String token);

@Transactional
@Modifying
@Query("UPDATE Token c " +
        "SET c.confirmDate = ?2 " +
        "WHERE c.token = ?1")
int updateConfirmedAt(String token,
                      LocalDateTime confirmedAt);
}

