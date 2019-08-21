package lt.kono.currencyrates.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lt.kono.currencyrates.Rate;

@Service
@Scope("singleton")
public class XMLService {
	
	 //Returning rate list from lb.lt webservice 
	  public ArrayList<Rate> getRates(String currency,  String lowDate, String highDate){
		  
		  ArrayList<Rate> rates = null;
		  
		  try {
	            String URL = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/"
	            		+ "getFxRatesForCurrency"
	            		+ "?tp=EU"
	            		+ "&ccy=" + currency
	            		+ "&dtFrom=" + lowDate
	            		+ "&dtTo=" + highDate;
	  

	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(URL);
	            doc.getDocumentElement().normalize();
	            
	            NodeList nodeList = doc.getElementsByTagName("FxRate");
	            
	            rates = new ArrayList<>();

	            for (int i = 0; i < nodeList.getLength(); i++) {

	                Node node = nodeList.item(i);

	                if(node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element elem = (Element) node;
	                    Rate rate = new Rate(
	                            elem.getElementsByTagName("Dt").item(0).getTextContent().replace('.', '-'),
	                            elem.getElementsByTagName("Ccy").item(1).getTextContent(),
	                            Double.parseDouble(elem.getElementsByTagName("Amt").item(1).getTextContent())
	                    );
	                    rates.add(rate);
	                }
	            }

	        } catch (Exception ex) {
	          System.out.println(ex.getMessage());
	        }
		  
		  return rates;
	  }
	  
	//Returning currency map from lb.lt webservice 
	  public Map<String, String> getCurriencyMap(){
		  
	  	  HashMap<String, String> currienciesMap = null;
		  
		  try {
	            String URL = "https://www.lb.lt//webservices/FxRates/FxRates.asmx/getCurrencyList?";

	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(URL);
	            doc.getDocumentElement().normalize();
	            
	            NodeList nodeList = doc.getElementsByTagName("CcyNtry");
	            
	            currienciesMap = new HashMap<String, String>();

	            for (int i = 0; i < nodeList.getLength(); i++) {

	                Node node = nodeList.item(i);

	                if(node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element elem = (Element) node;
	                    
	                    String code = elem.getElementsByTagName("Ccy").item(0).getTextContent();
	                    String name = elem.getElementsByTagName("CcyNm").item(1).getTextContent();
	                    
	                    currienciesMap.put(code,name.toUpperCase());
	                    
	                }
	            }

	        } catch (Exception ex) {
	          System.out.println(ex.getMessage());
	        }
		  
		  //Removing EURO
		  currienciesMap.remove("EUR");
		  
		  //Removing US Dollar Next Day
		  currienciesMap.remove("USN");
		  
		  
		  //Sorting map. Returning Linked because simple HashMap makes no guarantees about the iteration order
		  Map<String, String> sortedCurrienciesMap = currienciesMap.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())
				  .collect(Collectors.toMap(
				    Map.Entry::getKey, 
				    Map.Entry::getValue, 
				    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		  
		  return sortedCurrienciesMap;
	  }  
}
