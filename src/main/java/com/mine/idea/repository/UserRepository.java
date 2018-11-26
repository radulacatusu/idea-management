package com.mine.idea.repository;

import com.mine.idea.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @stefanl
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * @param email
     * @return
     */
    @Query("SELECT t FROM User t WHERE t.email = ?1")
    User findByEmail(String email);
}
