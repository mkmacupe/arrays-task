package by.alex.array.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.alex.array.entity.IntArrayEntity;
import by.alex.array.service.impl.ArrayCalculationServiceImpl;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import org.junit.jupiter.api.Test;

class ArrayCalculationServiceTest {

  private final ArrayCalculationService calculationService = new ArrayCalculationServiceImpl();

  @Test
  void shouldFindMinForStandardArray() {
    // given
    IntArrayEntity array = new IntArrayEntity(1L, new int[] {3, 1, 4, 1, 5});

    // when
    OptionalInt min = calculationService.findMin(array);

    // then
    assertEquals(1, min.orElseThrow());
  }

  @Test
  void shouldFindMaxForStandardArray() {
    // given
    IntArrayEntity array = new IntArrayEntity(1L, new int[] {3, 1, 4, 1, 5});

    // when
    OptionalInt max = calculationService.findMax(array);

    // then
    assertEquals(5, max.orElseThrow());
  }

  @Test
  void shouldCalculateSumForStandardArray() {
    // given
    IntArrayEntity array = new IntArrayEntity(1L, new int[] {3, 1, 4, 1, 5});

    // when
    OptionalLong sum = calculationService.calculateSum(array);

    // then
    assertEquals(14L, sum.orElseThrow());
  }

  @Test
  void shouldCalculateAverageForStandardArray() {
    // given
    IntArrayEntity array = new IntArrayEntity(1L, new int[] {3, 1, 4, 1, 5});

    // when
    OptionalDouble average = calculationService.calculateAverage(array);

    // then
    assertEquals(2.8, average.orElseThrow(), 0.0001);
  }

  @Test
  void shouldCalculateSumWithLongAccumulatorForIntOverflow() {
    // given
    IntArrayEntity array = new IntArrayEntity(2L, new int[] {Integer.MAX_VALUE, 1});

    // when
    OptionalLong sum = calculationService.calculateSum(array);

    // then
    assertEquals(2147483648L, sum.orElseThrow());
  }

  @Test
  void shouldReturnEmptyOptionalMinForEmptyArray() {
    // given
    IntArrayEntity array = new IntArrayEntity(3L, new int[0]);

    // when
    OptionalInt min = calculationService.findMin(array);

    // then
    assertTrue(min.isEmpty());
  }

  @Test
  void shouldReturnEmptyOptionalMaxForEmptyArray() {
    // given
    IntArrayEntity array = new IntArrayEntity(3L, new int[0]);

    // when
    OptionalInt max = calculationService.findMax(array);

    // then
    assertTrue(max.isEmpty());
  }

  @Test
  void shouldReturnEmptyOptionalSumForEmptyArray() {
    // given
    IntArrayEntity array = new IntArrayEntity(3L, new int[0]);

    // when
    OptionalLong sum = calculationService.calculateSum(array);

    // then
    assertTrue(sum.isEmpty());
  }

  @Test
  void shouldReturnEmptyOptionalAverageForEmptyArray() {
    // given
    IntArrayEntity array = new IntArrayEntity(3L, new int[0]);

    // when
    OptionalDouble average = calculationService.calculateAverage(array);

    // then
    assertTrue(average.isEmpty());
  }
}
