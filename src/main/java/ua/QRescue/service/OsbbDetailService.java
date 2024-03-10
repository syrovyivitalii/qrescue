package ua.QRescue.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.QRescue.config.MyOsbbDetails;
import ua.QRescue.models.Osbb;
import ua.QRescue.repositories.OsbbRepository;

import java.util.Optional;

@Service
public class OsbbDetailService implements UserDetailsService {

    private final OsbbRepository osbbRepository;

    public OsbbDetailService(OsbbRepository osbbRepository) {
        this.osbbRepository = osbbRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            System.out.println("Attempting to load user with username: " + login);
            Optional<Osbb> byLogin = osbbRepository.findByLogin(login);
            if (byLogin.isPresent()) {
                Osbb osbb = byLogin.get();
                System.out.println("User found: " + osbb.getLogin());
                return new MyOsbbDetails(osbb);
            } else {
                System.out.println("User not found with username: " + login);
                throw new UsernameNotFoundException("User not found with username: " + login);
            }
        } catch (Exception e) {
            System.out.println("Error fetching user: " + e.getMessage());
            throw new UsernameNotFoundException("User not found with username: " + login, e);
        }
    }
}
