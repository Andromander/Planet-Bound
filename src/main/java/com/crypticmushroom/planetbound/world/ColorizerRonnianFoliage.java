package com.crypticmushroom.planetbound.world;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorizerRonnianFoliage {
	/** Color buffer for foliage */
	private static int[] foliageBuffer = new int[65536];

	public static void setFoliageBiomeColorizer(int[] foliageBufferIn) {
		foliageBuffer = foliageBufferIn;
	}

	/**
	 * Gets the color modifier to use for foliage.
	 */
	public static int getFoliageColor(double temperature, double humidity) {
		humidity = humidity * temperature;
		int i = (int) ((1.0D - temperature) * 255.0D);
		int j = (int) ((1.0D - humidity) * 255.0D);
		return foliageBuffer[j << 8 | i];
	}
}
