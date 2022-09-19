package fish.yukiemeralis.eden.sculpture;

import fish.yukiemeralis.eden.sculpture.remapped.EdenPlayerInfoAction;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.EntityHuman;

public class PacketFactory 
{
    public static PacketPlayOutPlayerInfo playerInfo(EdenPlayerInfoAction action, EntityPlayer player)
    {
        return new PacketPlayOutPlayerInfo(action.getMojangMappedAction(), player);
    }

    public static PacketPlayOutNamedEntitySpawn namedEntitySpawn(EntityHuman entity)
    {
        return new PacketPlayOutNamedEntitySpawn(entity);
    }

    public static PacketPlayOutEntityHeadRotation entityHeadRotation(Entity entity, byte rotation)
    {
        return new PacketPlayOutEntityHeadRotation(entity, rotation);
    }
}
