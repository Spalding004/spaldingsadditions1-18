package com.spalding004.spaldingsadditions.core.objects.blocks.entity;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.spalding004.spaldingsadditions.core.init.ModBlockEntities;
import com.spalding004.spaldingsadditions.core.init.ModItems;
import com.spalding004.spaldingsadditions.recipes.CombinatrixRecipe;
import com.spalding004.spaldingsadditions.recipes.fuels.LapalFuel;
import com.spalding004.spaldingsadditions.screen.combinatrix.CombinatrixMenu;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CombinatrixBlockEntity extends BlockEntity implements MenuProvider {

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerID, Inventory inventory, Player player) {

		return new CombinatrixMenu(containerID, inventory, this, this.data);
	}

	private final ItemStackHandler itemHandler = new ItemStackHandler(6) {

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

	public CombinatrixBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
		super(ModBlockEntities.COMBINATRIX.get(), pWorldPosition, pBlockState);
		this.data = new ContainerData() {
			public int get(int index) {
				switch (index) {
				case 0:
					return CombinatrixBlockEntity.this.progress;
				case 1:
					return CombinatrixBlockEntity.this.maxProgress;
				case 2:
					return CombinatrixBlockEntity.this.fuelTime;
				case 3:
					return CombinatrixBlockEntity.this.maxFuelTime;
				case 4:
					return CombinatrixBlockEntity.this.slot1;
				case 5:
					return CombinatrixBlockEntity.this.slot2;
				case 6:
					return CombinatrixBlockEntity.this.slot3;
				case 7:
					return CombinatrixBlockEntity.this.slot4;
				default:
					return 0;
				}
			}

			public void set(int index, int value) {
				switch (index) {
				case 0:
					CombinatrixBlockEntity.this.progress = value;
					break;
				case 1:
					CombinatrixBlockEntity.this.maxProgress = value;
					break;
				case 2:
					CombinatrixBlockEntity.this.fuelTime = value;
					break;
				case 3:
					CombinatrixBlockEntity.this.maxFuelTime = value;
					break;
				case 4:
					CombinatrixBlockEntity.this.slot1 = value;
					break;
				case 5:
					CombinatrixBlockEntity.this.slot2 = value;
					break;
				case 6:
					CombinatrixBlockEntity.this.slot3 = value;
					break;
				case 7:
					CombinatrixBlockEntity.this.slot4 = value;
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

		return TextUtils.translate("container", "combinatrix", "");
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

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, CombinatrixBlockEntity pBlockEntity) {

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

	private static void checkSlots(ItemStackHandler itemHandler, CombinatrixBlockEntity pBlockEntity) {

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

	private static boolean hasFuelInFuelSlot(CombinatrixBlockEntity entity) {
		return !entity.itemHandler.getStackInSlot(0).isEmpty();
	}

	private static boolean isConsumingFuel(CombinatrixBlockEntity entity) {
		return entity.fuelTime > 0;
	}

	private static boolean hasRecipe(CombinatrixBlockEntity entity) {
		Level level = entity.level;
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}

		Optional<CombinatrixRecipe> match = level.getRecipeManager().getRecipeFor(CombinatrixRecipe.Type.INSTANCE,
				inventory, level);

		if (match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())) {
			entity.maxProgress = match.get().getTicks();
		}

		return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
	}

	private static void craftItem(CombinatrixBlockEntity entity) {
		Level level = entity.level;
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}

		Optional<CombinatrixRecipe> match = level.getRecipeManager().getRecipeFor(CombinatrixRecipe.Type.INSTANCE,
				inventory, level);

		if (match.isPresent()) {
			// entity.itemHandler.extractItem(1,1, false);
			entity.itemHandler.extractItem(1, 1, false);
			entity.itemHandler.extractItem(2, 1, false);
			entity.itemHandler.extractItem(3, 1, false);
			entity.itemHandler.extractItem(4, 1, false);
			entity.itemHandler.setStackInSlot(5, new ItemStack(match.get().getResultItem().getItem(),
					entity.itemHandler.getStackInSlot(5).getCount() + match.get().getResultItem().getCount()));

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
		return inventory.getItem(5).getItem() == output.getItem() || inventory.getItem(5).isEmpty();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
		return inventory.getItem(5).getMaxStackSize() > inventory.getItem(5).getCount();
	}
}