package ru.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.demo.configJwt.JwtTokenUtil;
import ru.demo.model.AuthToken;
import ru.demo.model.UserEntity;
import ru.demo.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthToken> createAuthenticationToken (@RequestBody UserEntity userEntity) throws Exception {

        System.out.println("Пришёл клиент с login/password - " + userEntity.getUserName() + "/"  + userEntity.getPassword());

        var user = jwtUserDetailsService.loadUserByUsername(userEntity.getUserName());

        System.out.println("Клиент из базы данных с login/password - " + user.getUsername() + "/"  + user.getPassword());

        authenticate(userEntity.getUserName(), userEntity.getPassword());

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userEntity.getUserName());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthToken(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}