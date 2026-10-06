/***********************************************************
 *    Nama File    : KoordinatKartesian.java
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 28 September 2026
 *    Deskripsi    : Implementasi komunkasi antar objek dengan kasus koordinat kartesian
 ***********************************************************/

import java.util.Scanner;
import java.lang.Math;
import java.util.InputMismatchException;

public class KoordinatKartesian
{
    public static void main(String args[])
    {
        Menu.menu();
    }
}

class Menu
{
    public static void menu()
    {
        boolean jalan = true;

        while (jalan)
        {
            printMenu();
            int pilihan = Helper.validInputInt("Pilih menu (1-5): ", 0, 5);

            switch (pilihan)
            {
                case 1:
                {
                    Koordinat p1 = new Koordinat(5, 3);
                    Koordinat p2 = new Koordinat(9, 7);
                    Koordinat p3 = new Koordinat();
                    Koordinat p4 = new Koordinat();

                    System.out.println("== CONTRUCTOR KONSTANTA ==\n");
                    p1.printKoordinat("A");
                    p2.printKoordinat("B");

                    System.out.println("\nKoordinat Titik Tengah: \n");
                    System.out.println("(VOID): ");
                    p3.titikTengahVoid(p1, p2);
                    p3.printKoordinat("T");

                    System.out.println("\n(RETURN): ");
                    p4 = p1.titikTengahReturn(p2);
                    p4.printKoordinat("T");
                    break;
                }
                case 2:
                {
                    Koordinat p1 = new Koordinat();
                    Koordinat p2 = new Koordinat();
                    Koordinat p3 = new Koordinat();
                    float jarak = 0.0f;

                    System.out.println("== INPUT DALAM ==\n");
                    p1.inputKoordinat();
                    p2.inputKoordinat();
                    p1.printKoordinat("A");
                    p2.printKoordinat("B");

                    System.out.println("\nJarak Titik: \n");
                    System.out.println("(VOID): ");
                    p3.jarakTitikVoid(p1, p2);

                    System.out.println("\n(RETURN): ");
                    jarak = p1.jarakTitikReturn(p2);
                    System.out.printf("Jarak antara 2 titik: %.2f\n\n", jarak);
                    break;
                }
                case 3:
                {
                    Koordinat p1 = new Koordinat();
                    Koordinat p2 = new Koordinat();
                    Koordinat p3 = new Koordinat();
                    Koordinat p4;

                    System.out.println("== INPUT LUAR ==\n");
                    p1.setAbsis(Helper.validInputFloat("Nilai Absis A: ", -99999, 99999));
                    p1.setOrdinat(Helper.validInputFloat("Nilai Ordinat A: ", -99999, 99999));
                    p2.setAbsis(Helper.validInputFloat("Nilai Absis B: ", -99999, 99999));
                    p2.setOrdinat(Helper.validInputFloat("Nilai Ordinat B: ", -99999, 99999));
                    p1.printKoordinat("A");
                    p2.printKoordinat("B");

                    System.out.println("\nCermin Sumbu X: \n");
                    System.out.println("(VOID): ");
                    p3.cerminSumbuXVoid(p1);
                    p3.printKoordinat("A'");

                    System.out.println("\n(RETURN): ");
                    p4 = p2.cerminSumbuXReturn();
                    p4.printKoordinat("B'");
                    break;
                }
                case 4:
                {
                    Koordinat p1 = new Koordinat();
                    Koordinat p2 = new Koordinat();
                    Koordinat p3 = new Koordinat();
                    Koordinat p4;

                    System.out.println("== INPUT LUAR ==\n");
                    p1.setAbsis(Helper.validInputFloat("Nilai Absis A: ", -99999, 99999));
                    p1.setOrdinat(Helper.validInputFloat("Nilai Ordinat A: ", -99999, 99999));
                    p2.setAbsis(Helper.validInputFloat("Nilai Absis B: ", -99999, 99999));
                    p2.setOrdinat(Helper.validInputFloat("Nilai Ordinat B: ", -99999, 99999));
                    p1.printKoordinat("A");
                    p2.printKoordinat("B");

                    System.out.println("\nCermin Sumbu Y: \n");
                    System.out.println("(VOID):");
                    p3.cerminSumbuYVoid(p1);
                    p3.printKoordinat("A'");

                    System.out.println("\n(RETURN): ");
                    p4 = p2.cerminSumbuYReturn();
                    p4.printKoordinat("B'");
                    break;
                }
                case 5:
                    System.out.println("Terima kasih telah menggunakan program ini!");
                    jalan = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        }
    }

    public static void printMenu()
    {
        System.out.println("\n==================================================");
        System.out.println("   PENGOLAHAN KOORDINAT KARTESIUS (VOID & RETURN)   ");
        System.out.println("==================================================");
        System.out.println("1. Constuctor, Mencari Titik Tengah (Void & Return)");
        System.out.println("2. Input Dalam, Mencari Jarak Titik (Void & Return)");
        System.out.println("3. Input Luar, Mencari Cermin Sumbu X (Void & Return)");
        System.out.println("4. Input Luar, Mencari Cermin Sumbu Y (Void & Return)");
        System.out.println("5. Keluar");
        System.out.println("==================================================");
    }
}

class Koordinat
{
    private float absis = 0;
    private float ordinat = 0;

