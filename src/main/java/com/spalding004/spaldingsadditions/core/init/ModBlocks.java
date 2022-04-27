package com.spalding004.spaldingsadditions.core.init;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModCombinatrix;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModEndcroachment;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModEndfection;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModFabricBlock;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModFabricator;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModFenceBlock;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModFlammablePillar;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModFrakhammer;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModLeaves;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModLeavesGrowable;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModLeavesMealable;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModMachineCasing;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModOre;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModPlanks;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModRecombobulator;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModSapling;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModSlabWooden;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModStairsWooden;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModStone;
import com.spalding004.spaldingsadditions.core.objects.blocks.ModThinLog;
import com.spalding004.spaldingsadditions.world.features.tree.BeechTreeGrower;
import com.spalding004.spaldingsadditions.world.features.tree.CrapeTreeGrower;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpaldingsAdditions.MOD_ID);
	
	public static final RegistryObject<Block> MARCASITE = BLOCKS.register("marcasite", () -> new ModStone());
	public static final RegistryObject<Block> MARCASITE_SMOOTH = BLOCKS.register("marcasite_smooth", () -> new ModStone());
	
	public static final RegistryObject<Block> VOIDSTONE = BLOCKS.register("voidstone", () -> new ModStone());
	public static final RegistryObject<Block> VOIDSTONE_POLISHED = BLOCKS.register("voidstone_polished", () -> new ModStone());
	public static final RegistryObject<Block> VOIDSTONE_COBBLE = BLOCKS.register("voidstone_cobble", () -> new ModStone());
	
	
	public static final RegistryObject<Block> SHALE = BLOCKS.register("shale", () -> new ModStone());
	public static final RegistryObject<Block> SHALE_SMOOTH = BLOCKS.register("shale_smooth", () -> new ModStone());
	public static final RegistryObject<Block> PRISMARINE_ORE = BLOCKS.register("prismarine_ore", () -> new ModStone());
	
	public static final RegistryObject<Block> APATITE = BLOCKS.register("apatite", () -> new ModStone());
	public static final RegistryObject<Block> APATITE_SMOOTH = BLOCKS.register("apatite_smooth", () -> new ModStone());
	
	public static final RegistryObject<Block> MIXED_STONE = BLOCKS.register("mixed_stone", () -> new ModStone());
