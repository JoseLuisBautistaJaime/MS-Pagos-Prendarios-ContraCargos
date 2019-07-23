package mx.com.nmp.pagos.mimonte.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;

/**
 * <p>Da soporte a operaciones comunes necesarias sobre una coleccion
 * de entidades del modelo.
 * Para crear un objeto de este tipo se necesita crear una clase
 * anonima que toma en el constructor la coleccion de entidades
 * que debe envolver. La clase anonima deberá especificar la
 * implementación necesaria para obtener el identificador de una
 * entidad.
 * <p>Ejemplo :
 * <code>
 * <pre>
 *  List<Usuario> usuarios = ...
 *  EntityCollection<Usuario, Long> ec = new EntityCollection<Usuario, Long>(usuarios){
 *      protected Long getPK(Usuario entity) {
 *          return entity.getId();
 *      }
 *  };
 *  Usuario gerente ...
 *  ec.contains(gerente);
 * </pre>
 * </code>  
 * @author rvizcaino
 *
 * @param <T> El tipo de la entidad
 * @param <PK> El tipo de la llave primaria
 */
public abstract class EntityCollection<T, PK extends Serializable> implements Serializable{
	
	private static final long serialVersionUID = -5158558779918859231L;
	private Map<PK, T> map;
	
	public EntityCollection() {
		map = new HashMap<PK, T>();
	}

	public EntityCollection(boolean sorted) {
		map = new LinkedHashMap<PK, T>();
	}

	public EntityCollection(Collection<T> entities) {
		this();
		entities = entities == null? new ArrayList<T>() : entities;
		for (T entity : entities)
			map.put(getPK(entity), entity);
	}
	
	public Collection<PK> ids() {
		return Collections.unmodifiableCollection(map.keySet());
	}
	
	public Collection<T> entities() {
		return Collections.unmodifiableCollection(map.values());
	}
	
	public int size() {
		return map.size();
	}
	
	public boolean isEmtpy() {
		return map.isEmpty();
	}

	public T getByPK(PK id) { // Para usar en Jsf como #{bean[PK]}
		return get(id);
	}

	public T get(PK id) {
		return map.get(id);
	}

	public Collection<T> get(Collection<PK> ids) {
		Map<PK, T> temp = new HashMap<PK, T>(map);
		temp.keySet().retainAll(ids);
		return temp.values();
	}
	
	public Collection<T> getAll(){
		return map.values();
	}

	public boolean contains(PK id) {
		return map.keySet().contains(id);
	}
	
	public boolean contains(T entity) {
		return map.values().contains(entity) || contains(getPK(entity));
	}
	
	public boolean containsAllById(Collection<PK> ids) {
		return map.keySet().containsAll(ids); 
	}
	
	public boolean containsAny(Collection<PK> ids) {
		return CollectionUtils.containsAny(map.keySet(), ids); 
	}
	
	public boolean containsAll(Collection<T> entities) {
		for (T entity : entities)
			if (!contains(entity))
				return false;
		return true;
	}
	
	public boolean remove(PK id) {
		return map.keySet().remove(id);
	}
	
	public boolean remove(T entity) {
		return map.values().remove(entity);
	}
	
	public boolean removeAllById(Collection<PK> ids) {
		return map.keySet().removeAll(ids);
	}
	
	public boolean removeAll(Collection<T> entities) {
		return map.values().removeAll(entities);
	}
	
	public boolean add(T entity) {
		T old = map.put(getPK(entity), entity);
		return old != entity;
	}
	
	public boolean addAll(Collection<T> entities) {
		boolean change = false;
		for (T entity : entities)
			change |= add(entity);
		return change;
	}
	
	public void clear(){
		map.clear();
	}
	
	public Collection<T> intersection(Collection<T> entities) {
		Collection<T> intersection = new HashSet<T>();
		for (T entity : entities)
			if (contains(entity))
				intersection.add(entity);
		return intersection;
	}
	
	public Collection<T> union(Collection<T> entities) {
		Collection<T> union = new HashSet<T>(entities);
		union.addAll(map.values());
		return union;
	}
	
	public Collection<T> difference(Collection<T> entities) {
		Collection<T> diff = new HashSet<T>(map.values());
		for (T entity : entities)
			if (contains(entity))
				diff.remove(get(getPK(entity)));
		return diff;
	}
	
	public Collection<T> symmetricDifference(Collection<T> entities) {
		Collection<T> symDeff = union(entities); 
		symDeff.removeAll(intersection(entities));
		return symDeff;
	}
	
	public abstract PK getPK(T entity);
}