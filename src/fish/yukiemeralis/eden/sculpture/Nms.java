package fish.yukiemeralis.eden.sculpture;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import fish.yukiemeralis.eden.Eden;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;

public class Nms 
{
    public static GameProfile generateGameProfile(String username)
    {
        return new GameProfile(UUID.randomUUID(), username);
    }

    public static PlayerConnection getConnection(Player player)
    {
        return ((CraftPlayer) player).getHandle().b;
    }

    public static void sendPacket(Player player, Packet<?>... packets)
    {
        PlayerConnection connection = getConnection(player);
        for (Packet<?> packet : packets)
            connection.a(packet);
    }

    public static void sendPacket(Collection<Player> players, Packet<?>... packets)
    {
        for (Player p : players)
            sendPacket(p, packets);
    }

    public static MinecraftServer getNmsServer()
    {
        return ((CraftServer) Eden.getInstance().getServer()).getServer();
    }

    public static WorldServer getNmsWorldServer(String worldName)
    {
        return ((CraftWorld) Eden.getInstance().getServer().getWorld(worldName)).getHandle();
    }
}
