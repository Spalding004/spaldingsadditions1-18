package com.spalding004.spaldingsadditions.world.features;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {

		public static final PlacedFeature BEECH_TREE_PLACED = 
				PlacementUtils.register("beech_tree_placed", 
				ModConfiguredFeature.BEECH_TREE_CHECKED.placed(VegetationPlacements.treePlacement(
						PlacementUtils.countExtra(0, .05f, 1)))		
				);

}
