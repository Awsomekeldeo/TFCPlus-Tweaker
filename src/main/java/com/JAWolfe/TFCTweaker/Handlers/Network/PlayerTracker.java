package com.JAWolfe.TFCTweaker.Handlers.Network;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerTracker 
{
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerLoggedInEvent event)
	{
		AbstractPacket pkt = new InitClientWorldPacket();
		TerraFirmaCraft.PACKET_PIPELINE.sendTo(pkt, (EntityPlayerMP) event.player);
	}
}
