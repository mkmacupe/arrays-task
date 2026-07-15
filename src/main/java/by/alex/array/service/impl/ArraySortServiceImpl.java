package by.alex.array.service.impl;

import by.alex.array.entity.IntArrayEntity;
import by.alex.array.service.ArraySortService;

public class ArraySortServiceImpl implements ArraySortService {

  @Override
  public int[] bubbleSort(IntArrayEntity array) {
    int[] values = array.getValues();
    for (int i = 0; i < values.length - 1; i++) {
      for (int j = 0; j < values.length - 1 - i; j++) {
        if (values[j] > values[j + 1]) {
          int temp = values[j];
          values[j] = values[j + 1];
          values[j + 1] = temp;
        }
      }
    }
    return values;
  }

  @Override
  public int[] insertionSort(IntArrayEntity array) {
    int[] values = array.getValues();
    for (int i = 1; i < values.length; i++) {
      int key = values[i];
      int j = i - 1;
      while (j >= 0 && values[j] > key) {
        values[j + 1] = values[j];
        j--;
      }
      values[j + 1] = key;
    }
    return values;
  }
}