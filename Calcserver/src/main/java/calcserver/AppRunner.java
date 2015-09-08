package calcserver;


import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class for application starting
 * @author AZZABI
 *
 */
public class AppRunner {
	
	final static Logger logger = Logger.getLogger(AppRunner.class);

    /**
     * Main method.
     */
    public static void main(String[] args) {
    	
    	// spring context initialization 
        logger.info("Initializing Spring context.");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        logger.info("Spring context initialized.");

        // getting the PriceListener service
        PriceListener priceListener = (PriceListener) applicationContext.getBean("priceListener");
        
        // create dummy info
        Price price = new PriceBidOffer(new BigDecimal(81.7), new BigDecimal(81.75));
        
        // call the handlePrice implementation
        priceListener.handlePrice("HKSE.1", price);
        priceListener.handlePrice("HKSE.2", price);
        priceListener.handlePrice("HKSE.3", price);
        priceListener.handlePrice("HKSE.4", price);
        priceListener.handlePrice("HKSE.5", price);
    	
    }

}
               