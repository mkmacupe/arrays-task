package by.alex.array.creator.impl;

import by.alex.array.creator.ArrayCreator;
import by.alex.array.entity.IntArrayEntity;

public class IntArrayCreator implements ArrayCreator {

  @Override
  public IntArrayEntity create(int[] values) {
    return new IntArrayEntity(values);
  }
}
