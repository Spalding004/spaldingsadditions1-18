package com.spalding004.spaldingsadditions.core.objects.items;

import com.spalding004.spaldingsadditions.SpaldingsAdditions.ModItemGroup;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ModFoodItem extends Item {

	public ModFoodItem(FoodProperties food) {
		super(new Item.Properties().tab(ModItemGroup.instance).food(food)
				);
		
	}
	
}
