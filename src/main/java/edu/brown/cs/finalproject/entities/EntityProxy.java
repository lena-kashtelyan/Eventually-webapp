package edu.brown.cs.finalproject.entities;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import edu.brown.cs.finalproject.database.Database;

public abstract class EntityProxy<E extends Entity> implements Entity {
  protected String id;
  protected E internal;

  private static Map<String, Entity> cache = new HashMap<>();

  public EntityProxy(String id) {
    this.id = id;
    loadCache();
  }

  @Override
  public String getID() {
    return id;
  }
  
  public Map<String,Entity> getCache() {
	  return cache;
  }

  private void loadCache() {
    if (internal != null) {
      return;
    }
    internal = (E) cache.get(id);
  }

  public void getInternal() {
    loadCache();
    if (internal != null) {
      return;
    }
    pullFromDB();
    cache.put(id, internal);

  }

  abstract protected void pullFromDB();

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object o) {
    try {
      Entity e = (Entity) o;
      return (id.equals(e.getID()));
    } catch (ClassCastException e) {
      return false;
    }
  }

  @Override
  public String toString() {
    getInternal();
    return internal.toString();
  }

}
