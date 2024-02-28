package com.example.restaurant_spring.entities;

import com.example.restaurant_spring.dtos.ReservationDto;
import com.example.restaurant_spring.enums.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reseration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String tableType;
    private String description;
    private Date dateTime;
    private ReservationStatus reservationStatus;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public ReservationDto getReservationDto(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(id);
        reservationDto.setTableType(tableType);
        reservationDto.setReservationStatus(reservationStatus);
        reservationDto.setDescription(description);
        reservationDto.setDateTime(dateTime);
        reservationDto.setCustomerId(user.getUserId());
        reservationDto.setCustomerName(user.getName());
        return  reservationDto;
    }
}
