package lt.kono.currencyRates.services;

import org.springframework.stereotype.Service;
import lt.kono.currencyRates.Rate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLService {

	  private final Logger logger = LoggerFactory.getLogger(XMLService.class);
	  
	  public List<Rate> getRates(String currency,  String lowDate, String highDate){
		  
		  List<Rate> rates = null;
		  
		  try {
	            String URL = "https://www.lb.lt/webservices/ExchangeRates/ExchangeRates.asmx/"
	            		+ "getExchangeRatesByCurrency"
	            		+ "?Currency=" + currency
	            		+ "&DateLow=" + lowDate
	            		+ "&DateHigh=" + highDate;

	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(URL);
	            doc.getDocumentElement().normalize();
	            
	            NodeList nodeList = doc.getElementsByTagName("item");
	            
	            rates = new ArrayList<>();

	            for (int i = 0; i < nodeList.getLength(); i++) {

	                Node node = nodeList.item(i);

	                if(node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element elem = (Element) node;
	                    Rate rate = new Rate(
	                            elem.getElementsByTagName("date").item(0).getTextContent(),
	                            elem.getElementsByTagName("currency").item(0).getTextContent(),
	                            Double.parseDouble(elem.getElementsByTagName("rate").item(0).getTextContent())
	                    );
	                    System.out.println(rate);
	                    rates.add(rate);
	                }
	            }

	        } catch (Exception ex) {
	            logger.error(ex.getMessage());
	        }
		  
		  return rates;
	  }
	  
	  
}
