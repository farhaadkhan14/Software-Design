package backgroundcheckservice;

import org.javatuples.Pair;

public interface CitizenshipCheck {
  static  Pair<Boolean, String> evaluate (String ssn) {
    if(ssn.charAt(2) == '3')
      return new Pair <Boolean, String>(true, "");
      
    return new Pair <Boolean, String>(false, "not a citizen");
  }
}