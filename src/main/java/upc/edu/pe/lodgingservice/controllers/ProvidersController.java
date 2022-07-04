package upc.edu.pe.lodgingservice.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.edu.pe.lodgingservice.entities.Provider;
import upc.edu.pe.lodgingservice.entities.Reservation;
import upc.edu.pe.lodgingservice.services.ProviderService;
import upc.edu.pe.lodgingservice.services.ReservationService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/lodging/providers")
public class ProvidersController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Provider>> fetchAll(){
        try{
            List<Provider> providers=providerService.findAll();
            return new ResponseEntity<List<Provider>>(providers, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Provider> createProvider(@Valid @RequestBody Provider provider, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        Provider providerDB= providerService.createProvider(provider);
        return ResponseEntity.status(HttpStatus.CREATED).body(providerDB);
    }

    @PostMapping("/{providerId}/reservations")
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation, BindingResult result,@PathVariable("providerId") Long providerId){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        Reservation reservationDB= reservationService.createReservation(providerId,reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }

    private String formatMessage( BindingResult result){

        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
