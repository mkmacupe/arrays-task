package by.alex.array.repository;

import static org.junit.jupiter.api.Assertions.*;

import by.alex.array.comparator.ArrayComparators;
import by.alex.array.entity.IntArrayEntity;
import by.alex.array.repository.impl.InMemoryArrayRepository;
import by.alex.array.specification.SumSpecification;
import by.alex.array.warehouse.Warehouse;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayRepositoryTest {

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
  public void testAddPositive() throws Exception {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();

    repo.add(entity);

    List<IntArrayEntity> expected = List.of(entity);
    List<IntArrayEntity> actual = repo.sort(ArrayComparators.BY_ID);

    assertEquals(expected, actual);
  }

  @Test
  public void testAddNullNegative() throws Exception {
    ArrayRepository repo = InMemoryArrayRepository.getInstance();

    repo.add(null);

    List<IntArrayEntity> actual = repo.sort(ArrayComparators.BY_ID);
    assertTrue(actual.isEmpty());
  }

  @Test
  public void testRemovePositive() throws Exception {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();

    repo.add(entity);
    repo.remove(entity);

    List<IntArrayEntity> actual = repo.sort(ArrayComparators.BY_ID);
    assertTrue(actual.isEmpty());
    assertFalse(Warehouse.getInstance().get(1L).isPresent());
  }

  @Test
  public void testRemoveNonExistentNegative() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();

    assertDoesNotThrow(() -> repo.remove(entity));
  }

  @Test
  public void testFindByPositive() throws Exception {
    IntArrayEntity entity1 = new IntArrayEntity(1L, new int[] {10});
    IntArrayEntity entity2 = new IntArrayEntity(2L, new int[] {20});
    IntArrayEntity entity3 = new IntArrayEntity(3L, new int[] {5});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();
    repo.add(entity1);
    repo.add(entity2);
    repo.add(entity3);

    List<IntArrayEntity> expected = Arrays.asList(entity1, entity2);
    List<IntArrayEntity> actual = repo.findBy(new SumSpecification(8L, 100L));

    assertEquals(new HashSet<>(expected), new HashSet<>(actual));
  }

  @Test
  public void testFindByEmptyResultNegative() throws Exception {
    IntArrayEntity entity1 = new IntArrayEntity(1L, new int[] {1});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();
    repo.add(entity1);

    List<IntArrayEntity> actual = repo.findBy(new SumSpecification(100L, 200L));

    assertTrue(actual.isEmpty());
  }

  @Test
  public void testFindByStreamPositive() throws Exception {
    IntArrayEntity entity1 = new IntArrayEntity(1L, new int[] {10});
    IntArrayEntity entity2 = new IntArrayEntity(2L, new int[] {20});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();
    repo.add(entity1);
    repo.add(entity2);

    List<IntArrayEntity> expected = List.of(entity2);
    List<IntArrayEntity> actual = repo.findByStream(new SumSpecification(15L, 100L));

    assertEquals(expected, actual);
  }

  @Test
  public void testFindBySumRangeBoundaryPositive() throws Exception {
    IntArrayEntity entity1 = new IntArrayEntity(1L, new int[] {1}); // sum: 1
    IntArrayEntity entity2 = new IntArrayEntity(2L, new int[] {5}); // sum: 5
    IntArrayEntity entity3 = new IntArrayEntity(3L, new int[] {10}); // sum: 10
    ArrayRepository repo = InMemoryArrayRepository.getInstance();
    repo.add(entity1);
    repo.add(entity2);
    repo.add(entity3);

    List<IntArrayEntity> expected = List.of(entity2);
    List<IntArrayEntity> actual = repo.findBy(new SumSpecification(3L, 8L));

    assertEquals(expected, actual);
  }

  @Test
  public void testSortById() throws Exception {
    IntArrayEntity entity1 = new IntArrayEntity(2L, new int[] {1});
    IntArrayEntity entity2 = new IntArrayEntity(1L, new int[] {2});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();
    repo.add(entity1);
    repo.add(entity2);

    List<IntArrayEntity> result = repo.sort(ArrayComparators.BY_ID);

    List<IntArrayEntity> expected = Arrays.asList(entity2, entity1);
    assertEquals(expected, result);
  }

  @Test
  public void testSortByLength() throws Exception {
    IntArrayEntity entity1 = new IntArrayEntity(1L, new int[] {1, 2, 3});
    IntArrayEntity entity2 = new IntArrayEntity(2L, new int[] {4});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();
    repo.add(entity1);
    repo.add(entity2);

    List<IntArrayEntity> result = repo.sort(ArrayComparators.BY_LENGTH);

    List<IntArrayEntity> expected = Arrays.asList(entity2, entity1);
    assertEquals(expected, result);
  }

  @Test
  public void testSortBySum() throws Exception {
    IntArrayEntity entity1 = new IntArrayEntity(1L, new int[] {10});
    IntArrayEntity entity2 = new IntArrayEntity(2L, new int[] {1, 2});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();
    repo.add(entity1);
    repo.add(entity2);

    List<IntArrayEntity> result = repo.sort(ArrayComparators.BY_SUM);

    List<IntArrayEntity> expected = Arrays.asList(entity2, entity1);
    assertEquals(expected, result);
  }

  @Test
  public void testSortByFirstElementSafeWithEmptyArray() throws Exception {
    IntArrayEntity entity1 = new IntArrayEntity(1L, new int[] {5});
    IntArrayEntity entity2 = new IntArrayEntity(2L, new int[0]);
    IntArrayEntity entity3 = new IntArrayEntity(3L, new int[] {-3});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();
    repo.add(entity1);
    repo.add(entity2);
    repo.add(entity3);

    List<IntArrayEntity> result = repo.sort(ArrayComparators.BY_FIRST_ELEMENT);

    List<IntArrayEntity> expected = Arrays.asList(entity3, entity2, entity1);
    assertEquals(expected, result);
  }

  @Test
  public void testGetInstanceReturnsSameInstancePositive() {
    InMemoryArrayRepository first = InMemoryArrayRepository.getInstance();
    InMemoryArrayRepository second = InMemoryArrayRepository.getInstance();

    assertSame(first, second);
  }

  @Test
  public void testSortDoesNotModifyStoragePositive() {
    IntArrayEntity entity1 = new IntArrayEntity(2L, new int[] {1});
    IntArrayEntity entity2 = new IntArrayEntity(1L, new int[] {2});
    ArrayRepository repo = InMemoryArrayRepository.getInstance();
    repo.add(entity1);
    repo.add(entity2);

    repo.sort(ArrayComparators.BY_ID);
    List<IntArrayEntity> afterSort = repo.findBy(entity -> true);

    assertEquals(Arrays.asList(entity1, entity2), afterSort);
  }
}
