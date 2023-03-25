package me.bush7root.anarchyhelper.conf;

import me.bush7root.anarchyhelper.AnarchyHelper;
import me.bush7root.anarchyhelper.func.addons.Widget;

import java.io.*;

public class widgetConfManager {
    public void loadConfig() {
        File confFile = new File("AnarchyHelper.txt");

        if (confFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(confFile));
                String widgetName;

                while ((widgetName = br.readLine()) != null) {
                    for (Widget widget : AnarchyHelper.widgets) {
                        if (widgetName.equals(widget.name)) {
                            widget.setToggled(true);
                        }
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    public void save() {
        File confFile = new File("AnarchyHelper.txt");

        if (confFile.exists()) {
            confFile.delete();
        }

        try {
            PrintWriter writer = new PrintWriter("AnarchyHelper.txt", "UTF-8");
            for (Widget widget : AnarchyHelper.widgets) {
                if (widget.isToggled()) {
                    writer.println(widget.name);
                }
            }
            writer.close();
        } catch (Exception ignored) {}
    }
}
