package com.example.GerenciadorDeSenhas.controller;
import com.example.GerenciadorDeSenhas.entity.Password;
import com.example.GerenciadorDeSenhas.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@CrossOrigin
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;


    @GetMapping("/{idPassword}")
    public ResponseEntity<Password> findById(
            @PathVariable("idPassword") Long idPassword
    ){
        return ResponseEntity.ok().body(this.passwordService.findById(idPassword));
    }


    @GetMapping
    public ResponseEntity<List<Password>> getAllRequests(){
        return ResponseEntity.ok().body(passwordService.findAll().stream().map(this::unencrypt).collect(Collectors.toList()));
    }


    @PostMapping
    public ResponseEntity<?> insert(
            @RequestBody Password password
    ){
        try {
            String passwordOriginal = password.getSenha();
            String passwordEncriptado = Base64.getEncoder().encodeToString(passwordOriginal.getBytes());
            password.setSenha(passwordEncriptado);
            this.passwordService.insert(password);
            return ResponseEntity.ok().body("Password Cadastrada com Sucesso.");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Password unencrypt(Password passwordEntity){
        byte[] decoded = Base64.getDecoder().decode(passwordEntity.getSenha());
        String decodedString = new String(decoded);
        passwordEntity.setSenha(decodedString);
        return passwordEntity;
    }

    @PutMapping("/{idPassword}")
    public ResponseEntity<?> update(
            @PathVariable Long idPassword,
            @RequestBody Password password
    ){
        try {
            this.passwordService.update(idPassword, password);
            return ResponseEntity.ok().body("Especialidade Atualizada com Sucesso.");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            this.passwordService.delete(id);
            return ResponseEntity.ok().body("Password deletada com sucesso.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
