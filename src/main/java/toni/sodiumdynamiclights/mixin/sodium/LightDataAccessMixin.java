/*
 * Copyright © 2023 LambdAurora <email@lambdaurora.dev>
 *
 * This file is part of SodiumDynamicLights.
 *
 * Licensed under the MIT License. For more information,
 * see the LICENSE file.
 */

package toni.sodiumdynamiclights.mixin.sodium;

import toni.sodiumdynamiclights.util.SodiumDynamicLightHandler;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = {
		"me.jellysquid.mods.sodium.client.model.light.data.LightDataAccess",
		"net.caffeinemc.mods.sodium.client.model.light.data.LightDataAccess"
}, remap = false)
public abstract class LightDataAccessMixin {
	@Dynamic
	@Inject(method = "getLightmap", at = @At("RETURN"), remap = false, require = 0, cancellable = true)
	private static void sodiumdynamiclights$getLightmap(int word, CallbackInfoReturnable<Integer> cir) {
		int lightmap = SodiumDynamicLightHandler.getLightmap(SodiumDynamicLightHandler.POS.get(), word, cir.getReturnValueI());
		cir.setReturnValue(lightmap);
	}
}
