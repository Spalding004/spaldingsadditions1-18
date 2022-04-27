package com.spalding004.spaldingsadditions.core.objects.blocks;

import java.util.Random;

import com.spalding004.spaldingsadditions.core.init.ModBlocks;

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

public class ModLeavesMealable extends LeavesBlock implements BonemealableBlock {

	private Block targetBlock = Blocks.OAK_LEAVES;
	private int canTransform = 0;
	
	public ModLeavesMealable(Block toCopy, String type) {
		super(BlockBehaviour.Properties.copy(toCopy));
		Random rand = new Random();
		
	
		switch(type) {
		
		case "crape": this.targetBlock = ModBlocks.CRAPE_MYRTLE_LEAVES_BLOSSOM.get();
			break; 
		default: 
			break;
		
		}
		
		
		
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
		
		if (rand.nextInt(10) == 1) {
			for(int x = -2; x < 3; x++) {
				for (int y = -2; y < 3; y++) {
					for (int z = -2; z <3; z++) {
						if (worldIn.getBlockState(pos.east(x).west(y).above(z)) == this.targetBlock.defaultBlockState()) nearby++;
				
				}
					}
			}
			
			if (nearby < 5) worldIn.setBlock(pos, this.targetBlock.defaultBlockState(), 16);
		} else {
			
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

	@Override
	public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level pLevel, Random pRand, BlockPos pPos, BlockState pState) {
		return (double) pLevel.random.nextFloat() < 0.45D;
	}

	@Override
	public void performBonemeal(ServerLevel pLevel, Random pRand, BlockPos pos, BlockState pState) {
		pLevel.setBlock(pos, this.targetBlock.defaultBlockState(), 16);
	}

}
