package cm.ftg.bookingImage.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String storeFile(MultipartFile file, String referencePattern);
}
