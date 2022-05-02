package com.spalding004.spaldingsadditions.core.init;

import net.minecraft.world.food.FoodProperties;

public class ModFoods{
	
	public static final FoodProperties RAW_CHEVAL =  (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).meat().build();
	public static final FoodProperties CHEVAL_STEAK = (new FoodProperties.Builder()).nutrition(9).saturationMod(0.7F).meat().build();

}
