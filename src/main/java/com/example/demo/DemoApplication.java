package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public ApplicationRunner run(RestClient.Builder builder) {
		// 1. Создаем клиент с базовым URL
		RestClient restClient = builder.baseUrl("http://localhost:8080").build();

		return args -> {
			// 2. Формируем и выполняем HTTP-запрос
			Quote quote = restClient
					.get().uri("/api/random") // GET запрос к эндпоинту /api/random
					.retrieve()               // Извлечь ответ
					.body(Quote.class);       // Преобразовать тело ответа в объект Quote

			// 3. Выводим результат в лог
			log.info(quote.toString());
		};
	}
}
