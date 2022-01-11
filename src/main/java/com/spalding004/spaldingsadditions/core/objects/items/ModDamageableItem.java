package com.spalding004.spaldingsadditions.core.objects.items;

import com.spalding004.spaldingsadditions.SpaldingsAdditions.ModItemGroup;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModDamageableItem extends Item {

	public ModDamageableItem(int i) {
		super(new Item.Properties()
				.tab(ModItemGroup.instance)
				.stacksTo(1)
				.durability(i));
	
		
	}
	
	@Override
	 public boolean isEnchantable(ItemStack p_41456_) {
	      return false;
	   }
	
}
