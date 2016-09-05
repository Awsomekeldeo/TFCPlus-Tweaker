package straywolfe.tfctweaker.handlers.anvilHandlers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class AnvilTransformationHandler 
{
	private static final AnvilTransformationHandler INSTANCE = new AnvilTransformationHandler();
	public static final AnvilTransformationHandler getInstance()
	{
		return INSTANCE;
	}
	
	public List<AnvilTransformationRecipe> anvilTransformationList;
	
	private AnvilTransformationHandler()
	{
		anvilTransformationList = new ArrayList<AnvilTransformationRecipe>();
	}
	
	public void addTransformation(AnvilTransformationRecipe recipe)
	{
		anvilTransformationList.add(recipe);
	}
	
	public void removeTransformation(AnvilTransformationRecipe recipe)
	{
		for(int i = 0; i < anvilTransformationList.size(); i++)
		{
			AnvilTransformationRecipe listedRecipe = anvilTransformationList.get(i);
			boolean matchingRecipe = true;
			
			if(!listedRecipe.output.equals(recipe.output))
				matchingRecipe = false;
			
			if(!listedRecipe.input1.equals(recipe.input1))
				matchingRecipe = false;
			
			if(!listedRecipe.input2.equals(recipe.input2))
				matchingRecipe = false;
			
			if(!listedRecipe.plan.equals(recipe.plan))
				matchingRecipe = false;
			
			if(listedRecipe.AnvilReq != recipe.AnvilReq)
				matchingRecipe = false;
			
			if(matchingRecipe)
				anvilTransformationList.remove(i);
		}
	}
	
	public AnvilTransformationRecipe findMatchingRecipe(ItemStack output, ItemStack input1, ItemStack input2)
	{
		for(AnvilTransformationRecipe recipe : anvilTransformationList)
		{
			if(recipe.matches(output, input1, input2))
				return recipe;
		}
		
		return null;
	}
}
