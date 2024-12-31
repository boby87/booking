package cm.ftg.bookingHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.ftg.bookingHouse.entity.Addon;
import cm.ftg.bookingHouse.entity.House;

import java.util.List;
import java.util.Optional;

public interface AddonRepository extends JpaRepository<Addon, Long> {

    Optional< Addon> findByReference(String reference);
    void AddAddonToHouse(Addon addon, House mobileNumber);
    void deleteAddonFromHouse(Addon addon, House house);
    List<Addon> findAllAddonsByHouse(House house);
    void updateAddonToHouse(Addon addon, House house);
}
