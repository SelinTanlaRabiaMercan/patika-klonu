package com.patika.View;

import com.patika.Helper.*;

import com.patika.Model.*;

//import com.patika.Helper.Helper;
//import com.patika.Helper.configSabitler;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class operatorGUI extends JFrame {
    private JPanel panel1;
    private JTabbedPane tab_kullanic_islem;
    private JLabel lbl_hosgeldiniz;
    private JScrollPane scrl_kaydirma_cubugu;
    private JTable tbl_user_list;
    private JTextField txt_ad_soyad;
    private JTextField txt_kullanici_adi;
    private JPasswordField txt_password;
    private JComboBox cmb_kullanici_yetki;
    private JButton btn_kullanici_ekle;
    private JTextField txt_kullanici_id;
    private JButton btn_sil;
    private JButton btn_ara;
    private JPanel kullanicilar;
    private JPanel kullanici_ekle_sail;
    private JPanel kurs_list;
    private JTable tbl_kurs_list;
    private JTextField txt_kurs_adi;
    private JButton btn_kurs_ekle;
    private JPanel dersler;
    private JTable tbl_dersler;
    private JPanel ders_ekle;
    private JTextField txt_ders_adi;
    private JTextField txt_lang;
    private JComboBox cmb_patika_kurs;
    private JComboBox cmb_egitmen_usertab;
    private JButton btn_ders_ekle;
    private JTextField txt_sil_id_dersler;
    private JButton btn_sil_dersler;
    private JButton btn_dersler_guncelle;
    private JScrollPane tbl_dersler_d;
    private JButton btncks;
    private Object[] row_kurs_list;
    private JPopupMenu kursMenu;
    private Object[] row_user_list;//satır
    private DefaultTableModel mdl_dersler_list;
    private Object[] row_dersler_list;

    //model User List
