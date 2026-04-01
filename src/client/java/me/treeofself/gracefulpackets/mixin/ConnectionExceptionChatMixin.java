package me.treeofself.gracefulpackets.mixin;

import io.netty.channel.ChannelHandlerContext;
import me.treeofself.gracefulpackets.config.GracefulPacketsConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public abstract class ConnectionExceptionChatMixin {
	@Inject(method = "exceptionCaught", at = @At("HEAD"))
	private void gracefulPackets$chatOnException(ChannelHandlerContext context, Throwable ex, CallbackInfo ci) {
		if (!GracefulPacketsConfig.SUPPRESS_ERRORS || !GracefulPacketsConfig.LOG_TO_CHAT) {
			return;
		}
		Throwable cause = ex.getCause();
		String errorMsg = cause != null ? cause.getMessage() : ex.getMessage();
		String errorType = cause != null ? cause.getClass().getSimpleName() : ex.getClass().getSimpleName();
		Minecraft client = Minecraft.getInstance();
		if (client != null && client.player != null) {
			Component line = Component.literal("Network Error: " + errorType + " - " + errorMsg)
				.withStyle(ChatFormatting.RED);
			client.execute(() -> client.gui.getChat().addClientSystemMessage(line));
		}
	}
}
