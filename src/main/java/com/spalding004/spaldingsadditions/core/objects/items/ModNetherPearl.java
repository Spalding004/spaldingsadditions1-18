package com.spalding004.spaldingsadditions.core.objects.items;

import java.util.List;

import javax.annotation.Nullable;

import com.spalding004.spaldingsadditions.SpaldingsAdditions.ModItemGroup;
import com.spalding004.spaldingsadditions.utils.TextUtils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModNetherPearl extends Item{

	public ModNetherPearl() {
		super(new Item.Properties().tab(ModItemGroup.instance));
		
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	   public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> list, TooltipFlag flagIn) {
		list.add(TextUtils.translate("tooltip", "nether_pearl").withStyle(ChatFormatting.YELLOW));  
	   }
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
	      ItemStack itemHeld = player.getItemInHand(hand);
	      byte diff;
	      switch(worldIn.getDifficulty()) {
	      case HARD:
	         diff = 3;
	         break;
	      case NORMAL:
	         diff = 2;
	         break;
	      case EASY:
	         diff = 1;
	         break;
	      case PEACEFUL:
	         diff = 0;
	         break;
	      default:
	         diff = 1;
	      }

	      player.setSecondsOnFire(1 + diff * 2);
	      itemHeld.shrink(1);
	      player.getCooldowns().addCooldown(this, 20);
	      return InteractionResultHolder.success(player.getItemInHand(hand));
	   }
	
}
