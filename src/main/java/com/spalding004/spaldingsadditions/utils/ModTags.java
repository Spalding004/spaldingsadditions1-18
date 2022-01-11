package com.spalding004.spaldingsadditions.utils;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {

	public static void register() {
		
		Blocks.registerBlockTags();
		Items.registerItemTags();
		
	}

	public static class Blocks {
		
		public static final Tags.IOptionalNamedTag<Block> BEECH_LOGS = tag("beech_logs");
		public static final Tags.IOptionalNamedTag<Block> ENDFECTED = tag("endfected");
		
		public static final Tags.IOptionalNamedTag<Block> ENDFECTION_TARGETS = tag("endfection_targets");
		
		
		@SuppressWarnings("unused")
		private static Tags.IOptionalNamedTag<Block> tag(String name) {

			return BlockTags.createOptional(new ResourceLocation(SpaldingsAdditions.MOD_ID, name));

		}
		@SuppressWarnings("unused")
		private static Tags.IOptionalNamedTag<Block> forgeTag(String name) {

			return BlockTags.createOptional(new ResourceLocation("forge", name));

		}
		
		public static void registerBlockTags() {
			
		}

	}

	public static class Items {

		public static final Tags.IOptionalNamedTag<Item> ITEM_FRAKHAMMER = tag("item_frakhammer");

		
		
		private static Tags.IOptionalNamedTag<Item> tag(String name) {

			return ItemTags.createOptional(new ResourceLocation(SpaldingsAdditions.MOD_ID, name));

		}
		@SuppressWarnings("unused")
		private static Tags.IOptionalNamedTag<Item> forgeTag(String name) {

			return ItemTags.createOptional(new ResourceLocation("forge", name));

		}
		
		public static void registerItemTags() {
			
		}
		
	}

}
