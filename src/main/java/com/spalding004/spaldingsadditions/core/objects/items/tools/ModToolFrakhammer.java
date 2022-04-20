package com.spalding004.spaldingsadditions.core.objects.items.tools;

import com.spalding004.spaldingsadditions.SpaldingsAdditions.ModItemGroup;
import com.spalding004.spaldingsadditions.utils.ModHelpers;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;


public class ModToolFrakhammer extends PickaxeItem {

	
	  protected final float efficiency;

	 public ModToolFrakhammer(int attack, float speed, Tier tier) {
		super(tier, attack, speed, new Item.Properties()
				
				.tab(ModItemGroup.instance)
				
			);
		this.efficiency = tier.getSpeed();
		 
		
	}
	
	 @Override
	 public float getDestroySpeed(ItemStack stack, BlockState state) {
	      if (canHarvestBlock(state)) return efficiency;
	      return 1.0F;
	   }
	 
	 public boolean canHarvestBlock(BlockState blockIn) {
	    
		 TagKey<Block> tag1 = BlockTags.MINEABLE_WITH_PICKAXE;
		 TagKey<Block> tag2 = BlockTags.MINEABLE_WITH_AXE;
		
		 
		 return ModHelpers.checkBlockTagsAny(blockIn.getBlock(), tag1, tag2);
		 
	      
	 }
	 
	 @Override
	   public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
	      return ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction) || ToolActions.AXE_DIG.equals(toolAction);
	   }
	 
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
	
	
	
	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		ItemStack toReturn = stack.copy();
		toReturn.setDamageValue(stack.getDamageValue() + 1);
		
		if (toReturn.getDamageValue() >= getMaxDamage(stack)) {
			return ItemStack.EMPTY;
		}
		
		return toReturn;
	}

	
}