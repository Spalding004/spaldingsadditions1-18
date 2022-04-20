package com.spalding004.spaldingsadditions.core.init;

import java.awt.Color;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
	public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, SpaldingsAdditions.MOD_ID);


    public static final RegistryObject<FlowingFluid> LAPIS_ESSENCE_FLUID
            = FLUIDS.register("lapis_essence_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.LAPIS_ESSENCE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> LAPIS_ESSENCE_FLOWING
            = FLUIDS.register("lapis_essence_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.LAPIS_ESSENCE_PROPERTIES));


    public static final ForgeFlowingFluid.Properties LAPIS_ESSENCE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> LAPIS_ESSENCE_FLUID.get(), () -> LAPIS_ESSENCE_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.LAVA_AMBIENT).overlay(WATER_OVERLAY_RL)
            .color(255)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> ModFluids.LAPIS_ESSENCE_BLOCK.get()).bucket(() -> ModItems.LAPIS_ESSENCE_BUCKET.get());

    public static final RegistryObject<LiquidBlock> LAPIS_ESSENCE_BLOCK = ModBlocks.BLOCKS.register("lapis_essence",
            () -> new LiquidBlock(() -> ModFluids.LAPIS_ESSENCE_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));
    //FlowingFluidBlock
    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}