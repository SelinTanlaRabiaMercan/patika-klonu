package com.patika.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static int screenCenter(String eksen, Dimension size) {
        int point;
        switch (eksen) {
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static boolean txtBosmu(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static void mesajPenceresi(String str, String titlestr, String bilgiPenceresibutonadi) {
        //teklMesajPenceresiButonu("Tamam");
        String msg;
        String title;
        switch (str) {
            default:
                msg = str;
                title = titlestr;
                UIManager.put("OptionPane.okButtonText", bilgiPenceresibutonadi);
                break;
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean mesajPenceresicokluCevap(String str, String titlestr) {
        //teklMesajPenceresiButonu("Tamam");
        String msg;
        String title;
        switch (str) {
            default:
                msg = str;
                title = titlestr;
                UIManager.put("OptionPane.yesButtonText","EVET");
                UIManager.put("OptionPane.noButtonText","HAYIR");
                break;
        }
        return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION) == 0;
    }

}
