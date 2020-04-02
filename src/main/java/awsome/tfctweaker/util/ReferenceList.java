package awsome.tfctweaker.util;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ReferenceList 
{
	private static final ReferenceList INSTANCE = new ReferenceList();
	public static final ReferenceList getInstance()
	{
		return INSTANCE;
	}
	
	public List<ItemStack> anvilIngredList;
	
	private ReferenceList()
	{
		anvilIngredList = new ArrayList<ItemStack>();
	}
	
	public void addAnvilIngred(ItemStack newIS)
	{
		if(!isAnvilInged(newIS))
		{
			anvilIngredList.add(newIS);
		}
	}
	
	public boolean isAnvilInged(ItemStack is)
	{
		for(int i = 0; i < anvilIngredList.size(); i++)
		{
			ItemStack listIS = anvilIngredList.get(i);
			if(OreDictionary.itemMatches(listIS, is, false))
				return true;
		}
		return false;
	}
}