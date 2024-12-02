package net.lmor.botanicalextramachinery.blocks.screens.uitlScreen;

import net.lmor.botanicalextramachinery.config.LibXClientConfig;
import net.lmor.botanicalextramachinery.config.LibXServerConfig;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class ScreenDrawLabelText {

    public static void drawLabelText(GuiGraphics guiGraphics, Font font, String idTextTranslatable, int[] pos, int[] image, int offsetY) {
        if (!LibXServerConfig.nameMechanism || !LibXClientConfig.nameMechanism) return;

        Component titleText = Component.translatable(idTextTranslatable);
        float scale = calculateOptimalScale(font, titleText, image[0] - 20);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, scale);
        guiGraphics.drawString(
                font,
                titleText,
                (int) ((pos[0] + image[0] / 2 - font.width(titleText) * scale / 2) / scale),
                (int) ((pos[1] + offsetY) / scale),
                0x00, false
        );
        guiGraphics.pose().popPose();
    }

    private static float calculateOptimalScale(Font font, Component text, int maxWidth) {
        int textWidth = font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}
