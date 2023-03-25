package me.bush7root.anarchyhelper;

import me.bush7root.anarchyhelper.conf.widgetConfManager;
import me.bush7root.anarchyhelper.core.AnarchyUsers;
import me.bush7root.anarchyhelper.core.Update;
import me.bush7root.anarchyhelper.events.onKey;
import me.bush7root.anarchyhelper.func.ConsoleManager;
import me.bush7root.anarchyhelper.func.addons.*;
import me.bush7root.anarchyhelper.listeners.draggableListener;
import me.bush7root.anarchyhelper.gui.anarchyMainGui;
import me.bush7root.anarchyhelper.conf.st;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;

@Mod("anarchyhelper")
public class AnarchyHelper {
    public static anarchyMainGui anarchyMainGui;
    public static ArrayList<Widget> widgets = new ArrayList<>();

    public AnarchyHelper() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        new st().socialExp();
        new Update().updateCheck();

        // Core
        MinecraftForge.EVENT_BUS.register(new Update());

        // Listeners
        MinecraftForge.EVENT_BUS.register(new draggableListener());

        // Events
        MinecraftForge.EVENT_BUS.register(new onKey());
        MinecraftForge.EVENT_BUS.register(new AnarchyUsers());

        // Managers
        MinecraftForge.EVENT_BUS.register(new ConsoleManager());

        // Widgets
        widgets.add(new NearWidget());
        widgets.add(new FPSIndicator());
        widgets.add(new ArmorStatus());
        widgets.add(new AutoSpawn());
        widgets.add(new AutoAccept());
        widgets.add(new ChatCalc());
        widgets.add(new Sprint());
        widgets.add(new Particles());
        widgets.add(new DangerNotify());
        new widgetConfManager().loadConfig();

        // Logic
        anarchyMainGui = new anarchyMainGui();
    }
}
