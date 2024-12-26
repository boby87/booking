package cm.ftg.bookingHouse.service;

import cm.ftg.bookingHouse.dto.HouseDto;

public interface IHouseService {

    void createHouse(HouseDto houseDto);

    HouseDto findByMobileNumber(String mobileNumber);
}
