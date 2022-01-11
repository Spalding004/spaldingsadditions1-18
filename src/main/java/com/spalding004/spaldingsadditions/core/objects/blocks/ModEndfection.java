package com.spalding004.spaldingsadditions.core.objects.blocks;

import java.util.Random;

import com.spalding004.spaldingsadditions.core.init.ModBlocks;
import com.spalding004.spaldingsadditions.core.init.ModDamages;
import com.spalding004.spaldingsadditions.utils.ModHelpers;
import com.spalding004.spaldingsadditions.utils.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModEndfection extends SpreadingSnowyDirtBlock {

	
	
	private String type;
	
	public ModEndfection(String type) {
	super(BlockBehaviour.Properties
			.of(Material.STONE)
			.strength(1F, 2F)
			.requiresCorrectToolForDrops()
			.randomTicks()
			.lightLevel((light) -> {
			      return 4;
			   })
			);
	
	this.type = type;
	
	
	
	

   }
	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		
		if (!worldIn.getBlockState(pos.above()).isAir() && type != "O") {
			
			worldIn.setBlockAndUpdate(pos, ModBlocks.ENDCROACHED_NETHERRACK.get().defaultBlockState());
			
		}
		
		switch (type) {
		case "B":
			//case B endfections do not spread.
			break;
		
		case "C":
			//case C endfections do not spread.
			break;
		default:
			spreadEnfection(state, worldIn, pos, rand, type);

			//spreadEnfection(state, worldIn, pos, rand, type);
		}
		
	}
	
	public void spreadEnfection(BlockState state, Level worldIn, BlockPos pos, Random rand, String type) {
		
	//	if (lives > 0) System.out.println("I have " + lives + " lives left!");
		int spread_check = rand.nextInt(10);
		
		BlockPos target_pos = ModHelpers.getRandomAdjacentFaceBlock(pos);
		//System.out.println("The block I checked was " + worldIn.getBlockState(pos).getBlock());
		if (spread_check < 5 ) {
		
			if (ModTags.Blocks.ENDFECTION_TARGETS.contains(worldIn.getBlockState(target_pos).getBlock())) {
				
				if (worldIn.getBlockState(target_pos).getBlock() == Blocks.OBSIDIAN) {
					worldIn.setBlockAndUpdate(target_pos, ModBlocks.ENDFECTED_OBSIDIAN.get().defaultBlockState());
				} else {
					worldIn.setBlockAndUpdate(target_pos, ModBlocks.ENDCROACHED_NETHERRACK.get().defaultBlockState());
					
				}
				
			}
		
			if (worldIn.getBlockState(target_pos).getBlock() == Blocks.NETHER_QUARTZ_ORE) {
			
				//worldIn.setBlockState(target_pos, ModBlocks.ENDFECTED_NETHER_QUARTZ_ORE.get().getDefaultState());
				
			}
			
		}
		
	}
	
	@OnlyIn(Dist.CLIENT)
	   public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
	      	int chance = rand.nextInt(25);
		
			if (chance == 1) {
	         spawnParticles(worldIn, pos);
	      }

	   }
	
	private void spawnParticles(Level worldIn, BlockPos pos) {
	      Random random = worldIn.random;
	      for(int i = 0; i < 6; ++i) {
	         double d1 = (double)((float)pos.getX() + random.nextFloat());
	         double d2 = (double)((float)pos.getY() + random.nextFloat());
	         double d3 = (double)((float)pos.getZ() + random.nextFloat());
	         if (i == 0 && !worldIn.getBlockState(pos.above()).isSolidRender(worldIn, pos)) {
	            d2 = (double)pos.getY() + 0.0625D + 1.0D;
	         }

	         if (i == 1 && !worldIn.getBlockState(pos.below()).isSolidRender(worldIn, pos)) {
	            d2 = (double)pos.getY() - 0.0625D;
	         }

	         if (i == 2 && !worldIn.getBlockState(pos.south()).isSolidRender(worldIn, pos)) {
	            d3 = (double)pos.getZ() + 0.0625D + 1.0D;
	         }

	         if (i == 3 && !worldIn.getBlockState(pos.north()).isSolidRender(worldIn, pos)) {
	            d3 = (double)pos.getZ() - 0.0625D;
	         }

	         if (i == 4 && !worldIn.getBlockState(pos.east()).isSolidRender(worldIn, pos)) {
	            d1 = (double)pos.getX() + 0.0625D + 1.0D;
	         }

	         if (i == 5 && !worldIn.getBlockState(pos.west()).isSolidRender(worldIn, pos)) {
	            d1 = (double)pos.getX() - 0.0625D;
	         }

	         if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1)) {
	            worldIn.addParticle(ParticleTypes.PORTAL, d1, d2, d3, 0.0D, -0.4D, 0.0D);
	         }
	      }
	}
	
	@Override
	public void stepOn(Level world, BlockPos pos,BlockState state, Entity entity) {
		entity.hurt(ModDamages.VOID_WALKING, 2.0F);
	}
	
}

