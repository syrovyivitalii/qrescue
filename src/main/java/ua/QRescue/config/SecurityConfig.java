package ua.QRescue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.QRescue.repositories.OsbbRepository;
import ua.QRescue.service.OsbbDetailService;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final OsbbRepository osbbRepository;

    private final SecretKey secretKey;
    public SecurityConfig(OsbbRepository osbbRepository, SecretKey secretKey) {
        this.osbbRepository = osbbRepository;
        this.secretKey = secretKey;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new OsbbDetailService(osbbRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JwtAuthenticationFilter(secretKey), UsernamePasswordAuthenticationFilter.class);
        try {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authz -> authz
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/osbb/admin/**").hasAuthority("ROLE_ADMIN")
                            .anyRequest().authenticated()
                    )
                    .httpBasic(Customizer.withDefaults());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ProviderManager authenticationManagerBean() {
        return new ProviderManager(authenticationProvider());
    }
}
