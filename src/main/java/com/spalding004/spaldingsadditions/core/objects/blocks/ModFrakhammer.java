package com.spalding004.spaldingsadditions.core.objects.blocks;

import javax.annotation.Nullable;

import com.spalding004.spaldingsadditions.core.init.ModBlockEntities;
import com.spalding004.spaldingsadditions.core.objects.blocks.entity.FrakhammerBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ModFrakhammer extends BaseEntityBlock{

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	
	public ModFrakhammer() {
		super(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK));
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub
		return new FrakhammerBlockEntity(pos, state);
	}
	
	 @Nullable
	    @Override
	    public BlockState getStateForPlacement(BlockPlaceContext context) {
	        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	    }

	   

	    @SuppressWarnings("deprecation")
		@Override
	    public BlockState mirror(BlockState state, Mirror mirror) {
	        return state.rotate(mirror.getRotation(state.getValue(FACING)));
	    }
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
		builder.add(LIT);
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
	
	@Override
	public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		
		if(state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = worldIn.getBlockEntity(pos);
			if (blockEntity instanceof FrakhammerBlockEntity) {
				((FrakhammerBlockEntity) blockEntity).drops();
				worldIn.removeBlockEntity(pos);
			}
		}
		
	}
	
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!player.level.isClientSide()) {
			BlockEntity entity = worldIn.getBlockEntity(pos);
			if (entity instanceof FrakhammerBlockEntity) {
				NetworkHooks.openGui((ServerPlayer)player, (FrakhammerBlockEntity)entity, pos);
			} else {
				throw new IllegalStateException("No container provided!");
			}
		}
		
		return InteractionResult.sidedSuccess(worldIn.isClientSide);
		
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level worldIn, BlockState state, BlockEntityType<T> blockEntityType) {
		return createTickerHelper(blockEntityType, ModBlockEntities.FRAKHAMMER.get(),  FrakhammerBlockEntity::tick);
		
	}
	
	
	/*
	@Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.getValue(LIT) ? 3 : 0;
    }
	
	*/
}
