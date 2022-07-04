package upc.edu.pe.lodgingservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="reservations")
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="provider_id")
    @JsonIgnore
    private Provider provider;

    public Provider getProvider() {
        return provider;
    }

    public Reservation setProvider(Provider provider) {
        this.provider = provider;
        return this;
    }
}
