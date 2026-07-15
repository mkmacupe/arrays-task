package by.alex.array.warehouse;

import static org.junit.jupiter.api.Assertions.*;

import by.alex.array.entity.ArrayParameters;
import by.alex.array.entity.IntArrayEntity;
import by.alex.array.exception.ArrayProcessingException;
import by.alex.array.observer.impl.ArrayObserverImpl;
import by.alex.array.repository.impl.InMemoryArrayRepository;
import java.lang.reflect.Field;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WarehouseTest {

  @BeforeEach
  public void setUp() throws Exception {
    resetSingleton(Warehouse.class);
    resetSingleton(InMemoryArrayRepository.class);
  }

  private void resetSingleton(Class<?> clazz) throws Exception {
    Field instanceField = clazz.getDeclaredField("instance");
    instanceField.setAccessible(true);
    instanceField.set(null, null);
  }

  @Test
  public void testPutAndGetPositive() {
    // given
    ArrayParameters expected = new ArrayParameters(1, 10, 15L, 5.0);
    Warehouse.getInstance().put(1L, expected);

    // when
    Optional<ArrayParameters> actualOpt = Warehouse.getInstance().get(1L);

    // then
    assertTrue(actualOpt.isPresent());
    assertEquals(expected, actualOpt.get());
  }

  @Test
  public void testGetNegative() {
    // when
    Optional<ArrayParameters> actualOpt = Warehouse.getInstance().get(999L);

    // then
    assertFalse(actualOpt.isPresent());
  }

  @Test
  public void testRemovePositive() {
    // given
    ArrayParameters params = new ArrayParameters(1, 10, 15L, 5.0);
    Warehouse.getInstance().put(1L, params);

    // when
    Warehouse.getInstance().remove(1L);

    // then
    assertFalse(Warehouse.getInstance().get(1L).isPresent());
  }

  @Test
  public void testGetReturnsValueAfterPutPositive() {
    // given
    ArrayParameters params = new ArrayParameters(1, 10, 15L, 5.0);

    // when
    Warehouse.getInstance().put(1L, params);

    // then
    assertTrue(Warehouse.getInstance().get(1L).isPresent());
  }

  @Test
  public void testObserverAutoUpdatePositive() throws ArrayProcessingException {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});
    entity.attach(new ArrayObserverImpl());
    entity.setElement(0, 10);
    ArrayParameters expected = new ArrayParameters(2, 10, 15L, 5.0);

    // when
    Optional<ArrayParameters> actualOpt = Warehouse.getInstance().get(1L);

    // then
    assertTrue(actualOpt.isPresent());
    assertEquals(expected, actualOpt.get());
  }

  @Test
  public void testObserverEmptyArrayNegative() throws ArrayProcessingException {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});
    entity.attach(new ArrayObserverImpl());

    // when
    entity.setValues(new int[0]);

    // then
    assertFalse(Warehouse.getInstance().get(1L).isPresent());
  }

  @Test
  public void testDetachObserverPositive() throws ArrayProcessingException {
    // given
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});
    entity.attach(new ArrayObserverImpl());
    InMemoryArrayRepository.getInstance().add(entity);
    InMemoryArrayRepository.getInstance().remove(entity);

    // when
    entity.setElement(0, 99);

    // then
    assertFalse(Warehouse.getInstance().get(1L).isPresent());
  }

  @Test
  public void testGetInstanceReturnsSameInstancePositive() {
    // given
    Warehouse first = Warehouse.getInstance();

    // when
    Warehouse second = Warehouse.getInstance();

    // then
    assertSame(first, second);
  }
}