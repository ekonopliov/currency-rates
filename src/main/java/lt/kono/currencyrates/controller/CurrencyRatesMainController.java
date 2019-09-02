package lt.kono.currencyrates.controller;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.kono.currencyrates.Rate;
import lt.kono.currencyrates.services.XMLService;


@Controller
public class CurrencyRatesMainController {

	XMLService xmlService = new XMLService();
	ArrayList<Rate> ratesList;
	Map<String,String> currencyMap = xmlService.getCurriencyMap();
	
	@RequestMapping("/")
	public String getRate(Model model,  
			@RequestParam(value = "selectedCurrencyCode", required=false) String selectedCurrencyCode,
			@RequestParam(value = "daterange", required=false) String daterange) {
		
		//Setting up default values when nothing is selected. Default is USD and from Today-20 days to Today-1
		if(selectedCurrencyCode == null) {
			selectedCurrencyCode = "USD";
		}
		
		if(daterange == null) {
			daterange = "";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			LocalDateTime ldt = LocalDateTime.now().minusDays(20);	
			daterange += formatter.format(ldt);
			
			daterange += " - ";
			
			ldt = LocalDateTime.now().minusDays(1);
			daterange += formatter.format(ldt);
		}
		
		
		model.addAttribute("selectedCurrencyName", currencyMap.get(selectedCurrencyCode));
		model.addAttribute("selectedCurrencyCode", selectedCurrencyCode);
		
		String lowDate = daterange.substring(0,10);
		String highDate = daterange.substring(13,23);
		
		model.addAttribute("lowDate",lowDate);
		model.addAttribute("highDate",highDate);
		
		ratesList = xmlService.getRates(selectedCurrencyCode, lowDate, highDate);
		
		model.addAttribute("ratesList", ratesList);
		model.addAttribute("currencyMap", currencyMap);
		
		//Checking whether rate has changed, and if yes, by how much
		if(ratesList!=null) {
		if(ratesList.size() > 0) {
			
			Double change = ratesList.get(0).getRate() - ratesList.get(ratesList.size()-1).getRate();
			
			if(change !=0) {
			model.addAttribute("rateChange","Rate has changed by " 
			+ new DecimalFormat("0.00000").format(change));	
			}
			
			else {
				model.addAttribute("rateChange","Rate has not been changed");
			}
			
			//Checking rate precisement to selected date
			if(!ratesList.get(0).getDate().equals(highDate) || (!ratesList.get(ratesList.size()-1).getDate().equals(lowDate))) {
			model.addAttribute("isPrecise",true);
			}
		}
		}
		else {
			model.addAttribute("rateChange", "");
		}
	
		return "index";
	}
	

}