    public Koordinat() {}
    public Koordinat(float absis, float ordinat)
    {
        this.absis = absis;
        this.ordinat = ordinat;
    }

    public void setAbsis(float absis) { this.absis = absis; }
    public void setOrdinat(float ordinat) { this.ordinat = ordinat; }

    public float getAbsis() { return this.absis; }
    public float getOrdinat() { return this.ordinat; }

    public void inputAbsis() { this.absis = Helper.validInputFloat("Input absis: ", -99999999, 99999999); }
    public void inputOrdinat() { this.ordinat = Helper.validInputFloat("Input absis: ", -99999999, 99999999); }
    public void inputKoordinat() { inputAbsis(); inputOrdinat(); }

    public void printKoordinat(String namaKoordinat)
    {
        System.out.printf("Koordinat %s = (%.2f, %.2f)%n", namaKoordinat, this.absis, this.ordinat);
    }

    public void titikTengahVoid(Koordinat p1, Koordinat p2)
    {
        this.absis = (p1.getAbsis() + p2.getAbsis()) / 2;
        this.ordinat = (p1.getOrdinat() + p2.getOrdinat()) / 2;
    }

    public Koordinat titikTengahReturn(Koordinat p)
    {
        float absisHasil = (this.absis + p.getAbsis()) / 2;
        float ordinatHasil = (this.ordinat + p.getOrdinat()) / 2;
        
        return new Koordinat(absisHasil, ordinatHasil);
    }

    public void cerminSumbuXVoid(Koordinat p)
    {
        this.absis = p.getAbsis();
        this.ordinat = -p.getOrdinat();
    }

    public Koordinat cerminSumbuXReturn()
    {
        float absisHasil = this.absis;
        float ordinatHasil = -this.ordinat;

        return new Koordinat(absisHasil, ordinatHasil);
    }

    public void cerminSumbuYVoid(Koordinat p)
    {
        this.absis = -p.getAbsis();
        this.ordinat = p.getOrdinat();
    }

    public Koordinat cerminSumbuYReturn()
    {
        float absisHasil = -this.absis;
        float ordinatHasil = this.ordinat;

        return new Koordinat(absisHasil, ordinatHasil);
    }

    public void jarakTitikVoid(Koordinat p1, Koordinat p2)
    {
        float dx = p2.getAbsis() - p1.getAbsis();
        float dy = p2.getOrdinat() - p1.getOrdinat();
        float jarak = (float)Math.sqrt(dx * dx + dy * dy);
        
        System.out.printf("Jarak antara dua titik: %.2f%n", jarak);
    }

    public float jarakTitikReturn(Koordinat p)
    {
        float dx = p.getAbsis() - this.absis;
        float dy = p.getOrdinat() - this.ordinat;

        return (float)Math.sqrt(dx * dx + dy * dy);
    }
}

class Helper
{
    static Scanner sc = new Scanner(System.in);

    public static float validInputFloat(String message, float min, float max){
        float result = 0;
        boolean valid = false;
        do{
            System.out.printf(message);
            try{
                result = sc.nextFloat();
                if (result < min || result > max){
                    System.out.printf("Input harus result %f - %f!!\n", min, max);
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e){
                System.out.printf("Input harus result %f - %f!!\n", min, max);
                sc.next();
            }
        } while (!valid);

        return result;
    }

    public static int validInputInt(String pesan, int min, int maks){
        int angka = 0;
        boolean valid = false;
        do{
            System.out.printf(pesan);
            try{
                angka = sc.nextInt();
                if (angka < min || angka > maks){
                    System.out.printf("Input harus angka %d - %d!!\n", min, maks);
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e){
                System.out.printf("Input harus angka %d - %d!!\n", min, maks);
                sc.next();
            }
        } while (!valid);

        return angka;
    }
}