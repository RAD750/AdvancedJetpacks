package advjetpacks.items;

import advjetpacks.CreativeTab;
import ic2.api.IElectricItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;

public class ItemGyroJetpack extends Item {

	boolean uomoAvvisato = false;
	final static int maxDamage = 125000;
	final static int fuelLowWarningLevel = maxDamage - (maxDamage / 100 * 15);


	public ItemGyroJetpack(int id) {
		super(id);
		this.canRepair = false;
		this.setMaxDamage(maxDamage);
		this.setCreativeTab(CreativeTab.tabAdvJetpacks);
		this.setItemName("GyroscopicJetpack");
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setTextureFile("/advjetpacks/textures/item.png");
		this.setIconIndex(0);
	}




	public static void startJetpack(EntityPlayer player, ItemStack jetpack) {
		if (!player.capabilities.allowFlying && !player.worldObj.isRemote && jetpack.getItemDamage() < (maxDamage - 10)) {
			player.capabilities.allowFlying = true;
			player.capabilities.disableDamage = false;
			player.worldObj.playSoundAtEntity(player, "AdvJetpacks_startup", 1.0F, 1.0F);
			if (player instanceof EntityPlayerMP) {
				EntityPlayerMP playerMP = (EntityPlayerMP) player;
				playerMP.sendPlayerAbilities();  
			}
		}
	}

	public static void stopJetpack(EntityPlayer player) {
		if (player.capabilities.allowFlying && !player.worldObj.isRemote) {

			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
			player.capabilities.disableDamage = false;

			if (player instanceof EntityPlayerMP) {
				EntityPlayerMP playerMP = (EntityPlayerMP) player;
				playerMP.sendPlayerAbilities();
			}
		}
	}

	public void useJetpack(EntityPlayer player, ItemStack jetpack ) {		
		if (player.capabilities.allowFlying) {
			if (player.capabilities.isFlying) {
				jetpack.damageItem(10, player);
			} else {
				jetpack.damageItem(1, player);
			}
			if (jetpack.getItemDamage() > fuelLowWarningLevel && jetpack.getItemDamage() < fuelLowWarningLevel + 200 && !uomoAvvisato) {
				player.worldObj.playSoundAtEntity(player, "AdvJetpacks_lowFuel", 1.0F, 1.0F);
				player.sendChatToPlayer("\2474\247lGyro Jetpack: \247e\247nCarburante in esaurimento!");
				uomoAvvisato = true;
			}
			if (jetpack.getItemDamage() > (maxDamage - 150)) {
				player.sendChatToPlayer("\2474\247lGyro Jetpack: \247e\247nCarburante esaurito!");
				jetpack.setItemDamage(jetpack.getMaxDamage() - 1);
				stopJetpack(player);
				uomoAvvisato = false;
			}
		}
	}



	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		EntityPlayer player = (EntityPlayer) par3Entity;

		if (player.getCurrentArmor(2) != null) {
			if (player.getCurrentArmor(2).getItem() != this ) {
				stopJetpack(player);
			}
		} else {
			stopJetpack(player);
		}
	}


	//Avvia il jetpack appena lo infili nello slot apposito
	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
	{
		startJetpack(player, itemStack);
		useJetpack(player, itemStack);
	}	

	public boolean isValidArmor(ItemStack stack, int armorType)
	{
		if (armorType == 1) {
			return true;
		} else {
			return false;
		}
	}
}
