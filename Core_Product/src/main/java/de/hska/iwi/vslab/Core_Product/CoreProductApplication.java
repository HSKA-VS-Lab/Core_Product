package de.hska.iwi.vslab.Core_Product;

import de.hska.iwi.vslab.Core_Product.Interfaces.ProductRepository;
import de.hska.iwi.vslab.Core_Product.Models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CoreProductApplication {
	private static final Logger log = LoggerFactory.getLogger(CoreProductApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoreProductApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository repository) {
		return (args) -> {
			// save a few Products
			repository.save(new Product("Apfel", 1.0, 1, "süß und saftig"));
			repository.save(new Product("Karotte", 1.1, 2, "sehr gesund"));
			repository.save(new Product("Schokobons", 1.2, 3, "sind klein und rund..."));

			// fetch all Products
			log.info("Products found with findAll():");
			log.info("-------------------------------");
			for (Product prod : repository.findAll()) {
				log.info(prod.toString());
			}
			log.info("");

			// fetch an individual Product by ID
			Product product = repository.findById(1);
			log.info("Product found with findById(1):");
			log.info("--------------------------------");
			log.info(product.toString());
			log.info("");

			// fetch Product by name
			log.info("Product found with findByName('Karotte'):");
			log.info("--------------------------------------------");
			log.info("Products found with findAll():");
			log.info("--------------------------------------------");
			repository.findAll().forEach(cat -> {
				log.info(cat.toString());
			});

		};
	}
}
