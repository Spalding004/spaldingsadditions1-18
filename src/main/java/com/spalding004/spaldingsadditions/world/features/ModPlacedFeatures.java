package com.spalding004.spaldingsadditions.world.features;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {

		public static final Holder<PlacedFeature> BEECH_TREE_PLACED = 
				PlacementUtils.register("beech_tree_placed", 
				ModConfiguredFeature.BEECH_TREE_SPAWN, (VegetationPlacements.treePlacement(
						PlacementUtils.countExtra(0, .05f, 1)))		
				);
		
		public static final Holder<PlacedFeature> CRAPE_TREE_PLACED = 
				PlacementUtils.register("crape_tree_placed", 
				ModConfiguredFeature.CRAPE_TREE_SPAWN, (VegetationPlacements.treePlacement(
						PlacementUtils.countExtra(0, .05f, 1)))		
				);

}
