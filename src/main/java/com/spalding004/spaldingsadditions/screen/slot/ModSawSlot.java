package com.spalding004.spaldingsadditions.screen.slot;

import com.spalding004.spaldingsadditions.core.init.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModSawSlot extends SlotItemHandler{

	public ModSawSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem() == ModItems.SAWBLADE.get();
		}

}
