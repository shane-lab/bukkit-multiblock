package nl.shanelab.multiblock.example;

import org.bukkit.plugin.java.JavaPlugin;

import nl.shanelab.multiblock.MultiBlockFactory;
import nl.shanelab.multiblock.example.multiblocks.ChickenSoupMultiBlock;
import nl.shanelab.multiblock.example.multiblocks.ExampleMultiBlock;

public class ExamplePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		super.onEnable();
		
		// must be registered on enable to cooperate with events
		
		MultiBlockFactory.INSTANCE.register(this, ExampleMultiBlock.class);
		MultiBlockFactory.INSTANCE.register(this, ChickenSoupMultiBlock.class);
	}
}
