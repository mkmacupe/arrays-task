package by.alex.array.factory.impl;

import by.alex.array.factory.ArrayFactory;
import by.alex.array.entity.IntArrayEntity;

public class IntArrayFactory implements ArrayFactory {

  private long idCounter = 1L;

  @Override
  public IntArrayEntity create(int[] values) {
    long currentId = idCounter;
    idCounter++;
    return new IntArrayEntity(currentId, values);
  }
}
