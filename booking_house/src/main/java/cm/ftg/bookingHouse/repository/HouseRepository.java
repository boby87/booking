package cm.ftg.bookingHouse.repository;

import cm.ftg.bookingHouse.entity.Addon;
import org.springframework.data.jpa.repository.JpaRepository;

import cm.ftg.bookingHouse.entity.House;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, Long> {
    Optional<House> findByMobileNumber(String mobileNumber);
    Optional<House> findByReference(UUID mobileNumber);
    void deleteByMobileNumber(String mobileNumber);

}
