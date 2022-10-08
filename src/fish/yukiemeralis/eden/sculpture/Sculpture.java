package fish.yukiemeralis.eden.sculpture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import fish.yukiemeralis.eden.module.EdenModule;
import fish.yukiemeralis.eden.module.EdenModule.ModInfo;
import fish.yukiemeralis.eden.module.annotation.ModuleFamily;
import fish.yukiemeralis.eden.sculpture.npc.NPC;
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
    private static Map<UUID, NPC> ACTIVE_NPCS = new HashMap<>(); 
    private static Map<Player, List<NPC>> SUBSCRIPTIONS = new HashMap<>();

    @Override
    public void onEnable() 
    {
        
    }

    @Override
    public void onDisable() 
    {
        
    }

    public static void registerNpc(NPC npc)
    {
        ACTIVE_NPCS.put(npc.getBukkitPlayer().getUniqueId(), npc);
    }

    public static Collection<NPC> getActiveNpcs()
    {
        return ACTIVE_NPCS.values();
    }

    public static EntityPlayer spawnNpc(String username, Location location)
    {
        EntityPlayer player = new EntityPlayer(Nms.getNmsServer(), Nms.getNmsWorldServer(location.getWorld().getName()), Nms.generateGameProfile(username), null);
        player.e(location.getX(), location.getY(), location.getZ());

        return player;
    }

    public static void subscribe(Player player, NPC npc)
    {
        if (!SUBSCRIPTIONS.containsKey(player))
            SUBSCRIPTIONS.put(player, new ArrayList<>());

        SUBSCRIPTIONS.get(player).add(npc);
    }

    public static void removeSubscription(Player player, NPC npc)
    {
        if (!SUBSCRIPTIONS.containsKey(player))
            return;

        SUBSCRIPTIONS.get(player).remove(npc);
    }

    public static List<NPC> getSubscribedNpcs(Player player)
    {
        if (!SUBSCRIPTIONS.containsKey(player))
            return null;
        return Collections.unmodifiableList(SUBSCRIPTIONS.get(player));
    }

    static void clearSubscriptions(Player player)
    {
        SUBSCRIPTIONS.remove(player);
    }

    public static void showNpc(Player target, EntityPlayer npc)
    {
        Nms.sendPacket(target, 
            PacketFactory.playerInfo(EdenPlayerInfoAction.ADD_PLAYER, npc), 
            PacketFactory.namedEntitySpawn(npc), 
            PacketFactory.entityHeadRotation(npc, (byte) (npc.cg() * 256/360))
        );
    }
}