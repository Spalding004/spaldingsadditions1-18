package com.spalding004.spaldingsadditions.screen;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.screen.fabricator.FabricatorMenu;
import com.spalding004.spaldingsadditions.screen.frakhammer.FrakhammerMenu;
import com.spalding004.spaldingsadditions.screen.recombobulator.RecombobulatorMenu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, SpaldingsAdditions.MOD_ID);

    public static final RegistryObject<MenuType<FabricatorMenu>> FABRICATOR_MENU =
            registerMenuType(FabricatorMenu::new, "fabricator_menu");
   
    public static final RegistryObject<MenuType<FrakhammerMenu>> FRAKHAMMER_MENU =
            registerMenuType(FrakhammerMenu::new, "frakhammer_menu");
    
    public static final RegistryObject<MenuType<RecombobulatorMenu>> RECOMBOBULATOR_MENU =
            registerMenuType(RecombobulatorMenu::new, "recombobulator_menu");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
 