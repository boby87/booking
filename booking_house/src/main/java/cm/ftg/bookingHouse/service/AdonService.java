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
    void AddAddonToHouse(AddonDto addonDto, HouseDto mobileNumber);
    void deleteAddonFromHouse(AddonDto addonDto, HouseDto houseDto);
    List<AddonDto> findAllAddonsByHouse(HouseDto houseDto);
    void updateAddonToHouse(AddonDto addonDto, HouseDto houseDto);


}
