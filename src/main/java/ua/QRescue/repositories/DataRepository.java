package ua.QRescue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.QRescue.models.Data;

public interface DataRepository extends JpaRepository<Data,Integer> {
}
