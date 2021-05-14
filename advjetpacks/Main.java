package advjetpacks;

import advjetpacks.handlers.CraftingHandler;
import advjetpacks.handlers.EventHandler;
import advjetpacks.items.Items;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.src.BaseMod;
import net.minecraftforge.common.MinecraftForge;

@Mod(name="AdvJetpacks", version="1.0", modid="AdvJetpacks")
public class Main extends BaseMod{
	
	@Override
	public String getVersion() {
		return "1.0";
	}
	

	@Override
	public void load() {

	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println("[AdvJetpacks] Per usare il Jetpack inserire il numero della carta di credito. Accettate Visa e Mastercard");	
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	
	
	@Init
	public void init(FMLInitializationEvent event) {
		Items.InitItems();
		Items.RegisterLanguage();
	}
	
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		CraftingHandler.AddRecipes();
	}
}
