package by.alex.array.specification;

import by.alex.array.entity.IntArrayEntity;
import java.util.Arrays;
import java.util.function.Predicate;

public class MaxSpecification implements Predicate<IntArrayEntity> {
  private final int threshold;

  public MaxSpecification(int threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(IntArrayEntity entity) {
    int max =
        Arrays.stream(entity.getValues())
            .max()
            .orElse(Integer.MIN_VALUE);
    return max == threshold;
  }
}