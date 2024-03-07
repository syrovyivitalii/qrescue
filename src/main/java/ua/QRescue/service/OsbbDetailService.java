package ua.QRescue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.QRescue.config.MyOsbbDetails;
import ua.QRescue.models.Osbb;
import ua.QRescue.repositories.OsbbRepository;
import ua.QRescue.util.NotFoundException;

import java.util.Optional;
@Service
public class OsbbDetailService implements UserDetailsService {
    @Autowired
    private OsbbRepository osbbRepository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Osbb> byLogin = osbbRepository.findByLogin(login);
        return byLogin.map(MyOsbbDetails::new).orElseThrow(NotFoundException::new);
    }
}
