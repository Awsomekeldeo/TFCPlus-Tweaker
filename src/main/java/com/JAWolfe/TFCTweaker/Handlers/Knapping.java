package com.JAWolfe.TFCTweaker.Handlers;

import java.util.HashMap;
import java.util.List;

import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
import com.bioxx.tfc.api.Crafting.ShapedRecipesTFC;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.Terrafirmacraft.Knapping")
public class Knapping 
{
	//Clay Working
	@ZenMethod
    public static void addClayWorkingRecipe(IItemStack output, String topRow, String secondRow, String thirdRow, String fourthRow, String bottomRow) 
	{
		processInputs(output, topRow, secondRow, thirdRow, fourthRow, bottomRow, 0, true);
	}
	
	@ZenMethod
    public static void removeClayWorkingRecipe(IItemStack output, String topRow, String secondRow, String thirdRow, String fourthRow, String bottomRow) 
	{
		processInputs(output, topRow, secondRow, thirdRow, fourthRow, bottomRow, 0, false);
	}
			
	//Leather Working
	@ZenMethod
    public static void addLeatherWorkingRecipe(IItemStack output, String topRow, String secondRow, String thirdRow, String fourthRow, String bottomRow) 
	{
		processInputs(output, topRow, secondRow, thirdRow, fourthRow, bottomRow, 1, true);
	}
	
	@ZenMethod
    public static void removeLeatherWorkingRecipe(IItemStack output, String topRow, String secondRow, String thirdRow, String fourthRow, String bottomRow) 
	{
		processInputs(output, topRow, secondRow, thirdRow, fourthRow, bottomRow, 1, false);
	}
	
	//Stone Knapping	
	@ZenMethod
    public static void addStoneWorkingRecipe(IItemStack output, String topRow, String secondRow, String thirdRow, String fourthRow, String bottomRow) 
	{
		processInputs(output, topRow, secondRow, thirdRow, fourthRow, bottomRow, 2, true);
	}
	
	@ZenMethod
    public static void removeStoneWorkingRecipe(IItemStack output, String topRow, String secondRow, String thirdRow, String fourthRow, String bottomRow) 
	{
		processInputs(output, topRow, secondRow, thirdRow, fourthRow, bottomRow, 2, false);
	}
	
	private static class addKnappingAction implements IUndoableAction 
	{
		private ItemStack outputStack;
		private String[] pattern;
		private int KnappingType;
		private ItemStack KnappingIS;
		private ShapedRecipesTFC recipe;
		
		
		public addKnappingAction(ItemStack output, String[] pattern, int type)
		{
			this.outputStack = output;
			this.pattern = pattern;
			
			if(type == 0)
				this.KnappingIS = new ItemStack(TFCItems.flatClay, 1, 1);
			else if(type == 1)
				this.KnappingIS = new ItemStack(TFCItems.flatLeather, 1);
			else if(type == 2)
				this.KnappingIS = new ItemStack(TFCItems.flatRock, 1, OreDictionary.WILDCARD_VALUE);
			
			this.recipe = createRecipe(outputStack, new Object[] {pattern[0], pattern[1], pattern[2], pattern[3], pattern[4], '#', this.KnappingIS});
		}
		
		@Override
		public void apply() 
		{
			CraftingManagerTFC.getInstance().addRecipe(outputStack, new Object[] {pattern[0], pattern[1], pattern[2], pattern[3], pattern[4], '#', this.KnappingIS});
		}

		@Override
		public String describe() 
		{
			if(KnappingType == 0)
				return "Adding item '" + outputStack.getDisplayName() + "' to clay working recipes'";
			else if(KnappingType == 1)
				return "Adding item '" + outputStack.getDisplayName() + "' to leather working recipes'";
			else if(KnappingType == 2)
				return "Adding item '" + outputStack.getDisplayName() + "' to stone knapping recipes'";
			else
				return "Adding item '" + outputStack.getDisplayName() + "' to knapping recipes'";
		}

