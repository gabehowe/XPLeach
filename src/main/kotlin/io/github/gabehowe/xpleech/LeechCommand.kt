package io.github.gabehowe.xpleech

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.lang.Integer.parseInt
import java.util.*

class LeechCommand(private val xpleech: XPLeech) : TabExecutor{
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        if (args.size == 1) {
            return mutableListOf("<amount>")
        }
        else {
            return mutableListOf()
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("§cOnly players can use that command")
            return true
        }
        if (args.isEmpty()) {
            return false
        }
        try {
            parseInt(args[0])
        } catch (e: NumberFormatException) {
            sender.sendMessage("§4${args[0]} is not a number.")
            return true
        }
        if (sender.inventory.firstEmpty() == -1) {
            sender.sendMessage("§cNot enough space in inventory")
            return true
        }
        if (sender.totalExperience < args[0].toInt()) {
            sender.sendMessage("§cYou don't have that much experience")
            return true
        }
        val item =  ItemStack(Material.EXPERIENCE_BOTTLE)
        val itemMeta = item.itemMeta
        itemMeta.setDisplayName("§6Leeched Exp Bottle (${args[0].toDouble() * (1 - xpleech.leechPrice)} exp)")
        itemMeta.persistentDataContainer.set(xpleech.key, PersistentDataType.DOUBLE, (args[0].toDouble() * (1 - xpleech.leechPrice)))
        item.itemMeta = itemMeta
        sender.inventory.addItem(item)
        return true
    }
}