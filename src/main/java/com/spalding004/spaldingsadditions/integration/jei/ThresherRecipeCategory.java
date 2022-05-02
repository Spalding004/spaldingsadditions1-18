package com.spalding004.spaldingsadditions.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.core.init.ModBlocks;
import com.spalding004.spaldingsadditions.recipes.ThresherRecipe;
import com.spalding004.spaldingsadditions.recipes.DimensionalFabricatorRecipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class ThresherRecipeCategory implements IRecipeCategory<ThresherRecipe>{

	public final static ResourceLocation UID = new ResourceLocation(SpaldingsAdditions.MOD_ID, "thresher");
	public final static ResourceLocation TEXTURE = new ResourceLocation(SpaldingsAdditions.MOD_ID, "textures/gui/thresher_jei.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableStatic arrow;
	protected final IDrawableAnimated animatedArrow;
	
	
	@SuppressWarnings("deprecation") // I know, I know...
	public ThresherRecipeCategory(IGuiHelper helper) {
	      this.background = helper.createDrawable(TEXTURE, 0, 0, 90, 62);
	      this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.THRESHER.get()));
	      this.arrow = helper.createDrawable(TEXTURE, 0, 65, 26, 8);
	      this.animatedArrow = helper.createAnimatedDrawable(arrow, 60, StartDirection.TOP, false);
	   }
	
	@Override
	public ResourceLocation getUid() {
		// TODO Auto-generated method stub
		return UID;
	}

	@Override
	public Class<? extends ThresherRecipe> getRecipeClass() {
		// TODO Auto-generated method stub
		return ThresherRecipe.class;
	}

	@Override
	public Component getTitle() {
		// TODO Auto-generated method stub
		return ModBlocks.THRESHER.get().getName();
	}

	@Override
	public IDrawable getBackground() {
		// TODO Auto-generated method stub
		return this.background;
	}

	@Override
	public IDrawable getIcon() {
		// TODO Auto-generated method stub
		return this.icon;
	}

	@Override
	public void setIngredients(ThresherRecipe recipe, IIngredients ingredients) {
		NonNullList<Ingredient> inputs = NonNullList.create();
		inputs.addAll(recipe.getIngredients());
		
		
		ingredients.setInputIngredients(inputs);
		
		ingredients.setOutputs(VanillaTypes.ITEM_STACK, recipe.getAllOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ThresherRecipe recipe, IIngredients ingredients) {
		//this.ticksInRecipe = recipe.getTicks();
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 34, 3);
		guiItemStacks.init(1, false, 6, 33);
		guiItemStacks.init(2, false, 25, 33);
		guiItemStacks.init(3, false, 44, 33);
		guiItemStacks.init(4, false, 63, 33);
		
		guiItemStacks.set(ingredients);
		
		
	}
	
	@Override
	public void draw(ThresherRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) 
	{
		
		animatedArrow.draw(matrixStack, 31, 22);
		
	
	}

}
