package org.example;

import com.formdev.flatlaf.FlatDarculaLaf;
import org.abstraction.ScheduleServiceAbstraction;
import org.concrete.Manager;
import org.example.model.Keeper;
import org.example.view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

    try{
        UIManager.setLookAndFeel(new FlatDarculaLaf());
        //for runtime scope
        Class.forName("org.usable.Implementation");
    } catch (ClassNotFoundException | UnsupportedLookAndFeelException ex) {
        throw new RuntimeException(ex);
    }
        ScheduleServiceAbstraction specification = Manager.getScheduleService();
        Keeper.getInstance().setScheduleServiceAbstraction(specification);
        new MainFrame();
    }

}