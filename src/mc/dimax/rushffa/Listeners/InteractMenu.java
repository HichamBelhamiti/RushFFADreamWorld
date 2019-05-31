package mc.dimax.rushffa.Listeners;

import mc.dimax.rushffa.Kits.Joueur;
import mc.dimax.rushffa.Main;
import mc.dimax.rushffa.Managers.CoordonatesManager;
import mc.dimax.rushffa.Menus.HubMenu;
import mc.dimax.rushffa.Menus.InfosMenu;
import mc.dimax.rushffa.Utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;


public class InteractMenu implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(e.getItem() != null){
            if(e.getItem().getType() == Material.IRON_AXE){
                Joueur.kitSend(player);
                Main.getInstance().getCoords().forEach(a -> {
                    player.teleport(a.getSpawn());
                });
                player.sendMessage("§7[§bRushFFA§7] §aBon courage l'ami...");
            }
            if(e.getItem().getType() == Material.BED){
                HubMenu.open(player);
            }
            if(e.getItem().getType() == Material.REDSTONE){
                InfosMenu.open(player);
            }
            if(e.getItem().getType() == Material.NETHER_STAR){
                player.teleport(new Location(Bukkit.getWorld("trush"), 644.584, 21, -272.605));
                player.getInventory().clear();
                ItemBuilder spec = new ItemBuilder(Material.WOOD_DOOR).setName("§cQuitter le mode spectateur");
                Main.getInstance().title.sendFullTitle(player, 20, 100, 20, "§eSpectateur", "§bvous êtes à présent spectateur");
                player.getInventory().setItem(4, spec.toItemStack());
                player.setGameMode(GameMode.SPECTATOR);
            }
        }


    }
    @EventHandler
    public void InventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null)
            return;
            if (e.getCurrentItem().getType() == Material.WOOD_DOOR) {
                    player.teleport(new Location(Bukkit.getWorld("ffarush"), -1446.5, 105, -594.2));
                    player.getInventory().clear();
                    ItemBuilder jouer = new ItemBuilder(Material.IRON_AXE).setName("§bJouer au jeu").setUnbreakable(true);
                    ItemBuilder infos = new ItemBuilder(Material.REDSTONE).setName("§aVos informations");
                    ItemBuilder spec = new ItemBuilder(Material.NETHER_STAR).setName("§bMode spectateur");
                    ItemBuilder hub = new ItemBuilder(Material.BED).setName("§cRevenir au lobby");
                    player.getInventory().setItem(4, infos.toItemStack());
                    player.getInventory().setItem(0, jouer.toItemStack());
                    player.getInventory().setItem(8, hub.toItemStack());
                    player.getInventory().setItem(1, spec.toItemStack());
                    player.setGameMode(GameMode.SURVIVAL);
            }
        }
    }
