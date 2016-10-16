package nl.shanelab.multiblock;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class MaterialWrapper implements IMaterial {

	private final Material material;
	
	public MaterialWrapper(Material material) {
		this.material = material;
	}
	
	@Override
	public Material getType() {
		return material;
	}

	@Override
	public boolean isValidBlock(Block block) {
		return block != null && block.getType() == material;
	}
}