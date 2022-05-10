package com.spalding004.spaldingsadditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.spalding004.spaldingsadditions.core.init.ModBlockEntities;
import com.spalding004.spaldingsadditions.core.init.ModBlocks;
import com.spalding004.spaldingsadditions.core.init.ModDamages;
import com.spalding004.spaldingsadditions.core.init.ModItems;
import com.spalding004.spaldingsadditions.core.init.ModRecipes;
import com.spalding004.spaldingsadditions.core.init.ModStructures;
import com.spalding004.spaldingsadditions.core.init.TransparentBlocks;
import com.spalding004.spaldingsadditions.events.loot_modifiers.ChevalMeatHorse;
import com.spalding004.spaldingsadditions.events.loot_modifiers.DungeonChestModifier;
import com.spalding004.spaldingsadditions.events.loot_modifiers.DungeonChestModifierCommon;
import com.spalding004.spaldingsadditions.events.loot_modifiers.ModDropsModifier;
import com.spalding004.spaldingsadditions.events.loot_modifiers.StrongholdLibraryModifier;
import com.spalding004.spaldingsadditions.events.loot_modifiers.WitheredCardBastionModifier;
import com.spalding004.spaldingsadditions.recipes.CombinatrixRecipe;
import com.spalding004.spaldingsadditions.recipes.DimensionalFabricatorRecipe;
import com.spalding004.spaldingsadditions.recipes.FrakhammerRecipe;
import com.spalding004.spaldingsadditions.screen.ModMenuTypes;
import com.spalding004.spaldingsadditions.screen.combinatrix.CombinatrixScreen;
import com.spalding004.spaldingsadditions.screen.fabricator.FabricatorScreen;
import com.spalding004.spaldingsadditions.screen.frakhammer.FrakhammerScreen;
import com.spalding004.spaldingsadditions.screen.recombobulator.RecombobulatorScreen;
import com.spalding004.spaldingsadditions.screen.thresher.ThresherScreen;
import com.spalding004.spaldingsadditions.utils.ModTags;
import com.spalding004.spaldingsadditions.world.features.underground.ModOreGeneration;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("spaldingsadditions")
@Mod.EventBusSubscriber(modid = SpaldingsAdditions.MOD_ID, bus = Bus.MOD)
public class SpaldingsAdditions {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "spaldingsadditions";
	public static SpaldingsAdditions instance;
	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	final IEventBus forgeBus = MinecraftForge.EVENT_BUS;
	public static List<Block> TARGET_BLOCKS = new ArrayList<Block>();

	public SpaldingsAdditions() {
		
		
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::clientSetup);
		modEventBus.addListener(this::enqueueIMC);
		modEventBus.addListener(this::processIMC);
	
		ModBlocks.BLOCKS.register(modEventBus);
		ModItems.ITEMS.register(modEventBus);
		ModStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
		ModRecipes.register(modEventBus);
		
		ModDamages.initDamages();
	
		ModTags.register();

		ModBlockEntities.register(modEventBus);
		ModMenuTypes.register(modEventBus);

		
		instance = this;
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {

		final IForgeRegistry<Item> registry = event.getRegistry();

		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().tab(ModItemGroup.instance);
			final Item blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());

			registry.register(blockItem);

		});

		// AutoFrakRecipes.instance().addRecipes();
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(ModOreGeneration::registerOres);

	}

	private void enqueueIMC(final InterModEnqueueEvent event) {

	}

	private void clientSetup(final FMLClientSetupEvent event) {
		TransparentBlocks.register();
		MenuScreens.register(ModMenuTypes.FABRICATOR_MENU.get(), FabricatorScreen::new);
		MenuScreens.register(ModMenuTypes.FRAKHAMMER_MENU.get(), FrakhammerScreen::new);
		MenuScreens.register(ModMenuTypes.RECOMBOBULATOR_MENU.get(), RecombobulatorScreen::new);
		MenuScreens.register(ModMenuTypes.COMBINATRIX_MENU.get(), CombinatrixScreen::new);
		MenuScreens.register(ModMenuTypes.THRESHER_MENU.get(), ThresherScreen::new);
	}

	private void processIMC(final InterModProcessEvent event) {
		LOGGER.info("Got IMC {}",
				event.getIMCStream().map(m -> m.messageSupplier().get()).collect(Collectors.toList()));
	}

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {

	}

	@SubscribeEvent
	public static void registerModifierSerializers(
			@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {

		event.getRegistry().register(new ModDropsModifier.Serializer()
				.setRegistryName(new ResourceLocation(SpaldingsAdditions.MOD_ID, "special_drops")));
		
		event.getRegistry().register(new WitheredCardBastionModifier.Serializer()
				.setRegistryName(new ResourceLocation(SpaldingsAdditions.MOD_ID, "withered_card_in_bastion")));
		
		event.getRegistry().register(new DungeonChestModifier.Serializer()
				.setRegistryName(new ResourceLocation(SpaldingsAdditions.MOD_ID, "energetic_crystal_dungeon_loot")));
		
		event.getRegistry().register(new DungeonChestModifier.Serializer()
				.setRegistryName(new ResourceLocation(SpaldingsAdditions.MOD_ID, "vendar_ingot_weaponsmith")));
		
		event.getRegistry().register(new StrongholdLibraryModifier.Serializer()
				.setRegistryName(new ResourceLocation(SpaldingsAdditions.MOD_ID, "saving_book_village")));
		
		event.getRegistry().register(new ChevalMeatHorse.Serializer()
				.setRegistryName(new ResourceLocation(SpaldingsAdditions.MOD_ID, "cheval_horse")));
		
		event.getRegistry().register(new DungeonChestModifierCommon.Serializer()
				.setRegistryName(new ResourceLocation(SpaldingsAdditions.MOD_ID, "woven_leather_village")));

	}
	
	@SubscribeEvent
	public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
		
		Registry.register(Registry.RECIPE_TYPE, FrakhammerRecipe.Type.ID, FrakhammerRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, DimensionalFabricatorRecipe.Type.ID, DimensionalFabricatorRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, CombinatrixRecipe.Type.ID, CombinatrixRecipe.Type.INSTANCE);
	}
	
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {

		}
	}

	public static class ModItemGroup extends CreativeModeTab {

		private ModItemGroup(int index, String label) {

			super(index, label);

		}

		@Override
		public ItemStack makeIcon() {

			return new ItemStack(ModItems.VENDAR_INGOT.get());

		}

		public static final ModItemGroup instance = new ModItemGroup(CreativeModeTab.TABS.length, "mod_creative_tab");

	}
}
