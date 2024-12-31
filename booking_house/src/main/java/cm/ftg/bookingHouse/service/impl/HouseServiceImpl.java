package cm.ftg.bookingHouse.service.impl;

import cm.ftg.bookingHouse.dto.HouseRequest;
import cm.ftg.bookingHouse.dto.ImageResponse;
import cm.ftg.bookingHouse.service.client.ImageFeignClient;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Service
@Transactional
public class HouseServiceImpl implements IHouseService {
    private final HouseRepository houseRepository;
    private final AddonRepository addonRepository;
    private final ImageFeignClient imageFeignClient;

    @Override
    public void createHouse(MultipartFile[] files, HouseRequest houseRequest) {
        var house = houseRepository.save(HouseMapper.mapFromHouseRequestToHouseDto(houseRequest));
        imageFeignClient.uploadImage(files, house.getReference().toString());

    }

    @Override
    public HouseDto findByMobileNumber(String mobileNumber) {
        House house = houseRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new  ResourceNotFoundException("house","mobileNumber", mobileNumber));

       List<String> images = imageFeignClient.getImages(house.getReference().toString()).getBody().stream().map(ImageResponse::url).toList();
       house.setImages(images);
        return HouseMapper.mapToHouseDto(house);
    }

    @Override
    public List<HouseDto> findAll() {
        return houseRepository.findAll().parallelStream().map(house -> {
            try {
                List<String> images = Objects.requireNonNull(imageFeignClient.getImages(house.getReference().toString()).getBody())
                        .stream()
                        .map(ImageResponse::url)
                        .toList();
                house.setImages(images);
            } catch (Exception e) {
                // Gérer les exceptions et, par exemple, continuer sans images
                house.setImages(Collections.emptyList());
            }
            return HouseMapper.mapToHouseDto(house);
        }).toList();
    }


    @Override
    public void deleteByMobileNumber(String mobileNumber) {
      houseRepository.deleteByMobileNumber(mobileNumber);

    }

    @Override
    public void deleteAll() {
       houseRepository.deleteAll();
       addonRepository.deleteAll();
    }

    @Override
    public HouseDto updateHouse(HouseRequest houseDto) {
        House house = houseRepository.findByMobileNumber(houseDto.getMobileNumber())
                .orElseThrow(() -> new  ResourceNotFoundException("house","mobileNumber", houseDto.getTypeHouse()));
        BeanUtils.copyProperties(houseDto, house);
        return HouseMapper.mapToHouseDto(house);
    }


}
