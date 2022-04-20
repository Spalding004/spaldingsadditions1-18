package com.spalding004.spaldingsadditions.core.objects.blocks;

import java.util.Random;

import com.spalding004.spaldingsadditions.core.init.ModBlocks;
import com.spalding004.spaldingsadditions.utils.ModHelpers;
import com.spalding004.spaldingsadditions.utils.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class ModEndcroachment extends SpreadingSnowyDirtBlock {


	public ModEndcroachment() {
		super(BlockBehaviour.Properties.of(Material.STONE).strength(1F, 2F).requiresCorrectToolForDrops().randomTicks()
				.lightLevel((light) -> {
					return 4;
				}));
		
		

	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {

		boolean shouldSpread = true;
		
		
		if (worldIn.getBlockState(pos.above()).isAir()) {
			int type = rand.nextInt(100);

			

			if (type <= 60) {
				worldIn.setBlockAndUpdate(pos, ModBlocks.ENDFECTED_NETHERRACK_C.get().defaultBlockState());
			}

			if (type > 60 && type <= 75) {
				worldIn.setBlockAndUpdate(pos, ModBlocks.ENDFECTED_NETHERRACK_A.get().defaultBlockState());
			}

			if (type > 75) {
				worldIn.setBlockAndUpdate(pos, ModBlocks.ENDFECTED_NETHERRACK_B.get().defaultBlockState());
			}

		}

		if (shouldSpread) {

			
			BlockPos target_pos = ModHelpers.getRandomAdjacentFaceBlock(pos);

			// if (worldIn.getBlockState(pos).getBlock() == Blocks.NETHERRACK
			if (ModHelpers.checkBlockTagsAny(worldIn.getBlockState(target_pos).getBlock(), ModTags.Blocks.ENDFECTION_TARGETS)
					&& ModHelpers.isAirAdjacent(worldIn, target_pos)) {

				if (worldIn.getBlockState(target_pos).getBlock() == Blocks.OBSIDIAN) {
					worldIn.setBlockAndUpdate(target_pos, ModBlocks.ENDFECTED_OBSIDIAN.get().defaultBlockState());
				} else {
					worldIn.setBlockAndUpdate(target_pos, ModBlocks.ENDCROACHED_NETHERRACK.get().defaultBlockState());

				}

			}

		}
	}

}