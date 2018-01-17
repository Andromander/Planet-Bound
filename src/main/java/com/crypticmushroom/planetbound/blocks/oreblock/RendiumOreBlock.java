package com.crypticmushroom.planetbound.blocks.oreblock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class RendiumOreBlock extends Block
{
    public RendiumOreBlock()
    {
        super(Material.ROCK, MapColor.GRAY);
        
		setHarvestLevel("pickaxe", 3);
		setHardness(5);
		setResistance(10);
        setSoundType(SoundType.METAL);
    }
}
