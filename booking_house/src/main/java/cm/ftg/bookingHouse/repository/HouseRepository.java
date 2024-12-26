package cm.ftg.bookingHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.ftg.bookingHouse.entity.House;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {
    Optional<House> findByMobileNumber(String mobileNumber);
}
