package awsome.tfctweaker.handlers.anvilHandlers;

import com.dunk.tfc.TileEntities.TEAnvil;
import com.dunk.tfc.api.Events.AnvilCraftEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class AnvilCraftingHandler 
{
	@SubscribeEvent
	public void onAnvilCrafting(AnvilCraftEvent event)
	{
		AnvilTransformationRecipe recipe = AnvilTransformationHandler.getInstance().findMatchingRecipe(event.result, event.input1, event.input2);

		if(event.anvilTE != null && event.anvilTE instanceof TEAnvil && recipe != null)
		{
			TEAnvil te = (TEAnvil)event.anvilTE;
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if(recipe.input1.hasTransformers())
			{
				IItemStack iitemstack = recipe.input1.applyTransform(MineTweakerMC.getIItemStack(event.input1), MineTweakerMC.getIPlayer(player));
				
				ItemStack is = MineTweakerMC.getItemStack(iitemstack);
				
				if(is != null)
					is.stackSize--;

				event.result =  is;
			}
			
			if(recipe.input2.hasTransformers())
			{
				IItemStack iitemstack = recipe.input2.applyTransform(MineTweakerMC.getIItemStack(event.input2), MineTweakerMC.getIPlayer(player));
				
				te.setInventorySlotContents(TEAnvil.INPUT2_SLOT, MineTweakerMC.getItemStack(iitemstack));
			}
		}
	}
}
