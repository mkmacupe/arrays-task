package by.alex.array.entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import by.alex.array.exception.ArrayProcessingException;
import org.junit.jupiter.api.Test;

public class IntArrayEntityTest {

  @Test
  public void testSetElementChangesValuePositive() throws ArrayProcessingException {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});

    // when
    entity.setElement(1, 99);

    // then
    assertArrayEquals(new int[] {1, 99, 3}, entity.getValues());
  }

  @Test
  public void testSetElementOutOfBoundsThrowsExceptionNegative() {
    // when
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});

    // then
    assertThrows(ArrayProcessingException.class, () -> entity.setElement(5, 99));
  }

  @Test
  public void testGetValuesReturnsDefensiveCopyPositive() {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});
    int[] snapshot = entity.getValues();

    // when
    snapshot[0] = 999;

    // then
    assertArrayEquals(new int[] {1, 2, 3}, entity.getValues());
  }
}