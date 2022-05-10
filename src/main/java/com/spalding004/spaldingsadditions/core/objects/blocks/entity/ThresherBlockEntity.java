package com.spalding004.spaldingsadditions.core.objects.blocks.entity;

import java.util.Optional;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.spalding004.spaldingsadditions.core.init.ModBlockEntities;
import com.spalding004.spaldingsadditions.core.init.ModBlocks;
import com.spalding004.spaldingsadditions.recipes.ThresherRecipe;
import com.spalding004.spaldingsadditions.recipes.fuels.LapalFuel;
import com.spalding004.spaldingsadditions.screen.thresher.ThresherMenu;
import com.spalding004.spaldingsadditions.utils.TextUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ThresherBlockEntity extends BlockEntity implements MenuProvider {

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerID, Inventory inventory, Player player) {

		return new ThresherMenu(containerID, inventory, this, this.data);
	}

	private final ItemStackHandler itemHandler = new ItemStackHandler(7) {

		@Override
		protected void onContentsChanged(int slot) {

		}

	};

	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

	protected final ContainerData data;
	private int progress = 0;
	private int maxProgress = 50;
	private int fuelTime = 0;
	private int maxFuelTime = 0;
	private int slot1;
	private int slot2;
	private int slot3;
	private int slot4;

	public ThresherBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
		super(ModBlockEntities.THRESHER.get(), pWorldPosition, pBlockState);
		this.data = new ContainerData() {
			public int get(int index) {
				switch (index) {
				case 0:
					return ThresherBlockEntity.this.progress;
				case 1:
					return ThresherBlockEntity.this.maxProgress;
				case 2:
					return ThresherBlockEntity.this.fuelTime;
				case 3:
					return ThresherBlockEntity.this.maxFuelTime;
				case 4:
					return ThresherBlockEntity.this.slot1;
				case 5:
					return ThresherBlockEntity.this.slot2;
				case 6:
					return ThresherBlockEntity.this.slot3;
				case 7:
					return ThresherBlockEntity.this.slot4;
				default:
					return 0;
				}
			}

			public void set(int index, int value) {
				switch (index) {
				case 0:
					ThresherBlockEntity.this.progress = value;
					break;
				case 1:
					ThresherBlockEntity.this.maxProgress = value;
					break;
				case 2:
					ThresherBlockEntity.this.fuelTime = value;
					break;
				case 3:
					ThresherBlockEntity.this.maxFuelTime = value;
					break;
				case 4:
					ThresherBlockEntity.this.slot1 = value;
					break;
				case 5:
					ThresherBlockEntity.this.slot2 = value;
					break;
				case 6:
					ThresherBlockEntity.this.slot3 = value;
					break;
				case 7:
					ThresherBlockEntity.this.slot4 = value;
					break;
				}
			}

			public int getCount() {
				return 8;
			}
		};
	}

	@Override
	public Component getDisplayName() {

		return TextUtils.translate("container", "thresher", "");
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return lazyItemHandler.cast();
		}

		return super.getCapability(cap, side);
	}

	@Override
	public void onLoad() {

		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);

	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
	}

	@Override
	protected void saveAdditional(@NotNull CompoundTag tag) {
		tag.put("inventory", itemHandler.serializeNBT());
		tag.putInt("combinatrix.progress", progress);
		tag.putInt("combinatrix.fuelTime", fuelTime);
		tag.putInt("combinatrix.maxFuelTime", maxFuelTime);
		tag.putInt("combinatrix.slot1", slot1);
		tag.putInt("combinatrix.slot2", slot2);
		tag.putInt("combinatrix.slot3", slot3);
		tag.putInt("combinatrix.slot4", slot4);
		super.saveAdditional(tag);

	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
		progress = nbt.getInt("combinatrix.progress");
		fuelTime = nbt.getInt("combinatrix.fuelTime");
		maxFuelTime = nbt.getInt("combinatrix.maxFuelTime");
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
		slot1 = nbt.getInt("combinatrix.slot1");
		slot2 = nbt.getInt("combinatrix.slot2");
		slot3 = nbt.getInt("combinatrix.slot3");
		slot4 = nbt.getInt("combinatrix.slot4");

	}

	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(i, itemHandler.getStackInSlot(i));
		}
		Containers.dropContents(this.level, this.worldPosition, inventory);
	}

	private void consumeFuel() {
		if (!itemHandler.getStackInSlot(0).isEmpty()) {
			this.fuelTime = 30 * (int) LapalFuel.getFuelStr(new ItemStack(itemHandler.getStackInSlot(0).getItem()));
			itemHandler.getStackInSlot(0).setCount(itemHandler.getStackInSlot(0).getCount() - 1);
			this.maxFuelTime = this.fuelTime;
		}
	}

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ThresherBlockEntity pBlockEntity) {
	
		
		
	

		checkSlots(pBlockEntity.itemHandler, pBlockEntity);

		if (hasRecipe(pBlockEntity))
			if (pBlockEntity.fuelTime > 0)
				pBlockEntity.fuelTime--;

		if (hasRecipe(pBlockEntity)) {
			if (hasFuelInFuelSlot(pBlockEntity) && !isConsumingFuel(pBlockEntity)) {
				pBlockEntity.consumeFuel();
				setChanged(pLevel, pPos, pState);
			}
			if (isConsumingFuel(pBlockEntity)) {
				pBlockEntity.progress++;
				pLevel.setBlockAndUpdate(pPos, pState.setValue(BlockStateProperties.LIT, true));
				setChanged(pLevel, pPos, pState);
				if (pBlockEntity.progress > pBlockEntity.maxProgress) {
					craftItem(pBlockEntity);
				}
			} else {
				pLevel.setBlockAndUpdate(pPos, pState.setValue(BlockStateProperties.LIT, false));
			}
		} else {
			pBlockEntity.resetProgress();
			pLevel.setBlockAndUpdate(pPos, pState.setValue(BlockStateProperties.LIT, false));
			setChanged(pLevel, pPos, pState);
		}
	}

	private static void checkSlots(ItemStackHandler itemHandler, ThresherBlockEntity pBlockEntity) {

		if (itemHandler.getStackInSlot(1) != ItemStack.EMPTY) {

			pBlockEntity.setSlot1(1);
		} else {

			pBlockEntity.setSlot1(0);
		}

		if (itemHandler.getStackInSlot(2) != ItemStack.EMPTY) {

			pBlockEntity.setSlot2(1);
		} else {

			pBlockEntity.setSlot2(0);
		}

		if (itemHandler.getStackInSlot(3) != ItemStack.EMPTY) {

			pBlockEntity.setSlot3(1);
		} else {

			pBlockEntity.setSlot3(0);
		}
		if (itemHandler.getStackInSlot(4) != ItemStack.EMPTY) {

			pBlockEntity.setSlot4(1);
		} else {

			pBlockEntity.setSlot4(0);
		}

	}

	private static boolean hasFuelInFuelSlot(ThresherBlockEntity entity) {
		return !entity.itemHandler.getStackInSlot(0).isEmpty();
	}

	private static boolean isConsumingFuel(ThresherBlockEntity entity) {
		return entity.fuelTime > 0;
	}

	private static boolean hasRecipe(ThresherBlockEntity entity) {
		Level level = entity.level;
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}

		Optional<ThresherRecipe> match = level.getRecipeManager().getRecipeFor(ThresherRecipe.Type.INSTANCE, inventory,
				level);

		if (match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())) {
			entity.maxProgress = match.get().getTicks();
		}

		return (match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getResultItem()) && (inventory.getItem(1).getCount() == 1) || 
				(inventory.getItem(2).getItem() == Items.NETHER_STAR && canInsertItemIntoSlot(inventory, new ItemStack(ModBlocks.ENERGETIC_ORE.get()), 3)));
	}

	private static void craftItem(ThresherBlockEntity entity) {
		Level level = entity.level;
		Random rand = new Random();
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}

		Optional<ThresherRecipe> match = level.getRecipeManager().getRecipeFor(ThresherRecipe.Type.INSTANCE, inventory,
				level);

		if (match.isPresent()) {
			// entity.itemHandler.extractItem(1,1, false);

			ItemStack catalyst = entity.itemHandler.getStackInSlot(1);
			int catDamage = catalyst.getDamageValue();
			if (catDamage < catalyst.getMaxDamage() - 1) {
				catalyst.setDamageValue(catDamage + 1);
				entity.itemHandler.setStackInSlot(1, catalyst);
			} else {
				entity.itemHandler.setStackInSlot(1, ItemStack.EMPTY);
			}

			entity.itemHandler.extractItem(2, 1, false);

			Item resultItem = match.get().getResultItem().getItem();
			
			if(inventory.getItem(2).getItem() == Items.NETHER_STAR) {
				entity.itemHandler.setStackInSlot(3, new ItemStack(ModBlocks.ENERGETIC_ORE.get(),
						entity.itemHandler.getStackInSlot(3).getCount() + 5));
			} else {
			entity.itemHandler.setStackInSlot(3, new ItemStack(resultItem,
					entity.itemHandler.getStackInSlot(3).getCount() + match.get().getResultItem().getCount()));
			}
			
			if (canInsertItemIntoSlot(inventory, match.get().getChances().get(0), 4)) {
				if (rand.nextInt(100) < 40)
					entity.itemHandler.setStackInSlot(4, new ItemStack(match.get().getChances().get(0).getItem(),
							entity.itemHandler.getStackInSlot(4).getCount() + 1));
			}
			if (canInsertItemIntoSlot(inventory, match.get().getChances().get(1), 5)) {
				if (rand.nextInt(100) < 20)
					entity.itemHandler.setStackInSlot(5, new ItemStack(match.get().getChances().get(1).getItem(),
							entity.itemHandler.getStackInSlot(5).getCount() + 1));
			}
			if (canInsertItemIntoSlot(inventory, match.get().getChances().get(2), 6)) {
				if (rand.nextInt(100) < 5)
					entity.itemHandler.setStackInSlot(6, new ItemStack(match.get().getChances().get(2).getItem(),
							entity.itemHandler.getStackInSlot(6).getCount() + 1));
			}

			entity.resetProgress();
		} else {
			entity.resetProgress();
		}
	}

	private void setSlot1(int i) {
		this.slot1 = i;
	}

	private void setSlot2(int i) {
		this.slot2 = i;
	}

	private void setSlot3(int i) {
		this.slot3 = i;
	}

	private void setSlot4(int i) {
		this.slot4 = i;
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
		return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
	}

	private static boolean canInsertItemIntoSlot(SimpleContainer inventory, ItemStack output, int slot) {
		return (inventory.getItem(slot).getItem() == output.getItem()
				&& inventory.getItem(slot).getMaxStackSize() > inventory.getItem(slot).getCount())
				|| inventory.getItem(slot).isEmpty();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
		return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
	}
}