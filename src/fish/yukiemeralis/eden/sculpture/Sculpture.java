package fish.yukiemeralis.eden.sculpture;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import fish.yukiemeralis.eden.module.EdenModule;
import fish.yukiemeralis.eden.module.EdenModule.ModInfo;
import fish.yukiemeralis.eden.module.annotation.ModuleFamily;
import fish.yukiemeralis.eden.sculpture.remapped.EdenPlayerInfoAction;
import net.minecraft.server.level.EntityPlayer;

@ModInfo(
    modName = "Sculpture",
    maintainer = "Yuki_emeralis",
    description = "NPCs module.",
    version = "1.0.0",
    supportedApiVersions = { "v1_19_R1" },
    modIcon = Material.PLAYER_HEAD
)
@ModuleFamily(name = "EdenExtras", icon = Material.AMETHYST_BLOCK)
public class Sculpture extends EdenModule
{
    @Override
    public void onEnable() 
    {
        
    }

    @Override
    public void onDisable() 
    {
        
    }

    public EntityPlayer spawnNpc(String username, Location location)
    {
        EntityPlayer player = new EntityPlayer(Nms.getNmsServer(), Nms.getNmsWorldServer(location.getWorld().getName()), Nms.generateGameProfile(username), null);
        player.getBukkitEntity().teleport(location);

        return player;
    }

    public void showNpc(Player target, EntityPlayer npc)
    {
        Nms.sendPacket(target, 
            PacketFactory.playerInfo(EdenPlayerInfoAction.ADD_PLAYER, npc), 
            PacketFactory.namedEntitySpawn(npc), 
            PacketFactory.entityHeadRotation(npc, (byte) (npc.cg() * 256/360))
        );
    }
}