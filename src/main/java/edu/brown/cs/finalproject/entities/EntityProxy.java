package edu.brown.cs.finalproject.entities;

import java.util.HashMap;
import java.util.Map;

public abstract class EntityProxy<E extends Entity> implements Entity {
	
	protected String id;
	protected E internal;
	
	private static Map<String, Entity> cache = new HashMap<>();


}
