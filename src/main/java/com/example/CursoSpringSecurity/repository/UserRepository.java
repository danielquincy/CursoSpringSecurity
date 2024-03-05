package com.example.CursoSpringSecurity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.CursoSpringSecurity.document.UserDocument;
import java.util.Optional;


public interface UserRepository extends MongoRepository<UserDocument,String>{
    Optional<UserDocument> findByUsername(String username);
}
