package upc.edu.pe.lodgingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.lodgingservice.entities.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Long> {
}
