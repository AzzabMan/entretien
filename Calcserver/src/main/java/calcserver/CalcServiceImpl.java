package calcserver;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * Dummy service class of calculation 
 * @author AZZABI
 *
 */
public class CalcServiceImpl implements CalcService {

	final static Logger logger = Logger.getLogger(CalcServiceImpl.class);
	
	@Override
	public Collection<CalcResult> calculate(final String instrumentId,
			Price price) throws CalcException {
		logger.debug("Performing caculation for instrument instrumentId: " + instrumentId);
		// Doing some calculations here using Price info ...
		
		Collection<CalcResult> res = new ArrayList<>();
		res.add(new CalcResult() {
			@Override
			public String getInstrumentId() {
				return instrumentId;
			}
		});

		return res;
	}

}
