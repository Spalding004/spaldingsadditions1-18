package com.spalding004.spaldingsadditions.screen.thresher;

import java.awt.Rectangle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.client.gui.widget.FuelBar;
import com.spalding004.spaldingsadditions.utils.TextUtils;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ThresherScreen extends AbstractContainerScreen<ThresherMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(SpaldingsAdditions.MOD_ID, "textures/gui/thresher.png");
	private FuelBar fuelBar;

    
    
    public ThresherScreen(ThresherMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.fuelBar = new FuelBar(104, 29, 31, 8);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTicks, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        
        if (this.menu.hasFuel()) {
        	int k = menu.getScaledFuelProgress();
        	blit(pPoseStack, i + 104, j + 28, 0, 167, k, 8);
        }
        
        if(this.menu.isCrafting()) {
        	int l = menu.getScaledProgress();
        	blit(pPoseStack, 76  + i    , 41 + j,  0, 175,  23, l);
        	
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
    
    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
    	
    
        this.font.draw(poseStack, this.title, (float)(this.getXSize()/2 - this.font.width(this.title)/2), (float)this.titleLabelY, 4210752);
        this.font.draw(poseStack, this.playerInventoryTitle, (float)(this.getXSize()/2 - this.font.width(this.playerInventoryTitle)/2), (float)this.inventoryLabelY + 2, 4210752);
     
    }
    @Override
    protected void renderTooltip(PoseStack poseStack, int mouseX, int mouseY) {
    	Rectangle bar = this.fuelBar.rect;
        
        if (isHovering(bar.x, bar.y, bar.width, bar.height, mouseX, mouseY)) {
            this.renderTooltip(poseStack, TextUtils.fuelPercent(this.menu.getFuelPercent()), mouseX, mouseY);
         }
        
        super.renderTooltip(poseStack, mouseX, mouseY);
    }
}
