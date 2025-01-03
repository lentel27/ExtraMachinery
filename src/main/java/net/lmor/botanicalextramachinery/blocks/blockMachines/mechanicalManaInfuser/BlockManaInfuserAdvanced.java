package net.lmor.botanicalextramachinery.blocks.blockMachines.mechanicalManaInfuser;

import net.lmor.botanicalextramachinery.ModBlocks;
import net.lmor.botanicalextramachinery.blocks.base.ExtraBotanicalBlock;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalManaInfuser.ContainerManaInfuserAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalManaInfuser.ScreenManaInfuserAdvanced;
import net.lmor.botanicalextramachinery.blocks.tesr.mechanicalManaInfuser.RenderManaInfuserAdvanced;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaInfuser.BlockEntityManaInfuserAdvanced;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.moddingx.libx.block.RotationShape;
import org.moddingx.libx.mod.ModX;
import org.moddingx.libx.registration.SetupContext;

import javax.annotation.Nonnull;

public class BlockManaInfuserAdvanced extends ExtraBotanicalBlock<BlockEntityManaInfuserAdvanced, ContainerManaInfuserAdvanced> {
    public static final RotationShape SHAPE;

    public BlockManaInfuserAdvanced(ModX mod, Class<BlockEntityManaInfuserAdvanced> teClass, MenuType<ContainerManaInfuserAdvanced> menu) {
        super(mod, teClass, menu, false, true);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext ctx) {
        super.registerClient(ctx);
        MenuScreens.register(ModBlocks.advancedManaInfuser.menu, ScreenManaInfuserAdvanced::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderManaInfuserAdvanced();
        });
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape(state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    static {
        SHAPE = new RotationShape(Shapes.or(ExtraBotanicalBlock.FRAME_SHAPE, box(2.6, 0.0, 2.6, 13.4, 4.6, 13.4), box(6.2, 0.0, 6.2, 9.8, 5.3, 9.8)));
    }
}
