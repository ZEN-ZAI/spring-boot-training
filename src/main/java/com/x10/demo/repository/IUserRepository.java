package com.x10.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.x10.demo.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
