package com.spalding004.spaldingsadditions.core.objects.blocks;

import java.util.Random;

import com.spalding004.spaldingsadditions.core.init.ModBlocks;
import com.spalding004.spaldingsadditions.utils.ModHelpers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ModLeavesGrowable extends LeavesBlock {

	private int canTransform = 0;

	public ModLeavesGrowable(Block toCopy) {
		super(BlockBehaviour.Properties.copy(toCopy));

	}

	@Override
	public boolean isRandomlyTicking(BlockState pState) {
		return !pState.getValue(PERSISTENT);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		int nearby = 0;
		if (!state.getValue(PERSISTENT) && state.getValue(DISTANCE) == 7) {
			dropResources(state, worldIn, pos);
			worldIn.removeBlock(pos, false);
		}

		if (ModHelpers.isAirAdjacent(worldIn, pos, 3)
				&& worldIn.getBlockState(pos.below()) == Blocks.AIR.defaultBlockState()
				&& worldIn.getBlockState(pos.below(2)) == Blocks.AIR.defaultBlockState()) {
			worldIn.setBlock(pos.below(), this.defaultBlockState(), 16);
		}

	}
	

	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return true;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 30;
	}

}
