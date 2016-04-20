package edu.brown.cs.finalproject.entities;

import java.util.Objects;

public class EntityBean implements Entity {

	protected String id;

	public EntityBean(String id) {
		this.id = id;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		try {
			Entity entity = (Entity) o;
			return getID().equals(entity.getID());
		} catch (ClassCastException cce) {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getID());
	}
}
