package cm.ftg.bookingHouse.entity;

import cm.ftg.bookingHouse.converter.AddonTypeConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Addon extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = AddonTypeConverter.class)
    private AddonType type;
    @ManyToOne
    @JoinColumn(name = "id_house")
    private House house;
}
