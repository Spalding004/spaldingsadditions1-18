package com.spalding004.spaldingsadditions.core.objects.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ModStone extends Block {

	public ModStone() {
		super(BlockBehaviour.Properties
				.of(Material.STONE)
				.strength(1.5F, 6F)
				.requiresCorrectToolForDrops()
				);
		
	
		
		
		
	}

}