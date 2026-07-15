package by.alex.array.specification;

import by.alex.array.entity.IntArrayEntity;
import java.util.Arrays;
import java.util.function.Predicate;

public class AverageSpecification implements Predicate<IntArrayEntity> {
  private final double threshold;

  public AverageSpecification(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(IntArrayEntity entity) {
    double average =
        Arrays.stream(entity.getValues())
            .average()
            .orElse(0.0);
    return average < threshold;
  }
}