package calcserver;

public interface PriceListener
{
  /**
   * Called by a price publisher when an instrument's price changes.
   * 
   * @param instrumentId
   *          unique string identifying the instrument
   * @param price
   *          snapshot of the instrument's current price
   */
  public void handlePrice(String instrumentId, Price price);
}