//	public static final RegistryObject<Block> MIXED_STONE_STAIRS = BLOCKS.register("mixed_stone_stairs", () -> new ModStairsBlockStone(BlockDec.MIXED_STONE.get()));
//	public static final RegistryObject<Block> MIXED_STONE_WALL = BLOCKS.register("mixed_stone_wall", () -> new ModWallBlock());

	
	public static final RegistryObject<Block> ALUNITE = BLOCKS.register("alunite", () -> new ModStone());
	public static final RegistryObject<Block> ALUNITE_SMOOTH = BLOCKS.register("alunite_smooth", () -> new ModStone());
	
	public static final RegistryObject<Block> UMBER = BLOCKS.register("umber", () -> new ModStone());
	public static final RegistryObject<Block> UMBER_SMOOTH = BLOCKS.register("umber_smooth", () -> new ModStone());
	
	public static final RegistryObject<Block> CORMALITE = BLOCKS.register("cormalite", () -> new ModStone());
	public static final RegistryObject<Block> CORMALITE_SMOOTH = BLOCKS.register("cormalite_smooth", () -> new ModStone());
	
	public static final RegistryObject<Block> PUMICE = BLOCKS.register("pumice", () -> new ModStone());
	
	public static final RegistryObject<Block> ENDCROACHED_NETHERRACK = BLOCKS.register("endcroached_netherrack", () -> new ModEndcroachment());
	public static final RegistryObject<Block> ENDFECTED_NETHERRACK_A = BLOCKS.register("endfected_netherrack_a", () -> new ModEndfection("A"));
	public static final RegistryObject<Block> ENDFECTED_NETHERRACK_B = BLOCKS.register("endfected_netherrack_b", () -> new ModEndfection("B"));
	public static final RegistryObject<Block> ENDFECTED_NETHERRACK_C = BLOCKS.register("endfected_netherrack_c", () -> new ModEndfection("C"));
	public static final RegistryObject<Block> ENDFECTED_OBSIDIAN = BLOCKS.register("endfected_obsidian", () -> new ModEndfection("O"));
	
	//	public static final RegistryObject<Block> ENDFECTED_NETHER_QUARTZ_ORE = BLOCKS.register("endfected_nether_quartz_ore", () -> new ModEndfectedOre());
	
	
	//ores
	public static final RegistryObject<Block> VENDAR_ORE = BLOCKS.register("vendar_ore", () -> new ModOre(3));
	public static final RegistryObject<Block> VENDAR_ORE_DENSE = BLOCKS.register("vendar_ore_dense", () -> new ModOre(3));
	public static final RegistryObject<Block> UMBER_GOLD_ORE = BLOCKS.register("umber_gold_ore", () -> new ModOre(2));
	
	public static final RegistryObject<Block> INDIRIUM_ORE = BLOCKS.register("indirium_ore", () -> new ModOre(2));
	public static final RegistryObject<Block> GELDAR_ORE = BLOCKS.register("geldar_ore", () -> new ModOre(2));
	public static final RegistryObject<Block> VIRONIUM_ORE = BLOCKS.register("vironium_ore", () -> new ModOre(2));
	
	
	
	public static final RegistryObject<Block> COAL_ORE_DIAMOND = BLOCKS.register("coal_ore_diamond", () -> new ModOre(0));
	public static final RegistryObject<Block> COAL_ORE_EMERALD = BLOCKS.register("coal_ore_emerald", () -> new ModOre(0));
	public static final RegistryObject<Block> COAL_ORE_GOLD = BLOCKS.register("coal_ore_gold", () -> new ModOre(0));
	public static final RegistryObject<Block> COAL_ORE_IRON = BLOCKS.register("coal_ore_iron", () -> new ModOre(0));
	public static final RegistryObject<Block> COAL_ORE_LAPIS = BLOCKS.register("coal_ore_lapis", () -> new ModOre(0));
	public static final RegistryObject<Block> COAL_ORE_REDSTONE = BLOCKS.register("coal_ore_redstone", () -> new ModOre(0));
	public static final RegistryObject<Block> COAL_ORE_COPPER = BLOCKS.register("coal_ore_copper", () -> new ModOre(0));
	
	//wood
	

	public static final RegistryObject<Block> BEECH_LOG = BLOCKS.register("beech_log", () -> new ModFlammablePillar());
	public static final RegistryObject<Block> BEECH_LOG_STRIPPED = BLOCKS.register("beech_log_stripped", () -> new ModFlammablePillar());
	
	public static final RegistryObject<Block> BEECH_WOOD = BLOCKS.register("beech_wood", () -> new ModFlammablePillar());
	public static final RegistryObject<Block> BEECH_WOOD_STRIPPED = BLOCKS.register("beech_wood_stripped", () -> new ModFlammablePillar());
	
	public static final RegistryObject<Block> BEECH_PLANKS = BLOCKS.register("beech_planks", () -> new ModPlanks());
	public static final RegistryObject<Block> BEECH_SAPLING = BLOCKS.register("beech_sapling", () -> new ModSapling(new BeechTreeGrower()));
	public static final RegistryObject<Block> CRAPE_MYRTLE_CUTTING = BLOCKS.register("crape_myrtle_cutting", () -> new ModSapling(new CrapeTreeGrower()));
	
	public static final RegistryObject<Block> BEECH_LEAVES = BLOCKS.register("beech_leaves", () -> new ModLeaves(Blocks.OAK_LEAVES));

	
	public static final RegistryObject<Block> CRAPE_MYRTLE_LEAVES_BLOSSOM = BLOCKS.register("crape_myrtle_leaves_blossom", () -> new ModLeaves(Blocks.OAK_LEAVES));
	public static final RegistryObject<Block> CRAPE_MYRTLE_PLANKS = BLOCKS.register("crape_myrtle_planks", () -> new ModPlanks());
	public static final RegistryObject<Block> CRAPE_MYRTLE_LEAVES = BLOCKS.register("crape_myrtle_leaves", () -> new ModLeavesMealable(Blocks.OAK_LEAVES, "crape"));
	
	public static final RegistryObject<Block> FABRICATOR = BLOCKS.register("fabricator", () -> new ModFabricator());
	public static final RegistryObject<Block> FRAKHAMMER = BLOCKS.register("frakhammer", () -> new ModFrakhammer());
	public static final RegistryObject<Block> RECOMBOBULATOR = BLOCKS.register("recombobulator", () -> new ModRecombobulator());	
	public static final RegistryObject<Block> COMBINATRIX = BLOCKS.register("combinatrix", () -> new ModCombinatrix());	
	
	public static final RegistryObject<Block> VENDAR_CASING = BLOCKS.register("vendar_casing", () -> new ModMachineCasing());
	
	public static final RegistryObject<Block> WOVEN_LEATHER = BLOCKS.register("woven_leather", () -> new ModFabricBlock(Blocks.BROWN_WOOL));
	
	public static final RegistryObject<Block> BEECH_STAIRS = BLOCKS.register("beech_stairs", () -> new ModStairsWooden(ModBlocks.BEECH_PLANKS.get()));
	public static final RegistryObject<Block> BEECH_FENCE = BLOCKS.register("beech_fence", () -> new ModFenceBlock());
	public static final RegistryObject<Block> BEECH_SLAB = BLOCKS.register("beech_slab", () -> new ModSlabWooden(ModBlocks.BEECH_PLANKS.get()));
	public static final RegistryObject<Block> CRAPE_MYRTLE_LOG = BLOCKS.register("crape_myrtle_log", () -> new ModThinLog());
	/*
	public static final RegistryObject<Block> PALM_LOG = BLOCKS.register("palm_log", () -> new ModLogs());
	public static final RegistryObject<Block> PALM_PLANKS = BLOCKS.register("palm_planks", () -> new ModPlanks());
	public static final RegistryObject<Block> PALM_STAIRS = BLOCKS.register("palm_stairs", () -> new ModStairsBlockWood(BlockDec.PALM_PLANKS.get()));
	public static final RegistryObject<Block> PALM_FENCE = BLOCKS.register("palm_fence", () -> new ModFenceBlock());
	public static final RegistryObject<Block> PALM_LEAVES = BLOCKS.register("palm_leaves", () -> new ModLeaves());
	
	public static final RegistryObject<Block> YEW_LOG = BLOCKS.register("yew_log", () -> new ModLogs());
	public static final RegistryObject<Block> YEW_PLANKS = BLOCKS.register("yew_planks", () -> new ModPlanks());
	public static final RegistryObject<Block> YEW_STAIRS = BLOCKS.register("yew_stairs", () -> new ModStairsBlockWood(BlockDec.YEW_PLANKS.get()));
	public static final RegistryObject<Block> YEW_FENCE = BLOCKS.register("yew_fence", () -> new ModFenceBlock());
	public static final RegistryObject<Block> YEW_LEAVES = BLOCKS.register("yew_leaves", () -> new ModLeaves());
	
	public static final RegistryObject<Block> PETRIFIED_PLANKS = BLOCKS.register("petrified_planks", () -> new ModStone());
	public static final RegistryObject<Block> PETRIFIED_STAIRS = BLOCKS.register("petrified_stairs", () -> new ModStairsBlockStone(BlockDec.PETRIFIED_PLANKS.get()));
	public static final RegistryObject<Block> PETRIFIED_WALL = BLOCKS.register("petrified_wall", () -> new ModWallBlock());
	
	public static final RegistryObject<Block> YEW_SAPLING = BLOCKS.register("yew_sapling", () -> new ModSaplingYew(() -> new YewTree()));
	public static final RegistryObject<Block> PALM_SAPLING = BLOCKS.register("palm_sapling", () -> new ModSaplingPalm(() -> new PalmTree()));
	

	
	//machines
	
	public static final RegistryObject<Block> AUTOFRAK = BLOCKS.register("autofrak", () -> new ModAutoFrak());
	*/
}
