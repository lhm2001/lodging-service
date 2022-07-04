package upc.edu.pe.lodgingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.lodgingservice.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
