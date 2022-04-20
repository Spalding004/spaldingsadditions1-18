package com.spalding004.spaldingsadditions.core.init;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;

import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModStructureReg {

	public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, SpaldingsAdditions.MOD_ID);
	
//	public static final RegistryObject<StructureFeature<JigsawConfiguration>> ENDED_PORTAL 
//	= STRUCTURES.register("ended_portal", () -> new EndedPortalStructure(true));
}
