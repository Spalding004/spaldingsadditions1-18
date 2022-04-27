package com.spalding004.spaldingsadditions.world.features;

import java.util.List;

import com.spalding004.spaldingsadditions.core.init.ModBlocks;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModConfiguredFeature {

	public static final Holder<? extends ConfiguredFeature<TreeConfiguration, ?>> BEECH_TREE =

			FeatureUtils.register("beech",
					Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
							BlockStateProvider.simple(ModBlocks.BEECH_LOG.get()), 
								new StraightTrunkPlacer(5, 6, 3),
							BlockStateProvider.simple(ModBlocks.BEECH_LEAVES.get()),
							new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
							new TwoLayersFeatureSize(1, 0, 2)).build());
	
	public static final Holder<PlacedFeature> BEECH_TREE_CHECKED = PlacementUtils.register("beech_tree_checked", 
			BEECH_TREE, PlacementUtils.filteredByBlockSurvival(ModBlocks.BEECH_SAPLING.get()));

	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> BEECH_TREE_SPAWN = 
					FeatureUtils.register("beech_tree_spawn", Feature.RANDOM_SELECTOR, 
							new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(BEECH_TREE_CHECKED,
									.5F)), BEECH_TREE_CHECKED));


	public static final Holder<? extends ConfiguredFeature<TreeConfiguration, ?>> CRAPE_TREE =

			FeatureUtils.register("crape",
					Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
							BlockStateProvider.simple(ModBlocks.CRAPE_MYRTLE_LOG.get().defaultBlockState()), 
								new StraightTrunkPlacer(2, 3, 3),
							BlockStateProvider.simple(ModBlocks.CRAPE_MYRTLE_LEAVES.get()),
							new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
							new TwoLayersFeatureSize(1, 1, 1)).build());
	
	public static final Holder<PlacedFeature> CRAPE_TREE_CHECKED = PlacementUtils.register("crape_tree_checked", 
			CRAPE_TREE, PlacementUtils.filteredByBlockSurvival(ModBlocks.CRAPE_MYRTLE_CUTTING.get()));

	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CRAPE_TREE_SPAWN = 
					FeatureUtils.register("crape_tree_spawn", Feature.RANDOM_SELECTOR, 
							new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(CRAPE_TREE_CHECKED,
									.5F)), CRAPE_TREE_CHECKED));

	
}
