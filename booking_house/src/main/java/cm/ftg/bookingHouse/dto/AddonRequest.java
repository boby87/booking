package cm.ftg.bookingHouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddonRequest(
        @Schema(
                description = "State  of house"
        )
        boolean state,

        @NotEmpty(message = "Type can not be a null or empty")
       @Schema(
                description = "type addons for exemple Parking, bedroom", example = "BEDROOM"
        )
        String type,

        @NotNull(message = "length can not be a null or empty")
        @Schema(
                description = "length of house",  example = "2.15"
        )
        double length,
        @NotNull(message = "length can not be a null or empty")
        @Schema(
                description = "length of house",  example = "2.15"
        )
        double width
        ){
}
