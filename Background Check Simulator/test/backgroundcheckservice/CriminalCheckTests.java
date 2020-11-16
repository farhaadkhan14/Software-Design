package backgroundcheckservice;

import org.javatuples.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriminalCheckTests {
  @Test
  void goodCriminalRecord() {
    assertAll(
      ()-> assertEquals(new Pair <Boolean, String> (true, ""),
        CriminalCheck.evaluate("574393383"))
    );
  }

  @Test
  void badCriminalRecord() {
    assertAll(
      ()-> assertEquals(new Pair <Boolean, String> (false, "crimes found"),
        CriminalCheck.evaluate("423312294"))
    );
  }
}