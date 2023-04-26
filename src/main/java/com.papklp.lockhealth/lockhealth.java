package com.papklp.lockhealth;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class lockhealth extends JavaPlugin implements Listener {

    private final double health = 20.0; // the health value to lock

    @Override
    public void onEnable() {
        // register the event listener
        Bukkit.getPluginManager().registerEvents(this, this);
        // set all online players' health to the locked value
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setHealth(health);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // check if the entity is a player
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            // cancel the event if the player's health is equal to the locked value
            if (player.getHealth() == health) {
                event.setCancelled(true);
            }
            // otherwise, set the player's health to the locked value after the damage
            else {
                Bukkit.getScheduler().runTaskLater(this, () -> player.setHealth(health), 1L);
            }
        }
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        // check if the entity is a player
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            // cancel the event if the player's health is equal to the locked value
            if (player.getHealth() == health) {
                event.setCancelled(true);
            }
            // otherwise, set the player's health to the locked value after the regain
            else {
                Bukkit.getScheduler().runTaskLater(this, () -> player.setHealth(health), 1L);
            }
        }
    }
}
