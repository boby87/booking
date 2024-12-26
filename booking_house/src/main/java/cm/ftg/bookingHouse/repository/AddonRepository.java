package cm.ftg.bookingHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.ftg.bookingHouse.entity.Addon;
import cm.ftg.bookingHouse.entity.House;

import java.util.List;

public interface AddonRepository extends JpaRepository<Addon, Long> {
    List<Addon> findAllByHouse(House house);
}
