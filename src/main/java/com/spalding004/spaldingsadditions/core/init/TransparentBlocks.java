package com.spalding004.spaldingsadditions.core.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class TransparentBlocks {

	private static List<RegistryObject<Block>> CUTOUT = new ArrayList<>();
	private static List<RegistryObject<Block>> TRANSLUCENT = new ArrayList<>();
	
	public static void register() {
		
		CUTOUT.add(ModBlocks.BEECH_SAPLING);
		CUTOUT.add(ModBlocks.BEECH_LEAVES);
		CUTOUT.add(ModBlocks.VENDAR_CASING);
		CUTOUT.add(ModBlocks.INDIRIUM_CASING);
		CUTOUT.add(ModBlocks.CRAPE_MYRTLE_CUTTING);
		
		
		for (RegistryObject<Block> block : CUTOUT) {
			
			ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
			
		}
		
		
		for (RegistryObject<Block> block : TRANSLUCENT) {
			
			ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.translucent());
			
		}
		
		
	}
	
}
