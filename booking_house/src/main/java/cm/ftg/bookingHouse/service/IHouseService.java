package cm.ftg.bookingHouse.service;

import java.util.List;

import cm.ftg.bookingHouse.dto.HouseRequest;
import cm.ftg.bookingHouse.dto.HouseDto;

public interface IHouseService {

    void createHouse(HouseDto houseDto);

    HouseDto findByMobileNumber(String mobileNumber);
    
    List<HouseDto> findAll();
    
    void deleteByMobileNumber(String mobileNumber);
    void deleteAll();

      HouseDto updateHouse(HouseRequest houseDto);
}
