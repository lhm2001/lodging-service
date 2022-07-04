package upc.edu.pe.lodgingservice.services;

import upc.edu.pe.lodgingservice.entities.Reservation;

public interface ReservationService extends CrudService<Reservation,Long>{

    Reservation createReservation(Long providerId,Reservation reservation);
}
