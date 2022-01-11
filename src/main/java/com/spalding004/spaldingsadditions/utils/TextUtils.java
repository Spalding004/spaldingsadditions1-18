package com.spalding004.spaldingsadditions.utils;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TextUtils {

    private static final String ENERGY_FORMAT = "%,d";

    public static TranslatableComponent translate(String prefix, String suffix, Object... params) {
        return new TranslatableComponent(prefix + "." + SpaldingsAdditions.MOD_ID + "." + suffix, params);
    }

    // Energy

    public static TranslatableComponent toolTipWithValue(int value, String type) {
		String s1 = String.format(ENERGY_FORMAT, value);
		
    	return translate("tooltip", type, s1);
    	
    }
    
    public static TranslatableComponent energy(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energy", s1);
    }
    
    public static TranslatableComponent fuelPercent(int amount) {
    	String s1 = String.format(ENERGY_FORMAT, amount);
    	String s2 = String.format("%s", "%");
    	return translate("misc", "fuelleft", s1, s2);
    }

    public static TranslatableComponent energyWithMax(int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", "energyWithMax", s1, s2);
    }

    // Fluids

    public static TranslatableComponent fluidWithMaxName(IFluidHandler tank) {
        FluidStack fluid = tank.getFluidInTank(0);
        Component fluidName = fluid.getDisplayName();
        String s1 = String.format(ENERGY_FORMAT, fluid.getAmount());
        String s2 = String.format(ENERGY_FORMAT, tank.getTankCapacity(0));
        return translate("misc", fluid.getAmount() > 0 ? "fluidWithMaxName" : "empty", fluidName, s1, s2);
    }

    public static TranslatableComponent fluidWithMaxName(FluidStack fluid, int max) {
    	Component fluidName = fluid.getDisplayName();
        String s1 = String.format(ENERGY_FORMAT, fluid.getAmount());
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", fluid.getAmount() > 0 ? "fluidWithMaxName" : "empty", fluidName, s1, s2);
    }

    public static TranslatableComponent fluidWithMaxName(String fluid, int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", amount > 0 ? "fluidWithMaxName" : "empty", fluid, s1, s2);
    }

    public static TranslatableComponent fluidWithMax(IFluidHandler tank) {
        FluidStack fluid = tank.getFluidInTank(0);
        String s1 = String.format(ENERGY_FORMAT, fluid.getAmount());
        String s2 = String.format(ENERGY_FORMAT, tank.getTankCapacity(0));
        return translate("misc", "fluidWithMax", s1, s2);
    }

    public static TranslatableComponent fluidWithMax(int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", "fluidWithMax", s1, s2);
    }

    public static Component fluidName(FluidStack stack) {
        if (stack.isEmpty()) return translate("misc", "empty");
        return stack.getDisplayName();
    }

    public static Component fluidName(Fluid fluid) {
        if (fluid == Fluids.EMPTY) return translate("misc", "empty");
        return fluid.getAttributes().getDisplayName(FluidStack.EMPTY);
    }

}