package nl.shanelab.multiblock.patternobjects;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;

import nl.shanelab.multiblock.IMaterial;
import nl.shanelab.multiblock.MaterialWrapper;
import nl.shanelab.multiblock.PatternObject;

/**
 * Item (entity) pattern for the multiblock structure
 * 
 * @author ShaneCraft
 *
 */
public class PatternItem extends PatternEntity {

	private IMaterial material;
	
	public PatternItem(@Nonnull Material itemMaterial, int x, int y, int z) {
		this(itemMaterial, new Vector(x, y, z));
	}
	
	public PatternItem(@Nonnull Material itemMaterial, Vector relativeVec) {
		super(EntityType.DROPPED_ITEM, relativeVec);
		
		this.material = new MaterialWrapper(itemMaterial);
	}
	
	public PatternItem(@Nonnull IMaterial material, int x, int y, int z) {
		this(material, new Vector(x, y, z));
	}
	
	public PatternItem(@Nonnull IMaterial material, Vector relativeVec) {
		super(EntityType.DROPPED_ITEM, relativeVec);
		
		this.material = material;
	}
	
	@Override
	protected PatternObject createRotatedClone(Vector vector) {
		return new PatternItem(material, vector);
	}
	
	@Override
	public boolean isValid(Location location) {
		Entity entity = getEntity(location);
		
		if (entity != null && entity instanceof Item) {
			return ((Item) entity).getItemStack().getType() == material.getType();
		}
		
		return false;
	}

	public Material getItemMaterial() {
		return material.getType();
	}
}
