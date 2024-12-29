package cm.ftg.bookingHouse.service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "bookingHouse")
public interface ImageFeignClient {
}
