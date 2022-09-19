package fish.yukiemeralis.eden.sculpture.npc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.level.EntityPlayer;

public class EdenNPC implements NPC
{
    private final GameProfile profile;
    private final EntityPlayer npcPlayer;
    private final List<Player> subscribers = new ArrayList<>(); // Players who recieve packets related to this instance

    public EdenNPC(GameProfile profile, EntityPlayer npcPlayer)
    {
        this.profile = profile;
        this.npcPlayer = npcPlayer;
    }
}
