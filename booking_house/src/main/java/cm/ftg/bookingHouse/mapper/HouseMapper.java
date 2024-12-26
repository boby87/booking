package cm.ftg.bookingHouse.mapper;


import java.util.List;

import cm.ftg.bookingHouse.dto.HouseDto;
import cm.ftg.bookingHouse.entity.Addon;
import cm.ftg.bookingHouse.entity.House;

public final class HouseMapper {

    public static HouseDto mapToHouseDto(House house, List<Addon> addons) {
        return new HouseDto(
                house.getDescription(),
                house.isAvailable(),
                house.isCategory(),
                house.getPrice(),
                house.getLocalisation(),
                addons.stream().map(AddonMapper::mapToAddonDto).toList(),
                house.isState(),
                house.getMobileNumber(),
                house.getLength(),
                house.getWidth());
    }

    public static House mapToHouse(HouseDto houseDto) {
        House house = new House();
        house.setAvailable(houseDto.available());
        house.setCategory(houseDto.category());
        house.setDescription(houseDto.description());
        house.setLocalisation(houseDto.Localisation());
        house.setPrice(houseDto.price());
        house.setLength(houseDto.length());
        house.setMobileNumber(houseDto.mobileNumber());
        house.setWidth(houseDto.width());
        return house;
    }
}
