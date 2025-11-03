package dev.mineland.smoothpickup.screens;

import dev.mineland.smoothpickup.SmoothPickup;
import dev.mineland.smoothpickup.SmoothPickupConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.util.HashMap;

public class SmoothPickupConfigScreen extends Screen {
    private Screen parent;

    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this, HeaderAndFooterLayout.DEFAULT_HEADER_AND_FOOTER_HEIGHT, HeaderAndFooterLayout.DEFAULT_HEADER_AND_FOOTER_HEIGHT);

    private final HashMap<AbstractWidget, settingsStuff> settingsWidgets = new HashMap<>();
    private final SmoothPickupConfig backupConfig;
    private final SmoothPickupConfig config = SmoothPickup.getActiveConfig();
    private final Button btnFreeGrid = Button.builder(Component.literal("Enable free grid"), button -> {
        settingsWidgets.get(button).onClick();
    }).tooltip(Tooltip.create(Component.translatable("smoothPickup.settings.isFreeGridEnabled.tooltip"))).build();







    public SmoothPickupConfigScreen(Screen parent) {
        super(Component.translatable("smoothPickup.settings.title"));
        this.parent = parent;
        this.font = Minecraft.getInstance().font;
        this.backupConfig = SmoothPickup.getActiveConfig().copy();
        this.config.loadSettings();
        createLayout();


        settingsWidgets.put(btnFreeGrid, new settingsStuff() {
            @Override
            public void onClick() {
                config.toggleFreeGridEnabled();
                updateText();
            }

            @Override
            public void updateText() {
                btnFreeGrid.setMessage(
                        getButtonText(
                                Component.translatable("smoothPickup.settings.isFreeGridEnabled"),
                                config.isFreeGridEnabled()
                        )
                );
            }
        });


    }


    void createLayout() {
        this.layout.addTitleHeader(Component.translatable("smoothPickup.settings.title"), getFont());

        LinearLayout footerLayout = LinearLayout.horizontal().spacing(Button.DEFAULT_SPACING);
        footerLayout.addChild(
                Button.builder(Component.translatable("gui.done"), button -> {
                    onClose();
                }).build()
        );

        footerLayout.addChild(
                Button.builder(Component.translatable("gui.cancel"), button -> {
                    onCancel();
                })
                        .build()
        );

        this.layout.addToFooter(footerLayout);

        LinearLayout mainBodyLayout = LinearLayout.vertical();
        mainBodyLayout.addChild(btnFreeGrid);

        this.layout.addToContents(mainBodyLayout);

    }

    @Override
    protected void init() {
        this.layout.visitWidgets(widget -> {
            AbstractWidget h = this.addRenderableWidget(widget);

            if (settingsWidgets.containsKey(h)) settingsWidgets.get(h).updateText();
        });
        this.layout.arrangeElements();

    }

    @Override
    public void onClose() {
        flushSettings();
        assert this.minecraft != null;
        this.minecraft.setScreen(parent);
    }

    public void onCancel() {
        assert this.minecraft != null;
        this.minecraft.setScreen(parent);
        SmoothPickup.activeConfig = backupConfig;
    }

    public void updateButtonText() {

    }

    public void flushSettings() {
        SmoothPickup.logger.debug("Flushing settings!");
        config.flushConfig();
    }



    private Component getButtonText(Component trans, Number value) {
        return getButtonTextInternal(trans, Component.literal(value.toString()));
    }

    private Component getButtonText(Component trans, String value) {
        return getButtonTextInternal(trans, Component.literal(value));
    }

    private Component getButtonText(Component trans, Boolean value) {
        var valueComponent = Component.literal(String.format("%b", value));

        valueComponent.withStyle(Style.EMPTY.withColor(
                value ? ChatFormatting.GREEN : ChatFormatting.RED)
            );

        return getButtonTextInternal(trans, valueComponent);
    }


    private Component getButtonTextInternal(Component trans, Component valueComponent) {
        var separatorComponent = Component.literal(": ");
        return Component.empty()
                .append(trans)
                .append(separatorComponent)
                .append(valueComponent);
    }




    private interface settingsStuff {
        void onClick();
        void updateText();
    }
}
