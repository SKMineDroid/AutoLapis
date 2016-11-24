package me.SKMineDroid;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Main
  extends JavaPlugin
{
  public ArrayList<EnchantingInventory> inventories;
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
public void onEnable()
  {
	  
    getServer().getPluginManager().registerEvents(new Listeners(this), this);
    this.inventories = new ArrayList();
  }
  
  public void onDisable()
  {
    for (EnchantingInventory ei : this.inventories) {
      ei.setItem(1, null);
    }
    this.inventories = null;
  }
}
