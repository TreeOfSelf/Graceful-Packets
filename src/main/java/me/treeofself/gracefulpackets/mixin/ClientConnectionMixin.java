package me.treeofself.gracefulpackets.mixin;

import io.netty.channel.ChannelHandlerContext;
import me.treeofself.gracefulpackets.config.GracefulPacketsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {
	private static final Logger LOGGER = LoggerFactory.getLogger("graceful-packets");

	@Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
	private void gracefulPackets$onExceptionCaught(ChannelHandlerContext context, Throwable ex, CallbackInfo ci) {
		if (!GracefulPacketsConfig.SUPPRESS_ERRORS) {
			return;
		}

		Throwable cause = ex.getCause();
		String errorMsg = cause != null ? cause.getMessage() : ex.getMessage();
		String errorType = cause != null ? cause.getClass().getSimpleName() : ex.getClass().getSimpleName();

		LOGGER.warn("[Graceful Packets] Suppressed network error: {} - {}", errorType, errorMsg);

		if (GracefulPacketsConfig.LOG_TO_CHAT) {
			MinecraftClient client = MinecraftClient.getInstance();
			if (client != null && client.player != null) {
				String message = "§cNetwork Error: " + errorType + " - " + errorMsg;
				client.execute(() -> client.player.sendMessage(
					net.minecraft.text.Text.literal(message),
					false
				));
			}
		}

		ci.cancel();
	}

}
