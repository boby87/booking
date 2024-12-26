package cm.ftg.bookingHouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(name = "House",
        description = "Schema to hold House information"
)
public record HouseDto(

        @NotEmpty(message = "description can not be a null or empty")
        @Schema(
                description = "Account type of Eazy Bank account", example = "Savings"
        )
        String description,
        boolean available,
        boolean category,
        @NotNull(message = "price can not be a null or empty")
        @Schema(
                description = "Price of house",
                 example = "1587000"
        )
        double price,
        @Schema(
                description = "Localisation of house",
                 example = "description of house"
        )
        String Localisation,
        List<AddonDto> addons,
        @Schema(
                description = "State  of house"
        )
        boolean state,
        @NotEmpty(message = "mobileNumber can not be a null or empty")
        @Schema(
                description = "number to contact",  example = "1234567890"
        )
        String mobileNumber,
        @NotNull(message = "length can not be a null or empty")
        @Schema(
                description = "length of house",  example = "2.15"
        )
        double length,
        @NotNull(message = "length can not be a null or empty")
        @Schema(
                description = "length of house",  example = "2.15"
        )
        double width) {
}
