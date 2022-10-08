package fish.yukiemeralis.eden.sculpture;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.command.EdenCommand;
import fish.yukiemeralis.eden.command.annotations.EdenCommandHandler;
import fish.yukiemeralis.eden.module.EdenModule;
import fish.yukiemeralis.eden.sculpture.npc.EdenNPC;
import fish.yukiemeralis.eden.sculpture.npc.NPC;
import net.minecraft.server.level.EntityPlayer;

public class SculptureDebugCommand extends EdenCommand
{
    public SculptureDebugCommand(EdenModule parent_module) 
    {
        super("sculpture", List.of("s", "sculpt"), parent_module);

        this.addBranch("spawn").addBranch("<NAME>");
    }

    @EdenCommandHandler(usage = "sculpture spawn", description = "DEBUG Spawns an NPC", argsCount = 1)
    public void edencommand_spawn(CommandSender sender, String commandLabel, String[] args)
    {
        if (!(sender instanceof Player))
            return;

        Location targetLoc = ((Player) sender).getTargetBlock(null, 30).getLocation().add(0d, 1d, 0d);

        EntityPlayer ent = Sculpture.spawnNpc(args[1], targetLoc);
        NPC npc = new EdenNPC(ent.fy(), ent, 20.0);

        npc.subscribe((Player) sender);
        npc.show((Player) sender);

        Bukkit.getScheduler().runTaskTimer(Eden.getInstance(), () -> {
            npc.teleport(npc.getBukkitPlayer().getLocation().add(0.3d, 0.0d, 0.0d));
        }, 1L, 0L);
    }
}
