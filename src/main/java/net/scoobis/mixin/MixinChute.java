package net.scoobis.mixin;

import com.simibubi.create.content.logistics.chute.ChuteBlockEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ChuteBlockEntity.class, remap = false)
public class MixinChute {

	@Unique
    private int ticksPerTick = 5;
    
    @Unique
    private int tickCounter = 0;

    @Inject( method = "tick", at = @At(value = "HEAD"), cancellable = true )
    public void tick(CallbackInfo ci) {
        tickCounter++;
        if (tickCounter % ticksPerTick != 0) {
            ci.cancel();
        }
    }
}