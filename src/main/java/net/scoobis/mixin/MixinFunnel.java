package net.scoobis.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.simibubi.create.content.logistics.funnel.FunnelBlockEntity;

@Mixin(value = FunnelBlockEntity.class, remap = false)
public class MixinFunnel {
    @Unique
    private int tickCounter = 0;

    @Inject( method = "tick", at = @At(value = "HEAD"), cancellable = true )
    public void tick(CallbackInfo ci) {
        tickCounter++;
        int ticksPerTick = 5;
        if (tickCounter % ticksPerTick != 0) {
            ci.cancel();
        }
    }
}
