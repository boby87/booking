package cm.ftg.bookingImage.service.impl;

import cm.ftg.bookingImage.config.FileStorageProperties;
import cm.ftg.bookingImage.dto.ImageResponse;
import cm.ftg.bookingImage.entity.Image;
import cm.ftg.bookingImage.exception.FileStorageException;
import cm.ftg.bookingImage.exception.ResourceNotFoundException;
import cm.ftg.bookingImage.repository.ImageRepository;
import cm.ftg.bookingImage.service.ImageService;
import cm.ftg.bookingImage.util.FileUtils;
import jakarta.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
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

    public void storeFile(MultipartFile file, String referencePattern) {
        String fileName = FileUtils.cleanFileName(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence: " + fileName);
            }


            Image image = imageRepository.save(
                    Image.builder()
                            .size(file.getSize())
                            .reference(UUID.randomUUID())
                            .fileName(fileName)
                            .referencePattern(referencePattern)
                            .contentType(file.getContentType())
                            .build());
            Path targetLocation = this.fileStorageLocation.resolve(image.getReference().toString().concat(generateFileExtension(fileName)));
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            image.setFileRelativePath(targetLocation.getFileName().toString());
            image.setUrl("bookingimage/api/images/files/"+targetLocation.getFileName());

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    private String generateFileExtension(String originalFilename) {
        String fileExtension = "";
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex > 0) {
            fileExtension = originalFilename.substring(dotIndex);
        }
        return fileExtension;
    }

    @Override
    public void storeFile(MultipartFile[] files, String referencePattern) {

        for (MultipartFile file : files) {
            storeFile(file, referencePattern);
        }
    }

    @Override
    public List<ImageResponse> loadFilesByReferencePattern(String referencePattern) {
            return imageRepository.findByReferencePattern(referencePattern).stream()
                    .map(image -> new ImageResponse(image.getFileName(), image.getUrl()))
                    .toList();

    }

    @Override
    public Resource loadFile(String filename) {
        try {
            Path file = fileStorageLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public List<ImageResponse> listStoredFiles() {

        List<ImageResponse> imageList = new ArrayList<>();
        try {
            Files.list(this.fileStorageLocation).forEach(path -> {
                imageList.add(new ImageResponse(path.getFileName().toString(), path.toString()));
            });
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
        return imageList;

    }

    @Override
    public Stream<Path> loadAllReferencePattern(String referencePattern) {
        return imageRepository.findByReferencePattern(referencePattern).stream().flatMap(image -> {
            try {
                return Files.walk(this.fileStorageLocation, 1)
                        .filter(path -> !path.equals(this.fileStorageLocation) && !path.toUri().toString().equals(image.getUrl()))
                        .map(this.fileStorageLocation::relativize);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).distinct();
    }
}
