package com.crypticmushroom.planetbound.blocks;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.crypticmushroom.planetbound.init.PBItems;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RendiumOre extends PBOre
{
    public RendiumOre(Planet... planets)
    {
        super(planets);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune)
    {
        return PBItems.rendium_chunk;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return ThreadLocalRandom.current().nextInt(3, 5 + 1);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            float max = 5 + fortune * 1.5F;
            int drops = ThreadLocalRandom.current().nextInt(3, (int) (max + 1));

            return drops;
        } else
        {
            return this.quantityDropped(random);
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }
}