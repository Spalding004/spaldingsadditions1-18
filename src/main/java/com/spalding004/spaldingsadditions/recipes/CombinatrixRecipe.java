package com.spalding004.spaldingsadditions.recipes;

import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spalding004.spaldingsadditions.SpaldingsAdditions;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.crafting.CraftingHelper;

public class CombinatrixRecipe implements Recipe<SimpleContainer> {
	private final ResourceLocation id;
	private final ItemStack output;
	private final NonNullList<Ingredient> recipeItems;
	private final int ticks;

	public CombinatrixRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, int ticks) {
		this.id = id;
		this.output = output;
		this.recipeItems = recipeItems;
		this.ticks = ticks;
	}

	@Override
	public boolean isSpecial() {
		return true;

	}

	@SuppressWarnings("unused")
	@Override
	public boolean matches(SimpleContainer pContainer, net.minecraft.world.level.Level pLevel) {

		int recipeLen = recipeItems.size();
		int checkLen = 0;
		Boolean check = false;
		SimpleContainer copy1 = pContainer;
		SimpleContainer copy = pContainer;

		int conTotal = 0;
		int recTotal = 0;

		if (recipeLen != checkLen) {
			check = false;
		}

		boolean con1 = false;
		boolean con2 = false;
		boolean con3 = false;
		boolean con4 = false;

		int con1Slot = -1;
		int con2Slot = -1;
		int con3Slot = -1;
		int con4Slot = -1;

		for (int a = 1; a < 4; a++) {

			if (recipeItems.get(0).test(copy.getItem(a))) {

				con1 = true;
				con1Slot = a;
			}

		}

		for (int a = 1; a < 4; a++) {
			if (a != con1Slot) {
				if (recipeItems.get(1).test(copy.getItem(a))) {

					con2 = true;
					con2Slot = a;
				}
			}

		}

		for (int a = 1; a < 4; a++) {
			if (a != con1Slot && a != con2Slot) {
				if (recipeItems.get(2).test(copy.getItem(a))) {

					con3 = true;
					con3Slot = a;
				}
			}

		}

		for (int a = 1; a < 4; a++) {
			if (a != con1Slot && a != con2Slot && a != con3Slot) {
				if (recipeItems.get(3).test(copy.getItem(a))) {

					con4 = true;
					con4Slot = a;
				}
			}
		}

		if (con1)
			conTotal++;
		if (con2)
			conTotal++;
		if (con3)
			conTotal++;
		if (con4)
			conTotal++;

		if (!recipeItems.get(0).test(ItemStack.EMPTY))
			recTotal++;
		if (!recipeItems.get(1).test(ItemStack.EMPTY))
			recTotal++;
		if (!recipeItems.get(2).test(ItemStack.EMPTY))
			recTotal++;
		if (!recipeItems.get(3).test(ItemStack.EMPTY))
			recTotal++;

		//System.out.println(conTotal +" | " + recTotal);
		return (conTotal == recTotal);

	}

	@Override
	public ItemStack assemble(SimpleContainer pContainer) {
		return output;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.recipeItems;
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return output.copy();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	public int getTicks() {
		return ticks;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	public static class Type implements RecipeType<CombinatrixRecipe> {
		private Type() {
		}

		public static final Type INSTANCE = new Type();
		public static final String ID = "combinatrix";
	}

	public static class Serializer implements RecipeSerializer<CombinatrixRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID = new ResourceLocation(SpaldingsAdditions.MOD_ID, "combinatrix");

		@Override
		public CombinatrixRecipe fromJson(ResourceLocation id, JsonObject json) {

			ItemStack output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "output"), false);
			int tick = GsonHelper.getAsInt(json, "ticks", 29);
			output.setCount(GsonHelper.getAsInt(json, "count", 1));
			// ItemStack output =
			// ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

			JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");

			NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

			for (int i = 0; i < ingredients.size(); i++) {
				inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
			}

			return new CombinatrixRecipe(id, output, inputs, tick);
		}

		@Override
		public CombinatrixRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
			NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

			for (int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromNetwork(buf));
			}

			ItemStack output = buf.readItem();
			int tick = buf.readInt();
			// output.setCount(buf.readInt());

			return new CombinatrixRecipe(id, output, inputs, tick);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buf, CombinatrixRecipe recipe) {
			buf.writeInt(recipe.getIngredients().size());
			for (Ingredient ing : recipe.getIngredients()) {
				ing.toNetwork(buf);
			}
			buf.writeItemStack(recipe.getResultItem(), false);
			buf.writeInt(recipe.ticks);
			// buf.writeInt(recipe.getResultItem().getCount());

		}

		@Override
		public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
			return INSTANCE;
		}

		@Nullable
		@Override
		public ResourceLocation getRegistryName() {
			return ID;
		}

		@Override
		public Class<RecipeSerializer<?>> getRegistryType() {
			return Serializer.castClass(RecipeSerializer.class);
		}

		@SuppressWarnings("unchecked")
		private static <G> Class<G> castClass(Class<?> cls) {
			return (Class<G>) cls;
		}
	}
}
