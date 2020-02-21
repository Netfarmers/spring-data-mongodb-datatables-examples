package com.github.example.repository;

import com.github.example.model.User;
import org.springframework.data.mongodb.datatables.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DataTablesRepository<User, Long> {
}
