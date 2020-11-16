package backgroundcheckservice;

import org.javatuples.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmploymentCheckTests {
  @Test
  void goodEmploymentHistory() {
    assertAll(
      ()-> assertEquals(new Pair <Boolean, String> (true, ""),
        EmploymentCheck.evaluate("534393387"))
    );
  }

  @Test
  void badEmploymentHistory() {
    assertAll(
      ()-> assertEquals(
        new Pair <Boolean, String> (false, "incorrect employment"),
        EmploymentCheck.evaluate("423312294"))
    );
  }
}
