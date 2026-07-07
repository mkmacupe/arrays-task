package by.alex.array.service;

import by.alex.array.entity.IntArrayEntity;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public interface ArrayCalculationService {

  OptionalInt findMin(IntArrayEntity array);

  OptionalInt findMax(IntArrayEntity array);

  OptionalLong calculateSum(IntArrayEntity array);

  OptionalDouble calculateAverage(IntArrayEntity array);
}
