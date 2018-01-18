package com.crypticmushroom.planetbound.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class GauntletCoreFrame extends Block
{
    public GauntletCoreFrame()
    {
        super(Material.ROCK, MapColor.GRAY);
        
        setSoundType(SoundType.STONE);
    }
}