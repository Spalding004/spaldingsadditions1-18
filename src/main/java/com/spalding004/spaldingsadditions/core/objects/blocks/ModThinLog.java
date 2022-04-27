package com.spalding004.spaldingsadditions.core.objects.blocks;

import com.spalding004.spaldingsadditions.utils.ModTags;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.LeadItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModThinLog extends FenceBlock {

	  protected final VoxelShape[] collisionShapeByIndex;
	  protected final VoxelShape[] shapeByIndex;
	
	
	
	public ModThinLog() {
		super(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE));
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
		this.collisionShapeByIndex = this.makeShapes(4.0F, 4.0F, 16.0F, 0.0F, 16.0F);
		this.shapeByIndex = this.makeShapes(4.0F, 4.0F, 16.0F, 0.0F, 16.0F);
	
		
	}
		
	@Override
	 public boolean connectsTo(BlockState pState, boolean pIsSideSolid, Direction pDirection) {
	      Block block = pState.getBlock();
	      boolean flag = this.isSameFence(pState);
	      boolean flag1 = block instanceof LeavesBlock || block instanceof ModLeaves;
	      return !isExceptionForConnection(pState) && pIsSideSolid || flag || flag1;
	   }
	@Override
	 public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
	    return InteractionResult.PASS;
	   }
	
	private boolean isSameFence(BlockState pState) {
	      return pState.is(ModTags.Blocks.THIN_LOGS) == this.defaultBlockState().is(ModTags.Blocks.THIN_LOGS);
	   }
	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
	      return this.shapeByIndex[this.getAABBIndex(pState)];
	   }
	@Override
	   public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		   return this.collisionShapeByIndex[this.getAABBIndex(pState)];
	   }

	@Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }
	
	
}
