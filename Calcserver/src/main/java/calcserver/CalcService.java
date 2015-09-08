package calcserver;

import java.util.Collection;

public interface CalcService
{
  /**
   * Execute calculations based on an instrument's price. This may involve
   * computationally intensive tasks.
   * 
   * @return collection of calculation results. This will never be
   *         <code>null</code>.
   */
  public Collection<CalcResult> calculate(String instrumentId, Price price) throws CalcException;
  
  @SuppressWarnings("serial")
  public abstract class CalcException extends Exception
  {
    public abstract String getInstrumentId(); 
  }
}
