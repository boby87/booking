package cm.ftg.bookingHouse.service;

import java.util.List;

import cm.ftg.bookingHouse.dto.HouseRequest;
import cm.ftg.bookingHouse.dto.HouseDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface IHouseService {

    void createHouse(MultipartFile[] files, HouseRequest houseDto);

    HouseDto findByMobileNumber(String mobileNumber);
    
    List<HouseDto> findAll();
    
    void deleteByMobileNumber(String mobileNumber);
    void deleteAll();

      HouseDto updateHouse(HouseRequest houseDto);
}
