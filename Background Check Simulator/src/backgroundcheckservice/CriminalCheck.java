package backgroundcheckservice;

import org.javatuples.*;

public interface CriminalCheck {
  static Pair<Boolean, String> evaluate (String ssn) {
    if(ssn.charAt(1) == '7')
      return new Pair <Boolean, String>(true, "");
      
    return new Pair <Boolean, String>(false, "crimes found");
  }
}