package by.alex.array.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import by.alex.array.entity.IntArrayEntity;
import by.alex.array.service.impl.ArraySortServiceImpl;
import org.junit.jupiter.api.Test;

class ArraySortServiceTest {

  private final ArraySortService sortService = new ArraySortServiceImpl();

  @Test
  void shouldSortArrayUsingBubbleSort() {
    // given
    IntArrayEntity array = new IntArrayEntity(1L, new int[] {5, 1, 4, 2, 8});
    int[] expected = {1, 2, 4, 5, 8};

    // when
    int[] result = sortService.bubbleSort(array);

    // then
    assertArrayEquals(expected, result);
  }

  @Test
  void shouldSortArrayUsingInsertionSort() {
    // given
    IntArrayEntity array = new IntArrayEntity(1L, new int[] {5, 1, 4, 2, 8});
    int[] expected = {1, 2, 4, 5, 8};

    // when
    int[] result = sortService.insertionSort(array);

    // then
    assertArrayEquals(expected, result);
  }

  @Test
  void shouldNotMutateOriginalEntityAfterSort() {
    // given
    int[] originalValues = {5, 1, 4, 2, 8};
    IntArrayEntity array = new IntArrayEntity(1L, originalValues);

    // when
    sortService.bubbleSort(array);
    sortService.insertionSort(array);

    // then
    assertArrayEquals(originalValues, array.getValues());
  }

  @Test
  void shouldReturnEmptyArrayWhenBubbleSortingEmptyArray() {
    // given
    IntArrayEntity array = new IntArrayEntity(1L, new int[0]);
    int[] expected = new int[0];

    // when
    int[] result = sortService.bubbleSort(array);

    // then
    assertArrayEquals(expected, result);
  }

  @Test
  void shouldReturnEmptyArrayWhenInsertionSortingEmptyArray() {
    // given
    IntArrayEntity array = new IntArrayEntity(1L, new int[0]);
    int[] expected = new int[0];

    // when
    int[] result = sortService.insertionSort(array);

    // then
    assertArrayEquals(expected, result);
  }
}
