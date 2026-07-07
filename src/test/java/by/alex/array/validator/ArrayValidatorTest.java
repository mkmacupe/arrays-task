package by.alex.array.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.alex.array.validator.impl.ArrayValidatorImpl;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ArrayValidatorTest {

  private final ArrayValidator validator = new ArrayValidatorImpl();

  @ParameterizedTest
  @MethodSource("provideValidLines")
  void shouldReturnTrueForValidLines(String line) {
    boolean result = validator.isValidNumberLine(line);
    assertTrue(result);
  }

  @ParameterizedTest
  @MethodSource("provideInvalidLines")
  void shouldReturnFalseForInvalidLines(String line) {
    boolean result = validator.isValidNumberLine(line);
    assertFalse(result);
  }

  static Stream<String> provideValidLines() {
    return Stream.of(
        "1, 2, 3",
        "10, 20, 30, 40, 50",
        "-5, -10, -15, -20",
        "100, -50, -25",
        "42, 17, 99, 3, 56, 8",
        "2, 4, 8, 16, 32, 64, 128");
  }

  static Stream<String> provideInvalidLines() {
    return Stream.of(
        "1y1 21 32",
        "1, 2, x3, 6..5, 77",
        "12 34 5a 7 90",
        "hello 3 8 9",
        "1; 2; 3",
        "11- 2 – 42-");
  }
}
