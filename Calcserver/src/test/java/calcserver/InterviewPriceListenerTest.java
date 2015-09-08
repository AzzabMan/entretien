package calcserver;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * PriceListener implementation service
 * @author AZZABI
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class InterviewPriceListenerTest {
	
	@Autowired
	PriceListener PriceListener;
	
	@Test
	public void TestHandlePrice() {
		PriceListener.handlePrice("TEST INSTRUMENT", new PriceBidOffer(new BigDecimal(1.2), new BigDecimal(1.4)));
	}

}
