package com.JAWolfe.TFCTweaker.proxy;

import com.JAWolfe.TFCTweaker.Handlers.Network.ServerTickHandling;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy 
{
	public void registerTickHandler()
	{
		FMLCommonHandler.instance().bus().register(new ServerTickHandling());
	}
}
