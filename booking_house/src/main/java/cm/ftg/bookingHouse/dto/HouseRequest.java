package cm.ftg.bookingHouse.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequest {
        @JsonProperty("reference")
        private String reference;

        @JsonProperty("description")
        private String description;

        @JsonProperty("available")
        private boolean available;

        @JsonProperty("category")
        private boolean category;

        @JsonProperty("price")
        private double price;

        @JsonProperty("Localisation")
        private String localisation; // JSON "Localisation" correspond ici

        @JsonProperty("state")
        private boolean state;

        @JsonProperty("mobileNumber")
        private String mobileNumber;

        @JsonProperty("length")
        private double length;

        @JsonProperty("width")
        private double width;

        @JsonProperty("typeHouse")
        private String typeHouse;

        // Constructeur, getters et setters
}
