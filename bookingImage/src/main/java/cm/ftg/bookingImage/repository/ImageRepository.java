package cm.ftg.bookingImage.repository;

import cm.ftg.bookingImage.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByReferencePattern(String referencePattern);
}
