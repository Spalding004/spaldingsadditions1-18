package com.spalding004.spaldingsadditions.world.features.underground;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.core.init.ModBlocks;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.VerticalAnchor;
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

	public static final List<Holder<PlacedFeature>> OVERWORLD_ORES = new ArrayList<>();
	public static final List<Holder<PlacedFeature>> NETHER_ORES = new ArrayList<>();
	public static final List<Holder<PlacedFeature>> END_ORES = new ArrayList<>();
	public static final List<Holder<PlacedFeature>> WATER_ORES = new ArrayList<>();
	public static final List<Holder<PlacedFeature>> OCEAN_ORES = new ArrayList<>();

	public static final RuleTest NETHERRACK_TEST = new BlockMatchTest(Blocks.NETHERRACK);
	public static final RuleTest COAL_TEST = new BlockMatchTest(Blocks.COAL_ORE);
	public static final RuleTest END_TEST = new BlockMatchTest(Blocks.END_STONE);
	public static final RuleTest VENDAR_TEST = new BlockMatchTest(ModBlocks.VENDAR_ORE.get());
	public static final RuleTest UMBER_TEST = new BlockMatchTest(ModBlocks.UMBER.get());
	public static final RuleTest SHALE_TEST = new BlockMatchTest(ModBlocks.SHALE.get());

	public static void registerOres() {

		// overworld stones

		final Holder<ConfiguredFeature<OreConfiguration, ?>> marcasite_stone = FeatureUtils.register("marcasite_stone",
				Feature.ORE,
						new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
								ModBlocks.MARCASITE.get().defaultBlockState())), 33));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> shale_stone = FeatureUtils.register("shale_stone",
				Feature.ORE, new OreConfiguration(List.of(OreConfiguration
						.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SHALE.get().defaultBlockState())), 55));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> apatite_stone = FeatureUtils.register("apatite_stone",
				Feature.ORE, new OreConfiguration(List.of(OreConfiguration
						.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.APATITE.get().defaultBlockState())), 33));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> pumice_stone = FeatureUtils.register("pumice_stone",
				Feature.ORE, new OreConfiguration(List.of(OreConfiguration
						.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PUMICE.get().defaultBlockState())), 10));

		// nether stones

		final Holder<ConfiguredFeature<OreConfiguration, ?>> alunite_stone = FeatureUtils.register("alunite_stone",
				Feature.ORE,(
						new OreConfiguration(List.of(OreConfiguration.target(NETHERRACK_TEST,
								ModBlocks.ALUNITE.get().defaultBlockState())), 33)));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> cormalite_stone = FeatureUtils.register("cormalite_stone",
				Feature.ORE,(
						new OreConfiguration(List.of(OreConfiguration.target(NETHERRACK_TEST,
								ModBlocks.CORMALITE.get().defaultBlockState())), 33)));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> umber_stone = FeatureUtils
				.register("umber_stone",
						Feature.ORE,(new OreConfiguration(List.of(OreConfiguration
								.target(NETHERRACK_TEST, ModBlocks.UMBER.get().defaultBlockState())),
								25)));

		/*
		 * 
		 * 
		 * overworld ores
		 * 
		 * 
		 */

		final Holder<ConfiguredFeature<OreConfiguration, ?>> indirium_ore = FeatureUtils.register("indirium_ore",
				Feature.ORE,(
						new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
								ModBlocks.INDIRIUM_ORE.get().defaultBlockState())), 6)));

		
		final Holder<ConfiguredFeature<OreConfiguration, ?>> vendar_ore = FeatureUtils.register("vendar_ore",
				Feature.ORE,(
						new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
								ModBlocks.VENDAR_ORE.get().defaultBlockState())), 5)));
		
		final Holder<ConfiguredFeature<OreConfiguration, ?>> vendar_ore_dense = FeatureUtils.register("vendar_ore_dense",
				Feature.ORE,(
						new OreConfiguration(List.of(OreConfiguration.target(VENDAR_TEST,
								ModBlocks.VENDAR_ORE_DENSE.get().defaultBlockState())), 3)));
		
		final Holder<ConfiguredFeature<OreConfiguration, ?>> prismarine_ore = FeatureUtils.register("prismarine_ore",
				Feature.ORE,(
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
		
		final Holder<ConfiguredFeature<OreConfiguration, ?>> umber_gold_ore = FeatureUtils.register("umber_gold_ore",
				Feature.ORE,(
						new OreConfiguration(List.of(OreConfiguration.target(UMBER_TEST,
								ModBlocks.UMBER_GOLD_ORE.get().defaultBlockState())), 6)));
		
		final Holder<ConfiguredFeature<OreConfiguration, ?>> geldar_ore = FeatureUtils.register("geldar_ore",
				Feature.ORE,(
						new OreConfiguration(List.of(OreConfiguration.target(NETHERRACK_TEST,
								ModBlocks.GELDAR_ORE.get().defaultBlockState())), 6)));
		
		/*
		 * 
		 * 
		 * END ORES
		 * 
		 * 
		 */
		
		final Holder<ConfiguredFeature<OreConfiguration, ?>> vironium_ore = FeatureUtils.register("vironium_ore",
				Feature.ORE,(
						new OreConfiguration(List.of(OreConfiguration.target(END_TEST,
								ModBlocks.VIRONIUM_ORE.get().defaultBlockState())), 6)));
		

		/*
		 *
		 * 
		 * coal ores
		 *
		 *
		 */

		final Holder<ConfiguredFeature<OreConfiguration, ?>> coal_ore_diamond = FeatureUtils.register("coal_ore_diamond",
				Feature.ORE,(new OreConfiguration(List.of(
						OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_DIAMOND.get().defaultBlockState())), 4)));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> coal_ore_iron = FeatureUtils.register("coal_ore_iron",
				Feature.ORE,(new OreConfiguration(
						List.of(OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_IRON.get().defaultBlockState())),
						4)));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> coal_ore_gold = FeatureUtils.register("coal_ore_gold",
				Feature.ORE,(new OreConfiguration(
						List.of(OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_GOLD.get().defaultBlockState())),
						4)));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> coal_ore_emerald = FeatureUtils.register("coal_ore_emerald",
				Feature.ORE,(new OreConfiguration(List.of(
						OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_EMERALD.get().defaultBlockState())), 4)));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> coal_ore_redstone = FeatureUtils.register("coal_ore_redstone",
				Feature.ORE,(new OreConfiguration(List.of(
						OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_REDSTONE.get().defaultBlockState())), 4)));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> coal_ore_lapis = FeatureUtils.register("coal_ore_lapis",
				Feature.ORE,(new OreConfiguration(
						List.of(OreConfiguration.target(COAL_TEST, ModBlocks.COAL_ORE_LAPIS.get().defaultBlockState())),
						4)));

		final Holder<ConfiguredFeature<OreConfiguration, ?>> coal_ore_copper = FeatureUtils.register("coal_ore_copper",
				Feature.ORE,(new OreConfiguration(
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
		
		

		final Holder<PlacedFeature> placedIndiriumOre = PlacementUtils.register("indirium_ore",
				indirium_ore,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.aboveBottom(200)),
						InSquarePlacement.spread(), CountPlacement.of(50));
		OVERWORLD_ORES.add(placedIndiriumOre);

		final Holder<PlacedFeature> placedVendarOre = PlacementUtils.register("vendar_ore",
				vendar_ore, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(20)),
						InSquarePlacement.spread(), CountPlacement.of(16));
		OVERWORLD_ORES.add(placedVendarOre);
		
		final Holder<PlacedFeature> placedVendarOreDense = PlacementUtils.register("vendar_ore_dense",
				vendar_ore_dense, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(20)),
						InSquarePlacement.spread(), CountPlacement.of(40));
		OVERWORLD_ORES.add(placedVendarOreDense);

		final Holder<PlacedFeature> placedMarcasite = PlacementUtils.register("marcasite_stone",
				marcasite_stone,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(120)),
						InSquarePlacement.spread(), CountPlacement.of(7));
		OVERWORLD_ORES.add(placedMarcasite);

		final Holder<PlacedFeature> placedApatite = PlacementUtils.register("apatite_stone",
				apatite_stone,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(120)),
						InSquarePlacement.spread(), CountPlacement.of(7));
		OVERWORLD_ORES.add(placedApatite);

		final Holder<PlacedFeature> placedPumice = PlacementUtils.register("pumice_stone",
				pumice_stone,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(55)),
						InSquarePlacement.spread(), CountPlacement.of(7));
		OVERWORLD_ORES.add(placedPumice);

		final Holder<PlacedFeature> placedShale = PlacementUtils.register("shale_stone",
				shale_stone,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)),
						InSquarePlacement.spread(), CountPlacement.of(15));
		WATER_ORES.add(placedShale);

		final Holder<PlacedFeature> placedAlunite = PlacementUtils.register("alunite_stone",
				alunite_stone,
						HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(180)),
						InSquarePlacement.spread(), CountPlacement.of(7));
		NETHER_ORES.add(placedAlunite);

		final Holder<PlacedFeature> placedCormalite = PlacementUtils.register("cormalite_stone",
				cormalite_stone,
						HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(50), VerticalAnchor.absolute(95)),
						InSquarePlacement.spread(), CountPlacement.of(7));
		NETHER_ORES.add(placedCormalite);

		final Holder<PlacedFeature> placedUmber = PlacementUtils.register("umber_stone",
				umber_stone, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
						InSquarePlacement.spread(), CountPlacement.of(12));
		NETHER_ORES.add(placedUmber);
		
		final Holder<PlacedFeature> placedUmberGold = PlacementUtils.register("umber_gold_ore",
				umber_gold_ore, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
						InSquarePlacement.spread(), CountPlacement.of(12));
		NETHER_ORES.add(placedUmberGold);
		
		final Holder<PlacedFeature> placedGeldar = PlacementUtils.register("geldar_ore",
				geldar_ore, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
						InSquarePlacement.spread(), CountPlacement.of(64));
		NETHER_ORES.add(placedGeldar);
		
		final Holder<PlacedFeature> placedVironium = PlacementUtils.register("vironium_ore",
				vironium_ore, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)),
						InSquarePlacement.spread(), CountPlacement.of(48));
		END_ORES.add(placedVironium);

		final Holder<PlacedFeature> placedPrismarine = PlacementUtils.register("prismarine_ore",
				prismarine_ore, HeightRangePlacement.uniform(VerticalAnchor.absolute(20), VerticalAnchor.absolute(60)),
						InSquarePlacement.spread(), CountPlacement.of(60));
		WATER_ORES.add(placedPrismarine);
		
		
		/*
		 * 
		 * 
		 * coal ores diamond, iron, gold, redstone, emerald, lapis
		 * 
		 */

		final Holder<PlacedFeature> placedCoalDiamond = PlacementUtils.register("coal_ore_diamond",
				coal_ore_diamond,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(30)),
						InSquarePlacement.spread(), CountPlacement.of(60));
		OVERWORLD_ORES.add(placedCoalDiamond);

		final Holder<PlacedFeature> placedCoalCopper = PlacementUtils.register("coal_ore_copper",
				coal_ore_copper,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(120)),
						InSquarePlacement.spread(), CountPlacement.of(80));
		OVERWORLD_ORES.add(placedCoalCopper);

		final Holder<PlacedFeature> placedCoalIron = PlacementUtils.register("coal_ore_iron",
				coal_ore_iron,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(80)),
						InSquarePlacement.spread(), CountPlacement.of(140));
		OVERWORLD_ORES.add(placedCoalIron);

		final Holder<PlacedFeature> placedCoalGold = PlacementUtils.register("coal_ore_gold",
				coal_ore_gold,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40)),
						InSquarePlacement.spread(), CountPlacement.of(120));
		OVERWORLD_ORES.add(placedCoalGold);

		final Holder<PlacedFeature> placedCoalEmerald = PlacementUtils.register("coal_ore_emerald",
				coal_ore_emerald,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)),
						InSquarePlacement.spread(), CountPlacement.of(70));
		OVERWORLD_ORES.add(placedCoalEmerald);

		final Holder<PlacedFeature> placedCoalRedstone = PlacementUtils.register("coal_ore_redstone",
				coal_ore_redstone,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(30)),
						InSquarePlacement.spread(), CountPlacement.of(90));
		OVERWORLD_ORES.add(placedCoalRedstone);

		final Holder<PlacedFeature> placedCoalLapis = PlacementUtils.register("coal_ore_lapis",
				coal_ore_lapis,
						HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40)),
						InSquarePlacement.spread(), CountPlacement.of(90));
		OVERWORLD_ORES.add(placedCoalLapis);
	}

	@Mod.EventBusSubscriber(modid = SpaldingsAdditions.MOD_ID, bus = Bus.FORGE)
	public static class ForgeBusSubscriber {
		@SubscribeEvent
		public static void biomeLoading(BiomeLoadingEvent event) {

			final List<Holder<PlacedFeature>> features = event.getGeneration()
					.getFeatures(Decoration.UNDERGROUND_ORES);

			if (event.getCategory() == BiomeCategory.RIVER || event.getCategory() == BiomeCategory.OCEAN) {
				ModOreGeneration.WATER_ORES.forEach(ore -> features.add(ore));
			}
			
			if (event.getCategory() == BiomeCategory.OCEAN) {
				ModOreGeneration.OCEAN_ORES.forEach(ore -> features.add(ore));
			}

			switch (event.getCategory()) {
			case NETHER -> ModOreGeneration.NETHER_ORES.forEach(ore -> features.add(ore));
			case THEEND -> ModOreGeneration.END_ORES.forEach(ore -> features.add(ore));
			default -> ModOreGeneration.OVERWORLD_ORES.forEach(ore -> features.add(ore));
			}

		}
	}

}
