package com.spalding004.spaldingsadditions.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.core.init.ModBlocks;
import com.spalding004.spaldingsadditions.recipes.CombinatrixRecipe;
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

public class CombinatrixRecipeCategory implements IRecipeCategory<CombinatrixRecipe>{

	public final static ResourceLocation UID = new ResourceLocation(SpaldingsAdditions.MOD_ID, "combinatrix");
	public final static ResourceLocation TEXTURE = new ResourceLocation(SpaldingsAdditions.MOD_ID, "textures/gui/combinatrix_jei.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableStatic arrow;
	protected final IDrawableAnimated animatedArrow;
	
	
	@SuppressWarnings("deprecation") // I know, I know...
	public CombinatrixRecipeCategory(IGuiHelper helper) {
	      this.background = helper.createDrawable(TEXTURE, 0, 0, 104, 52);
	      this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.COMBINATRIX.get()));
	      this.arrow = helper.createDrawable(TEXTURE, 9, 59, 12, 25);
	      this.animatedArrow = helper.createAnimatedDrawable(arrow, 60, StartDirection.LEFT, false);
	   }
	
	@Override
	public ResourceLocation getUid() {
		// TODO Auto-generated method stub
		return UID;
	}

	@Override
	public Class<? extends CombinatrixRecipe> getRecipeClass() {
		// TODO Auto-generated method stub
		return CombinatrixRecipe.class;
	}

	@Override
	public Component getTitle() {
		// TODO Auto-generated method stub
		return ModBlocks.COMBINATRIX.get().getName();
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
	public void setIngredients(CombinatrixRecipe recipe, IIngredients ingredients) {
		NonNullList<Ingredient> inputs = NonNullList.create();
		inputs.addAll(recipe.getIngredients());
		
		ingredients.setInputIngredients(inputs);
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
		
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CombinatrixRecipe recipe, IIngredients ingredients) {
		//this.ticksInRecipe = recipe.getTicks();
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 6, 9);
		guiItemStacks.init(1, true, 27, 9);
		guiItemStacks.init(2, true, 6, 30);
		guiItemStacks.init(3, true, 27, 30);
		guiItemStacks.init(4, false, 78, 18);
		
		guiItemStacks.set(ingredients);
		
		
	}
	
	@Override
	public void draw(CombinatrixRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) 
	{
		
		animatedArrow.draw(matrixStack, 54, 15);
		
	
	}

}
