package com.project.util;

import com.project.View.Main;
import com.project.View.ReservePanel;
import com.project.View.SearchPanel;
import com.project.View.SettingPanel;

public class ViewSaver {

    private static Main main;
    private static ReservePanel reservePanel;
    private static SettingPanel settingPanel;
    private static SearchPanel searchPanel;

    public static String AdminPASS = "123123";
    public static boolean accessOath = false;


    public static void setMain(Main main) {
        ViewSaver.main = main;
    }

    public static Main getMain() {

        return main;
    }

    public static void setReservePanel(ReservePanel reservePanel) {
        ViewSaver.reservePanel = reservePanel;
    }

    public static void setSettingPanel(SettingPanel settingPanel) {
        ViewSaver.settingPanel = settingPanel;
    }

    public static void setSearchPanel(SearchPanel searchPanel) {
        ViewSaver.searchPanel = searchPanel;
    }

    public static ReservePanel getReservePanel() {

        return reservePanel;
    }

    public static SettingPanel getSettingPanel() {
        return settingPanel;
    }

    public static SearchPanel getSearchPanel() {
        return searchPanel;
    }
}
