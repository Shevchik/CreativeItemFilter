package org.hurricanegames.creativeitemfilter;

import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.hurricanegames.commandlib.commands.BukkitCommandExecutor;
import org.hurricanegames.creativeitemfilter.commands.CreativeItemFilterCommandHelper;
import org.hurricanegames.creativeitemfilter.commands.CreativeItemFilterCommands;
import org.hurricanegames.creativeitemfilter.handler.CreativeItemFilterHandler;

public class CreativeItemFilter extends JavaPlugin implements Listener {

	private final CreativeItemFilterConfiguration configuration = new CreativeItemFilterConfiguration(new File(getDataFolder(), "config.yml"));

	@Override
	public void onEnable() {
		configuration.reload();
		getServer().getPluginManager().registerEvents(new CreativeItemFilterHandler(getLogger(), configuration), this);
		getCommand("creativeitemfilter").setExecutor(new BukkitCommandExecutor(new CreativeItemFilterCommands(new CreativeItemFilterCommandHelper(configuration)), "creativeitemfilter.admin"));
	}
}
