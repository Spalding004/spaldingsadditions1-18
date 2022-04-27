package com.spalding004.spaldingsadditions.network;

import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class PacketHandler {
	
	private  PacketHandler() {
	
	}
	
	private static final String PROTOCOL_VERSION = "1";
	
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(SpaldingsAdditions.MOD_ID, "main"), 
			() -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	
		@SuppressWarnings("unused")
		public static void init() {
			
			int index = 0;
			
		}
		
	

}
