package straywolfe.tfctweaker.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import straywolfe.tfctweaker.handlers.network.ServerTickHandling;

public class CommonProxy 
{
	public void registerTickHandler()
	{
		FMLCommonHandler.instance().bus().register(new ServerTickHandling());
	}
}
