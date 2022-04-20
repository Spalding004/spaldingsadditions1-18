package com.spalding004.spaldingsadditions.events.loot_modifiers;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class DungeonChestModifier extends LootModifier {
    private final Item addition;

    protected DungeonChestModifier(LootItemCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        Random rand = new Random();
        int testInt = rand.nextInt(4);
        
        if (testInt == 0) {
        	generatedLoot.add(new ItemStack(addition, 1));
        	if (rand.nextInt(4) == 0) {
            	generatedLoot.add(new ItemStack(addition, 1));
            	
            }
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<DungeonChestModifier> {

        @Override
        public DungeonChestModifier read(ResourceLocation name, JsonObject object,
                                                      LootItemCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "addition")));
            return new DungeonChestModifier(conditionsIn, addition);
        }

        @Override
        public JsonObject write(DungeonChestModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return json;
        }
    }
}