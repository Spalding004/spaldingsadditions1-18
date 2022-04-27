package com.spalding004.spaldingsadditions.screen.recombobulator;

import java.awt.Rectangle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.spalding004.spaldingsadditions.SpaldingsAdditions;
import com.spalding004.spaldingsadditions.client.gui.widget.FuelBar;
import com.spalding004.spaldingsadditions.client.gui.widget.LockIcon;
import com.spalding004.spaldingsadditions.screen.frakhammer.FrakhammerMenu;
import com.spalding004.spaldingsadditions.utils.TextUtils;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RecombobulatorScreen extends AbstractContainerScreen<RecombobulatorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(SpaldingsAdditions.MOD_ID, "textures/gui/recombobulator_gui.png");
	private FuelBar fuelBar;
	private LockIcon lockIcon;

    
    
    public RecombobulatorScreen(RecombobulatorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.fuelBar = new FuelBar(40, 26, 96, 8);
        this.lockIcon = new LockIcon(82, 43, 12, 8);
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
        	blit(pPoseStack, i + 40, j + 26, 0, 168, k, 8);
        }
        
        if(this.menu.isCrafting()) {
        	int l = menu.getScaledProgress();
        	blit(pPoseStack, 64 + i, 57 + j, 0, 177, l, 16);
        }
        
        if(this.menu.hasBook()) {
        	blit(pPoseStack, 82 + i, 43 + j, 0, 196, 12, 8);
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
        Rectangle icon = this.lockIcon.rect;
        if (isHovering(bar.x, bar.y, bar.width, bar.height, mouseX, mouseY)) {
            this.renderTooltip(poseStack, TextUtils.fuelPercent(this.menu.getFuelPercent()), mouseX, mouseY);
         }
        
        if (isHovering(icon.x, icon.y, icon.width, icon.height, mouseX, mouseY)) {
           if (this.menu.hasBook()) {
        	this.renderTooltip(poseStack, TextUtils.translate("recomb", "has_book", ""), mouseX, mouseY);
           } else {
        	this.renderTooltip(poseStack, TextUtils.translate("recomb", "no_book", ""), mouseX, mouseY);
               
           }
         
        }
        
        super.renderTooltip(poseStack, mouseX, mouseY);
    }
}
