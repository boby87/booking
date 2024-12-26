package cm.ftg.bookingImage.controller;

import cm.ftg.bookingImage.entity.UploadResponse;
import cm.ftg.bookingImage.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("referencePattern") String referencePattern) {
        String fileName = imageService.storeFile(file, referencePattern);
        String message = "File uploaded successfully: " + fileName;

        return ResponseEntity.ok(new UploadResponse(fileName, message));
    }
}
