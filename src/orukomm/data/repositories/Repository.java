package orukomm.data.repositories;

/**
 * Data context read- and write methods.
 */
public interface Repository<Entity> {

	public void add(Entity entity);
	
	public void remove(Entity entity);
	
	public void update(Entity entity);

	// Fetch an entity by id.
	public Entity getById(int id);
}
