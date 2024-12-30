package cm.ftg.bookingImage.service;

import cm.ftg.bookingImage.dto.ImageResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface ImageService {
    void storeFile(MultipartFile file, String referencePattern);
    void storeFile(MultipartFile[] files, String referencePattern);
    Resource loadFile(String filename);
    List<ImageResponse> loadFilesByReferencePattern(String referencePattern);
    List<ImageResponse> listStoredFiles();
    Stream<Path> loadAllReferencePattern(String referencePattern);
}
