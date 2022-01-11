package com.spalding004.spaldingsadditions.core.objects.blocks.entity;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.spalding004.spaldingsadditions.core.init.ModBlockEntities;
import com.spalding004.spaldingsadditions.core.init.ModItems;
import com.spalding004.spaldingsadditions.core.objects.items.ModSavingBook;
import com.spalding004.spaldingsadditions.recipes.fuels.LapalFuel;
import com.spalding004.spaldingsadditions.screen.recombobulator.RecombobulatorMenu;
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
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class RecombobulatorBlockEntity extends BlockEntity implements MenuProvider {

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerID, Inventory inventory, Player player) {

		return new RecombobulatorMenu(containerID, inventory, this, this.data);
	}

	private final ItemStackHandler itemHandler = new ItemStackHandler(4) {

		@Override
		protected void onContentsChanged(int slot) {

		}

	};

	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

	protected final ContainerData data;
	private int progress = 0;
	private int maxProgress = 120;
	private int fuelTime = 0;
	private int maxFuelTime = 0;
	private int hasBook = 0;

	public RecombobulatorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
		super(ModBlockEntities.RECOMBOBULATOR.get(), pWorldPosition, pBlockState);
		this.data = new ContainerData() {
			public int get(int index) {
				switch (index) {
				case 0:
					return RecombobulatorBlockEntity.this.progress;
				case 1:
					return RecombobulatorBlockEntity.this.maxProgress;
				case 2:
					return RecombobulatorBlockEntity.this.fuelTime;
				case 3:
					return RecombobulatorBlockEntity.this.maxFuelTime;
				case 4:
					return RecombobulatorBlockEntity.this.hasBook;
				default:
					return 0;
				}
			}

			public void set(int index, int value) {
				switch (index) {
				case 0:
					RecombobulatorBlockEntity.this.progress = value;
					break;
				case 1:
					RecombobulatorBlockEntity.this.maxProgress = value;
					break;
				case 2:
					RecombobulatorBlockEntity.this.fuelTime = value;
					break;
				case 3:
					RecombobulatorBlockEntity.this.maxFuelTime = value;
					break;
				case 4:
					RecombobulatorBlockEntity.this.hasBook = value;
					break;
				}
			}

			public int getCount() {
				return 5;
			}
		};
	}

	@Override
	public Component getDisplayName() {

		return TextUtils.translate("container", "recombobulator", "");
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
		tag.putInt("Recombobulator.progress", progress);
		tag.putInt("Recombobulator.fuelTime", fuelTime);
		tag.putInt("Recombobulator.maxFuelTime", maxFuelTime);
		tag.putInt("Recombobulator.hasbook", hasBook);
		super.saveAdditional(tag);

	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
		progress = nbt.getInt("Recombobulator.progress");
		fuelTime = nbt.getInt("Recombobulator.fuelTime");
		maxFuelTime = nbt.getInt("Recombobulator.maxFuelTime");
		hasBook = nbt.getInt("Recombobulator.hasbook");
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

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, RecombobulatorBlockEntity pBlockEntity) {

		if (pBlockEntity.itemHandler.getStackInSlot(2).getItem() == ModItems.SAVING_BOOK.get()) {

			pBlockEntity.setBook(1);
		} else {

			pBlockEntity.setBook(0);
		}

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

	private static boolean hasFuelInFuelSlot(RecombobulatorBlockEntity entity) {
		return !entity.itemHandler.getStackInSlot(0).isEmpty();
	}

	private static boolean isConsumingFuel(RecombobulatorBlockEntity entity) {
		return entity.fuelTime > 0;
	}

	private static boolean hasRecipe(RecombobulatorBlockEntity entity) {

		return entity.itemHandler.getStackInSlot(3).getDamageValue() > 0;
	}

	private static void craftItem(RecombobulatorBlockEntity entity) {
		// Level level = entity.level;
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}

		if (hasRecipe(entity)) {
			// entity.itemHandler.extractItem(1,1, false);
			ItemStack catalyst = entity.itemHandler.getStackInSlot(1);
			boolean saveEnchants = false;
			int currDam = entity.itemHandler.getStackInSlot(3).getDamageValue();
			int catDamage = catalyst.getDamageValue();
			int catToDamage = (entity.itemHandler.getStackInSlot(3).isEnchanted() ? 5 : 1);
			int toRepair = ((entity.itemHandler.getStackInSlot(3).isEnchanted() && entity.hasBook == 1) ? 7 : 10);
			if (catDamage < catalyst.getMaxDamage() - 1) {
				catalyst.setDamageValue(catDamage + catToDamage);
				entity.itemHandler.setStackInSlot(1, catalyst);
			} else {
				entity.itemHandler.setStackInSlot(1, ItemStack.EMPTY);
			}

			ItemStack toOutput = entity.itemHandler.getStackInSlot(3);
			// Map<Enchantment, Integer> savedEnchants =
			// EnchantmentHelper.getEnchantments(toOutput);
			// check if saving book is in
			if (entity.itemHandler.getStackInSlot(2).getItem() == ModItems.SAVING_BOOK.get()) {
				saveEnchants = true;

			} else {

				saveEnchants = false;

			}

			if (!saveEnchants) {
				toOutput = new ItemStack(toOutput.getItem());
			}

			toOutput.setDamageValue(currDam - toRepair);
			if (toOutput.getDamageValue() < 0) {
				toOutput.setDamageValue(0);
			}

			entity.itemHandler.setStackInSlot(3, toOutput);
		}
		entity.resetProgress();
	}

	private void setBook(int i) {
		this.hasBook = i;
	}

	private void resetProgress() {
		this.progress = 0;
	}

}