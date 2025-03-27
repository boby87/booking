package cm.ftg.bookingHouse.ia.agent;

import cm.ftg.bookingHouse.entity.House;
import cm.ftg.bookingHouse.entity.TypeHouse;
import cm.ftg.bookingHouse.repository.HouseRepository;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class HouseAiTools {
    private final HouseRepository houseRepository;

    public HouseAiTools(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Tool("Get all houses")
    List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    @Tool("Get all houses by type house")
    List<House> getAllHousesByTypeHouse(TypeHouse typeHouse) {
        return houseRepository.findByType(typeHouse);
    }

    @Tool("update price of house by id")
    House updateHouseById(Long id, double price){
        var house = houseRepository.findById(id).get();
        house.setPrice(price);
        return house;
    }
}
