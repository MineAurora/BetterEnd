package com.dfsek.betterend.world.generators.biomes;

import org.bukkit.Material;
import org.bukkit.World;
import org.polydev.gaea.biome.BiomeTerrain;
import org.polydev.gaea.math.FastNoise;
import org.polydev.gaea.world.palette.BlockPalette;
import org.polydev.gaea.world.palette.RandomPalette;

import java.util.Random;


public class ShatteredEndGenerator extends BiomeTerrain {
    private final BlockPalette palette;
    private final FastNoise shattered;

    public ShatteredEndGenerator() {
        super();
        shattered = new FastNoise();
        shattered.setNoiseType(FastNoise.NoiseType.SimplexFractal);
        shattered.setFrequency(0.1f);
        shattered.setFractalOctaves(3);
        this.palette = new RandomPalette(new Random(2403)).add(Material.END_STONE, 1);
    }

    @Override
    public double getNoise(FastNoise gen, World w, int x, int z) {
        if(shattered.getSeed() != gen.getSeed()) shattered.setSeed(gen.getSeed());
        return gen.getNoise(x, z) + 0.3 * shattered.getSimplexFractal(x, z);
    }

    @Override
    public double getNoise(FastNoise fastNoise, World w, int i, int i1, int i2) {
        return getNoise(fastNoise, w, i, i2);
    }

    @Override
    public BlockPalette getPalette(int y) {
        return this.palette;
    }
}
