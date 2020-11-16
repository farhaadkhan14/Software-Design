package backgroundcheckservice;

import org.javatuples.Pair;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface BackgroundCheck {

  @SafeVarargs
  static Pair<Boolean, String> evaluateCriteria(
    String ssn, Function<String,  Pair<Boolean, String>> ... criteria) {

    String reason = Stream.of(criteria)
      .map(criterion -> criterion.apply(ssn))
      .filter(response -> !response.getValue0())
      .map(response -> response.getValue1())
      .collect(Collectors.joining(", "));

    if(reason.isEmpty())
      return new Pair<Boolean, String> (true, "");

    return new Pair<Boolean, String> (false, reason);

  }
}