//tabloda sutun isimlerinin gözükmesi
    private DefaultTableModel mdl_user_list = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            //id sutununa çift tıklayınca düzenleme şeyi açık olmasın
            if (column == 0 || column == 4) {
                return false;
            }
            return super.isCellEditable(row, column);
        }
    };


    private final operator operator;

    //DATABASEDEN VERİLERİ GETİRMEK
    public operatorGUI(operator operator) {
        this.operator = operator;

        add(panel1);
        setSize(1000, 500);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//pencere kapanınca arka plandada çalışmasın
        setTitle(configSabitler.PROJE_TITLE);//Başlık
        setVisible(true);
        lbl_hosgeldiniz.setText("Hoşgeldin " + operator.getName());


        Object[] col_user_list = {"İd", "İsim", "Kullanıcı Adı", "Şifre", "Kullanıcı Yetkinliği"};//sutun listesi
        mdl_user_list.setColumnIdentifiers(col_user_list);
        tbl_user_list.setModel(mdl_user_list);
        //masa başlıkları hareket edemesin
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        //tablo üzerindeki değişiklikleri kaydet güncelle
        tbl_user_list.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {

                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString());
                String name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 1).toString();
                String username = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 2).toString();
                String password = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 3).toString();
                // String kullanici_turu=tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();
                if (usertable.update(user_id, name, username, password)) {
                    Helper.mesajPenceresi("Güncellendi", "Bilgi Pernceresi", "Tamam");
                    comboboxIcerigiegitmen();
                    comboboxIcerigi();
                    tabloYenileme();
                    tabloYenilemeDersler();
                } else {
                    Helper.mesajPenceresi("Hata Oluştu", "Hata", "Tamam");
                }
            }
        });
        //tabloda üzerine geldiğim id textboxtta yazılsın
        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                txt_kullanici_id.setText(select_user_id);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }

        });

        row_user_list = new Object[col_user_list.length];
        tabloYenileme();

        //satır doldurmak
        /*
        Object[] ilkSatir={"1","deneme","deneme","deneme","deneme"};
        mdl_user_list.addRow(ilkSatir);
        */

        btn_kullanici_ekle.addActionListener(e -> {
            if (Helper.txtBosmu(txt_ad_soyad) || Helper.txtBosmu(txt_kullanici_adi) || Helper.txtBosmu(txt_password)) {
                // Helper.alanlariDoldurMesaji();
                Helper.mesajPenceresi("Tüm alanları Doldurunuz", "HATA", "Tamam");
            } else {
                if (cmb_kullanici_yetki.getSelectedItem().toString().equals("operator")||
                cmb_kullanici_yetki.getSelectedItem().toString().equals("student")){
                    Helper.mesajPenceresi("Operatorler sadece educator ekleyebilir","Yetersiz Yetki","Tamam");
                }
                if (cmb_kullanici_yetki.getSelectedItem().toString().equals("educator")) {
                    if (usertable.add(
                            txt_ad_soyad.getText(), txt_kullanici_adi.getText(), txt_password.getText(),
                            cmb_kullanici_yetki.getSelectedItem().toString()
                    )) {
                        Helper.mesajPenceresi("İşlem Başarılı", "Tamamlandı", "Tamam");
                        textleriTemizle();
                        tabloYenileme();
                        kullanicilar.setVisible(true);
                        comboboxIcerigiegitmen();
                        comboboxIcerigi();
                    }else {
                        Helper.mesajPenceresi("Bir Hata Oluştu", "HATA", "Tamam");
                    }
                }
            }
        });
        btn_sil.addActionListener(e -> {
            if (Helper.txtBosmu(txt_kullanici_id)) {
                Helper.mesajPenceresi("Silmek istediğiniz Kullanıcının id sini girin", "HATA", "Tamam");
            } else {
                int user_id = Integer.parseInt(txt_kullanici_id.getText());
                if (usertable.delete(user_id)) {
                    Helper.mesajPenceresi("Kullanıcı Silindi", "SUCCESS", "Tamam");
                    tabloYenileme();
                    kullanicilar.setVisible(true);
                    comboboxIcerigi();
                    comboboxIcerigiegitmen();
                    tabloYenilemeDersler();
                }
            }
        });

        btn_ara.addActionListener(e -> {
            String name = txt_ad_soyad.getText();
            String username = txt_kullanici_adi.getText();
            ArrayList<usertable> arabul = usertable.search(name, username);
            tabloYenileme(arabul);
            kullanicilar.setVisible(true);
        });


        //   ---- KURS ŞEYİ ----

        kursMenu = new JPopupMenu();
        JMenuItem kursupdate = new JMenuItem("Güncelle");
        JMenuItem kursdelete = new JMenuItem("Sil");
        kursMenu.add(kursupdate);
        kursMenu.add(kursdelete);

        kursupdate.addActionListener(e -> {
            //seçilen row un idsini getir
            try {
                int select_id = Integer.parseInt(tbl_kurs_list.getValueAt(tbl_kurs_list.getSelectedRow(), 0).toString());
                kursUpdate kursUpdate = new kursUpdate(kurs.fetch(select_id));
                //update şeyi kapandığı an tablo güncellensin
                kursUpdate.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        tabloYenilemekurs();
                        comboboxIcerigi();
                        //  comboboxIcerigiegitmen();
                        tabloYenilemeDersler();
                    }
                });
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        });
        kursdelete.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_kurs_list.getValueAt(tbl_kurs_list.getSelectedRow(), 0).toString());
            Helper.mesajPenceresicokluCevap("Silmek istediğine emin misin", "Siliniyor");
            kurs.delete(select_id);
            comboboxIcerigi();
            //update şeyi kapandığı an tablo güncellensin
            tabloYenilemekurs();
            tabloYenilemeDersler();

        });

        Object[] col_kurs_list = {"id", "Kurs Adı"};
        mdl_kurs_list.setColumnIdentifiers(col_kurs_list);
        tbl_kurs_list.setModel(mdl_kurs_list);

        tbl_kurs_list.setComponentPopupMenu(kursMenu);
        //masa başlıkları hareket edemesin
        tbl_kurs_list.getTableHeader().setReorderingAllowed(false);

        row_kurs_list = new Object[col_kurs_list.length];
        tabloYenilemekurs();

        //sağ tık yaptığım satır seçili kalsın
        tbl_kurs_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int secili_row = tbl_kurs_list.rowAtPoint(point);
                tbl_kurs_list.setRowSelectionInterval(secili_row, secili_row);
            }
        });
        //KURS EKLE
        btn_kurs_ekle.addActionListener(e -> {
            if (Helper.txtBosmu(txt_kurs_adi)) {
                Helper.mesajPenceresi("kurs adı giriniz", "hata", "tamam");
            } else {
                if (kurs.add(txt_kurs_adi.getText())) {
                    Helper.mesajPenceresi("Eklendi", "SUCCESS", "Tamam");
                    tabloYenilemekurs();
                    comboboxIcerigi();
                    // comboboxIcerigiegitmen();
                    textleriTemizle();
                }
            }
        });
        // DERSLER ŞEYİ
        mdl_dersler_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //id sutununa çift tıklayınca düzenleme şeyi açık olmasın
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        //  mdl_dersler_list = new DefaultTableModel();
        Object[] col_dersler = {"id", "Eğitmen", "Patika", "Ders Adı", "Programlama dili"};
        mdl_dersler_list.setColumnIdentifiers(col_dersler);
        row_dersler_list = new Object[col_dersler.length];
        tbl_dersler.setModel(mdl_dersler_list);//
        tbl_dersler.getTableHeader().setReorderingAllowed(false);//
        comboboxIcerigi();
        comboboxIcerigiegitmen();
        tabloYenilemeDersler();

        //DERSLER DERS EKLE
        btn_ders_ekle.addActionListener(e -> {
            Item patikaitem = (Item) cmb_patika_kurs.getSelectedItem();
            Item useritem = (Item) cmb_egitmen_usertab.getSelectedItem();
            if (Helper.txtBosmu(txt_ders_adi) || Helper.txtBosmu(txt_lang)) {
                Helper.mesajPenceresi("boş alan bırakmayınız", "Eksik işlem", "Tamam");
            } else {
                if (com.patika.Model.dersler.add(useritem.getKey(), patikaitem.getKey(), txt_ders_adi.getText(), txt_lang.getText())) {
                    Helper.mesajPenceresi("Başarılı", "Başarılı", "Tamam");
                    tabloYenilemeDersler();
                } else {
                    Helper.mesajPenceresi("İşlem Başaısız", "ERROR", "Tamam");
                }
            }
        });
        //DERSLER SİL BUTON
        btn_sil_dersler.addActionListener(e -> {
            if (Helper.txtBosmu(txt_sil_id_dersler)) {
                Helper.mesajPenceresi("silinecek id boş bırakılamaz", "HATA", "Tamam");
            } else {
                if (com.patika.Model.dersler.delete(Integer.parseInt(txt_sil_id_dersler.getText()))) {
                    Helper.mesajPenceresicokluCevap("Silmek istediğinize eminmisiniz", "SİL");
                    tabloYenilemeDersler();
                }
            }
        });
        //DERSLER TABLODA ÜZERİNE GELDİĞİM TEXTBOXLARDA YAZSIN
        tbl_dersler.getSelectionModel().addListSelectionListener(e -> {
            try {

                String select_dersler_id = tbl_dersler.getValueAt(tbl_dersler.getSelectedRow(), 0).toString();
                String name = tbl_dersler.getValueAt(tbl_dersler.getSelectedRow(), 3).toString();
                String lang = tbl_dersler.getValueAt(tbl_dersler.getSelectedRow(), 4).toString();


                txt_sil_id_dersler.setText(select_dersler_id);
                txt_ders_adi.setText(name);
                txt_lang.setText(lang);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        });

        //DERSLER GÜNCELLE
        /*
        btn_dersler_guncelle.addActionListener(e -> {
            if (com.patika.Model.dersler.update(cmb_egitmen_usertab.getSelectedIndex(),
                    cmb_patika_kurs.getSelectedIndex(), txt_ders_adi.getText(), txt_lang.getText(), Integer.parseInt(txt_sil_id_dersler.getText()))) {
                Helper.mesajPenceresicokluCevap("gnc eminmisiniz", "ıuyL");
                tabloYenilemeDersler();
            }
        });
         */
        btncks.addActionListener(e -> {
       dispose();
       LoginGUI loginGUI =new LoginGUI();
        });
    }


    public void tabloYenilemeDersler() {
        DefaultTableModel clearModels = (DefaultTableModel) tbl_dersler.getModel();
        clearModels.setRowCount(0);
        int i = 0;
        for (dersler obj : com.patika.Model.dersler.getlist()) {
            i = 0;
            row_dersler_list[i++] = obj.getId();
            //row_dersler_list[i++] = obj.getEgitmen().getName();//eğitmen adı
            row_dersler_list[i++] = obj.getEgitmen().getName();
            row_dersler_list[i++] = obj.getKurs12().getName();//patika adı
            row_dersler_list[i++] = obj.getName();//
            row_dersler_list[i++] = obj.getLang();
            mdl_dersler_list.addRow(row_dersler_list);

            txt_lang.setText(null);
            txt_ders_adi.setText(null);
            txt_sil_id_dersler.setText(null);
        }
    }

    public void tabloYenileme(ArrayList<usertable> kisiArama) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        //Tabloyu doldurmak
        int i = 0;
        for (usertable obj : kisiArama) {
            i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUsername();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getKullanici_turu();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void tabloYenilemekurs() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_kurs_list.getModel();
        clearModel.setRowCount(0);
        //Tabloyu doldurmak
        for (kurs obj : kurs.getList(5, txt_kurs_adi.getText())) {
            int i = 0;
            row_kurs_list[i++] = obj.getId();
            row_kurs_list[i++] = obj.getName();
            mdl_kurs_list.addRow(row_kurs_list);
        }
    }

    //kullanıcı eklediğimiz an tablo güncellensin
    public void tabloYenileme() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        //Tabloyu doldurmak
        for (usertable obj : usertable.getList()) {
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUsername();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getKullanici_turu();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void textleriTemizle() {
        txt_ad_soyad.setText(null);
        txt_kullanici_adi.setText(null);
        txt_password.setText(null);
        txt_kullanici_id.setText(null);
        txt_kurs_adi.setText(null);
    }


    private DefaultTableModel mdl_kurs_list = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            //id sutununa çift tıklayınca düzenleme şeyi açık olmasın
            if (column == 0) {
                return false;
            }
            return super.isCellEditable(row, column);
        }
    };

    public void comboboxIcerigi() {
        cmb_patika_kurs.removeAllItems();
        tabloYenilemekurs();
        for (kurs obj : kurs.getList()) {
            cmb_patika_kurs.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void comboboxIcerigiegitmen() {
        cmb_egitmen_usertab.removeAllItems();
        for (usertable obj : usertable.getList()) {
            if (obj.getKullanici_turu().equals("educator")) {
                cmb_egitmen_usertab.addItem(new Item(obj.getId(), obj.getName()));
            }
            //cmb_egitmen_usertab.addItem(new Item(obj.getEgitmen().getId(), obj.getEgitmen().getName()));
        }
    }
}
