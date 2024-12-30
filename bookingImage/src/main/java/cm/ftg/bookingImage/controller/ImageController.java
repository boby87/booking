package cm.ftg.bookingImage.controller;

import cm.ftg.bookingImage.dto.ImageResponse;
import cm.ftg.bookingImage.dto.ResponseDto;
import cm.ftg.bookingImage.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static cm.ftg.bookingImage.constants.ImageConstants.MESSAGE_201;
import static cm.ftg.bookingImage.constants.ImageConstants.STATUS_201;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDto> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("referencePattern") String referencePattern) {
         imageService.storeFile(file, referencePattern);

        return ResponseEntity.ok(new ResponseDto(STATUS_201,MESSAGE_201));
    }

    @PostMapping("/uploads")
    public ResponseEntity<ResponseDto> uploadImages(@RequestPart("files") MultipartFile[] files, @RequestPart("referencePattern") String referencePattern) {
        imageService.storeFile(files, referencePattern);

        return ResponseEntity.ok(new ResponseDto(STATUS_201,MESSAGE_201));
    }

    @GetMapping("/{referencePattern}")
    public ResponseEntity<List<ImageResponse>> getImage(@PathVariable String referencePattern) {
        return ResponseEntity.ok(imageService.loadFilesByReferencePattern(referencePattern));
    }

    @GetMapping("/files")
    public ResponseEntity<List<ImageResponse>> getListFiles( @RequestParam("referencePattern") String referencePattern) {
        List<ImageResponse> fileInfos = imageService.loadAllReferencePattern(referencePattern).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ImageController.class, "getFile", path.getFileName().toString()).build().toString();

            return new ImageResponse(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping
    public ResponseEntity<List<ImageResponse>> listImages() {
        return ResponseEntity.ok(imageService.listStoredFiles());
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = imageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .contentType(determineContentType(filename))
                .body(file);
    }

    private MediaType determineContentType(String filename) {
        try {
            Path path = Paths.get(filename);
            String mimeType = Files.probeContentType(path);
            return mimeType != null ? MediaType.parseMediaType(mimeType) : MediaType.APPLICATION_OCTET_STREAM;
        } catch (IOException e) {
            return MediaType.APPLICATION_OCTET_STREAM; // Type par défaut
        }
    }
}
