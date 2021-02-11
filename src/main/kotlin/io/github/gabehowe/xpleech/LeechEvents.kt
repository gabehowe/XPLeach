package io.github.gabehowe.xpleech

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ExpBottleEvent
import org.bukkit.persistence.PersistentDataType

class LeechEvents(private val xpLeech: XPLeech) : Listener {
    @EventHandler
    fun onXpThrow(event : ExpBottleEvent) {
        if (!event.entity.item.itemMeta.persistentDataContainer.has(xpLeech.key, PersistentDataType.DOUBLE)) {
            return
        }
        event.experience = event.entity.item.itemMeta.persistentDataContainer.get(xpLeech.key, PersistentDataType.DOUBLE)!!.toInt()
    }
}