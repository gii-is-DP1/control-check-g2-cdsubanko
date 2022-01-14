package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feeding {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    LocalDate startDate;


    @Column(name = "weeks_duration", nullable = false)
    @Positive
    double weeksDuration;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    @NotNull
    Pet pet;   

    @ManyToOne
    @JoinColumn(name = "feeding_id")
    FeedingType feedingType;

}
