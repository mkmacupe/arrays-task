package by.alex.array.entity;

import java.util.Arrays;

public class IntArrayEntity {

  private final long id;
  private int[] values;

  public IntArrayEntity(long id, int[] values) {
    this.id = id;
    this.values = (values != null) ? Arrays.copyOf(values, values.length) : new int[0];
  }

  public long getId() {
    return id;
  }

  public int[] getValues() {
    return Arrays.copyOf(values, values.length);
  }

  public void setValues(int[] values) {
    this.values = (values != null) ? Arrays.copyOf(values, values.length) : new int[0];
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
    return (id == other.id) && Arrays.equals(values, other.values);
  }

  @Override
  public int hashCode() {
    int result = Long.hashCode(id);
    result = 31 * result + Arrays.hashCode(values);
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("IntArrayEntity{");
    sb.append("id=").append(id);
    sb.append(", values=").append(Arrays.toString(values));
    sb.append('}');
    return sb.toString();
  }
}
