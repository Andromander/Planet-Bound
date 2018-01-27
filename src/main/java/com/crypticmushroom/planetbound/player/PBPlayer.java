package com.crypticmushroom.planetbound.player;

import com.crypticmushroom.planetbound.inventory.InventoryGauntlet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class PBPlayer
{
    @CapabilityInject(PBPlayer.class)
    public static Capability<PBPlayer> PLAYER_MANAGER;
    
    private EntityPlayer player;
    
    private InventoryGauntlet inventoryGauntlet;
    
    public PBPlayer(){}
    
    public PBPlayer(EntityPlayer player)
    {
        this.player = player;
        
        inventoryGauntlet = new InventoryGauntlet();
    }
    
    public static void init()
    {
        CapabilityManager.INSTANCE.register(PBPlayer.class, new Storage(), PBPlayer.class);
        
        MinecraftForge.EVENT_BUS.register(new PBPlayerEventHandler());
    }
    
    public static PBPlayer get(EntityPlayer player) 
    {
        return player.getCapability(PLAYER_MANAGER, null);
    }
    
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        inventoryGauntlet.writeToNBT(compound);
        
        return compound;
    }
    
    public void readFromNBT(NBTTagCompound compound)
    {
        inventoryGauntlet.readFromNBT(compound);
    }
    
    public EntityPlayer getPlayer()
    {
        return player;
    }
    
    public InventoryGauntlet getInventoryGauntlet()
    {
        return inventoryGauntlet;
    }
    
    public static class Storage implements IStorage<PBPlayer>
    {
        @Override
        public NBTBase writeNBT(Capability<PBPlayer> capability, PBPlayer instance, EnumFacing side)
        {
            return instance.writeToNBT(new NBTTagCompound());
        }

        @Override
        public void readNBT(Capability<PBPlayer> capability, PBPlayer instance, EnumFacing side, NBTBase nbt)
        {
            instance.readFromNBT((NBTTagCompound)nbt);
        }
    }
    
    public static class Factory implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
    {
        private final PBPlayer player;

        public Factory(PBPlayer player)
        {
            this.player = player;
        }
        
        @Override
        public NBTTagCompound serializeNBT()
        {
            return player.writeToNBT(new NBTTagCompound());
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound)
        {
            player.readFromNBT(compound);
        }
        
        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing)
        {
            return capability == PLAYER_MANAGER;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing)
        {
            return capability == PLAYER_MANAGER ? (T)player : null;
        }
    }
}