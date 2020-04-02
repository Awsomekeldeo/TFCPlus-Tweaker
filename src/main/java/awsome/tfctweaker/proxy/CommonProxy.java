package awsome.tfctweaker.proxy;

import awsome.tfctweaker.handlers.network.ServerTickHandling;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy 
{
	public void registerTickHandler()
	{
		FMLCommonHandler.instance().bus().register(new ServerTickHandling());
	}
}
