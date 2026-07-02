package by.alex.array.service;

import by.alex.array.entity.IntArrayEntity;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface ArrayCalculationService {

  OptionalInt findMin(IntArrayEntity array);

  OptionalInt findMax(IntArrayEntity array);

  OptionalInt calculateSum(IntArrayEntity array);

  OptionalDouble calculateAverage(IntArrayEntity array);
}
