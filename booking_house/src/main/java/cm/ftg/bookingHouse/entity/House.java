package cm.ftg.bookingHouse.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class House extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private   String description;
    private  boolean available;
    private boolean category;
    private double price;
    private  String Localisation;
    private  String mobileNumber;
}
