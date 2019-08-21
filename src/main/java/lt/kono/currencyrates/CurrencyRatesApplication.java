package lt.kono.currencyrates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lt.kono.currencyrates.services.XMLService;

//Ernestas Konopliovas. Exchange rates analyzer

@SpringBootApplication
public class CurrencyRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRatesApplication.class, args);
	}

}
