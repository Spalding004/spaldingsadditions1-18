package com.spalding004.spaldingsadditions.utils;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {

	public static void register() {
		
		Blocks.registerBlockTags();
		Items.registerItemTags();
		
	}

	public static class Blocks {
		
		public static final TagKey<Block> BEECH_LOGS = tag("beech_logs");
		public static final TagKey<Block> ENDFECTED = tag("endfected");
		
		public static final TagKey<Block> ENDFECTION_TARGETS = tag("endfection_targets");
		
		
		@SuppressWarnings("unused")
		private static TagKey<Block> tag(String name) {

			return BlockTags.create(new ResourceLocation(SpaldingsAdditions.MOD_ID, name));

		}
		@SuppressWarnings("unused")
		private static TagKey<Block> forgeTag(String name) {

			return BlockTags.create(new ResourceLocation("forge", name));

		}
		
		public static void registerBlockTags() {
			
		}

	}

	public static class Items {

		public static final TagKey<Item> ITEM_FRAKHAMMER = tag("item_frakhammer");

		
		
		private static TagKey<Item> tag(String name) {

			return ItemTags.create(new ResourceLocation(SpaldingsAdditions.MOD_ID, name));

		}
		@SuppressWarnings("unused")
		private static TagKey<Item> forgeTag(String name) {

			return ItemTags.create(new ResourceLocation("forge", name));

		}
		
		public static void registerItemTags() {
			
		}
		
	}

}
