package fish.yukiemeralis.eden.sculpture.npc;

import java.util.List;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.level.EntityPlayer;

public interface NPC 
{
    public GameProfile getGameProfile();
    public EntityPlayer getNmsPlayer();
    public HumanEntity getBukkitPlayer();
    public List<Player> getSubscribers();
    public void subscribe(Player player);
}
