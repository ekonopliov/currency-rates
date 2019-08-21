package lt.kono.currencyRates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lt.kono.currencyRates.services.XMLService;


@SpringBootApplication
public class CurrencyRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRatesApplication.class, args);
		XMLService xmlService = new XMLService();
		xmlService.getRates("AUD","2014-02-01","2014-02-09");
	}

}
