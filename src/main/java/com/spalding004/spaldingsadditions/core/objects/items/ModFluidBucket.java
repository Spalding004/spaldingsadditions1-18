package com.spalding004.spaldingsadditions.core.objects.items;

import com.spalding004.spaldingsadditions.SpaldingsAdditions.ModItemGroup;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

public class ModFluidBucket extends BucketItem{

	@SuppressWarnings("deprecation")
	public ModFluidBucket(Fluid fluid) {
		
		super(fluid, new Item.Properties().tab(ModItemGroup.instance));
		
	}

}
