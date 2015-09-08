package calcserver;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import calcserver.CalcService.CalcException;

/**
 * PriceListener implementation service
 * @author AZZABI
 *
 */
public class InterviewPriceListener implements PriceListener {

	final static Logger logger = Logger.getLogger(InterviewPriceListener.class);
	
	// create an ExecutorService to handle parallel executions
	// 10 is the threads pools executor size, this should be fixed according to calls number
	final static ExecutorService executor = Executors.newFixedThreadPool(10);
	
	@Autowired
	private CalcService calcService;
	
	@Autowired
	private CalcResultPublisher calcResultPublisher;
	
	/**
	 * Default constructor for Spring instantiation
	 */
	public InterviewPriceListener() {}
	

	@Override
	public void handlePrice(final String instrumentId, final Price price) {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				handlePriceTask(instrumentId, price);
			}
		});
//		try {
//			executor.awaitTermination(100, TimeUnit.SECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		executor.shutdown();
	}
	
	public void handlePriceTask(String instrumentId, Price price) {
		
		// check the method attributes
		if(StringUtils.isEmpty(instrumentId)) {
			throw new IllegalArgumentException("InstrumentId is empty or null");
		}
		if(price == null ) {
			throw new IllegalArgumentException("price paramater is null");
		}
		
		logger.info("Handling price for instrument " + instrumentId);
		try {
			
			Collection<CalcResult> calcResult = calcService.calculate(instrumentId, price);
			
			if(!CollectionUtils.isEmpty(calcResult)) {
				calcResultPublisher.publish(calcResult);
			}
			
		} catch (CalcException e) {
			logger.error("Error while calculating price for instrument: " + instrumentId, e);
		}
	}

}
