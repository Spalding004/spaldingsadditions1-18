package com.spalding004.spaldingsadditions.events.loot_modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;
import com.spalding004.spaldingsadditions.core.init.ModItems;
import com.spalding004.spaldingsadditions.core.objects.items.tools.ModToolFrakhammer;
import com.spalding004.spaldingsadditions.recipes.FrakhammerRecipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModDropsModifier extends LootModifier {


	public ModDropsModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		@SuppressWarnings("unused")
		Random random = new Random();
		String broken = generatedLoot.toString();
		ItemStack ctxTool = context.getParam(LootContextParams.TOOL);
		int enchant_level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, ctxTool);
		
		
		
		
		if (generatedLoot.size() == 0) {

			return generatedLoot;

		}
	
		ArrayList<ItemStack> ret = checkloots(generatedLoot, broken, enchant_level, ctxTool, context);

		if (ret.size() == 0 || generatedLoot.get(0).getItem() == ret.get(0).getItem()
				|| EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, ctxTool) != 0) {

			return generatedLoot;

		} else {
			
			return ret;
		}
	}

	/*
	 * 
	 * 
	 * This is NOT the correct way to use the global loot modifier system.
	 * 
	 * The forge team created a lovely tool here that I have completely bastardized
	 * into something else entirely.
	 * 
	 * Do as I say, not as I do.
	 * 
	 * This is a temporary measure until I convert all of the blockdrops to proper JSON
	 * 
	 * 
	 * 
	 * 
	 */

	private ArrayList<ItemStack> checkloots(List<ItemStack> generatedLoot, String broken, int enchant, ItemStack tool, LootContext context) {
		ArrayList<ItemStack> listReturn = new ArrayList<ItemStack>();
		Random random = new Random();
		String checkedOre = "nonya";
		                 
			if (broken.contains("lapis_lazuli")) {

				if (tool.getItem() == ModItems.TOOL_VENDAR_FRAKHAMMER.get()) {
    				listReturn.add(new ItemStack(ModItems.FRACTURED_LAPIS.get(), 2 + random.nextInt(3)));
    				return listReturn;
    			}
    			
    			if (tool.getItem() == ModItems.IRON_FRAKHAMMER.get()) {
    				listReturn.add(new ItemStack(ModItems.LAPIS_SHARD.get(), 8 + random.nextInt(6)));
    				return listReturn;
    			}
				
			
			return listReturn;

			}

		
			if (tool.getItem() instanceof ModToolFrakhammer) {
				ArrayList<ItemStack> fraktest = new ArrayList<ItemStack>();
				generatedLoot.forEach((stack) -> fraktest.add(frak(tool, stack, context)));
			//	System.out.println(fraktest);
				if (fraktest.get(0) != generatedLoot.get(0)) {
					return fraktest;
				}
				
			}
			
			

		checkedOre = "vendar";
		if (broken.contains(checkedOre + "_ore_dense")) {

			listReturn.add(new ItemStack(ModItems.VENDAR_CHUNK.get(), 3 + random.nextInt(1 + enchant)));

			return listReturn;
		}
		
		checkedOre = "vendar";
		if (broken.contains(checkedOre + "_ore")) {

			listReturn.add(new ItemStack(ModItems.VENDAR_CHUNK.get(), 1 + random.nextInt(1 + enchant)));

			return listReturn;
		}

		checkedOre = "indirium";
		if (broken.contains(checkedOre + "_ore")) {

			listReturn.add(new ItemStack(ModItems.INDIRIUM_CHUNK.get(), 1 + random.nextInt(1 + enchant)));

			return listReturn;
		}

		checkedOre = "vironium";
		if (broken.contains(checkedOre + "_ore")) {

			listReturn.add(new ItemStack(ModItems.VIRONIUM_CHUNK.get(), 1 + random.nextInt(1 + enchant)));

			return listReturn;
		}

		checkedOre = "geldar";
		if (broken.contains(checkedOre + "_ore")) {

			listReturn.add(new ItemStack(ModItems.GELDAR_CHUNK.get(), 1 + random.nextInt(1 + enchant)));

			return listReturn;
		}
		
		checkedOre = "prismarine";
		if (broken.contains(checkedOre + "_ore")) {
			int blend = 1 + random.nextInt(5);
			int blend2 = Math.abs(blend - 5);
			
			if (blend > 0) {
			listReturn.add(new ItemStack(Items.PRISMARINE_CRYSTALS, blend + blend * random.nextInt(1 + enchant)));
			}
			if (blend2 > 0 ) {
			listReturn.add(new ItemStack(Items.PRISMARINE_SHARD, blend2 + blend2 * random.nextInt(1 + enchant)));
			}
			return listReturn;
		}

		if (broken.contains("coal") && !broken.contains("coal_ore_")) {
			listReturn = (ArrayList<ItemStack>) generatedLoot;
			if (random.nextInt(200 + enchant * 2) >= 198)
				listReturn.add(new ItemStack(ModItems.CHARGED_CARBON.get()));

			return listReturn;
		}

		// riddled coal ores

		checkedOre = "diamond";
		if (broken.contains("coal_ore_" + checkedOre)) {

			listReturn.add(new ItemStack(Items.DIAMOND, 1 + random.nextInt(1 + enchant)));
			listReturn.add(new ItemStack(Items.COAL, 1 + random.nextInt(1 + enchant)));
			if (random.nextInt(20 + enchant * 2) >= 19)
				listReturn.add(new ItemStack(ModItems.CHARGED_CARBON.get()));
			return listReturn;
		}

		checkedOre = "emerald";
		if (broken.contains("coal_ore_" + checkedOre)) {

			listReturn.add(new ItemStack(Items.EMERALD, 1 + random.nextInt(1 + enchant)));
			listReturn.add(new ItemStack(Items.COAL, 1 + random.nextInt(1 + enchant)));
			if (random.nextInt(20 + enchant * 2) >= 19)
				listReturn.add(new ItemStack(ModItems.CHARGED_CARBON.get()));
			return listReturn;
		}
		
		checkedOre = "copper";
		if (broken.contains("coal_ore_" + checkedOre)) {

			listReturn.add(new ItemStack(Items.RAW_COPPER, 1 + random.nextInt(1 + enchant)));
			listReturn.add(new ItemStack(Items.COAL, 1 + random.nextInt(1 + enchant)));
			if (random.nextInt(20 + enchant * 2) >= 19)
				listReturn.add(new ItemStack(ModItems.CHARGED_CARBON.get()));
			return listReturn;
		}

		checkedOre = "lapis";
		if (broken.contains("coal_ore_" + checkedOre)) {

			listReturn.add(new ItemStack(Items.LAPIS_LAZULI, 3 + random.nextInt(3) + 3 * random.nextInt(1 + enchant)));
			listReturn.add(new ItemStack(Items.COAL, 1 + random.nextInt(1 + enchant)));
			if (random.nextInt(20 + enchant * 2) >= 19)
				listReturn.add(new ItemStack(ModItems.CHARGED_CARBON.get()));
			return listReturn;
		}

		checkedOre = "redstone";
		if (broken.contains("coal_ore_" + checkedOre)) {

			listReturn.add(new ItemStack(Items.REDSTONE, 4 + random.nextInt(2) + 4 * random.nextInt(1 + enchant)));
			listReturn.add(new ItemStack(Items.COAL, 1 + random.nextInt(1 + enchant)));
			if (random.nextInt(20 + enchant * 2) >= 19)
				listReturn.add(new ItemStack(ModItems.CHARGED_CARBON.get()));
			return listReturn;
		}

		checkedOre = "iron";
		if (broken.contains("coal_ore_" + checkedOre)) {

			if (ModList.get().isLoaded("fortunate")) {
				listReturn.add(
						new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("fortunate", "iron_chunk")),
								1 + random.nextInt(1 + enchant)));
			} else {
				listReturn.add(new ItemStack(Items.RAW_IRON, 1 + random.nextInt(1 + enchant)));
			}
			if (random.nextInt(20 + enchant * 2) >= 19)
				listReturn.add(new ItemStack(ModItems.CHARGED_CARBON.get()));
			listReturn.add(new ItemStack(Items.COAL, 1 + random.nextInt(1 + enchant)));
			return listReturn;
		}

		checkedOre = "gold";
		if (broken.contains("coal_ore_" + checkedOre)) {

			if (ModList.get().isLoaded("fortunate")) {
				listReturn.add(
						new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("fortunate", "gold_chunk")),
								1 + random.nextInt(1 + enchant)));
			} else {
				listReturn.add(new ItemStack(Items.RAW_GOLD, 1 + random.nextInt(1 + enchant)));
			}
			if (random.nextInt(20 + enchant * 2) >= 19)
				listReturn.add(new ItemStack(ModItems.CHARGED_CARBON.get()));
			listReturn.add(new ItemStack(Items.COAL, 1 + random.nextInt(1 + enchant)));
			return listReturn;
		}

		return (ArrayList<ItemStack>) generatedLoot;

	}

	private static ItemStack frak(ItemStack tool, ItemStack stack, LootContext context) {

		 return context.getLevel().getRecipeManager().getRecipeFor(FrakhammerRecipe.Type.INSTANCE, new SimpleContainer(ItemStack.EMPTY, tool, 
				 stack), context.getLevel())
				 .map(FrakhammerRecipe::getResultItem)
				 .filter(itemStack -> !itemStack.isEmpty())
				 .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
				 .orElse(stack);
                
		
	}
	
	public static class Serializer extends GlobalLootModifierSerializer<ModDropsModifier> {
		@Override
		public ModDropsModifier read(ResourceLocation name, JsonObject json, LootItemCondition[] conditionsIn) {
			return new ModDropsModifier(conditionsIn);
		}

		@Override
		public JsonObject write(ModDropsModifier instance) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
