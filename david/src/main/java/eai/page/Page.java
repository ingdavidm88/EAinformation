package eai.page;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.example.david.dto.Constants;
import com.example.david.model.LogError;
import com.example.david.service.LogErrorService;

public class Page {
	
    public Element element(String url, String match, int numberOfRetries, String userAgent){
        Element element = null;
        int tries = 0;
        
        while (tries <  numberOfRetries) {
        	try {
        		Document document = 
            			Jsoup
            				.connect(url)
            				.userAgent(userAgent)
            				.get();
        		
        		element = document.getElementById(match);
        		
        		tries += 1;
        		
        		if(element != null)
        			break;
        		
        		Thread.sleep(500);
        	}catch (Exception e) {
        		tries += 1;
        		element = null;
			}
        }
        
        return element;
    }
    
    public BigDecimal convertTo(LogErrorService logErrorService, String userName) {
    	BigDecimal price = BigDecimal.ZERO;
    	try {
			Document firstsDiv = Jsoup.connect("https://www.google.com.co/search?q=1+usd+to+cop").get();
			String priceStr =
					firstsDiv.getElementsByClass("dDoNo").get(0).text()
					.replaceAll(" Colombian pesos", "")
					.replaceAll(" ","");
			price = new BigDecimal(priceStr);
		} catch (Exception exception) {
			logErrorService.save(new LogError(exception, userName, "convertTo"));
		}
    	
    	return price.setScale(2, RoundingMode.DOWN);
    }
    
    public String priceValue(
            BigDecimal usdToCop,
            String priceUSD,
            BigDecimal shippingUsd,
            BigDecimal shippingCop){
        
    	DecimalFormat formateador = new DecimalFormat(Constants.CURRENCYFORMAT.val());
    	
    	BigDecimal priceCop = new BigDecimal(priceUSD).multiply(usdToCop);
        BigDecimal percentageGain = new BigDecimal(Constants.PERCENTAGE_GAIN.val());
        BigDecimal gain = priceCop.multiply(percentageGain).divide(new BigDecimal("100"));
        BigDecimal total = priceCop.add(gain).add(shippingUsd).add(shippingCop);
    
        return formateador.format (total);
    }
}
