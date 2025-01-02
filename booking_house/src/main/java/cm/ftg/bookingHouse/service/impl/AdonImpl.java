package cm.ftg.bookingHouse.service.impl;

import cm.ftg.bookingHouse.dto.AddonDto;
import cm.ftg.bookingHouse.dto.HouseDto;
import cm.ftg.bookingHouse.entity.Addon;
import cm.ftg.bookingHouse.entity.AddonType;
import cm.ftg.bookingHouse.exception.ResourceNotFoundException;
import cm.ftg.bookingHouse.mapper.AddonMapper;
import cm.ftg.bookingHouse.repository.AddonRepository;
import cm.ftg.bookingHouse.repository.HouseRepository;
import cm.ftg.bookingHouse.service.AdonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdonImpl implements AdonService {
    private final AddonRepository addonRepository;
    private final HouseRepository houseRepository;
    @Autowired
    public AdonImpl(AddonRepository addonRepository, HouseRepository houseRepository) {
        this.addonRepository = addonRepository;
        this.houseRepository = houseRepository;
    }


    @Override
    public AddonDto createAddon(AddonDto addonDto) {
        return null;
    }

    @Override
    public Optional<AddonDto> findByRef(String reference) {
        return Optional.empty();
    }

    @Override
    public List<AddonDto> findAll() {
        return List.of();
    }

    @Override
    public AddonDto updateAddon(AddonDto addonDto, String reference) {
        return null;
    }

    @Override
    public void deleteAllAddon() {

    }

    @Override
    public void AddAddonToHouse(Long id, String mobileNumber) {

    }

    @Override
    public void deleteAddonFromHouse(Long id, String mobileNumber) {

    }

    @Override
    public List<AddonDto> findAllAddonsByHouse(String mobileNumber) {
        return List.of();
    }

    @Override
    public void updateAddonToHouse(Long id, String mobileNumber) {

    }
}
