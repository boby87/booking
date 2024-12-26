package cm.ftg.bookingImage.repository;

import cm.ftg.bookingImage.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
