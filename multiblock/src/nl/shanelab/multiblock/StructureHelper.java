package nl.shanelab.multiblock;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

import nl.shanelab.multiblock.patternobjects.PatternBlock;

public class StructureHelper {

	private final Plugin plugin;
	
	private final IMultiBlock multiBlock;
	
	public StructureHelper(Plugin plugin, IMultiBlock multiBlock) {
		this.plugin = plugin;
		this.multiBlock = multiBlock;
	}
	
	public void spawnStructure(Location location) {
		MultiBlockPattern pattern = multiBlock.getMultiBlockPattern();
		
		if (pattern != null) {
			int maxX = 0;
			int minX = 0;
			int maxY = 0;
			int minY = 0;
			int maxZ = 0;
			int minZ = 0;
			
			List<PatternBlock> patternBlocks = new ArrayList<>();
			for (PatternObject patternObject : pattern.getPatternObjects()) {
				if (patternObject instanceof PatternBlock) {
					patternBlocks.add((PatternBlock) patternObject);
					
					int x = patternObject.getX();
					int y = patternObject.getY();
					int z = patternObject.getZ();
					
					if (x > 0) {
						maxX = x > maxX ? x : maxX;
					} else {
						minX = x < minX ? x : minX;
					}
					
					if (y > 0) {
						maxY = y > maxY ? y : maxY;
					} else {
						minY = y < minY ? y : minY;
					}
					
					if (z > 0) {
						maxZ = z > maxZ ? z : maxZ;
					} else {
						minZ = z < minZ ? z : minZ;
					}
				}
			}
			
			BoundingBox box = new BoundingBox(location, maxX, minX, maxY, minY, maxZ, minZ);
			if (patternBlocks != null) {
				createStructure(pattern.getCoreMaterial(), box, patternBlocks);
			}
		}
	}
	
	private void createStructure(IMaterial core, BoundingBox box, List<PatternBlock> patternBlocks) {
		if (canGenerate(core, box, patternBlocks)) {
			System.out.println("can generate");
			
			/*Location location = box.getStartLocation();
			
			for (PatternBlock patternBlock : patternBlocks) {
				location.add(patternBlock.getLocation());
				Block block = location.getBlock();
				if (block == null || !patternBlock.isValid(location)) {
					block.setType(patternBlock.getMaterial());
				}
				
				location.subtract(patternBlock.getLocation());
			}*/
		}
	}
	
	private boolean canGenerate(IMaterial core, BoundingBox box, List<PatternBlock> patternBlocks) {
		boolean flag = false;
		
		Location location = box.getStartLocation();
		int startX = location.getBlockX();
		int startY = location.getBlockY();
		int startZ = location.getBlockZ();
		
		World world = location.getWorld();
		
		int maxY = box.getMaxY() + 1;
		int maxX = box.getMaxX() + 1;
		int maxZ = box.getMaxZ() + 1;
		for (int y = box.getMinY(); y < maxY; y++) {
			if (flag) {
				break;
			}
			for (int x = box.getMinX(); x < maxX; x++) {
				if (flag) {
					break;
				}
				for (int z = box.getMinZ(); z < maxZ; z++) {
					if (flag) {
						break;
					}
					
					Block block = world.getBlockAt(x, y, z);
					if (startX == x && startY == y && startZ == z) {
						flag = block.getType() == core.getType();
					} else {
						Material material = getMaterial(world, x, y, z, patternBlocks);
						if (material != null) {
							flag = block.getType() == material;
						} else {
							flag = block.isEmpty();
						}
					}
					
					if (flag) {
						flag = false;
					}
				}
			}
		}
		
		return flag;
	}
	
	private Material getMaterial(World world, int x, int y, int z, List<PatternBlock> patternBlocks) {
		Material material = null;
		
		for (PatternBlock patternBlock : patternBlocks) {
			if (material == null) {
				Location location = new Location(world, x, y, z);
				
				Block block = location.getBlock();
				if (block != null && block.getType() == patternBlock.getMaterial()) {
					material = patternBlock.getMaterial();
				}
			}
		}
		
		return material;
	}
	
	public class BoundingBox {
		
		private final Location startLocation;
		
		private final int maxX, minX, maxY, minY, maxZ, minZ;
		
		protected BoundingBox(final Location startLocation, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
			this.startLocation = startLocation;
			
			int x = startLocation.getBlockX();
			int y = startLocation.getBlockY();
			int z = startLocation.getBlockZ();
			
			this.maxX = maxX + x;
			this.minX = minX + x;
			this.maxY = maxY + y;
			this.minY = minY + y;
			this.maxZ = maxZ + z;
			this.minZ = minZ + z;
		}
		
		public Location getStartLocation() {
			return startLocation;
		}
		
		public int getMaxX() {
			return maxX;
		}
		
		public int getMinX() {
			return minX;
		}
		
		public int getMaxY() {
			return maxY;
		}
		
		public int getMinY() {
			return minY;
		}
		
		public int getMaxZ() {
			return maxZ;
		}
		
		public int getMinZ() {
			return minZ;
		}
	}
}
