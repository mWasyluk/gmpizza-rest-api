package pl.mvasio.gmpizza.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.mvasio.gmpizza.data.UserRepository;
import pl.mvasio.gmpizza.security.User;
import pl.mvasio.gmpizza.security.UserDetailsService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${security.token.expiration-time}")
    private long expirationTime;
    @Value("${security.token.secret}")
    private String secret;

    @GetMapping
    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try{
            userDetailsService.loadUserByUsername(user.getUsername());
        }catch (UsernameNotFoundException | BadCredentialsException e){
            throw new UsernameNotFoundException(e.getMessage());
        }
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put(HttpHeaders.AUTHORIZATION, Collections.singletonList("Bearer " + token));

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
