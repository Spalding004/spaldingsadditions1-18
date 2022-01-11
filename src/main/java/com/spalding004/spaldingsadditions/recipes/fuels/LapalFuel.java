package com.spalding004.spaldingsadditions.recipes.fuels;

import com.spalding004.spaldingsadditions.core.init.ModItems;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class LapalFuel {

	public static double getFuelStr(ItemStack fuel) {
		Item item = fuel.getItem();
		double str = 0.0D;
		
		if (item == (Items.LAPIS_LAZULI)) {
			str = 1.0D;
		}
		
	
		if (item == (ModItems.FRACTURED_LAPIS).get()) {
			str = 3.0D;
		}
	 
		
		if (item == (new ItemStack(Blocks.LAPIS_BLOCK)).getItem()) {
			str = 10.0D;
		}

		if (item == (ModItems.REDUCED_LAPIS_INGOT.get())) {
			str = 2.5D;
		}

		if (item == (ModItems.ENERGETIC_CRYSTAL.get())) {
			str = 15.0D;
		}

		if (item == (ModItems.CHARGED_CARBON.get())) {
			str = 45.0D;
		}

		return str;
	}
}
	

