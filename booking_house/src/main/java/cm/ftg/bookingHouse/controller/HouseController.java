package cm.ftg.bookingHouse.controller;

import cm.ftg.bookingHouse.constants.HouseConstants;
import cm.ftg.bookingHouse.dto.HouseRequest;
import cm.ftg.bookingHouse.dto.HouseDto;
import cm.ftg.bookingHouse.dto.ResponseDto;
import cm.ftg.bookingHouse.service.IHouseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "CRUD REST APIs for Accounts in EazyBank", description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE account details")
@RestController
@RequestMapping(path = "/house")
@Validated
public class HouseController {
    private final IHouseService iHouseService;

    public HouseController(IHouseService iHouseService) {
        this.iHouseService = iHouseService;
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> createAccount(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("houseDto") String houseDtoJson) {

        // Désérialiser houseDtoJson en objet Java
        ObjectMapper objectMapper = new ObjectMapper();
        HouseRequest houseDto;
        try {
            houseDto = objectMapper.readValue(houseDtoJson, HouseRequest.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(new ResponseDto(HouseConstants.STATUS_500, "Invalid JSON format for HouseDto")); // Meilleure gestion des erreurs        }
        }
        iHouseService.createHouse(files, houseDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HouseConstants.STATUS_201, HouseConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch House Details REST API", description = "REST API to fetch House &  Addon details based on a mobile number")
    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    @GetMapping("/fetch")
    public ResponseEntity<HouseDto> fetchAccountDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        HouseDto houseDto = iHouseService.findByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(houseDto);
    }


    @Operation(
            summary = "update House Details REST API",
            description = "REST API to update House details")
    @ApiResponse(responseCode = "200",
            description = "HTTP Status OK")

    @PutMapping("")
    public ResponseEntity<HouseDto> updateHouseByMobileNumber(@Valid @RequestBody HouseRequest houseRequest) {
        HouseDto houseDto = iHouseService.updateHouse(houseRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(houseDto);

    }


    @Operation(summary = "delete House REST API", description = "REST API to delete House &  Addon inside BookingHouse")
    @ApiResponse(responseCode = "201", description = "HTTP Status ACCEPTED")

    @DeleteMapping("/deleyeByMobileNumber")
    public ResponseEntity<ResponseDto> deleyeByMobileNumber(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        iHouseService.deleteByMobileNumber(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(HouseConstants.STATUS_201, HouseConstants.MESSAGE_201));
    }

    @Operation(summary = "deleteAll House REST API", description = "REST API to deleteAll House &  Addon inside BookingHouse")
    @ApiResponse(responseCode = "201", description = "HTTP Status ACCEPTED")

    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseDto> deleteAll() {

        iHouseService.deleteAll();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(HouseConstants.STATUS_201, HouseConstants.MESSAGE_201));
    }

    @Operation(summary = "findAll House Details REST API", description = "REST API to findAll &  Addon details")
    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    @GetMapping("/findAll")
    public ResponseEntity<List<HouseDto>> findAll() {
        List<HouseDto> houseDto = iHouseService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(houseDto);
    }

}
