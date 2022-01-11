package com.spalding004.spaldingsadditions.screen.slot;

import com.spalding004.spaldingsadditions.core.init.ModItems;
import com.spalding004.spaldingsadditions.core.objects.items.ModDimensionalCard;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModDurableItemSlot extends SlotItemHandler{

	public ModDurableItemSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.isDamageableItem() && !(stack.getItem() instanceof ModDimensionalCard) && stack.getItem() != ModItems.REPLICATING_CRYSTAL.get() ;
		}

}
