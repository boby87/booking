package cm.ftg.bookingHouse.service;

import cm.ftg.bookingHouse.dto.AddonDto;
import cm.ftg.bookingHouse.dto.AddonRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAddonService {
    void createAddon(MultipartFile[] files, AddonRequest addonRequest, String referenceHouse);
    List<AddonDto> findAll();
    void deleteAllAddon();
    List<AddonDto> findAllAddonsByHouseReference(String referenceHouse);
    void updateAddonToHouse(AddonRequest addonRequest, String reference);


}
