package nl.shanelab.multiblock;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;

/**
 * Item (entity) pattern for the multiblock structure
 * 
 * @author ShaneCraft
 *
 */
public class PatternItem extends PatternEntity {

	private Material itemMaterial;
	
	public PatternItem(@Nonnull Material itemMaterial, int x, int y, int z) {
		this(itemMaterial, new Vector(x, y, z));
	}
	
	public PatternItem(@Nonnull Material itemMaterial, Vector relativeVec) {
		super(EntityType.DROPPED_ITEM, relativeVec);
		
		this.itemMaterial = itemMaterial;
	}
	
	@Override
	protected PatternObject createRotatedClone(Vector vector) {
		return new PatternItem(itemMaterial, vector);
	}
	
	@Override
	public boolean isValid(Location location) {
		Entity entity = getEntity(location);
		
		if (entity != null && entity instanceof Item) {
			return ((Item) entity).getItemStack().getType() == itemMaterial;
		}
		
		return false;
	}

	public Material getItemMaterial() {
		return itemMaterial;
	}
}
