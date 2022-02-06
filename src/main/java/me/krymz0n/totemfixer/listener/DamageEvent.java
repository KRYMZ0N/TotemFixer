package me.krymz0n.totemfixer.listener;

import me.krymz0n.totemfixer.Main;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class DamageEvent implements Listener {
    private final Main plugin;

    public DamageEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent evt) {
        if (evt.getEntity() instanceof Player) {
            Player p = (Player) evt.getEntity();
            ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
            // --Checking if the player has a totem in their inventory, then using it if so--
            if (p.getHealth() <= 0.5d && p.getInventory().containsAtLeast(totem, 1)) {
                System.out.println("case 1");
                evt.setDamage(0.0d);
                p.playEffect(EntityEffect.TOTEM_RESURRECT);
                p.getInventory().remove(totem);
                return;
            }

            if (evt.getDamage() >= p.getHealth() && p.getInventory().containsAtLeast(totem, 1)) {
                System.out.println("case 2");
                evt.setDamage(p.getHealth() - 0.5d);
                p.playEffect(EntityEffect.TOTEM_RESURRECT);
                p.getInventory().remove(totem);

            }
        }
    }
}
