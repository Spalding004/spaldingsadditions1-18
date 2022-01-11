package com.spalding004.spaldingsadditions.screen.slot;

import com.spalding004.spaldingsadditions.core.objects.items.tools.ModToolFrakhammer;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModHammerSlot extends SlotItemHandler{

	public ModHammerSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem() instanceof ModToolFrakhammer;
		}

}
