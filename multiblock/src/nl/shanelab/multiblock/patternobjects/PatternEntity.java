package nl.shanelab.multiblock.patternobjects;

import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import nl.shanelab.multiblock.PatternObject;

/**
 * Entity pattern for the multiblock structure
 * 
 * @author ShaneCraft
 *
 */
public class PatternEntity extends PatternObject {

	private final EntityType entityType;
	
	public PatternEntity(@Nonnull EntityType entityType, int x, int y, int z) {
		this(entityType, new Vector(x, y, z));
	}
	
	public PatternEntity(@Nonnull EntityType entityType, Vector relativeVec) {
		super(relativeVec);
		
		if (entityType == EntityType.DROPPED_ITEM && !(this instanceof PatternItem)) {
			throw new IllegalArgumentException("Use PatternItem to use items in your multiblock pattern");
		}
		
		this.entityType = entityType;
	}

	@Override
	protected PatternObject createRotatedClone(Vector vector) {
		return new PatternEntity(entityType, vector);
	}

	@Override
	public boolean isValid(Location location) {
		return getEntity(location) != null;
	}
	
	protected Entity getEntity(Location location) {
		if (location == null) {
			return null;
		}
		
		List<Entity> entities = (List<Entity>) location.getWorld().getNearbyEntities(location, 1, 1, 1);
		
		Entity entity = null;
		for (Entity e : entities) {
			if (entity == null) {
				if (e.getType() == entityType) {
					entity = e;
				}
			}
		}
		
		return entity;
	}
	
	public EntityType getEntityType() {
		return entityType;
	}
}
