package by.alex.array.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.alex.array.entity.IntArrayEntity;
import org.junit.jupiter.api.Test;

public class SpecificationTest {

  @Test
  public void testSumSpecificationInRangePositive() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[]{1, 2, 3});

    // when
    boolean result = new SumSpecification(5L, 10L).test(entity);

    // then
    assertTrue(result);
  }

  @Test
  public void testSumSpecificationOutOfRangeNegative() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[]{1, 2, 3});

    // when
    boolean result = new SumSpecification(7L, 10L).test(entity);

    // then
    assertFalse(result);
  }

  @Test
  public void testAverageSpecificationBelowThresholdPositive() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[]{2, 4, 6});

    // when
    boolean result = new AverageSpecification(5.0).test(entity);

    // then
    assertTrue(result);
  }

  @Test
  public void testAverageSpecificationNotBelowThresholdNegative() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[]{2, 4, 6});

    // when
    boolean result = new AverageSpecification(4.0).test(entity);

    // then
    assertFalse(result);
  }

  @Test
  public void testMaxSpecificationEqualsThresholdPositive() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[]{1, 5, 3});

    // when
    boolean result = new MaxSpecification(5).test(entity);

    // then
    assertTrue(result);
  }

  @Test
  public void testMaxSpecificationNotEqualsThresholdNegative() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[]{1, 5, 3});

    // when
    boolean result = new MaxSpecification(4).test(entity);

    // then
    assertFalse(result);
  }

  @Test
  public void testMinSpecificationNotBelowThresholdPositive() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[]{3, 5, 7});

    // when
    boolean result = new MinSpecification(3).test(entity);

    // then
    assertTrue(result);
  }

  @Test
  public void testMinSpecificationBelowThresholdNegative() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[]{3, 5, 7});

    // when
    boolean result = new MinSpecification(4).test(entity);

    // then
    assertFalse(result);
  }

  @Test
  public void testLengthSpecificationAboveThresholdPositive() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[]{1, 2, 3});

    // when
    boolean result = new LengthSpecification(2).test(entity);

    // then
    assertTrue(result);
  }

  @Test
  public void testLengthSpecificationNotAboveThresholdNegative() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[]{1, 2, 3});

    // when
    boolean result = new LengthSpecification(3).test(entity);

    // then
    assertFalse(result);
  }
}