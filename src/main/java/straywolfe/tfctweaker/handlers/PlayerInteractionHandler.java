package straywolfe.tfctweaker.handlers;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemBloom;
import com.bioxx.tfc.Items.ItemIngot;
import com.bioxx.tfc.Items.ItemMetalSheet;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.Items.ItemUnfinishedArmor;
import com.bioxx.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import straywolfe.tfctweaker.util.ReferenceList;

public class PlayerInteractionHandler 
{
	@SubscribeEvent
	public void onToolTip(ItemTooltipEvent event)
	{
		ItemStack object = event.itemStack;
		
		if(!(object.getItem() instanceof ItemTerra || object.getItem() instanceof ItemTerraBlock))
		{
			if (object.hasTagCompound())
			{
				if(TFC_ItemHeat.hasTemp(object))
				{
					float temp = TFC_ItemHeat.getTemp(object);
					float meltTemp = -1;
					HeatIndex hi = HeatRegistry.getInstance().findMatchingIndex(object);
					if(hi != null)
						meltTemp = hi.meltTemp;
	
					if(meltTemp != -1)
					{
						event.toolTip.add(TFC_ItemHeat.getHeatColor(temp, meltTemp));
					}
				}
			}
		}
		
		if(!(object.getItem() instanceof ItemIngot ||
			object.getItem() instanceof ItemMetalSheet ||
			object.getItem() instanceof ItemUnfinishedArmor ||
			object.getItem() instanceof ItemBloom ||
			object.getItem() == TFCItems.wroughtIronKnifeHead))
		{
			if (object.hasTagCompound())
			{
				if(TFC_ItemHeat.hasTemp(object))
				{
					
					if(ReferenceList.getInstance().isAnvilInged(object))
					{
						String s = "";
						if(HeatRegistry.getInstance().isTemperatureDanger(object))
						{
							s += EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.danger") + " | ";
						}
		
						if(HeatRegistry.getInstance().isTemperatureWeldable(object))
						{
							s += EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.weldable") + " | ";
						}
		
						if(HeatRegistry.getInstance().isTemperatureWorkable(object))
						{
							s += EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.workable");
						}
		
						if (!"".equals(s))
							event.toolTip.add(s);
					}
				}
			}
		}
		
		if(!(object.getItem() instanceof ItemTerra))
		{
			if (object.hasTagCompound())
			{
				if (object.getTagCompound().hasKey(TEAnvil.ITEM_CRAFTING_VALUE_TAG) || object.getTagCompound().hasKey(TEAnvil.ITEM_CRAFTING_RULE_1_TAG))
					event.toolTip.add(TFC_Core.translate("gui.ItemWorked"));
			}
		}
	}
}
