package com.spalding004.spaldingsadditions.world.features.underground;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.core.init.ModBlocks;

import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public class ModOreGeneration {

	public static final List<PlacedFeature> OVERWORLD_ORES = new ArrayList<>();
	public static final List<PlacedFeature> NETHER_ORES = new ArrayList<>();
	public static final List<PlacedFeature> END_ORES = new ArrayList<>();
	public static final List<PlacedFeature> WATER_ORES = new ArrayList<>();
	public static final List<PlacedFeature> OCEAN_ORES = new ArrayList<>();

	public static final RuleTest NETHERRACK_TEST = new BlockMatchTest(Blocks.NETHERRACK);
	public static final RuleTest COAL_TEST = new BlockMatchTest(Blocks.COAL_ORE);
	public static final RuleTest END_TEST = new BlockMatchTest(Blocks.END_STONE);
	public static final RuleTest VENDAR_TEST = new BlockMatchTest(ModBlocks.VENDAR_ORE.get());
	public static final RuleTest UMBER_TEST = new BlockMatchTest(ModBlocks.UMBER.get());
	public static final RuleTest SHALE_TEST = new BlockMatchTest(ModBlocks.SHALE.get());

	public static void registerOres() {

		// overworld stones

		final ConfiguredFeature<?, ?> marcasite_stone = FeatureUtils.register("marcasite_stone",
				Feature.ORE.configured(
						new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
								ModBlocks.MARCASITE.get().defaultBlockState())), 33)));

		final ConfiguredFeature<?, ?> shale_stone = FeatureUtils.register("shale_stone",
				Feature.ORE.configured(new OreConfiguration(List.of(OreConfiguration
						.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SHALE.get().defaultBlockState())), 55)));

		final ConfiguredFeature<?, ?> apatite_stone = FeatureUtils.register("apatite_stone",
				Feature.ORE.configured(new OreConfiguration(List.of(OreConfiguration
						.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.APATITE.get().defaultBlockState())), 33)));

		final ConfiguredFeature<?, ?> pumice_stone = FeatureUtils.register("pumice_stone",
				Feature.ORE.configured(new OreConfiguration(List.of(OreConfiguration
						.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PUMICE.get().defaultBlockState())), 10)));

		// nether stones

		final ConfiguredFeature<?, ?> alunite_stone = FeatureUtils.register("alunite_stone",
				Feature.ORE.configured(
						new OreConfiguration(List.of(OreConfiguration.target(NETHERRACK_TEST,
								ModBlocks.ALUNITE.get().defaultBlockState())), 33)));

		final ConfiguredFeature<?, ?> cormalite_stone = FeatureUtils.register("cormalite_stone",
				Feature.ORE.configured(
						new OreConfiguration(List.of(OreConfiguration.target(NETHERRACK_TEST,
								ModBlocks.CORMALITE.get().defaultBlockState())), 33)));

		final ConfiguredFeature<?, ?> umber_stone = FeatureUtils
				.register("umber_stone",
						Feature.ORE.configured(new OreConfiguration(List.of(OreConfiguration
								.target(NETHERRACK_TEST, ModBlocks.UMBER.get().defaultBlockState())),
								25)));

		/*
		 * 
		 * 
		 * overworld ores
		 * 
		 * 
		 */

		final ConfiguredFeature<?, ?> indirium_ore = FeatureUtils.register("indirium_ore",
				Feature.ORE.configured(
						new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
								ModBlocks.INDIRIUM_ORE.get().defaultBlockState())), 6)));

		
		final ConfiguredFeature<?, ?> vendar_ore = FeatureUtils.register("vendar_ore",
				Feature.ORE.configured(
						new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
								ModBlocks.VENDAR_ORE.get().defaultBlockState())), 5)));
		
		final ConfiguredFeature<?, ?> vendar_ore_dense = FeatureUtils.register("vendar_ore_dense",
				Feature.ORE.configured(
						new OreConfiguration(List.of(OreConfiguration.target(VENDAR_TEST,
								ModBlocks.VENDAR_ORE_DENSE.get().defaultBlockState())), 3)));
		
		final ConfiguredFeature<?, ?> prismarine_ore = FeatureUtils.register("prismarine_ore",
				Feature.ORE.configured(
						new OreConfiguration(List.of(OreConfiguration.target(SHALE_TEST,
								ModBlocks.PRISMARINE_ORE.get().defaultBlockState())), 15)));

		/*
		 *
		 *
		 * 
		 * NETHER ORES
		 *
		 *
		 */
		
		final ConfiguredFeature<?, ?> umber_gold_ore = FeatureUtils.register("umber_gold_ore",
				Feature.ORE.configured(
						new OreConfiguration(List.of(OreConfiguration.target(UMBER_TEST,
								ModBlocks.UMBER_GOLD_ORE.get().defaultBlockState())), 6)));
		
		final ConfiguredFeature<?, ?> geldar_ore = FeatureUtils.register("geldar_ore",
				Feature.ORE.configured(
						new OreConfiguration(List.of(OreConfiguration.target(UMBER_TEST,
								ModBlocks.GELDAR_ORE.get().defaultBlockState())), 6)));
		
		/*
		 * 
		 * 
		 * END ORES
		 * 
		 * 
		 */
		
		final ConfiguredFeature<?, ?> vironium_ore = FeatureUtils.register("vironium_ore",
				Feature.ORE.configured(
						new OreConfiguration(List.of(OreConfiguration.target(END_TEST,
								ModBlocks.VIRONIUM_ORE.get().defaultBlockState())), 6)));
		

		/*
		 *
		 * 
		 * coal ores
		 *
		 *
		 */

		final ConfiguredFeature<?, ?> coal_ore_diamond = FeatureUtils.register("coal_ore_diamond",
				Feature.ORE.configured(new OreConfiguration(List.of(
						OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_DIAMOND.get().defaultBlockState())), 4)));

		final ConfiguredFeature<?, ?> coal_ore_iron = FeatureUtils.register("coal_ore_iron",
				Feature.ORE.configured(new OreConfiguration(
						List.of(OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_IRON.get().defaultBlockState())),
						4)));

		final ConfiguredFeature<?, ?> coal_ore_gold = FeatureUtils.register("coal_ore_gold",
				Feature.ORE.configured(new OreConfiguration(
						List.of(OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_GOLD.get().defaultBlockState())),
						4)));

		final ConfiguredFeature<?, ?> coal_ore_emerald = FeatureUtils.register("coal_ore_emerald",
				Feature.ORE.configured(new OreConfiguration(List.of(
						OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_EMERALD.get().defaultBlockState())), 4)));

		final ConfiguredFeature<?, ?> coal_ore_redstone = FeatureUtils.register("coal_ore_redstone",
				Feature.ORE.configured(new OreConfiguration(List.of(
						OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_REDSTONE.get().defaultBlockState())), 4)));

		final ConfiguredFeature<?, ?> coal_ore_lapis = FeatureUtils.register("coal_ore_lapis",
				Feature.ORE.configured(new OreConfiguration(
						List.of(OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_LAPIS.get().defaultBlockState())),
						4)));

		final ConfiguredFeature<?, ?> coal_ore_copper = FeatureUtils.register("coal_ore_copper",
				Feature.ORE.configured(new OreConfiguration(
						List.of(OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_COPPER.get().defaultBlockState())),
						4)));

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * FEATURES
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		

		final PlacedFeature placedIndiriumOre = PlacementUtils.register("indirium_ore",
				indirium_ore.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.aboveBottom(200)),
						InSquarePlacement.spread(), CountPlacement.of(25)));
		OVERWORLD_ORES.add(placedIndiriumOre);

		final PlacedFeature placedVendarOre = PlacementUtils.register("vendar_ore",
				vendar_ore.placed(HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(20)),
						InSquarePlacement.spread(), CountPlacement.of(8)));
		OVERWORLD_ORES.add(placedVendarOre);
		
		final PlacedFeature placedVendarOreDense = PlacementUtils.register("vendar_ore_dense",
				vendar_ore_dense.placed(HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(20)),
						InSquarePlacement.spread(), CountPlacement.of(40)));
		OVERWORLD_ORES.add(placedVendarOreDense);

		final PlacedFeature placedMarcasite = PlacementUtils.register("marcasite_stone",
				marcasite_stone.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(120)),
						InSquarePlacement.spread(), CountPlacement.of(7)));
		OVERWORLD_ORES.add(placedMarcasite);

		final PlacedFeature placedApatite = PlacementUtils.register("apatite_stone",
				apatite_stone.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(120)),
						InSquarePlacement.spread(), CountPlacement.of(7)));
		OVERWORLD_ORES.add(placedApatite);

		final PlacedFeature placedPumice = PlacementUtils.register("pumice_stone",
				pumice_stone.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(55)),
						InSquarePlacement.spread(), CountPlacement.of(7)));
		OVERWORLD_ORES.add(placedPumice);

		final PlacedFeature placedShale = PlacementUtils.register("shale_stone",
				shale_stone.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)),
						InSquarePlacement.spread(), CountPlacement.of(15)));
		WATER_ORES.add(placedShale);

		final PlacedFeature placedAlunite = PlacementUtils.register("alunite_stone",
				alunite_stone.placed(
						HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(180)),
						InSquarePlacement.spread(), CountPlacement.of(7)));
		NETHER_ORES.add(placedAlunite);

		final PlacedFeature placedCormalite = PlacementUtils.register("cormalite_stone",
				cormalite_stone.placed(
						HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(50), VerticalAnchor.absolute(95)),
						InSquarePlacement.spread(), CountPlacement.of(7)));
		NETHER_ORES.add(placedCormalite);

		final PlacedFeature placedUmber = PlacementUtils.register("umber_stone",
				umber_stone.placed(HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
						InSquarePlacement.spread(), CountPlacement.of(12)));
		NETHER_ORES.add(placedUmber);
		
		final PlacedFeature placedUmberGold = PlacementUtils.register("umber_gold_ore",
				umber_gold_ore.placed(HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
						InSquarePlacement.spread(), CountPlacement.of(12)));
		NETHER_ORES.add(placedUmberGold);
		
		final PlacedFeature placedGeldar = PlacementUtils.register("geldar_ore",
				geldar_ore.placed(HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
						InSquarePlacement.spread(), CountPlacement.of(24)));
		NETHER_ORES.add(placedGeldar);
		
		final PlacedFeature placedVironium = PlacementUtils.register("vironium_ore",
				vironium_ore.placed(HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)),
						InSquarePlacement.spread(), CountPlacement.of(24)));
		END_ORES.add(placedVironium);

		final PlacedFeature placedPrismarine = PlacementUtils.register("prismarine_ore",
				prismarine_ore.placed(HeightRangePlacement.uniform(VerticalAnchor.absolute(20), VerticalAnchor.absolute(60)),
						InSquarePlacement.spread(), CountPlacement.of(60)));
		WATER_ORES.add(placedPrismarine);
		
		
		/*
		 * 
		 * 
		 * coal ores diamond, iron, gold, redstone, emerald, lapis
		 * 
		 */

		final PlacedFeature placedCoalDiamond = PlacementUtils.register("coal_ore_diamond",
				coal_ore_diamond.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(30)),
						InSquarePlacement.spread(), CountPlacement.of(60)));
		OVERWORLD_ORES.add(placedCoalDiamond);

		final PlacedFeature placedCoalCopper = PlacementUtils.register("coal_ore_copper",
				coal_ore_copper.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(120)),
						InSquarePlacement.spread(), CountPlacement.of(80)));
		OVERWORLD_ORES.add(placedCoalCopper);

		final PlacedFeature placedCoalIron = PlacementUtils.register("coal_ore_iron",
				coal_ore_iron.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(80)),
						InSquarePlacement.spread(), CountPlacement.of(140)));
		OVERWORLD_ORES.add(placedCoalIron);

		final PlacedFeature placedCoalGold = PlacementUtils.register("coal_ore_gold",
				coal_ore_gold.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40)),
						InSquarePlacement.spread(), CountPlacement.of(120)));
		OVERWORLD_ORES.add(placedCoalGold);

		final PlacedFeature placedCoalEmerald = PlacementUtils.register("coal_ore_emerald",
				coal_ore_emerald.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)),
						InSquarePlacement.spread(), CountPlacement.of(70)));
		OVERWORLD_ORES.add(placedCoalEmerald);

		final PlacedFeature placedCoalRedstone = PlacementUtils.register("coal_ore_redstone",
				coal_ore_redstone.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(30)),
						InSquarePlacement.spread(), CountPlacement.of(90)));
		OVERWORLD_ORES.add(placedCoalRedstone);

		final PlacedFeature placedCoalLapis = PlacementUtils.register("coal_ore_lapis",
				coal_ore_lapis.placed(
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40)),
						InSquarePlacement.spread(), CountPlacement.of(90)));
		OVERWORLD_ORES.add(placedCoalLapis);
	}

	@Mod.EventBusSubscriber(modid = SpaldingsAdditions.MOD_ID, bus = Bus.FORGE)
	public static class ForgeBusSubscriber {
		@SubscribeEvent
		public static void biomeLoading(BiomeLoadingEvent event) {

			final List<Supplier<PlacedFeature>> features = event.getGeneration()
					.getFeatures(Decoration.UNDERGROUND_ORES);

			if (event.getCategory() == BiomeCategory.RIVER || event.getCategory() == BiomeCategory.OCEAN) {
				ModOreGeneration.WATER_ORES.forEach(ore -> features.add(() -> ore));
			}
			
			if (event.getCategory() == BiomeCategory.OCEAN) {
				ModOreGeneration.OCEAN_ORES.forEach(ore -> features.add(() -> ore));
			}

			switch (event.getCategory()) {
			case NETHER -> ModOreGeneration.NETHER_ORES.forEach(ore -> features.add(() -> ore));
			case THEEND -> ModOreGeneration.END_ORES.forEach(ore -> features.add(() -> ore));
			default -> ModOreGeneration.OVERWORLD_ORES.forEach(ore -> features.add(() -> ore));
			}

		}
	}

}
