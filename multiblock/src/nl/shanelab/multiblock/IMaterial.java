package nl.shanelab.multiblock;

import org.bukkit.Material;
import org.bukkit.block.Block;

public interface IMaterial {

	Material getType();
	
	boolean isValidBlock(Block block);
}
