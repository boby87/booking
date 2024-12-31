package cm.ftg.bookingHouse.service.impl;

import cm.ftg.bookingHouse.dto.AddonDto;
import cm.ftg.bookingHouse.dto.AddonRequest;
import cm.ftg.bookingHouse.dto.ImageResponse;
import cm.ftg.bookingHouse.entity.Addon;
import cm.ftg.bookingHouse.entity.AddonType;
import cm.ftg.bookingHouse.exception.ResourceNotFoundException;
import cm.ftg.bookingHouse.mapper.AddonMapper;
import cm.ftg.bookingHouse.mapper.HouseMapper;
import cm.ftg.bookingHouse.repository.AddonRepository;
import cm.ftg.bookingHouse.repository.HouseRepository;
import cm.ftg.bookingHouse.service.IAddonService;
import cm.ftg.bookingHouse.service.client.ImageFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class IAddonImpl implements IAddonService {
    private final AddonRepository addonRepository;
    private final HouseRepository houseRepository;
    private final ImageFeignClient imageFeignClient;

    @Autowired
    public IAddonImpl(AddonRepository addonRepository, HouseRepository houseRepository, ImageFeignClient imageFeignClient) {
        this.addonRepository = addonRepository;
        this.houseRepository = houseRepository;
        this.imageFeignClient = imageFeignClient;
    }
    @Override
    public void createAddon(MultipartFile[] files, AddonRequest addonRequest, String referenceHouse) {

        Addon addon = new Addon();
        BeanUtils.copyProperties(addonRequest, addon);
        addon.setReference(UUID.randomUUID());
        Addon addon1 = addonRepository.save(addon);
        houseRepository.findByReference(UUID.fromString(referenceHouse)).ifPresent(house -> addon1.setHouse(house));
        imageFeignClient.uploadImage(files, addon1.getReference().toString());

    }


    @Override
    public List<AddonDto> findAll() {
        return  addonRepository.findAll().stream().map(AddonMapper::mapToAddonDto).toList();
    }

    @Override
    public void updateAddonToHouse(AddonRequest addonRequest, String reference) {
        Addon addon = addonRepository.findByReference(UUID.fromString(reference)).orElseThrow(() -> new ResourceNotFoundException("addon", "reference", reference));
        BeanUtils.copyProperties(addonRequest, addon);
    }

    @Override
    public void deleteAllAddon() {
        addonRepository.deleteAll();

    }

    @Override
    public List<AddonDto> findAllAddonsByHouseReference(String referenceHouse) {
        return addonRepository.findByHouse_Reference(UUID.fromString(referenceHouse)).parallelStream().map(addon -> {
            try {
                List<String> images = Objects.requireNonNull(imageFeignClient.getImages(addon.getReference().toString()).getBody())
                        .stream()
                        .map(ImageResponse::url)
                        .toList();
                addon.setImages(images);
            } catch (Exception e) {
                // Gérer les exceptions et, par exemple, continuer sans images
                addon.setImages(Collections.emptyList());
            }
            return AddonMapper.mapToAddonDto(addon);
        }).toList();
    }

}
