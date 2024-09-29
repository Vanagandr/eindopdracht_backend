package com.eindopdracht.eindopdracht_forster.repository;

import com.eindopdracht.eindopdracht_forster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
