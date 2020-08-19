package com.dfsek.betterend.world.terrain.biomes;

import com.dfsek.betterend.util.ConfigUtil;
import com.dfsek.betterend.world.Biome;
import com.dfsek.betterend.world.terrain.BiomeGenerator;
import com.dfsek.betterend.world.terrain.ChunkSlice;
import com.dfsek.betterend.world.terrain.FeatureGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.List;

public class VoidGenerator extends BiomeGenerator {
    public VoidGenerator(World world) {
        super(world);
    }

    @Override
    public int getMaxHeight(int x, int z) {
        double iNoise = super.getNoiseGenerator().noise((double) x / ConfigUtil.outerEndNoise, (double) z / ConfigUtil.outerEndNoise, 0.1D,
                0.55D);
        return (int) (ConfigUtil.islandHeightMultiplierTop * (iNoise*(1D- Biome.getVoidLevel(x, z, super.getWorld().getSeed())) - ConfigUtil.landPercent) + 64);
    }

    @Override
    public int getMinHeight(int x, int z) {
        double iNoise = super.getNoiseGenerator().noise((double) x / ConfigUtil.outerEndNoise, (double) z / ConfigUtil.outerEndNoise, 0.1D,
                0.55D);
        return (int) ((-ConfigUtil.islandHeightMultiplierBottom * (iNoise*(1D- Biome.getVoidLevel(x, z, super.getWorld().getSeed())) - ConfigUtil.landPercent) + 64) + 1);
    }

    @Override
    public ChunkSlice generateSlice(byte x, byte z, int chunkX, int chunkZ) {
        ChunkSlice slice = new ChunkSlice(x, z);
        int min = getMinHeight((chunkX << 4) + x, (chunkZ << 4) + z);
        int max = getMaxHeight((chunkX << 4) + x, (chunkZ << 4) + z);
        if(Biome.isAetherVoid((chunkX << 4) + x, (chunkZ << 4) + z, super.getWorld().getSeed())) {
            for(int i = min; i < max; i++) {
                if(i == max-1) slice.setBlock(i, Material.GRASS_BLOCK);
                else if(i > max-3) slice.setBlock(i, Material.DIRT);
                else slice.setBlock(i, Material.STONE);
            }
        } else {
            for(int i = min; i < max; i++) {
                slice.setBlock(i, Material.END_STONE);
            }
        }
        return slice;
    }
    @Override
    public List<FeatureGenerator> getFeatures() {
        return null;
    }
}
