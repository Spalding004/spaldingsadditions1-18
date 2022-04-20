package com.spalding004.spaldingsadditions.world.features.tree;

import java.util.Random;

import com.spalding004.spaldingsadditions.world.features.ModConfiguredFeature;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class BeechTreeGrower extends AbstractTreeGrower {

	
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random p_60014_, boolean p_60015_) {
		// TODO Auto-generated method stub
		return ModConfiguredFeature.BEECH_TREE;
	}}
