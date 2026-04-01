package me.treeofself.gracefulpackets.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import me.treeofself.gracefulpackets.config.GracefulPacketsConfig;
import net.minecraft.network.PacketDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(PacketDecoder.class)
public abstract class DecoderHandlerMixin {
	private static final Logger LOGGER = LoggerFactory.getLogger("graceful-packets");

	@Inject(method = "decode", at = @At(value = "NEW", target = "java/io/IOException"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
	private void gracefulPackets$skipExtraBytes(ChannelHandlerContext context, ByteBuf buf, List<Object> objects, CallbackInfo ci) {
		if (!GracefulPacketsConfig.SUPPRESS_ERRORS) {
			return;
		}

		buf.skipBytes(buf.readableBytes());
		ci.cancel();
	}
}
