package com.spalding004.spaldingsadditions.core.init;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.world.structures.ModNetherStructures;

import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 
 * Massive thanks to TelepathicGrunt for... all of the below.
 *
 */

	public class ModStructures {

	    /**
	     * We are using the Deferred Registry system to register our structure as this is the preferred way on Forge.
	     * This will handle registering the base structure for us at the correct time so we don't have to handle it ourselves.
	     *
	     * HOWEVER, do note that Deferred Registries only work for anything that is a Forge Registry. This means that
	     * configured structures and configured features need to be registered directly to BuiltinRegistries as there
	     * is no Deferred Registry system for them.
	     */
	    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, SpaldingsAdditions.MOD_ID);

	    /**
	     * Registers the base structure itself and sets what its path is. In this case,
	     * this base structure will have the resourcelocation of structure_tutorial:sky_structures.
	     */
	   // public static final RegistryObject<StructureFeature<?>> MOD_NETHER_STRUCTURES = DEFERRED_REGISTRY_STRUCTURE.register("mod_nether_structures", ModNetherStructures::new);

	}
	

