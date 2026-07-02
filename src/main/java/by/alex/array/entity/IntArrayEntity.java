package by.alex.array.entity;

import java.util.Arrays;

public class IntArrayEntity {

  private int[] values;

  public IntArrayEntity(int[] values) {
    this.values = values.clone();
  }

  public int[] getValues() {
    return values.clone();
  }

  public void setValues(int[] values) {
    this.values = values.clone();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntArrayEntity other = (IntArrayEntity) o;
    return Arrays.equals(values, other.values);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(values);
  }

  @Override
  public String toString() {
    return "IntArrayEntity{values=" + Arrays.toString(values) + "}";
  }
}