		@Override
		public boolean canUndo() 
		{
			return true;
		}
		
		@Override
		public void undo() 
		{
			List<IRecipe> KnappingList = CraftingManagerTFC.getInstance().getRecipeList();
			
			for (int i = 0; i < KnappingList.size(); i++)
			{
				if (KnappingList.get(i) != null && KnappingList.get(i) instanceof ShapedRecipesTFC)
				{
					ShapedRecipesTFC recipesTFC = (ShapedRecipesTFC)KnappingList.get(i);
					
					if(comparePatterns(recipesTFC, this.recipe))
						KnappingList.remove(i--);
				}
			}
		}
		
		@Override
		public String describeUndo() 
		{
			if(KnappingType == 0)
				return "Removing item '" + outputStack.getDisplayName() + "' from clay working recipes.'";
			else if(KnappingType == 1)
				return "Removing item '" + outputStack.getDisplayName() + "' from leather working recipes.'";
			else if(KnappingType == 2)
				return "Removing item '" + outputStack.getDisplayName() + "' from stone knapping recipes.'";
			else
				return "Removing item '" + outputStack.getDisplayName() + "' from knapping recipes.'";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}		
	}
	
	private static class removeKnappingAction implements IUndoableAction 
	{
		private ItemStack outputStack;
		private String[] pattern;
		private int KnappingType;
		private ItemStack KnappingIS;
		private ShapedRecipesTFC recipe;
		
		
		public removeKnappingAction(ItemStack output, String[] pattern, int type)
		{
			this.outputStack = output;
			this.pattern = pattern;
			
			if(type == 0)
				this.KnappingIS = new ItemStack(TFCItems.flatClay, 1, 1);
			else if(type == 1)
				this.KnappingIS = new ItemStack(TFCItems.flatLeather, 1);
			else if(type == 2)
				this.KnappingIS = new ItemStack(TFCItems.flatRock, 1, OreDictionary.WILDCARD_VALUE);
			
			this.recipe = createRecipe(outputStack, new Object[] {pattern[0], pattern[1], pattern[2], pattern[3], pattern[4], '#', this.KnappingIS});
		}
		
		@Override
		public void apply() 
		{
			List<IRecipe> KnappingList = CraftingManagerTFC.getInstance().getRecipeList();
			
			for (int i = 0; i < KnappingList.size(); i++)
			{
				if (KnappingList.get(i) != null && KnappingList.get(i) instanceof ShapedRecipesTFC)
				{
					ShapedRecipesTFC recipesTFC = (ShapedRecipesTFC)KnappingList.get(i);
					
					if(comparePatterns(recipesTFC, this.recipe))
						KnappingList.remove(i--);
				}
			}
		}

		@Override
		public String describe() 
		{
			if(KnappingType == 0)
				return "Removing item '" + outputStack.getDisplayName() + "' from clay working recipes.'";
			else if(KnappingType == 1)
				return "Removing item '" + outputStack.getDisplayName() + "' from leather working recipes.'";
			else if(KnappingType == 2)
				return "Removing item '" + outputStack.getDisplayName() + "' from stone knapping recipes.'";
			else
				return "Removing item '" + outputStack.getDisplayName() + "' from knapping recipes.'";
		}

		@Override
		public boolean canUndo() 
		{
			return true;
		}
		
		@Override
		public void undo() 
		{
			CraftingManagerTFC.getInstance().addRecipe(outputStack, new Object[] {pattern[0], pattern[1], pattern[2], pattern[3], pattern[4], '#', this.KnappingIS});
		}
		
		@Override
		public String describeUndo() 
		{			
			if(KnappingType == 0)
				return "Adding item '" + outputStack.getDisplayName() + "' to clay working recipes'";
			else if(KnappingType == 1)
				return "Adding item '" + outputStack.getDisplayName() + "' to leather working recipes'";
			else if(KnappingType == 2)
				return "Adding item '" + outputStack.getDisplayName() + "' to stone knapping recipes'";
			else
				return "Adding item '" + outputStack.getDisplayName() + "' to knapping recipes'";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}		
	}
	
