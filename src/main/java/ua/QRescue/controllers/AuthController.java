package ua.QRescue.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.QRescue.config.MyOsbbDetails;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
public class AuthController {
    private final ProviderManager authenticationManager;

    private final UserDetailsService userDetailsService;
    private final SecretKey secretKey;


    public AuthController(ProviderManager authenticationManager, UserDetailsService userDetailsService, SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.secretKey = secretKey;
    }

    private String generateToken(String username) {
        MyOsbbDetails userDetails = (MyOsbbDetails) userDetailsService.loadUserByUsername(username);
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        int userId = userDetails.getUserId(); // get user ID from userDetails

        // Generate token using the secret key
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId) // Include user ID in the token
                .claim("authorities", authorities) // Include authorities in the token
                .claim("role", authorities.contains("ROLE_ADMIN") ? "admin" : "user") // Include role in the token
                .signWith(secretKey) // Use the injected secretKey
                .compact();
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.isAuthenticated()) {
                authentication.getPrincipal();
                String token = generateToken(username);

                // Parse the token
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token);
                Claims claims = claimsJws.getBody();

                Map<String, String> response = new HashMap<>();
                response.put("userId", claims.get("userId").toString());
                response.put("token", token);
                response.put("message", "Login successful!");
                response.put("role", (String) claims.get("role")); // Include role in the response
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Invalid username or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (AuthenticationException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}
