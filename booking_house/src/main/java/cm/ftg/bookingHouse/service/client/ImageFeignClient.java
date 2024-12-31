package cm.ftg.bookingHouse.service.client;

import cm.ftg.bookingHouse.dto.ImageResponse;
import cm.ftg.bookingHouse.dto.ResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.http.HttpStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static cm.ftg.bookingHouse.constants.HouseConstants.STATUS_500;

@FeignClient(name = "BOOKINGIMAGE", url = "http://localhost:8082/api")
public interface ImageFeignClient {

    @CircuitBreaker(name = "bookingImage", fallbackMethod = "uploadImageFallback")
    @PostMapping(value = "/images/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ResponseDto> uploadImage(@RequestPart("files") MultipartFile[] files, @RequestParam("referencePattern") String referencePattern);

    @Retry(name = "retryGetImages", fallbackMethod = "getImagesFallback")
    @GetMapping("/{referencePattern}")
     ResponseEntity<List<ImageResponse>> getImages(@PathVariable String referencePattern);

    default ResponseEntity<List<ImageResponse>> getImagesFallback(String referencePattern, Exception exception) {
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);
    }

    default ResponseEntity<ResponseDto> uploadImageFallback(MultipartFile[] files, String referencePattern, Exception exception) {
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(new ResponseDto(STATUS_500, exception.getMessage()));
    }
}
