package ua.QRescue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.QRescue.models.HeadOsbb;

import java.util.Optional;

public interface HeadOsbbRepository extends JpaRepository <HeadOsbb,Integer> {
    Optional<HeadOsbb> findById(int id);
}
