package net.lmor.botaniaextramachinery.blocks.blockMachines.mechanicalManaPool;

import de.melanx.botanicalmachinery.blocks.base.BotanicalBlock;
import net.lmor.botaniaextramachinery.ModBlocks;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalManaPool.ContainerManaPoolUltimate;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolUltimate;
import net.lmor.botaniaextramachinery.blocks.tesr.mechanicalManaPool.RenderManaPoolUltimate;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUltimate;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.block.RotationShape;
import org.moddingx.libx.mod.ModX;
import org.moddingx.libx.registration.SetupContext;

import javax.annotation.Nonnull;

public class BlockManaPoolUltimate extends BotanicalBlock<BlockEntityManaPoolUltimate, ContainerManaPoolUltimate> {
    public static final RotationShape SHAPE;

    public BlockManaPoolUltimate(ModX mod, Class<BlockEntityManaPoolUltimate> teClass, MenuType<ContainerManaPoolUltimate> menu) {
        super(mod, teClass, menu, false, true);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext ctx) {
        super.registerClient(ctx);
        MenuScreens.register(ModBlocks.ultimateManaPool.menu, ScreenManaPoolUltimate::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderManaPoolUltimate();
        });
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape((Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityManaPoolUltimate){
                ((BlockEntityManaPoolUltimate) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    static {
        SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE, new VoxelShape[]{box(2.0, 1.0, 2.0, 14.0, 1.1, 14.0), box(2.0, 1.0, 13.0, 14.0, 6.0, 14.0), box(2.0, 1.0, 2.0, 14.0, 6.0, 3.0), box(13.0, 1.0, 3.0, 14.0, 6.0, 13.0), box(2.0, 1.0, 3.0, 3.0, 6.0, 13.0)}));
    }
}
