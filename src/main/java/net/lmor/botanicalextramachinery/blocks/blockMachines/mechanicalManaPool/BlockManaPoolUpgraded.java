package net.lmor.botanicalextramachinery.blocks.blockMachines.mechanicalManaPool;

import de.melanx.botanicalmachinery.blocks.base.BotanicalBlock;
import io.github.noeppi_noeppi.libx.block.RotationShape;
import io.github.noeppi_noeppi.libx.mod.ModX;
import net.lmor.botanicalextramachinery.ModBlocks;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalManaPool.ContainerManaPoolUpgraded;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolUpgraded;
import net.lmor.botanicalextramachinery.blocks.tesr.mechanicalManaPool.RenderManaPoolUltimate;
import net.lmor.botanicalextramachinery.blocks.tesr.mechanicalManaPool.RenderManaPoolUpgraded;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUpgraded;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
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

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class BlockManaPoolUpgraded extends BotanicalBlock<BlockEntityManaPoolUpgraded, ContainerManaPoolUpgraded> {
    public static final RotationShape SHAPE;

    public BlockManaPoolUpgraded(ModX mod, Class<BlockEntityManaPoolUpgraded> teClass, MenuType<ContainerManaPoolUpgraded> menu) {
        super(mod, teClass, menu, false, true);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(ResourceLocation id, Consumer<Runnable> defer) {
        super.registerClient(id, defer);
        MenuScreens.register(ModBlocks.upgradedManaPool.menu, ScreenManaPoolUpgraded::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderManaPoolUpgraded();
        });
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape(state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityManaPoolUpgraded){
                ((BlockEntityManaPoolUpgraded) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    static {
        SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE, box(2.0, 1.0, 2.0, 14.0, 1.1, 14.0), box(2.0, 1.0, 13.0, 14.0, 6.0, 14.0), box(2.0, 1.0, 2.0, 14.0, 6.0, 3.0), box(13.0, 1.0, 3.0, 14.0, 6.0, 13.0), box(2.0, 1.0, 3.0, 3.0, 6.0, 13.0)));
    }
}
