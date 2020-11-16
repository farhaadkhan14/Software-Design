package backgroundcheckservice;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCheckTests {
  @Test
  void canary() {
    assertTrue(true);
  }

  @Test
  void goodCredit(){
    assertAll(
      ()-> assertEquals(new Pair <Boolean, String> (true, ""),
        CreditCheck.evaluate("787402759"))
    );
  }

  @Test
  void badCredit(){
    assertAll(
      () ->
        assertEquals(new Pair<Boolean, String>(false, "poor credit score"),
          CreditCheck.evaluate("822581759"))
    );
  }
}