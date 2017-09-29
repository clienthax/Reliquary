package xreliquary.init;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xreliquary.reference.Compatibility;
import xreliquary.reference.Reference;
import xreliquary.util.LogHelper;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModFluids {
	private static final String XP_JUICE_FLUID_NAME = "xpjuice";
	private static final String MILK_FLUID_NAME = "milk";

	public static Fluid fluidXpJuice;
	public static Fluid milk;

	public static void preInit() {
		if(!Loader.isModLoaded(Compatibility.MOD_ID.ENDERIO)) {
			LogHelper.info("XP Juice registered by Reliquary.");
			fluidXpJuice = new Fluid(XP_JUICE_FLUID_NAME, new ResourceLocation(Reference.MOD_ID, "fluids/xpjuice_still"), new ResourceLocation(Reference.MOD_ID, "fluids/xpjuice_flowing")).setLuminosity(10).setDensity(800).setViscosity(1500).setUnlocalizedName("xreliquary.xpjuice");
			FluidRegistry.registerFluid(fluidXpJuice);
		} else {
			LogHelper.info("XP Juice registration left to Ender IO.");
		}

		if(!Loader.isModLoaded(Compatibility.MOD_ID.TINKERS_CONSTRUCT) && !FluidRegistry.isFluidRegistered(MILK_FLUID_NAME)) {
			milk = new Fluid(MILK_FLUID_NAME, new ResourceLocation(Reference.MOD_ID, "fluids/milk_still"), new ResourceLocation(Reference.MOD_ID, "fluids/milk_flowing")).setTemperature(320);
			FluidRegistry.registerFluid(milk);
		}
	}

	public static void init() {
		if(fluidXpJuice == null) { //should have been registered by open blocks / Ender IO
			fluidXpJuice = FluidRegistry.getFluid(XP_JUICE_FLUID_NAME);
			if(fluidXpJuice == null) {
				LogHelper.error("Liquid XP Juice registration left to Ender IO but could not be found.");
			}
		}

		if(milk == null) {
			milk = FluidRegistry.getFluid(MILK_FLUID_NAME);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onIconLoad(TextureStitchEvent.Pre event) {
		TextureMap textureMap = event.getMap();
		registerFluidSprites(textureMap, fluidXpJuice);
		registerFluidSprites(textureMap, milk);
	}

	@SideOnly(Side.CLIENT)
	private static void registerFluidSprites(TextureMap textureMap, Fluid fluid) {
		if (fluid != null) {
			textureMap.registerSprite(fluid.getStill());
			textureMap.registerSprite(fluid.getFlowing());
		}
	}
}