	private static ShapedRecipesTFC createRecipe(ItemStack outputIS, Object objects[])
	{
		String pattern = "";
		
		int i = 0;
		
		int width = 0;
		int height = 0;
		
		while (objects[i] instanceof String)
		{
			String tempPattern = (String)objects[i++];
			height++;
			width = tempPattern.length();
			pattern = new StringBuilder().append(pattern).append(tempPattern).toString();
		}
		
		HashMap<Character, ItemStack> hashmap = new HashMap<Character, ItemStack>();
		
		for (; i < objects.length; i += 2)
		{
			Character character = (Character)objects[i];
			ItemStack isTemplate = null;
			
			if (objects[i + 1] instanceof Item)
			{
				isTemplate = new ItemStack((Item)objects[i + 1]);
			}
			else if (objects[i + 1] instanceof ItemStack)
			{
				isTemplate = (ItemStack)objects[i + 1];
			}
			
			hashmap.put(character, isTemplate);
		}

		ItemStack patternIS[] = new ItemStack[width * height];
		for (int j = 0; j < width * height; j++)
		{
			char c = pattern.charAt(j);
			
			if (hashmap.containsKey(Character.valueOf(c)))
			{
				patternIS[j] = hashmap.get(Character.valueOf(c)).copy();
			}
			else
			{
				patternIS[j] = null;
			}
		}

		return new ShapedRecipesTFC(width, height, patternIS, outputIS);
	}
	
	private static boolean comparePatterns(ShapedRecipesTFC recipe1, ShapedRecipesTFC recipe2)
	{		
		if (recipe1.getRecipeOutput().getItem() == recipe2.getRecipeOutput().getItem())
		{
			if(recipe1.getRecipeOutput().getItemDamage() == recipe2.getRecipeOutput().getItemDamage() &&
					recipe1.getRecipeOutput().stackSize == recipe2.getRecipeOutput().stackSize)
			{
				ItemStack[] pattern1 = recipe1.getRecipeItems();
				ItemStack[] pattern2 = recipe2.getRecipeItems();
				
				for(int i = 0; i < pattern1.length; i++)
				{
					if(pattern1[i] != null && pattern2[i] == null)
						return false;
					else if(pattern1[i] == null && pattern2[i] != null)
						return false;
				}
				return true;
			}
		}
		return false;
	}
	
	private static void processInputs(IItemStack output, String topRow, String secondRow, String thirdRow, String fourthRow, String bottomRow, int type, boolean addRecipe)
	{
		ItemStack outputStack = MineTweakerMC.getItemStack(output);
		
		String[] pattern = new String[5];
		
		if(outputStack == null || outputStack.getItem() == null)
			MineTweakerAPI.logError("Missing OutputStack");
		else if(topRow.length() < 2)
			MineTweakerAPI.logError("Incorrect pattern for the top row");
		else if(secondRow.length() < 2)
			MineTweakerAPI.logError("Incorrect pattern for the second row");
		else if(thirdRow.length() < 2)
			MineTweakerAPI.logError("Incorrect pattern for the third row");
		else if(fourthRow.length() < 2)
			MineTweakerAPI.logError("Incorrect pattern for the fourth row");
		else if(bottomRow.length() < 2)
			MineTweakerAPI.logError("Incorrect pattern for the bottom row");
		else
		{
			pattern[0] = topRow;
			pattern[1] = secondRow;
			pattern[2] = thirdRow;
			pattern[3] = fourthRow;
			pattern[4] = bottomRow;
			
			if(addRecipe)
				MineTweakerAPI.apply(new addKnappingAction(outputStack, pattern, type));
			else
				MineTweakerAPI.apply(new removeKnappingAction(outputStack, pattern, type));
		}
	}
}
