package by.alex.array.specification;

import by.alex.array.entity.IntArrayEntity;
import java.util.Arrays;
import java.util.function.Predicate;

public class MinSpecification implements Predicate<IntArrayEntity> {
  private final int threshold;

  public MinSpecification(int threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(IntArrayEntity entity) {
    int min =
        Arrays.stream(entity.getValues())
            .min()
            .orElse(Integer.MAX_VALUE);
    return min >= threshold;
  }
}
