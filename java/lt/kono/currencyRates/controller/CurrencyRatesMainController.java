package lt.kono.currencyRates.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyRatesMainController {
	
	@GetMapping("/")
	public String start(Model model) {
		
		model.addAttribute("theDate", new java.util.Date());
		
		return "main";
	}

}