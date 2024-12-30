package cm.ftg.bookingHouse.mapper;


import cm.ftg.bookingHouse.dto.HouseRequest;
import cm.ftg.bookingHouse.dto.HouseDto;
import cm.ftg.bookingHouse.entity.House;
import cm.ftg.bookingHouse.entity.TypeHouse;

public final class HouseMapper {

    public static HouseDto mapToHouseDto(House house) {
        return new HouseDto(
                house.getReference().toString(),
                house.getDescription(),
                house.isAvailable(),
                house.isCategory(),
                house.getPrice(),
                house.getLocalisation(),
                house.getAddons().stream().map(AddonMapper::mapToAddonDto).toList(),
                house.isState(),
                house.getMobileNumber(),
                house.getLength(),
                house.getWidth(),
                house.getImages(),
                house.getType().getDescription());

    }

    public static House mapToHouse(HouseDto houseRequest) {
        House house = new House();
        house.setAvailable(houseRequest.available());
        house.setCategory(houseRequest.category());
        house.setDescription(houseRequest.description());
        house.setLocalisation(houseRequest.Localisation());
        house.setPrice(houseRequest.price());
        house.setLength(houseRequest.length());
        house.setMobileNumber(houseRequest.mobileNumber());
        house.setWidth(houseRequest.width());
        house.setType(TypeHouse.valueOf(houseRequest.typeHouse()));
        return house;
    }

    public static House mapFromHouseRequestToHouseDto(HouseRequest houseRequest) {
        House house = new House();
        house.setAvailable(houseRequest.isAvailable());
        house.setCategory(houseRequest.isCategory());
        house.setDescription(houseRequest.getDescription());
        house.setLocalisation(houseRequest.getLocalisation());
        house.setPrice(houseRequest.getPrice());
        house.setLength(houseRequest.getLength());
        house.setMobileNumber(houseRequest.getMobileNumber());
        house.setWidth(houseRequest.getWidth());
        house.setType(TypeHouse.valueOf(houseRequest.getTypeHouse()));
        return house;
    }
}
