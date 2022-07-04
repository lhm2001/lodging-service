package upc.edu.pe.lodgingservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.lodgingservice.entities.Provider;
import upc.edu.pe.lodgingservice.entities.Reservation;
import upc.edu.pe.lodgingservice.exception.ResourceNotFoundException;
import upc.edu.pe.lodgingservice.repositories.ProviderRepository;
import upc.edu.pe.lodgingservice.repositories.ReservationRepository;
import upc.edu.pe.lodgingservice.services.ReservationService;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<Reservation> findAll() throws Exception {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation createReservation(Long providerId,Reservation reservation) {
        Provider provider= providerRepository.findById(providerId)
                .orElseThrow(()->new ResourceNotFoundException("Provider","Id",providerId));

        return reservationRepository.save(reservation.setProvider(provider));
    }
}
