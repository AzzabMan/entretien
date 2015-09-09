package calcserver;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import calcserver.CalcService.CalcException;

/**
 * PriceListener implementation service
 * 
 * @author AZZABI
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
public class InterviewPriceListenerTest {

	@Mock
	private CalcServiceImpl calcService;
	// private CalcServiceImpl calcService = mock(CalcServiceImpl.class);

	@InjectMocks
	private InterviewPriceListener priceListener;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_high_number_loading_of_prices() {

		for (int i = 0; i < 10000; i++) {
			priceListener.handlePrice("HKSE." + i, new PriceBidOffer(new BigDecimal(1.2), new BigDecimal(1.4)));
		}
		try {
			verify(calcService, times(10000)).calculate(anyString(),(Price) anyObject());
		} catch (CalcException e) {
			fail("Exception occurred");
		}
	}

}
