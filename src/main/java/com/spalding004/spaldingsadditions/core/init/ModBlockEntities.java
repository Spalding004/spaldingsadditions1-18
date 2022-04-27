package com.spalding004.spaldingsadditions.core.init;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.core.objects.blocks.entity.CombinatrixBlockEntity;
import com.spalding004.spaldingsadditions.core.objects.blocks.entity.FabricatorBlockEntity;
import com.spalding004.spaldingsadditions.core.objects.blocks.entity.FrakhammerBlockEntity;
import com.spalding004.spaldingsadditions.core.objects.blocks.entity.RecombobulatorBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
	
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
			
			DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SpaldingsAdditions.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<FabricatorBlockEntity>> FABRICATOR = BLOCK_ENTITIES.register("fabricator",
			() -> BlockEntityType.Builder.of(FabricatorBlockEntity::new, ModBlocks.FABRICATOR.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<FrakhammerBlockEntity>> FRAKHAMMER = BLOCK_ENTITIES.register("frakhammer",
			() -> BlockEntityType.Builder.of(FrakhammerBlockEntity::new, ModBlocks.FRAKHAMMER.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<RecombobulatorBlockEntity>> RECOMBOBULATOR = BLOCK_ENTITIES.register("recombobulator",
			() -> BlockEntityType.Builder.of(RecombobulatorBlockEntity::new, ModBlocks.RECOMBOBULATOR.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<CombinatrixBlockEntity>> COMBINATRIX = BLOCK_ENTITIES.register("combinatrix",
			() -> BlockEntityType.Builder.of(CombinatrixBlockEntity::new, ModBlocks.COMBINATRIX.get()).build(null));
	
	
	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}

}
