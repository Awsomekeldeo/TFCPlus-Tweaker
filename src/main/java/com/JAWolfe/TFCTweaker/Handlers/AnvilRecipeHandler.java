package com.JAWolfe.TFCTweaker.Handlers;

import java.util.ArrayList;
import java.util.List;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import net.minecraft.world.World;

public class AnvilRecipeHandler 
{
	private static final AnvilRecipeHandler INSTANCE = new AnvilRecipeHandler();
	public static final AnvilRecipeHandler getInstance()
	{
		return INSTANCE;
	}
	
	public List<IUndoableAction> anvilRecipeList;
	public static World world;
	
	private AnvilRecipeHandler()
	{
		anvilRecipeList = new ArrayList<IUndoableAction>();
	}
	
	public void addAnvilRecipe(IUndoableAction recipe)
	{
		anvilRecipeList.add(recipe);
	}
	
	public void registerRecipes()
	{
		for(IUndoableAction recipe : anvilRecipeList)
			MineTweakerAPI.apply(recipe);
	}
}
