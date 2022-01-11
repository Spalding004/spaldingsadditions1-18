package com.spalding004.spaldingsadditions.core.objects.items;

import com.spalding004.spaldingsadditions.SpaldingsAdditions.ModItemGroup;

import net.minecraft.world.item.Item;

public class ModGenericItem extends Item {

	public ModGenericItem() {
		super(new Item.Properties().tab(ModItemGroup.instance)
				);
		
	}
	
}
