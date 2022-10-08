package fish.yukiemeralis.eden.sculpture;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fish.yukiemeralis.eden.sculpture.npc.NPC;

public class NpcRenderListener implements Listener
{
    /**
     * Handler for showing and hiding NPCs that are within/out of range
     * @param event
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if (Sculpture.getSubscribedNpcs(event.getPlayer()) == null)
            return;

        for (NPC npc : Sculpture.getSubscribedNpcs(event.getPlayer()))
        {
            if (npc.isWithinRenderRange(event.getPlayer()))
            {
                if (!npc.isShownTo(event.getPlayer()))
                {
                    npc.show(event.getPlayer());
                }

                return;
            }   

            if (npc.isShownTo(event.getPlayer()))
            {
                npc.hide(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        if (Sculpture.getSubscribedNpcs(event.getPlayer()) == null)
            return;

        for (NPC npc : Sculpture.getSubscribedNpcs(event.getPlayer()))
        {
            npc.hide(event.getPlayer());
            npc.unsubscribe(event.getPlayer());
        }

        Sculpture.clearSubscriptions(event.getPlayer()); // TODO Figure out a single implementation of shown/hidden instead of being split across Sculpture and EdenNPCs
    }
}
