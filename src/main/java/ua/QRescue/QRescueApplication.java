package ua.QRescue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:5173")
@SpringBootApplication
public class QRescueApplication {

	public static void main(String[] args) {
		SpringApplication.run(QRescueApplication.class, args);
	}

}
