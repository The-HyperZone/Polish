package io.github.darkkronicle.polish.gui.screens;

import io.github.darkkronicle.polish.api.AbstractPEntry;
import io.github.darkkronicle.polish.gui.complexwidgets.AbstractPWidgetList;
import io.github.darkkronicle.polish.gui.complexwidgets.EntryButtonList;
import io.github.darkkronicle.polish.gui.widgets.CleanButton;
import io.github.darkkronicle.polish.util.Colors;
import io.github.darkkronicle.polish.util.DrawUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class BasicConfigScreen extends AbstractConfigScreen {

    public BasicConfigScreen(Text title, EntryButtonList widget) {
        super(title, (MinecraftClient.getInstance().getWindow().getScaledWidth() / 2) - 300, (MinecraftClient.getInstance().getWindow().getScaledHeight() / 2) - 100, 600, 200);
        widget.setOffsetPos(x + 10, y + 30);
        widget.setDimensions(580, 150);
        add(widget);
        CleanButton reset = new CleanButton(x, y, 40, 15, Colors.BLACK.color().withAlpha(180), new LiteralText("Reset All"), button -> reset());
        reset.setRelativePos(width - 45, 0);
        add(reset);
        CleanButton save = new CleanButton(x, y, 40, 15, Colors.BLACK.color().withAlpha(180), new LiteralText("Save"), button -> reset());
        save.setRelativePos((width / 2) - 20, height - 20);
        add(save);
    }

    @Override
    public void save() {
        for (Drawable widget : widgetManager.getWidgets()) {
            if (widget instanceof EntryButtonList) {
                for (AbstractPWidgetList.Entry<?> entry : ((EntryButtonList) widget).getEntries()) {
                    if (entry instanceof AbstractPEntry) {
                        ((AbstractPEntry<?, ?>) entry).save();
                    }
                }
            }
        }
    }

    @Override
    public void reset() {
        for (Drawable widget : widgetManager.getWidgets()) {
            if (widget instanceof EntryButtonList) {
                for (AbstractPWidgetList.Entry<?> entry : ((EntryButtonList) widget).getEntries()) {
                    if (entry instanceof AbstractPEntry) {
                        ((AbstractPEntry<?, ?>) entry).reset();
                    }
                }
            }
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        matrices.push();
        float scale = 0.5F;
        matrices.scale(0.5F, 0.5F, 1);
        int scaledX = Math.round(x / scale);
        int scaledY = Math.round(y / scale);
        int scaledWidth = Math.round(width / scale);
        int scaledHeight = Math.round(height / scale);
        DrawUtil.fillRoundedRect(matrices, scaledX, scaledY, scaledWidth, scaledHeight, 15, Colors.DARKGRAY.color().withAlpha(100).color());
        matrices.scale(2, 2, 1);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
