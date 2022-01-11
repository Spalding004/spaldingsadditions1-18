package com.spalding004.spaldingsadditions.core.objects.items;

import java.util.List;

import javax.annotation.Nullable;

import com.spalding004.spaldingsadditions.SpaldingsAdditions.ModItemGroup;
import com.spalding004.spaldingsadditions.utils.TextUtils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModSavingBook extends Item {

	public ModSavingBook() {
		super(new Item.Properties().tab(ModItemGroup.instance)
				);
		
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	   public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> list, TooltipFlag flagIn) {
		list.add(TextUtils.translate("tooltip", "saving_book").withStyle(ChatFormatting.DARK_PURPLE));  
	   }
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isFoil(ItemStack stack) {
	      return true;
	   }
}
