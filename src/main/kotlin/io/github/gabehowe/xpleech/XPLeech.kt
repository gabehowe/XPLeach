package io.github.gabehowe.xpleech

import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class XPLeech : JavaPlugin() {
    val key = NamespacedKey(this, "expAmount")
    val leechPrice: Double
        get() {
            return config.get("leech-price") as Double? ?: 0.0
        }
    override fun onEnable() {
        // Plugin startup logic
        getCommand("leech")?.setExecutor(LeechCommand(this))
        server.pluginManager.registerEvents(LeechEvents(this), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}