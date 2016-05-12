package com.JAWolfe.TFCTweaker;

import com.JAWolfe.TFCTweaker.Handlers.*;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import minetweaker.MineTweakerAPI;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = References.ModID, name = References.ModName, version = References.ModVersion, dependencies = References.ModDependencies)
public class TFCTweaker 
{
	@EventHandler
	public void initialize(FMLInitializationEvent event)
	{
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
