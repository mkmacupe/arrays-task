package by.alex.array.factory.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.alex.array.entity.ArrayParameters;
import by.alex.array.entity.IntArrayEntity;
import by.alex.array.factory.ArrayFactory;
import by.alex.array.warehouse.Warehouse;
import java.lang.reflect.Field;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntArrayFactoryTest {

  @BeforeEach
  public void setUp() throws Exception {
    Field instanceField = Warehouse.class.getDeclaredField("instance");
    instanceField.setAccessible(true);
    instanceField.set(null, null);
  }

  @Test
  public void testCreateReturnsEntityWithGivenValuesPositive() {
    // given
    ArrayFactory factory = new IntArrayFactory();

    // when
    IntArrayEntity entity = factory.create(new int[] {1, 2, 3});

    // then
    assertArrayEquals(new int[] {1, 2, 3}, entity.getValues());
  }

  @Test
  public void testCreateAssignsIncrementingIdsPositive() {
    // given
    ArrayFactory factory = new IntArrayFactory();
    IntArrayEntity first = factory.create(new int[] {1});

    // when
    IntArrayEntity second = factory.create(new int[] {2});

    // then
    assertEquals(first.getId() + 1, second.getId());
  }

  @Test
  public void testCreateSeedsWarehousePositive() {
    // given
    ArrayFactory factory = new IntArrayFactory();
    IntArrayEntity entity = factory.create(new int[] {1, 2, 3});

    // when
    Optional<ArrayParameters> paramsOpt = Warehouse.getInstance().get(entity.getId());

    // then
    assertTrue(paramsOpt.isPresent());
    assertEquals(new ArrayParameters(1, 3, 6L, 2.0), paramsOpt.get());
  }
}