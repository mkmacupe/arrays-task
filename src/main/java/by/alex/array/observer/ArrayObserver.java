package by.alex.array.observer;

import by.alex.array.entity.IntArrayEntity;

@FunctionalInterface
public interface ArrayObserver {
  void onValuesChanged(IntArrayEntity entity);
}