package awsome.tfctweaker.handlers.anvilHandlers;

import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class AnvilTransformationRecipe 
{
	public IIngredient input1;
	public IIngredient input2;
	public IItemStack output;
	public String plan;
	public int AnvilReq;
	
	public AnvilTransformationRecipe(IItemStack output, IIngredient input1, IIngredient input2, String plan, int AnvilReq)
	{
		this.output = output;
		this.input1 = input1;
		this.input2 = input2;
		this.plan = plan;
		this.AnvilReq = AnvilReq;
	}
	
	public boolean matches(ItemStack output, ItemStack input1, ItemStack input2)
	{
		boolean matchFound = true;
		
		if(!OreDictionary.itemMatches(MineTweakerMC.getItemStack(this.output), output, false))
			matchFound = false;
		
		if(!OreDictionary.itemMatches(MineTweakerMC.getItemStack(this.input1), input1, false))
			matchFound = false;
		
		if(!OreDictionary.itemMatches(MineTweakerMC.getItemStack(this.input2), input2, false))
			matchFound = false;
		
		return matchFound;
	}
}
