package backgroundcheckservice;

import org.javatuples.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CitizenshipCheckTests {
  @Test
  void isACitizen() {
    assertAll(
      ()-> assertEquals(new Pair <Boolean, String> (true, ""),
        CitizenshipCheck.evaluate("423312295"))
    );
  }

  @Test
  void isNotACitizen() {
    assertAll(
      ()-> assertEquals(new Pair <Boolean, String> (false, "not a citizen"),
        CitizenshipCheck.evaluate("827591289"))
    );
  }
}
