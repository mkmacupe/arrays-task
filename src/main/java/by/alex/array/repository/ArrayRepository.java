package by.alex.array.repository;

import by.alex.array.entity.IntArrayEntity;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface ArrayRepository {
  boolean add(IntArrayEntity array);

  boolean remove(IntArrayEntity array);

  List<IntArrayEntity> findBy(Predicate<IntArrayEntity> specification);

  List<IntArrayEntity> findByStream(Predicate<IntArrayEntity> specification);

  List<IntArrayEntity> sort(Comparator<IntArrayEntity> comparator);
}
