package ua.QRescue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.QRescue.models.Osbb;

public interface OsbbRepository extends JpaRepository<Osbb,Integer> {
    @Modifying(clearAutomatically = true)
    @Query(value = "Delete from Osbb c WHERE c.id=:id")
    void deleteById(@Param("id") int id);
}
