package webISS;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static webISS.OpenNotifyWebService.*;

public class ISSWebServiceTests {

  @Test
  void fetchIssFlyOverDataReturnsTimestampReturnedByParseJSON() throws Exception {
    assertEquals(1570250881, ISSWebService.fetchIssFlyOverData(29.7604,-95.343631));
  }
}
