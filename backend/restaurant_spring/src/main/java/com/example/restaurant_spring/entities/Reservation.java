package com.example.restaurant_spring.entities;
import com.example.restaurant_spring.dtos.ReservationDto;
import com.example.restaurant_spring.enums.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private  String tableType;
    private String description;
    private Date dateTime;
    private ReservationStatus reservationStatus;
    @ManyToOne()
    @JoinColumn(name = "userId",referencedColumnName = "userId",foreignKey = @ForeignKey(name = "fk_user_reservation"))
    private User user;
    @OneToMany(mappedBy = "reservation",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> productSet;

    public ReservationDto getReservationDto(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservationId);
        reservationDto.setTableType(tableType);
        reservationDto.setReservationStatus(reservationStatus);
        reservationDto.setDescription(description);
        reservationDto.setDateTime(dateTime);
        reservationDto.setCustomerId(user.getUserId());
        reservationDto.setCustomerName(user.getName());
        return  reservationDto;
    }
}
