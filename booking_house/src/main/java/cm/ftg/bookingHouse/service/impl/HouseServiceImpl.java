package cm.ftg.bookingHouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cm.ftg.bookingHouse.dto.HouseDto;
import cm.ftg.bookingHouse.entity.Addon;
import cm.ftg.bookingHouse.entity.House;
import cm.ftg.bookingHouse.exception.ResourceNotFoundException;
import cm.ftg.bookingHouse.mapper.AddonMapper;
import cm.ftg.bookingHouse.mapper.HouseMapper;
import cm.ftg.bookingHouse.repository.AddonRepository;
import cm.ftg.bookingHouse.repository.HouseRepository;
import cm.ftg.bookingHouse.service.IHouseService;


@RequiredArgsConstructor
@Service
@Transactional
public class HouseServiceImpl implements IHouseService {
    private final HouseRepository houseRepository;
    private final AddonRepository addonRepository;

    @Override
    public void createHouse(HouseDto houseDto) {
        var house = houseRepository.save(HouseMapper.mapToHouse(houseDto));
        for (Addon addon : houseDto.addons().stream().map(AddonMapper::mapToAddon).toList()) {
           addon = addonRepository.save(addon);
            addon.setHouse(house);
        }
    }

    @Override
    public HouseDto findByMobileNumber(String mobileNumber) {
        House house = houseRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new  ResourceNotFoundException("house","mobileNumber", mobileNumber));
        var addon = addonRepository.findAllByHouse(house);
        return HouseMapper.mapToHouseDto(house, addon);
    }
}
