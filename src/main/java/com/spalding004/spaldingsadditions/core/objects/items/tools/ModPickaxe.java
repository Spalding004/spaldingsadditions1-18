package com.spalding004.spaldingsadditions.core.objects.items.tools;

import com.spalding004.spaldingsadditions.SpaldingsAdditions.ModItemGroup;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class ModPickaxe extends PickaxeItem {

	public ModPickaxe(Tier tier) {
		super(tier, (int) tier.getAttackDamageBonus(), -2.5F, new Item.Properties().tab(ModItemGroup.instance));
	
		// TODO Auto-generated constructor stub
	}

	
	
}
