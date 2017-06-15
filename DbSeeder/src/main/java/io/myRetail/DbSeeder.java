package io.myRetail;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbSeeder implements CommandLineRunner {
	
	
	@Autowired
    private PriceRepository priceRepository;

	public static void main(String[] args) {
		SpringApplication.run(DbSeeder.class, args);
	}
    
    @Override
    public void run(String... strings) throws Exception {
    	
    	priceRepository.deleteAll();
        
    	priceRepository.save(new Price(15117729, 254.81, "USD"));
		priceRepository.save(new Price(16483589, 431.01, "USD"));
		priceRepository.save(new Price(16696652, 220.00, "USD"));
		priceRepository.save(new Price(16752456, 198.745, "USD"));
		priceRepository.save(new Price(13860428, 13.5, "USD"));
    }
}
