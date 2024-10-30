package net.lmor.botaniaextramachinery.blocks.blockMachines.mechanicalIndustrialAgglomerationFactory;

import de.melanx.botanicalmachinery.blocks.base.BotanicalBlock;
import net.lmor.botaniaextramachinery.ModBlocks;
import net.lmor.botaniaextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryUltimate;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory.ScreenIndustrialAgglomerationFactoryUltimate;
import net.lmor.botaniaextramachinery.blocks.tesr.mechanicalIndustrialAgglomerationFactory.RenderIndustrialAgglomerationFactoryUltimate;
import net.lmor.botaniaextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryUltimate;
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

public class BlockIndustrialAgglomerationFactoryUltimate extends BotanicalBlock<BlockEntityIndustrialAgglomerationFactoryUltimate, ContainerIndustrialAgglomerationFactoryUltimate> {
    public static final RotationShape SHAPE;

    public BlockIndustrialAgglomerationFactoryUltimate(ModX mod, Class<BlockEntityIndustrialAgglomerationFactoryUltimate> teClass, MenuType<ContainerIndustrialAgglomerationFactoryUltimate> menu) {
        super(mod, teClass, menu, false, true);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext ctx) {
        super.registerClient(ctx);
        MenuScreens.register(ModBlocks.ultimateIndustrialAgglomerationFactory.menu, ScreenIndustrialAgglomerationFactoryUltimate::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderIndustrialAgglomerationFactoryUltimate();
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
            if (blockEntity instanceof BlockEntityIndustrialAgglomerationFactoryUltimate){
                ((BlockEntityIndustrialAgglomerationFactoryUltimate) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    static {
        SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE, new VoxelShape[]{box(2.6, 0.0, 2.6, 13.4, 4.6, 13.4), box(6.2, 0.0, 6.2, 9.8, 5.3, 9.8)}));
    }
}
