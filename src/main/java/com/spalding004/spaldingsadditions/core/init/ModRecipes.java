package com.spalding004.spaldingsadditions.core.init;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.recipes.CombinatrixRecipe;
import com.spalding004.spaldingsadditions.recipes.DimensionalFabricatorRecipe;
import com.spalding004.spaldingsadditions.recipes.FrakhammerRecipe;
import com.spalding004.spaldingsadditions.recipes.ThresherRecipe;

import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = 
			DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SpaldingsAdditions.MOD_ID);
	
	public static final RegistryObject<RecipeSerializer<FrakhammerRecipe>> FRAKHAMMER_RECIPE_SERIALIZER =
			SERIALIZERS.register("frakhammer", () -> FrakhammerRecipe.Serializer.INSTANCE);
	
	public static final RegistryObject<RecipeSerializer<DimensionalFabricatorRecipe>> FABRICATOR_RECIPE_SERIALIZER =
			SERIALIZERS.register("fabricator", () -> DimensionalFabricatorRecipe.Serializer.INSTANCE);
	
	public static final RegistryObject<RecipeSerializer<CombinatrixRecipe>> COMBINATRIX_RECIPE_SERIALIZER =
			SERIALIZERS.register("combinatrix", () -> CombinatrixRecipe.Serializer.INSTANCE);
	
	public static final RegistryObject<RecipeSerializer<ThresherRecipe>> THRESHER_RECIPE_SERIALIZER =
			SERIALIZERS.register("thresher", () -> ThresherRecipe.Serializer.INSTANCE);
	
	  public static void register(IEventBus eventBus) {
	        SERIALIZERS.register(eventBus);
	        
	    }
	
}
