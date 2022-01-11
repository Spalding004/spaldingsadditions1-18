package com.spalding004.spaldingsadditions.recipes;

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

public class FrakhammerRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int ticks;

    public FrakhammerRecipe(ResourceLocation id, ItemStack output,
                               NonNullList<Ingredient> recipeItems, int ticks) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, net.minecraft.world.level.Level pLevel) {
        if(recipeItems.get(0).test(pContainer.getItem(1))) {
            return recipeItems.get(1).test(pContainer.getItem(2));
        }

        return false;
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

    public static class Type implements RecipeType<FrakhammerRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "frakhammer";
    }

    public static class Serializer implements RecipeSerializer<FrakhammerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(SpaldingsAdditions.MOD_ID,"frakhammer");

        @Override
        public FrakhammerRecipe fromJson(ResourceLocation id, JsonObject json) {
        	
        
        	ItemStack output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "output"), false);
        	int tick = GsonHelper.getAsInt(json, "ticks", 29);
        	output.setCount(GsonHelper.getAsInt(json, "count", 1));
      //  	ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
        	

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
           
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new FrakhammerRecipe(id, output, inputs, tick);
        }

        @Override
        public FrakhammerRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            int tick = buf.readInt();
        //    output.setCount(buf.readInt());
           
            return new FrakhammerRecipe(id, output, inputs, tick);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FrakhammerRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
            buf.writeInt(recipe.ticks);
      //      buf.writeInt(recipe.getResultItem().getCount());
            
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
            return (Class<G>)cls;
        }
    }
}
