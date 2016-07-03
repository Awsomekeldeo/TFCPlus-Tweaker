package com.JAWolfe.TFCTweaker.Handlers.Network;

import com.JAWolfe.TFCTweaker.Handlers.AnvilRecipeHandler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class ChunkEventHandler 
{
	@SubscribeEvent
	public void onLoadWorld(WorldEvent.Load event)
	{
		if(!event.world.isRemote && event.world.provider.dimensionId == 0)
		{
			AnvilRecipeHandler.world = event.world;
    		AnvilRecipeHandler.getInstance().registerRecipes();
		}
	}
}
