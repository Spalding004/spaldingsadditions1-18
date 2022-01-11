package com.spalding004.spaldingsadditions.core.objects.blocks;

import javax.annotation.Nullable;

import com.spalding004.spaldingsadditions.core.init.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

public class ModFlammablePillar extends RotatedPillarBlock {

	
	public ModFlammablePillar() {
		super(BlockBehaviour.Properties.copy(Blocks.OAK_LOG));

		
	}

	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return true;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}

	
	@Nullable
	@Override
	public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player, ItemStack stack, ToolAction toolActions) {
		
		if(stack.getItem() instanceof AxeItem) {
			
			if(state.is(ModBlocks.BEECH_LOG.get())) {
				
				return ModBlocks.BEECH_LOG_STRIPPED.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
				
			}
			
			if(state.is(ModBlocks.BEECH_WOOD.get())) {
				
				return ModBlocks.BEECH_WOOD_STRIPPED.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
				
			}
			
			
			
		}
		
		
		return super.getToolModifiedState(state, world, pos, player, stack, toolActions);
		
		
	}
	
}
