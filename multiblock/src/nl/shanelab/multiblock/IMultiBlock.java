package nl.shanelab.multiblock;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface IMultiBlock {

	void onActivate(Plugin plugin, Location location, Player player, MultiBlockActivationType activationType);
	
	@Nonnull MultiBlockPattern getMultiBlockPattern();
}
