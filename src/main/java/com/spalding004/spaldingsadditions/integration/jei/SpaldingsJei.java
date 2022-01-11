package com.spalding004.spaldingsadditions.integration.jei;

import java.util.Objects;
import java.util.Set;
import com.google.common.collect.ImmutableSet;
import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.core.init.ModBlocks;
import com.spalding004.spaldingsadditions.recipes.DimensionalFabricatorRecipe;
import com.spalding004.spaldingsadditions.recipes.FrakhammerRecipe;
import com.spalding004.spaldingsadditions.screen.fabricator.FabricatorScreen;
import com.spalding004.spaldingsadditions.screen.frakhammer.FrakhammerScreen;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;


@JeiPlugin
public class SpaldingsJei implements IModPlugin{

	@Override
	public ResourceLocation getPluginUid() {
		// TODO Auto-generated method stub
		return new ResourceLocation(SpaldingsAdditions.MOD_ID, "jei_plugin");
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
	{
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.FABRICATOR.get()), FabricatorRecipeCategory.UID);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.FRAKHAMMER.get()), FrakhammerRecipeCategory.UID);
		
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		
		registration.addRecipeCategories(new FabricatorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new FrakhammerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		
		Minecraft mc = Minecraft.getInstance();
		ClientLevel world = Objects.requireNonNull(mc.level);
		
		Set<DimensionalFabricatorRecipe> fabricating = ImmutableSet.copyOf(world.getRecipeManager().getAllRecipesFor(DimensionalFabricatorRecipe.Type.INSTANCE));
		registration.addRecipes(fabricating, FabricatorRecipeCategory.UID);
		
		Set<FrakhammerRecipe> frakking = ImmutableSet.copyOf(world.getRecipeManager().getAllRecipesFor(FrakhammerRecipe.Type.INSTANCE));
		registration.addRecipes(frakking, FrakhammerRecipeCategory.UID);
		
		
	}
	
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {

		registration.addRecipeClickArea(FabricatorScreen.class, 84, 40, 27, 17, FabricatorRecipeCategory.UID);
		registration.addRecipeClickArea(FrakhammerScreen.class, 84, 40, 27, 17, FrakhammerRecipeCategory.UID);
		
	}
	
	
}
