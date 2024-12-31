package cm.ftg.bookingHouse.repository;

import cm.ftg.bookingHouse.entity.Addon;
import org.springframework.data.jpa.repository.JpaRepository;

import cm.ftg.bookingHouse.entity.House;

import java.util.List;
import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {
    Optional<House> findByMobileNumber(String mobileNumber);
    void deleteByMobileNumber(String mobileNumber);

}
