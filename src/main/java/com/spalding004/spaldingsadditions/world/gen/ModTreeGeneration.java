package com.spalding004.spaldingsadditions.world.gen;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import com.spalding004.spaldingsadditions.world.features.ModPlacedFeatures;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModTreeGeneration {
	
	public static void generateTrees(final BiomeLoadingEvent event) {
		
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
		Set<Type> types = BiomeDictionary.getTypes(key);
		
		if(types.contains(BiomeDictionary.Type.PLAINS)) {
			
			List<Supplier<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
			
			base.add(() -> ModPlacedFeatures.BEECH_TREE_PLACED);
			
		}
		
	}

}
