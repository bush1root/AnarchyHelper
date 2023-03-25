package me.bush7root.anarchyhelper.core;

import me.bush7root.anarchyhelper.conf.coreConf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Update {
    String updateUrl = "https://clowdy.space/AnarchyHelper/version";

    public void updateCheck() {
        try {
            String key;
            HttpsURLConnection urlConnection = (HttpsURLConnection) new URL(updateUrl).openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 Safari/537.36");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((key = buffer.readLine()) != null) {
                coreConf.needUpdate = !key.equals(coreConf.version);
            }
        } catch (Exception ignored) {}
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent e) {
        if (coreConf.needUpdate) {
            try {
                if (e.getEntity() == Minecraft.getInstance().player) {
                    Minecraft.getInstance().player.sendMessage(new StringTextComponent("§fВышла новая версия §cAnarchy§fHelper, вы можете скачать §cобновление §fтут:§c https://t.me/AnarchyHelper_bot§f (Telegram)"),
                            Minecraft.getInstance().player.getUUID());
                }
            } catch (Exception ignored) {}
        }
    }
}
