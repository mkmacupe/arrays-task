package by.alex.array.factory.impl;

import by.alex.array.entity.IntArrayEntity;
import by.alex.array.factory.ArrayFactory;
import by.alex.array.observer.ArrayObserver;
import by.alex.array.observer.impl.ArrayObserverImpl;

public class IntArrayFactory implements ArrayFactory {

  private long idCounter = 1L;

  @Override
  public IntArrayEntity create(int[] values) {
    long currentId = idCounter;
    idCounter++;
    IntArrayEntity entity = new IntArrayEntity(currentId, values);
    ArrayObserver observer = new ArrayObserverImpl();
    entity.attach(observer);
    observer.onValuesChanged(entity);
    return entity;
  }
}
