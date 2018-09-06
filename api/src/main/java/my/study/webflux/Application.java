package my.study.webflux;

import my.study.webflux.listener.ApplicationStartingEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setWebApplicationType(WebApplicationType.REACTIVE);
		app.addListeners(new ApplicationStartingEventListener());
		app.run(args);
	}
}
