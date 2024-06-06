package shap.mtcoding.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@EnableJpaAuditing
@SpringBootApplication
public class BankApplication {

	private static final Logger log = LoggerFactory.getLogger(BankApplication.class);

	public static void main(String[] args) {

		log.debug("디버그 실행 중");
		log.info("본 서버 실행 중");

		ConfigurableApplicationContext context = SpringApplication.run(BankApplication.class, args);
//		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
