package calcserver;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import calcserver.CalcService.CalcException;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * PriceListener implementation service
 * 
 * @author AZZABI
 * 
 */
public class InterviewPriceListener implements PriceListener {

	final static Logger logger = Logger.getLogger(InterviewPriceListener.class);

	// using Guava library here to be able to set thread name
	final ThreadFactory threadFactory = new ThreadFactoryBuilder()
			.setNameFormat("Orders-%d").setDaemon(true).build();

	// create an ExecutorService to handle parallel executions
	// 6 is the threads pools executor size, this should be fixed according
	// server processors configuration
	final ExecutorService executorService = Executors.newFixedThreadPool(6,
			threadFactory);

	@Autowired
	private CalcService calcService;

	@Autowired
	private CalcResultPublisher calcResultPublisher;

	/**
	 * Default constructor for Spring instantiation
	 */
	public InterviewPriceListener() {
	}

	@Override
	public void handlePrice(final String instrumentId, final Price price) {
		
		Future<?> future = executorService.submit(new Runnable() {
			@Override
			public void run() {
				handlePriceTask(instrumentId, price);
			}
		});
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			executorService.awaitTermination(10000, TimeUnit.SECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		executorService.shutdown();
	}

	public void handlePriceTask(String instrumentId, Price price) {
		// check the method attributes
		if (StringUtils.isEmpty(instrumentId)) {
			throw new IllegalArgumentException("InstrumentId is empty or null");
		}
		if (price == null) {
			throw new IllegalArgumentException("price paramater is null");
		}

		logger.info("Handling price for instrument " + instrumentId);
		try {

			Collection<CalcResult> calcResult = calcService.calculate(
					instrumentId, price);

			if (!CollectionUtils.isEmpty(calcResult)) {
				calcResultPublisher.publish(calcResult);
			}
		} catch (CalcException e) {
			logger.error("Error while calculating price for instrument: "
					+ instrumentId, e);
		} catch (Exception e) {
			// catching all the Exceptions otherwise they will not be visible
			logger.error(
					"Exception occurred while calculating price for instrument: "
							+ instrumentId, e);
		}
	}
}
