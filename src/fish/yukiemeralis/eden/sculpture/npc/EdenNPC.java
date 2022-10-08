package fish.yukiemeralis.eden.sculpture.npc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import fish.yukiemeralis.eden.sculpture.Nms;
import fish.yukiemeralis.eden.sculpture.PacketFactory;
import fish.yukiemeralis.eden.sculpture.Sculpture;
import net.minecraft.server.level.EntityPlayer;

public class EdenNPC implements NPC
{
    private final GameProfile profile;
    private final EntityPlayer npcPlayer;
    private final List<Player> subscribers = new ArrayList<>(); // Players who receive packets related to this instance
    private final List<Player> showPlayers = new ArrayList<>();
    private double npcRenderDistance;

    public EdenNPC(GameProfile profile, EntityPlayer npcPlayer, double npcRenderDistance)
    {
        this.profile = profile;
        this.npcPlayer = npcPlayer;
        this.npcRenderDistance = npcRenderDistance;
    }

    @Override
    public Location teleport(double x, double y, double z) 
    {
        return teleport(new Location(npcPlayer.getBukkitEntity().getWorld(), x, y, z));
    }

    @Override
    public Location teleport(Location location) 
    {
        this.npcPlayer.e(location.getX(), location.getY(), location.getZ());
        Nms.sendPacket(this.subscribers, PacketFactory.entityTeleport(this.npcPlayer));
        return location;
    }

    @Override
    public GameProfile getGameProfile() 
    {
        return this.profile;
    }

    @Override
    public EntityPlayer getNmsPlayer() 
    {
        return this.npcPlayer;
    }

    @Override
    public HumanEntity getBukkitPlayer() 
    {
        return this.npcPlayer.getBukkitEntity();
    }

    @Override
    public void subscribe(Player player) 
    {
        synchronized (this.subscribers)
        {
            Sculpture.subscribe(player, this);
            this.subscribers.add(player);
        }
    }

    @Override
    public boolean unsubscribe(Player player) 
    {
        synchronized (this.subscribers)
        {
            Sculpture.removeSubscription(player, this);
            return this.subscribers.remove(player);   
        }
    }

    @Override
    public List<Player> getSubscribers() 
    {
        return this.subscribers;
    }

    @Override
    public void cleanSubscribers() 
    {
        synchronized (this.subscribers)
        {
            this.subscribers.removeIf((subscriber) -> {
                if (subscriber == null)
                    return true;
                if (!subscriber.isOnline())
                    return true;
                return false;
            });  
        }
    }

    @Override
    public boolean show(Player player) 
    {
        if (!subscribers.contains(player))
            return false;
        if (!isWithinRenderRange(player))
            return false;
        
        Sculpture.showNpc(player, this.npcPlayer);
        this.showPlayers.add(player);

        return true;
    }

    @Override
    public boolean hide(Player player) 
    {
        if (!subscribers.contains(player))
            return false;

        PacketFactory.entityDestroy(this.npcPlayer);
        this.showPlayers.remove(player);

        return true;
    }

    @Override
    public boolean isShownTo(Player player)
    {
        return this.showPlayers.contains(player);
    }

    @Override
    public boolean isWithinRenderRange(Player player) 
    {
        if (!this.getBukkitPlayer().getWorld().equals(player.getWorld()))
            return false;

        return getBukkitPlayer().getLocation().distanceSquared(player.getLocation()) <= (this.npcRenderDistance * this.npcRenderDistance);
    }
}
