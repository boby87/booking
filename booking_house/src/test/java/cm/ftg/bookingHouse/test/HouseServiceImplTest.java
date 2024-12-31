package cm.ftg.bookingHouse.test;
import cm.ftg.bookingHouse.dto.HouseRequest;
import cm.ftg.bookingHouse.entity.Addon;
import cm.ftg.bookingHouse.entity.House;
import cm.ftg.bookingHouse.repository.AddonRepository;
import cm.ftg.bookingHouse.repository.HouseRepository;
import cm.ftg.bookingHouse.service.client.ImageFeignClient;
import cm.ftg.bookingHouse.service.impl.HouseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class HouseServiceImplTest {

    @Mock
    private HouseRepository houseRepository;
    @Mock
    private ImageFeignClient imageFeignClient;

    @Mock
    private AddonRepository addonRepository;

    private HouseServiceImpl houseService;

    @BeforeEach
    void setUp() {
        houseService = new HouseServiceImpl(houseRepository, addonRepository,imageFeignClient);
    }

    @Test
    void testCreatedHouse() {
        // Arrange
        Long houseId = 1L;
        House house = new House();
        house.setDescription("GHGJSJL");
        house.setMobileNumber("1122266666");

        Long houseDtoId = 1L;
        HouseRequest houseRequest = new HouseRequest();
        houseRequest.setDescription("GHGJSJL");
        houseRequest.setMobileNumber("1122266666");

        MultipartFile[] files = new MultipartFile[0];
        House expectedHouse = new House();
        expectedHouse.setId(houseId);
        expectedHouse.setDescription("GHGJSJL");
        expectedHouse.setMobileNumber("1122266666");
        when(houseRepository.save(house)).thenReturn(expectedHouse);

        // Act
        houseService.createHouse(files,houseRequest);

        // Assert
        assertEquals(expectedHouse, actualHouse);
    }

    @Test
    void testGetHouseAddons() {
        // Arrange
        Long houseId = 1L;
        House house = new House();
        house.setId(houseId);
        when(houseRepository.findById(houseId)).thenReturn(Optional.of(house));
        Addon expectedAddon = new Addon();
        when(addonRepository.findByHouseId(houseId)).thenReturn(List.of(expectedAddon));

        // Act
        List<Addon> actualAddons = houseService.getHouseAddons(houseId);

        // Assert
        assertEquals(List.of(expectedAddon), actualAddons);
    }
}