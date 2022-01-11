package com.spalding004.spaldingsadditions.screen.slot;

import com.spalding004.spaldingsadditions.recipes.fuels.LapalFuel;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModLapalFuelSlot extends SlotItemHandler{

	public ModLapalFuelSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return LapalFuel.getFuelStr(stack) > 0;
		}

}
