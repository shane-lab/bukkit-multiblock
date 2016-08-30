package nl.shanelab.multiblock.example;

import org.bukkit.plugin.java.JavaPlugin;

import nl.shanelab.multiblock.MultiBlockFactory;

public class ExamplePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		super.onEnable();
		
		// fully automatic multiblock structure after registration
		// must be registered on enable to cooperate with events
		
		MultiBlockFactory.INSTANCE.register(this, ExampleMultiBlock.class);
	}
}
