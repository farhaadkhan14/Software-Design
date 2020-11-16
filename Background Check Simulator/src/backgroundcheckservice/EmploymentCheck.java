package backgroundcheckservice;

import org.javatuples.Pair;

public interface EmploymentCheck {
  static Pair<Boolean, String> evaluate (String ssn) {
    if(ssn.charAt(8) == '7')
      return new Pair<Boolean, String>(true, "");
      
    return new Pair <Boolean, String>  (false, "incorrect employment");
  }

}