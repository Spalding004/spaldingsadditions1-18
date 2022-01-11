package com.spalding004.spaldingsadditions.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.core.init.ModStructureReg;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.event.world.WorldEvent;

/*
 * 
 * 
 * MASSIVE THANKS TO MCJTY FOR HIS TUTORIAL ON HOW TO SET ALL OF THIS UP,
 * ALONG WITH TELEPATHICGRUNT, ON WHOM'S WORK THIS WAS BASED.
 * 
 * 
*/

public class ModStructures {

	public static ConfiguredStructureFeature<?, ?> CONFIGURED_ENDED_PORTAL = ModStructureReg.ENDED_PORTAL.get()
			.configured(new JigsawConfiguration(() -> PlainVillagePools.START, 0));

	public static void registerConfiguredStructures() {

		Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE,
				new ResourceLocation(SpaldingsAdditions.MOD_ID, "ended_portal"), CONFIGURED_ENDED_PORTAL);

	}

	public static void setupStructures() {

		setupMapSpacingAndLand(ModStructureReg.ENDED_PORTAL.get(), new StructureFeatureConfiguration(10, 3, 867552044),
				false);

	}

	private static <F extends StructureFeature<?>> void setupMapSpacingAndLand(F structure,
			StructureFeatureConfiguration structureFeatureConfiguration, boolean transform) {

		StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

		if (transform) {

			StructureFeature.NOISE_AFFECTING_FEATURES = ImmutableList.<StructureFeature<?>>builder()
					.addAll(StructureFeature.NOISE_AFFECTING_FEATURES).add(structure).build();

		}

		StructureSettings.DEFAULTS = ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
				.putAll(StructureSettings.DEFAULTS).put(structure, structureFeatureConfiguration).build();

		BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
			Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue()
					.structureSettings().structureConfig();

			if (structureMap instanceof ImmutableMap) {
				Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
				tempMap.put(structure, structureFeatureConfiguration);
				settings.getValue().structureSettings().structureConfig = tempMap;
			} else {
				structureMap.put(structure, structureFeatureConfiguration);
			}
		});

	}
	
	//@SubscribeEvent
	public static void addDimensionalSpacing(final WorldEvent.Load event) {
		
		if (event.getWorld() instanceof ServerLevel serverLevel) {
			
			ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();
			//skip superflat
			if (chunkGenerator instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) return;
			
			ConfiguredStructureFeature<? , ?> endedPortal  = CONFIGURED_ENDED_PORTAL;
			
			
			StructureSettings worldStructureConfig = chunkGenerator.getSettings();
			
			var structureToMultimap = new HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>,
					ResourceKey<Biome>>>();
			
			for (var biomeEntry : serverLevel.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {
				BiomeCategory category = biomeEntry.getValue().getBiomeCategory();
				if (endedPortal != null) {
					if (category == BiomeCategory.NETHER /*&& biomeEntry.getValue()*/) {
				//if (biomeEntry.getValue() == NetherBiomes.netherWastes()) {		
						
						associateBiomeToConfiguredStructure(structureToMultimap, endedPortal, biomeEntry.getKey());
					}
				}
				
			}
			
			 ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, 
			 ResourceKey<Biome>>> tempStructureToMultiMap =
	                    ImmutableMap.builder();
	            worldStructureConfig.configuredStructures.entrySet()
	                    .stream()
	                    .filter(entry -> !structureToMultimap.containsKey(entry.getKey()))
	                    .forEach(tempStructureToMultiMap::put);

	            // Add our structures to the structure map/multimap and set the world to use this combined map/multimap.
	            structureToMultimap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));

	            // Requires AccessTransformer (see resources/META-INF/accesstransformer.cfg)
	            worldStructureConfig.configuredStructures = tempStructureToMultiMap.build();
	        }
	    }
		
		
	private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultimap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey) {
        structureToMultimap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
        var configuredStructureToBiomeMultiMap = structureToMultimap.get(configuredStructureFeature.feature);
        if (configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey)) {
            SpaldingsAdditions.LOGGER.error("""
                    Detected 2 ConfiguredStructureFeatures that share the same base StructureFeature trying to be added to same biome. One will be prevented from spawning.
                    This issue happens with vanilla too and is why a Snowy Village and Plains Village cannot spawn in the same biome because they both use the Village base structure.
                    The two conflicting ConfiguredStructures are: {}, {}
                    The biome that is attempting to be shared: {}
                """,
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature),
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries()
                           .stream()
                           .filter(e -> e.getValue() == biomeRegistryKey)
                           .findFirst()
                           .get().getKey()),
                    biomeRegistryKey
            );
        } else {
            configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
        }
    }

	 @NotNull
	public
	    static PieceGeneratorSupplier.Context<JigsawConfiguration> createContextWithConfig(PieceGeneratorSupplier.Context<JigsawConfiguration> context, JigsawConfiguration newConfig) {
	        return new PieceGeneratorSupplier.Context<>(
	                context.chunkGenerator(),
	                context.biomeSource(),
	                context.seed(),
	                context.chunkPos(),
	                newConfig,
	                context.heightAccessor(),
	                context.validBiome(),
	                context.structureManager(),
	                context.registryAccess()
	        );
	    }

	    @SuppressWarnings("unused")
		private static final Lazy<List<MobSpawnSettings.SpawnerData>> STRUCTURE_MONSTERS = Lazy.of(() -> ImmutableList.of(
	            new MobSpawnSettings.SpawnerData(EntityType.ILLUSIONER, 200, 4, 9),
	            new MobSpawnSettings.SpawnerData(EntityType.VINDICATOR, 200, 4, 9)
	    ));
	//    @SubscribeEvent
	    public static void setupStructureSpawns(final StructureSpawnListGatherEvent event) {
	     /*
	    	if (event.getStructure() == ModStructures.ENDED_PORTAL.get() || event.getStructure() == Registration.PORTAL_MYSTERIOUS.get()) {
	            event.addEntitySpawns(MobCategory.MONSTER, STRUCTURE_MONSTERS.get());
	        }
	        */
	    }
	
	
}
