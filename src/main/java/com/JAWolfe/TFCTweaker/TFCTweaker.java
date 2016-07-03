package com.JAWolfe.TFCTweaker;

import com.JAWolfe.TFCTweaker.Handlers.*;
import com.JAWolfe.TFCTweaker.Handlers.Network.ChunkEventHandler;
import com.JAWolfe.TFCTweaker.Handlers.Network.InitClientWorldPacket;
import com.JAWolfe.TFCTweaker.Handlers.Network.PlayerTracker;
import com.JAWolfe.TFCTweaker.proxy.CommonProxy;
import com.JAWolfe.TFCTweaker.util.References;
import com.bioxx.tfc.TerraFirmaCraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import minetweaker.MineTweakerAPI;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = References.ModID, name = References.ModName, version = References.ModVersion, dependencies = References.ModDependencies)
public class TFCTweaker 
{
	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
	
	@EventHandler	
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.registerTickHandler();
	}
	
	@EventHandler
	public void initialize(FMLInitializationEvent event)
	{
		TerraFirmaCraft.PACKET_PIPELINE.registerPacket(InitClientWorldPacket.class);
		FMLCommonHandler.instance().bus().register(new PlayerTracker());
		MinecraftForge.EVENT_BUS.register(new ChunkEventHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerInteractionHandler());
		
		if (Loader.isModLoaded("MineTweaker3"))
		{
			MineTweakerAPI.registerClass(ItemHeat.class);
			MineTweakerAPI.registerClass(Loom.class);
			MineTweakerAPI.registerClass(Quern.class);
			MineTweakerAPI.registerClass(Barrel.class);
			MineTweakerAPI.registerClass(Anvil.class);
			MineTweakerAPI.registerClass(Knapping.class);
		}
	}
}
