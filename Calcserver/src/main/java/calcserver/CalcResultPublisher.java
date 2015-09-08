package calcserver;

import java.util.Collection;

public interface CalcResultPublisher
{  
  /**
   * Publish multiple calculation results to the rest of the system.
   */
  public void publish(Collection<CalcResult> calcResult);
}
