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

        Addon addon = new Addon();
        BeanUtils.copyProperties(addonDto, addon);
        return AddonMapper.mapToAddonDto(addonRepository.save(addon));
    }

    @Override
    public Optional<AddonDto> findByRef(String reference) {
        Addon addon = addonRepository.findByReference(reference).orElseThrow(() -> new ResourceNotFoundException("addon", "reference", reference));
        return null;
    }

    @Override
    public List<AddonDto> findAll() {
        return  addonRepository.findAll().stream().map(AddonMapper::mapToAddonDto).toList();
    }

    @Override
    public AddonDto updateAddon(AddonDto addonDto, String reference) {
        Addon addon = addonRepository.findByReference(reference).orElseThrow(() -> new ResourceNotFoundException("addon", "reference", reference));
          addon.setType(AddonType.valueOf(addonDto.type()));
        return AddonMapper.mapToAddonDto(addonRepository.save(addon));
    }

    @Override
    public void deleteAllAddon() {
        addonRepository.deleteAll();

    }

    @Override
    public void AddAddonToHouse(AddonDto addonDto, HouseDto houseDto) {


    }

    @Override
    public void deleteAddonFromHouse(AddonDto addonDto, HouseDto houseDto) {

    }

    @Override
    public List<AddonDto> findAllAddonsByHouse(HouseDto houseDto) {
        return List.of();
    }

    @Override
    public void updateAddonToHouse(AddonDto addonDto, HouseDto houseDto) {

    }
}
