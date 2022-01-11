package com.spalding004.spaldingsadditions.core.init;

import java.util.function.Supplier;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class ModToolTiers {

	
	
	public enum ModItemTiers implements Tier {
		VENDAR(3121, 9.0F, 2.5F, 4, 16, () -> {
		return Ingredient.of(ModItems.VENDAR_INGOT.get());
	}),
		VENDAR_FRAK(512, 9.0F, 2.5F, 4, 16, () -> {
		return Ingredient.of(ModItems.VENDAR_INGOT.get());
	});;
		
		private final int harvest;
		private final int dur;
		private final float eff;
		private final float att;
		private final int enchant;
		private final Supplier<Ingredient> repair;
		
	private ModItemTiers(int dur, float eff, float att, int harvest, int enchant, Supplier<Ingredient> repair) {
		this.harvest = harvest;
		this.dur = dur;
		this.eff = eff;
		this.att = att;
		this.enchant = enchant;
		this.repair = repair;
		
	}

	@Override
	public int getUses() {
		// TODO Auto-generated method stub
		return dur;
	}

	@Override
	public float getSpeed() {
		// TODO Auto-generated method stub
		return eff;
	}

	@Override
	public float getAttackDamageBonus() {
		// TODO Auto-generated method stub
		return att;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return harvest;
	}

	@Override
	public int getEnchantmentValue() {
		// TODO Auto-generated method stub
		return enchant;
	}

	@Override
	public Ingredient getRepairIngredient() {
		// TODO Auto-generated method stub
		return repair.get();
	}
		

		
		
	}
	
	
}
