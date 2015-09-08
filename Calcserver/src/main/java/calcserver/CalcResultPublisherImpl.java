package calcserver;

import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * Dummy service class to simulate publishing
 * @author AZZABI
 *
 */
public class CalcResultPublisherImpl implements CalcResultPublisher {

	final static Logger logger = Logger.getLogger(InterviewPriceListener.class);
	
	@Override
	public void publish(Collection<CalcResult> calcResult) {
		logger.info("Publishing multiple calculation results to the rest of the system.");
	}

}
