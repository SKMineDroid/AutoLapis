package me.SKMineDroid;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

@SuppressWarnings("unused")
public class Listeners
  implements Listener
{
  private static final GameMode Creative = null;
  private Main plugin;
  private ItemStack lapis;
  
  public Listeners(Main plugin)
  {
    this.plugin = plugin;
    
    Dye d = new Dye();
    d.setColor(DyeColor.BLUE);
    this.lapis = d.toItemStack();
    this.lapis.setAmount(64);
    this.lapis.getItemMeta().setDisplayName("§b§oLAPIS LAZULI");
    
  }
  
  @EventHandler
  public void openInventoryEvent(InventoryOpenEvent e)
  {
    if ((e.getInventory() instanceof EnchantingInventory))
    {
      e.getInventory().setItem(1, this.lapis);
      this.plugin.inventories.add(
        (EnchantingInventory)e.getInventory());
    }
  }
  
  @EventHandler
  public void closeInventoryEvent(InventoryCloseEvent e)
  {
    if ((e.getInventory() instanceof EnchantingInventory)) {
      if (this.plugin.inventories.contains((EnchantingInventory)e.getInventory()))
      {
        e.getInventory().setItem(1, null);
        this.plugin.inventories.remove(
          (EnchantingInventory)e.getInventory());
      }
    }
  }
  
  @EventHandler
  public void inventoryClickEvent(InventoryClickEvent e)
  {
    if ((e.getClickedInventory() instanceof EnchantingInventory)) {
      if ((this.plugin.inventories.contains((EnchantingInventory)e.getInventory())) && 
        (e.getSlot() == 1)) {
        e.setCancelled(true);
      }
    }
  }
  
  @EventHandler
  public void enchantItemEvent(EnchantItemEvent e)
  {
    if (this.plugin.inventories.contains((EnchantingInventory)e.getInventory())) {
      e.getInventory().setItem(1, this.lapis);
    }
  }

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getInventory() == null) {
			return;
		}
		
		Inventory i = e.getInventory();
		String name = i.getName();
		
		if (e.getCurrentItem() == null) {
			return;
		}
		
		if (e.getCurrentItem().getType() == Material.AIR) {
			return;
		}
		String LAPISLAZULI = ("§b§oLAPIS LAZULI");
		if (e.getInventory().getName().equals(LAPISLAZULI)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void OnEnchnatingClickLapis(PlayerDropItemEvent e) {
	Player p = e.getPlayer();
	if (p.getOpenInventory().getType() == InventoryType.ENCHANTING) {
	   e.setCancelled(true);
       e.getPlayer().updateInventory();
	   }
	}
}
