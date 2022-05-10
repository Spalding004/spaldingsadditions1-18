package com.spalding004.spaldingsadditions.core.objects.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModHiddenItem extends Item {

	public ModHiddenItem() {
		super(new Item.Properties()
				);
		
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isFoil(ItemStack stack) {
	      return true;
	   }
	
}
