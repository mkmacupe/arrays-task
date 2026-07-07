package by.alex.array.service.impl;

import by.alex.array.entity.IntArrayEntity;
import by.alex.array.service.ArrayCalculationService;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class ArrayCalculationServiceImpl implements ArrayCalculationService {

  @Override
  public OptionalInt findMin(IntArrayEntity array) {
    int[] values = array.getValues();
    if (values.length == 0) {
      return OptionalInt.empty();
    }
    int min = values[0];
    for (int value : values) {
      if (value < min) {
        min = value;
      }
    }
    return OptionalInt.of(min);
  }

  @Override
  public OptionalInt findMax(IntArrayEntity array) {
    int[] values = array.getValues();
    if (values.length == 0) {
      return OptionalInt.empty();
    }
    int max = values[0];
    for (int value : values) {
      if (value > max) {
        max = value;
      }
    }
    return OptionalInt.of(max);
  }

  @Override
  public OptionalLong calculateSum(IntArrayEntity array) {
    int[] values = array.getValues();
    if (values.length == 0) {
      return OptionalLong.empty();
    }
    long sum = 0L;
    for (int value : values) {
      sum += value;
    }
    return OptionalLong.of(sum);
  }

  @Override
  public OptionalDouble calculateAverage(IntArrayEntity array) {
    int[] values = array.getValues();
    if (values.length == 0) {
      return OptionalDouble.empty();
    }
    long sum = 0L;
    for (int value : values) {
      sum += value;
    }
    return OptionalDouble.of((double) sum / values.length);
  }
}
