package by.alex.array.comparator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.alex.array.entity.IntArrayEntity;
import org.junit.jupiter.api.Test;

public class ArrayComparatorsTest {

  @Test
  public void testByIdOrdersByIdPositive() {
    // given
    IntArrayEntity bigger = new IntArrayEntity(2L, new int[] {9});

    // when
    IntArrayEntity smaller = new IntArrayEntity(1L, new int[] {9});

    // then
    assertTrue(ArrayComparators.BY_ID.compare(bigger, smaller) > 0);
  }

  @Test
  public void testBySumOrdersBySumPositive() {
    // given
    IntArrayEntity small = new IntArrayEntity(1L, new int[] {1, 2}); // sum 3

    // when
    IntArrayEntity big = new IntArrayEntity(2L, new int[] {10}); // sum 10

    // then
    assertTrue(ArrayComparators.BY_SUM.compare(small, big) < 0);
  }

  @Test
  public void testByLengthOrdersByLengthPositive() {
    // given
    IntArrayEntity shortArray = new IntArrayEntity(1L, new int[] {1});

    // when
    IntArrayEntity longArray = new IntArrayEntity(2L, new int[] {1, 2, 3});

    // then
    assertTrue(ArrayComparators.BY_LENGTH.compare(shortArray, longArray) < 0);
  }

  @Test
  public void testByFirstElementSafeWithEmptyArrayPositive() {
    // given
    IntArrayEntity empty = new IntArrayEntity(1L, new int[0]);

    // when
    IntArrayEntity nonEmpty = new IntArrayEntity(2L, new int[] {5});

    // then
    assertDoesNotThrow(() -> ArrayComparators.BY_FIRST_ELEMENT.compare(empty, nonEmpty));
    assertTrue(ArrayComparators.BY_FIRST_ELEMENT.compare(empty, nonEmpty) < 0);
  }
}