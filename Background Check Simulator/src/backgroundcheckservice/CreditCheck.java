package backgroundcheckservice;

import org.javatuples.Pair;

public interface CreditCheck {
  static Pair<Boolean, String>  evaluate(String ssn) {
    if(ssn.charAt(0) == '7')
      return new Pair<Boolean, String>(true, "");
      
    return new Pair <Boolean, String>  (false, "poor credit score");
  }
}