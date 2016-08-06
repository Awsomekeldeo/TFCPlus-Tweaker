package straywolfe.tfctweaker.handlers.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import straywolfe.tfctweaker.handlers.AnvilRecipeHandler;

public class ServerTickHandling 
{
	@SubscribeEvent
	public void onServerTick(WorldTickEvent event)
    {		
        if(event.phase == Phase.START)
        {
        	if(event.world.provider.dimensionId == 0 && AnvilRecipeHandler.world == null)
        	{
        		AnvilRecipeHandler.world = event.world;
        		AnvilRecipeHandler.getInstance().registerRecipes();
        	}
        }
    }
}
