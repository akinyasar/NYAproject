package com.company;

import java.sql.*;
import java.util.*;
public class Main
{
    public static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;
    private static String kullaniciadi;
    private static int sifre;

    public static void main(String[] args) throws SQLException {


        BaglantiAc.getInstance();
        Login.getInstance();
        BaglantiKapat.getInstance();
        SicaklikGoruntule s=new SicaklikGoruntule();
        Uyari uyari=new Uyari();
        while (true) {
            int islem;
            System.out.println("1-Soğutucuyu Aç");
            System.out.println("2-Soğutucuyu Kapat");
            System.out.println("3-Sıcaklığı Görüntüle");
            System.out.println("4-Çıkış");
            System.out.println("Lütfen yapmak istediğiniz işlemi seçiniz...");
            Scanner yenigirdi=new Scanner(System.in);
            islem = yenigirdi.nextInt();
            double sicaklik=s.SicaklikOlc();
            switch (islem) {
                case 1:
                    System.out.println("Soğutucu Başarıyla Açıldı!");
                    break;
                case 2:
                    System.out.println("Soğutucu Başarıyla Kapatıldı!");
                    break;
                case 3:
                    System.out.printf("%s: %.1f\n","Ortam sıcaklığı: " , sicaklik , "°C");
                    uyari.UyariVer(sicaklik);
                    break;
                case 4:
                   System.exit(0);
                default:
                    break;
            }
        }
    }
public static class BaglantiAc {
        private static BaglantiAc instance ;
        private BaglantiAc()
        {
            try
            {   /***** Bağlantı kurulumu *****/
                    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/nyaproje",
                    "postgres", "22082000");
                if (conn != null)
                    System.out.println("Veritabanına bağlandı!");
                else
                System.out.println("Bağlantı girişimi başarısız!");

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
         }
    public static synchronized BaglantiAc getInstance(){
        instance=new BaglantiAc();
        return instance;
    }
}

    public static class BaglantiKapat {
        private static BaglantiKapat instance ;
        private BaglantiKapat() {

            try {
                /***** Bağlantı sonlandırma *****/
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static synchronized BaglantiKapat getInstance(){
            instance=new BaglantiKapat();
            return instance;
        }
    }


    public static class Login {
        private static Login instance ;
        private Login() throws SQLException {

            while (true) {
                Scanner girdi = new Scanner(System.in);
                System.out.print("Kullanici adi giriniz:");
                kullaniciadi = girdi.next();
                System.out.print("Sifre giriniz:");
                sifre = girdi.nextInt();
                String sql = "select * from kullanicigiris where kullaniciadi=? and sifre=?";

                stmt = conn.prepareStatement(sql);
                stmt.setString(1, kullaniciadi);
                stmt.setString(2, String.valueOf(sifre));
                rs = stmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Giriş Başarılı!");
                    break;
                } else {
                    System.out.println("Kullanıcı adı veya şifre hatalı!..Lütfen tekrar deneyin.");
                }
            }
        }
        public static synchronized Login getInstance() throws SQLException {
            instance=new Login();
            return instance;
        }
    }
    public interface ISicaklik
    {
        public double SicaklikOlc();

    }
    public static class SicaklikGoruntule implements ISicaklik{
        public  double SicaklikOlc() {
            Random r = new Random(); //random sınıfı
            double derece = r.nextDouble() * 45 + 15;
            return derece;
        }
    }
    public static class Uyari
    {
        public void UyariVer(double sicaklik)
        {
            if(sicaklik>=40.0)
            {
                System.out.println("Uyarı: Sıcaklık çok fazla!!!");
            }
        }
    }
}