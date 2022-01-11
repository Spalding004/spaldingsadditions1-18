package com.spalding004.spaldingsadditions.world.features;

import java.util.List;

import com.spalding004.spaldingsadditions.core.init.ModBlocks;
import net.minecraft.data.worldgen.features.FeatureUtils;
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

public class ModConfiguredFeature {

	public static final ConfiguredFeature<TreeConfiguration, ?> BEECH_TREE =

			FeatureUtils.register("beech",
					Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(
							BlockStateProvider.simple(ModBlocks.BEECH_LOG.get()), 
								new StraightTrunkPlacer(5, 6, 3),
							BlockStateProvider.simple(ModBlocks.BEECH_LEAVES.get()),
							new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
							new TwoLayersFeatureSize(1, 0, 2)).build()));

	public static final ConfiguredFeature<RandomFeatureConfiguration, ?> BEECH_TREE_CHECKED = FeatureUtils
			.register("beech_tree_feature",
					Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(
							List.of(new WeightedPlacedFeature(
							BEECH_TREE.filteredByBlockSurvival(ModBlocks.BEECH_SAPLING.get()), 0.1f)),
							BEECH_TREE.filteredByBlockSurvival(ModBlocks.BEECH_SAPLING.get()))));


	
	
}
