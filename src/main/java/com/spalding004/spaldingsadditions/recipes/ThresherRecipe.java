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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ThresherRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack input;
    private final NonNullList<Ingredient> recipeItems;
    private final int ticks;
    private final NonNullList<ItemStack> results;
    private final NonNullList<ItemStack> allResults;

    public ThresherRecipe(ResourceLocation id, ItemStack output,
                               NonNullList<Ingredient> recipeItems, int ticks, NonNullList<ItemStack> results, NonNullList<ItemStack> allResults) {
        this.id = id;
        this.input = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.results = results;
        this.allResults = allResults;
        
    }

    @Override
    public boolean matches(SimpleContainer pContainer, net.minecraft.world.level.Level pLevel) {
        return recipeItems.get(0).test(pContainer.getItem(2));
        

      
    }

    @Override
    public boolean isSpecial() {
		return true;
    	
    }
    
    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return input;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
     }
    
    public NonNullList<ItemStack> getChances() {
        return this.results;
     }
    
    public NonNullList<ItemStack> getAllOutputs() {
    	return this.allResults;
    	
    	
    }
    
    
    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return input.copy();
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

    public static class Type implements RecipeType<ThresherRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "thresher";
    }

    public static class Serializer implements RecipeSerializer<ThresherRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(SpaldingsAdditions.MOD_ID,"thresher");

        @Override
        public ThresherRecipe fromJson(ResourceLocation id, JsonObject json) {
        	
        	ItemStack chance1 = ItemStack.EMPTY;
        	ItemStack chance2 = ItemStack.EMPTY;
        	ItemStack chance3 = ItemStack.EMPTY;
        	boolean isChance1, isChance2, isChance3 = false;
        	ItemStack output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "output"), false);
        	int tick = GsonHelper.getAsInt(json, "ticks", 80);
        	output.setCount(GsonHelper.getAsInt(json, "count", 1));
        	
        	isChance1 = GsonHelper.isObjectNode(json, "chance1");
        	isChance2 = GsonHelper.isObjectNode(json, "chance2");
        	isChance3 = GsonHelper.isObjectNode(json, "chance3");
        	
        	if (isChance1) chance1 = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "chance1"), false);
        	if (isChance2) chance2 = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "chance2"), false);
        	if (isChance3) chance3 = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "chance3"), false);
        	
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
           
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
            NonNullList<ItemStack> chances = NonNullList.withSize(3, ItemStack.EMPTY);
            NonNullList<ItemStack> allResults = NonNullList.withSize(4, ItemStack.EMPTY);
           
            inputs.set(0, Ingredient.fromJson(ingredients.get(0)));
            
            
            chances.set(0, chance1);
            chances.set(1, chance2);
            chances.set(2, chance3);
            
            allResults.set(0, output);
            allResults.set(1, chance1);
            allResults.set(2, chance2);
            allResults.set(3, chance3);
          

            return new ThresherRecipe(id, output, inputs, tick, chances, allResults);
        }

        @Override
        public ThresherRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            NonNullList<ItemStack> chances = NonNullList.withSize(buf.readInt(), ItemStack.EMPTY);
            NonNullList<ItemStack> allResults = NonNullList.withSize(4, ItemStack.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }
            
            ItemStack output = buf.readItem();
            allResults.set(0, output);
            for (int n = 0; n < chances.size(); n++) {
            	allResults.set(n+1, chances.get(n));
            }

           
            int tick = buf.readInt();
        //    output.setCount(buf.readInt());
           
            return new ThresherRecipe(id, output, inputs, tick, chances, allResults);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ThresherRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            buf.writeInt(recipe.getChances().size());
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
