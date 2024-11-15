package com.aor.hero.viewer.menu;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.menu.Menu;
import com.aor.hero.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {

    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {
        int screenWidth = gui.getScreenWidth();
        String centipedeText = "C E N T I P E D E";
        int centipedeTextX = (screenWidth - centipedeText.length()) / 2;

        // Draw the centipede title
        for (int i = 0; i < centipedeText.length(); i++) {
            gui.drawCharacter(centipedeTextX + i, 3, centipedeText.charAt(i), "#9932CC");
        }

        // Draw menu entries
        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            String entryText = getModel().getEntry(i);
            int entryX = (screenWidth - entryText.length()) / 2;
            String textColor = getModel().isSelected(i) ? "#BA55D3" : "#7B68EE";

            for (int j = 0; j < entryText.length(); j++) {
                gui.drawCharacter(entryX + j, 7 + i * 2, entryText.charAt(j), textColor);
            }
        }
    }
}
