package cm.ftg.bookingHouse;

import cm.ftg.bookingHouse.entity.House;
import cm.ftg.bookingHouse.entity.TypeHouse;
import cm.ftg.bookingHouse.repository.HouseRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "House microservice REST API Documentation",
				description = "BookingHouse House microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Madan Reddy",
						email = "tutor@eazybytes.com",
						url = "https://www.eazybytes.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.eazybytes.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "EazyHouse House microservice REST API Documentation",
				url = "https://www.eazybytes.com/swagger-ui.html"
		)
)
public class BookingHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingHouseApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(HouseRepository houseRepository){
		return args -> {
			List<String > houseIds = List.of(
					"maison de trois chambres avec cuisine et douche moderne situé pres de la route",
					"studio moderne avec douche et cuisine situé pres de la mer",
					"apartment de trois chambres avec cuisine et douche moderne situé pres de la route"
					,"villa de trois chambres avec cuisine et douche moderne situé pres de la route");
			houseIds.forEach(accountId->{
				for (TypeHouse type : TypeHouse.values()){
					House house = new  House();
					house.setDescription(accountId);
					house.setType(type);
					house.setAvailable(Math.random()*100>50);
					house.setCategory(Math.random()*100>50);
					house.setPrice(1000+ Math.random()*70000);
					house.setMobileNumber(String.valueOf(Math.random()*70000));
					houseRepository.save(house);
				}
			});
		};
	}
}
