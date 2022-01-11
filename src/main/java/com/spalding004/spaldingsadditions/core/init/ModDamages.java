package com.spalding004.spaldingsadditions.core.init;

import net.minecraft.world.damagesource.DamageSource;

public class ModDamages {

	public static DamageSource VOID_WALKING;
	public static DamageSource NETHER_GAZE;
	
	public static void initDamages() {
		VOID_WALKING = (new DamageSource("void_walking").bypassArmor());
		NETHER_GAZE = (new DamageSource("nether_gaze").setScalesWithDifficulty().setExplosion());
		
	}
}