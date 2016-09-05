package straywolfe.tfctweaker.handlers.network;

import com.bioxx.tfc.Handlers.Network.AbstractPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import straywolfe.tfctweaker.handlers.anvilHandlers.AnvilRecipeHandler;

public class InitClientWorldPacket extends AbstractPacket
{
	public InitClientWorldPacket()
	{
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
	}

	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		AnvilRecipeHandler.world = player.worldObj;
		AnvilRecipeHandler.getInstance().registerRecipes();
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
	}

}
