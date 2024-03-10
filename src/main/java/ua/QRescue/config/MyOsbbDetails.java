package ua.QRescue.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.QRescue.models.Osbb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyOsbbDetails implements UserDetails {
   private Osbb osbb;

    public MyOsbbDetails(Osbb osbb) {
        this.osbb = osbb;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (isAdmin(osbb.getLogin())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        // Log the granted authorities
        System.out.println("Granted authorities: " + authorities);
        return authorities;
    }


    // Method to check if the user is an admin
    private boolean isAdmin(String login) {
        // You can query the database or check against a predefined list of admin usernames
        // For simplicity, let's assume the admin username is "admin"
        return "admin".equals(login);
    }

    @Override
    public String getPassword() {
        return osbb.getPassword();
    }

    @Override
    public String getUsername() {
        return osbb.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
