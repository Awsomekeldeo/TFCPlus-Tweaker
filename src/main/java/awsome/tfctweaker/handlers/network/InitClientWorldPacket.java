package awsome.tfctweaker.handlers.network;

import com.dunk.tfc.Handlers.Network.AbstractPacket;

import awsome.tfctweaker.handlers.anvilHandlers.AnvilRecipeHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

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
