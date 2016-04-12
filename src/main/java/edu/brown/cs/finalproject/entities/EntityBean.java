package edu.brown.cs.finalproject.entities;

import java.util.Objects;

public abstract class EntityBean implements Entity {
  protected String _id;

  public EntityBean(String id) {
    this._id = id;
  }

  @Override
  public String getID() {
    return _id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(_id);
  }

  @Override
  public boolean equals(Object o) {
    try {
      Entity e = (Entity) o;
      return (_id.equals(e.getID()));
    } catch (ClassCastException e) {
      return false;
    }
  }

}
