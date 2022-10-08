package fish.yukiemeralis.eden.sculpture.npc;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.level.EntityPlayer;

public interface NPC 
{
    public Location teleport(double x, double y, double z);
    public Location teleport(Location location);

    public GameProfile getGameProfile();
    public EntityPlayer getNmsPlayer();
    public HumanEntity getBukkitPlayer();

    public void subscribe(Player player);
    public boolean unsubscribe(Player player);
    public List<Player> getSubscribers();
    public void cleanSubscribers();

    public boolean show(Player player);
    public boolean hide(Player player);
    public boolean isShownTo(Player player);
    public boolean isWithinRenderRange(Player player);
}
