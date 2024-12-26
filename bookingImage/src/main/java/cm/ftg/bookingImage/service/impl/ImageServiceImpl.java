package cm.ftg.bookingImage.service.impl;

import cm.ftg.bookingImage.config.FileStorageProperties;
import cm.ftg.bookingImage.entity.Image;
import cm.ftg.bookingImage.exception.FileStorageException;
import cm.ftg.bookingImage.repository.ImageRepository;
import cm.ftg.bookingImage.service.ImageService;
import cm.ftg.bookingImage.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final Path fileStorageLocation;

    public ImageServiceImpl(ImageRepository imageRepository, FileStorageProperties fileStorageProperties) {
        this.imageRepository = imageRepository;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.");
        }
    }

    public String storeFile(MultipartFile file, String referencePattern) {
        String fileName = FileUtils.cleanFileName(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence: " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            imageRepository.save(
                    Image.builder()
                            .url(targetLocation.toUri().toString())
                            .fileName(fileName)
                            .size(file.getSize())
                            .referencePattern(referencePattern)
                            .contentType(file.getContentType())
                            .build());

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }
}
