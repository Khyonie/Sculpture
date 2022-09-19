package fish.yukiemeralis.eden.sculpture.remapped;

import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

/**
 * Remap of EnumPlayerInfoAction
 */
public enum EdenPlayerInfoAction 
{
    ADD_PLAYER(EnumPlayerInfoAction.a),
    UPDATE_GAME_MODE(EnumPlayerInfoAction.b),
    UPDATE_LATENCY(EnumPlayerInfoAction.c),
    UPDATE_DISPLAY_NAME(EnumPlayerInfoAction.d),
    REMOVE_PLAYER(EnumPlayerInfoAction.e)
    ;

    private final EnumPlayerInfoAction mojangMappedAction;
    private EdenPlayerInfoAction(EnumPlayerInfoAction mojangMappedAction)
    {
        this.mojangMappedAction = mojangMappedAction;
    }

    public EnumPlayerInfoAction getMojangMappedAction()
    {
        return this.mojangMappedAction;
    }
}
