package cm.ftg.bookingImage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookingImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingImageApplication.class, args);
	}

}
