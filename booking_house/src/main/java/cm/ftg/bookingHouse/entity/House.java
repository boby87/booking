package cm.ftg.bookingHouse.entity;

import java.util.List;

import cm.ftg.bookingHouse.converter.AddonTypeConverter;
import cm.ftg.bookingHouse.converter.TypeHouseConverter;
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
    @Convert(converter = TypeHouseConverter.class)
    private TypeHouse  type;
    @OneToMany (mappedBy ="house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Addon> addons;
}
