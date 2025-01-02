package cm.ftg.bookingHouse.service;

import cm.ftg.bookingHouse.dto.AddonDto;
import cm.ftg.bookingHouse.dto.HouseDto;

import java.util.List;
import java.util.Optional;

public interface AdonService {
    AddonDto createAddon(AddonDto addonDto);
    Optional<AddonDto> findByRef(String reference);
    List<AddonDto> findAll();
    AddonDto updateAddon(AddonDto addonDto, String reference);
    void deleteAllAddon();
    void AddAddonToHouse(Long id, String mobileNumber);
    void deleteAddonFromHouse(Long id, String mobileNumber);
    List<AddonDto> findAllAddonsByHouse( String mobileNumber);
    void updateAddonToHouse(Long id,  String mobileNumber);


}
