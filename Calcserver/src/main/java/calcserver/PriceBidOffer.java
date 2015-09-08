package calcserver;

import java.math.BigDecimal;

/**
 * This is an example of a Price, that have bid and offer values
 * @author AZZABI
 *
 */
public class PriceBidOffer extends Price {
	
	// bid price of instrument
	BigDecimal bid;
	
	// offer price of instrument
	BigDecimal offer;
	
	/**
	 * @param bid
	 * @param offer
	 */
	public PriceBidOffer(BigDecimal bid, BigDecimal offer) {
		super();
		this.bid = bid;
		this.offer = offer;
	}

	/**
	 * @return the bid
	 */
	public BigDecimal getBid() {
		return bid;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	/**
	 * @return the offer
	 */
	public BigDecimal getOffer() {
		return offer;
	}

	/**
	 * @param offer the offer to set
	 */
	public void setOffer(BigDecimal offer) {
		this.offer = offer;
	}
	
}
