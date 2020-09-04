package com.dfsek.betterend.world.decor;

import com.dfsek.betterend.population.structures.EndStructure;
import org.bukkit.block.Biome;
import org.polydev.gaea.math.ProbabilityCollection;
import org.polydev.gaea.tree.Tree;
import org.polydev.gaea.biome.Decorator;
import org.polydev.gaea.world.Fauna;

public class AetherForestDecorator implements Decorator<EndStructure> {
    private final ProbabilityCollection<Fauna> fauna = new ProbabilityCollection<Fauna>().add(Fauna.GRASS, 75)
            .add(Fauna.TALL_GRASS, 10)
            .add(Fauna.FERN, 5)
            .add(Fauna.TALL_FERN, 5)
            .add(Fauna.POPPY, 5)
            .add(Fauna.BLUE_ORCHID, 5);

    private final ProbabilityCollection<EndStructure> structures = new ProbabilityCollection<EndStructure>()
            .add(EndStructure.AETHER_RUIN, 75)
            .add(EndStructure.WOOD_HOUSE, 12)
            .add(EndStructure.COBBLE_HOUSE, 12)
            .add(EndStructure.GOLD_DUNGEON, 1);
    private final ProbabilityCollection<Tree> trees = new ProbabilityCollection<Tree>()
            .add(Tree.GIANT_OAK, 1);

    @Override
    public ProbabilityCollection<EndStructure> getStructures() {
        return structures;
    }

    @Override
    public ProbabilityCollection<Tree> getTrees() {
        return trees;
    }

    @Override
    public int getTreeDensity() {
        return 1;
    }

    @Override
    public boolean overrideStructureChance() {
        return false;
    }
    
    @Override
    public boolean shouldGenerateSnow() {
        return false;
    }

    @Override
    public Biome getVanillaBiome() {
        return Biome.END_HIGHLANDS;
    }

    @Override
    public ProbabilityCollection<Fauna> getFauna() {
        return fauna;
    }

    @Override
    public int getFaunaChance() {
        return 60;
    }
}