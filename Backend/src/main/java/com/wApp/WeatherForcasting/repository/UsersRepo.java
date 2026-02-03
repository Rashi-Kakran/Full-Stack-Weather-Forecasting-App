package com.wApp.WeatherForcasting.repository;

import com.wApp.WeatherForcasting.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, String> {


    Users findByEmail(String email);

    boolean existsByEmail(String email);

}
