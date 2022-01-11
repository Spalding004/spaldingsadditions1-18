package com.spalding004.spaldingsadditions.core.objects.blocks.entity;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.spalding004.spaldingsadditions.core.init.ModBlockEntities;
import com.spalding004.spaldingsadditions.recipes.DimensionalFabricatorRecipe;
import com.spalding004.spaldingsadditions.recipes.fuels.LapalFuel;
import com.spalding004.spaldingsadditions.screen.fabricator.FabricatorMenu;
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

public class FabricatorBlockEntity extends BlockEntity implements MenuProvider {

	
	
	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerID, Inventory inventory, Player player) {

		return new FabricatorMenu(containerID, inventory, this, this.data);
	}

	private final ItemStackHandler itemHandler = new ItemStackHandler(4) {

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

	public FabricatorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
		super(ModBlockEntities.FABRICATOR.get(), pWorldPosition, pBlockState);
		this.data = new ContainerData() {
			public int get(int index) {
				switch (index) {
				case 0:
					return FabricatorBlockEntity.this.progress;
				case 1:
					return FabricatorBlockEntity.this.maxProgress;
				case 2:
					return FabricatorBlockEntity.this.fuelTime;
				case 3:
					return FabricatorBlockEntity.this.maxFuelTime;
				default:
					return 0;
				}
			}

			public void set(int index, int value) {
				switch (index) {
				case 0:
					FabricatorBlockEntity.this.progress = value;
					break;
				case 1:
					FabricatorBlockEntity.this.maxProgress = value;
					break;
				case 2:
					FabricatorBlockEntity.this.fuelTime = value;
					break;
				case 3:
					FabricatorBlockEntity.this.maxFuelTime = value;
					break;
				}
			}

			public int getCount() {
				return 4;
			}
		};
	}

	@Override
	public Component getDisplayName() {

		return TextUtils.translate("container", "fabricator", "");
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
		tag.putInt("fabricator.progress", progress);
		tag.putInt("fabricator.fuelTime", fuelTime);
		tag.putInt("fabricator.maxFuelTime", maxFuelTime);
		super.saveAdditional(tag);

	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
		progress = nbt.getInt("fabricator.progress");
		fuelTime = nbt.getInt("fabricator.fuelTime");
		maxFuelTime = nbt.getInt("fabricator.maxFuelTime");
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));

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

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, FabricatorBlockEntity pBlockEntity) {
		
		if (hasRecipe(pBlockEntity))
			if (pBlockEntity.fuelTime > 0) pBlockEntity.fuelTime--;
			

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

	private static boolean hasFuelInFuelSlot(FabricatorBlockEntity entity) {
		return !entity.itemHandler.getStackInSlot(0).isEmpty();
	}

	private static boolean isConsumingFuel(FabricatorBlockEntity entity) {
		return entity.fuelTime > 0;
	}

	private static boolean hasRecipe(FabricatorBlockEntity entity) {
		Level level = entity.level;
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}

		Optional<DimensionalFabricatorRecipe> match = level.getRecipeManager()
				.getRecipeFor(DimensionalFabricatorRecipe.Type.INSTANCE, inventory, level);
		
		if (match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())) {
		entity.maxProgress = match.get().getTicks();
		}
		
		return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
	}

	private static void craftItem(FabricatorBlockEntity entity) {
		Level level = entity.level;
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}

		Optional<DimensionalFabricatorRecipe> match = level.getRecipeManager()
				.getRecipeFor(DimensionalFabricatorRecipe.Type.INSTANCE, inventory, level);

		if (match.isPresent()) {
			// entity.itemHandler.extractItem(1,1, false);
			ItemStack catalyst = entity.itemHandler.getStackInSlot(1);
			int catDamage = catalyst.getDamageValue();
			if (catDamage < catalyst.getMaxDamage()-1) {
				catalyst.setDamageValue(catDamage+1);
				entity.itemHandler.setStackInSlot(1, catalyst);
			} else {
				entity.itemHandler.setStackInSlot(1, ItemStack.EMPTY);
			}
			entity.itemHandler.extractItem(2, 1, false);

			entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
					entity.itemHandler.getStackInSlot(3).getCount() + match.get().getResultItem().getCount()));

			entity.resetProgress();
		}
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
		return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
		return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
	}
}