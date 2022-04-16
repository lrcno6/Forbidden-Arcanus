package com.stal111.forbidden_arcanus.common.block;

import com.stal111.forbidden_arcanus.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Cherry Flower Vines Block <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.block.CherryFlowerVinesBlock
 *
 * @author stal111
 * @version 1.18.2 - 2.1.0
 * @since 2022-04-15
 */
public class CherryFlowerVinesBlock extends GrowingPlantHeadBlock implements CherryFlowerVines {

    private static final double GROWTH_PROBABILITY = 0.1D;

    protected static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public CherryFlowerVinesBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, GROWTH_PROBABILITY);
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(@Nonnull Random random) {
        return random.nextInt(1) + 1;
    }

    @Override
    protected boolean canGrowInto(@Nonnull BlockState state) {
        return state.isAir();
    }

    @Nonnull
    @Override
    protected Block getBodyBlock() {
        return ModBlocks.CHERRY_FLOWER_VINES_PLANT.get();
    }

    @Override
    public boolean canSurvive(@Nonnull BlockState state, @Nonnull LevelReader level, @Nonnull BlockPos pos) {
        BlockState relativeState = level.getBlockState(pos.relative(this.growthDirection.getOpposite()));

        return relativeState.is(this.getHeadBlock()) || relativeState.is(this.getBodyBlock()) || relativeState.is(ModBlocks.CHERRYWOOD_LEAVES.get());
    }

    @Override
    public void entityInside(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull Entity entity) {
        CherryFlowerVines.entityInside(state, level, pos, entity);
    }
}
