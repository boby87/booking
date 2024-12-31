package cm.ftg.bookingHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.ftg.bookingHouse.entity.Addon;
import cm.ftg.bookingHouse.entity.House;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddonRepository extends JpaRepository<Addon, Long> {

    Optional< Addon> findByReference(UUID reference);
    List< Addon> findByHouse_Reference(UUID reference);
}
