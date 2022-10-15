package com.example.GerenciadorDeSenhas.repository;
import com.example.GerenciadorDeSenhas.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password,Long> {

}
