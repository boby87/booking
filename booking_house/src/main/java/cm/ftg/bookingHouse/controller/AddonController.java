package cm.ftg.bookingHouse.controller;

import cm.ftg.bookingHouse.constants.HouseConstants;
import cm.ftg.bookingHouse.dto.*;
import cm.ftg.bookingHouse.service.IAddonService;
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

@Tag(name = "CRUD REST APIs for Addon in BookingHouse", description = "CRUD REST APIs in BookingHouse to CREATE, UPDATE, FETCH AND DELETE addon details")
@RestController
@RequestMapping(path = "/addon")
@Validated
public class AddonController {
    private final IAddonService iAddonService;

    public AddonController(IAddonService iAddonService) {
        this.iAddonService = iAddonService;
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> createAccount(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("addonRequest") String addonRequest,
            @RequestParam("referenceHouse") String referenceHouse) {

        // Désérialiser addonRequest en objet Java
        ObjectMapper objectMapper = new ObjectMapper();
        AddonRequest request;
        try {
            request = objectMapper.readValue(addonRequest, AddonRequest.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(new ResponseDto(HouseConstants.STATUS_500, "Invalid JSON format for HouseDto")); // Meilleure gestion des erreurs        }
        }
        iAddonService.createAddon(files, request, referenceHouse);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HouseConstants.STATUS_201, HouseConstants.MESSAGE_201));
    }


    @Operation(
            summary = "update Addon Details REST API",
            description = "REST API to update Addon details")
    @ApiResponse(responseCode = "200",
            description = "HTTP Status OK")

    @PutMapping("")
    public ResponseEntity<ResponseDto> updateHouseByMobileNumber(@Valid @RequestBody AddonRequest addonRequest, @RequestParam("reference") String reference) {
        iAddonService.updateAddonToHouse(addonRequest, reference);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HouseConstants.STATUS_201, HouseConstants.MESSAGE_201));

    }


    @Operation(summary = "find all addons by house reference REST API", description = "REST API to find addons &  Addon inside BookingHouse")
    @ApiResponse(responseCode = "201", description = "HTTP Status ACCEPTED")

    @DeleteMapping("/findAllAddonsByHouseReference")
    public ResponseEntity<ResponseDto> findAllAddonsByHouseReference(
            @RequestParam("referenceHouse") String referenceHouse) {
        iAddonService.findAllAddonsByHouseReference(referenceHouse);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(HouseConstants.STATUS_201, HouseConstants.MESSAGE_201));
    }

    @Operation(summary = "deleteAll Addons REST API", description = "REST API to deleteAll addons &  Addon inside BookingHouse")
    @ApiResponse(responseCode = "201", description = "HTTP Status ACCEPTED")

    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseDto> deleteAll() {

        iAddonService.deleteAllAddon();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(HouseConstants.STATUS_201, HouseConstants.MESSAGE_201));
    }

    @Operation(summary = "findAll Addon Details REST API", description = "REST API to findAll &  Addon details")
    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    @GetMapping("/findAll")
    public ResponseEntity<List<AddonDto>> findAll() {
        List<AddonDto> houseDto = iAddonService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(houseDto);
    }

}
