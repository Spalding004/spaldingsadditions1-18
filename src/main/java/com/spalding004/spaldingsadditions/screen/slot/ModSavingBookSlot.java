package com.spalding004.spaldingsadditions.screen.slot;

import com.spalding004.spaldingsadditions.core.init.ModItems;
import com.spalding004.spaldingsadditions.core.objects.items.ModDimensionalCard;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModSavingBookSlot extends SlotItemHandler{

	public ModSavingBookSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem() == ModItems.SAVING_BOOK.get();
		}

}
