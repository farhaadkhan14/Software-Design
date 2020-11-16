package backgroundcheckservice;

import org.javatuples.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackgroundCheckTests {

  @Test
  void noFailures () {
    assertEquals(new Pair<Boolean, String> (true, ""),
      BackgroundCheck.evaluateCriteria("773456677",
        CriminalCheck::evaluate,
        CitizenshipCheck::evaluate,
        EmploymentCheck::evaluate,
        CreditCheck::evaluate));
  }

  @Test
  void oneFailure(){
    assertEquals(new Pair<Boolean, String> (false,
        "crimes found"),
      BackgroundCheck.evaluateCriteria("703456677",
        CriminalCheck::evaluate,
        CitizenshipCheck::evaluate,
        EmploymentCheck::evaluate,
        CreditCheck::evaluate));
  }

  @Test
  void twoFailures () {
    assertEquals(new Pair<Boolean, String> (false,
        "crimes found, "
        + "poor credit score"),
      BackgroundCheck.evaluateCriteria("003456677",
        CriminalCheck::evaluate,
        CitizenshipCheck::evaluate,
        EmploymentCheck::evaluate,
        CreditCheck::evaluate));
  }

  @Test
  void threeFailures() {
    assertEquals(new Pair<Boolean, String> (false,
        "not a citizen, "
        + "poor credit score, "
        + "incorrect employment"),
      BackgroundCheck.evaluateCriteria("070456670",
        CriminalCheck::evaluate,
        CitizenshipCheck::evaluate,
        CreditCheck::evaluate,
        EmploymentCheck::evaluate));
  }
}