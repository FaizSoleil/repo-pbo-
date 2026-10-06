
/***********************************************************
 *    Nama File    : SelisihWaktu.java
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 28 September 2026
 *    Deskripsi    : Program kalkulasi selisih waktu
 ***********************************************************/

import java.util.InputMismatchException;
import java.util.Scanner;

class Waktu{
    private int jam;
    private int menit;
    private int detik;

    // Construtor
    public Waktu (){}

    public Waktu(int jam, int menit, int detik){
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    // Setter Getter
    public void setWaktu(int jam, int menit, int detik){
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    public void setJam(int jam){
        this.jam = jam;
    }

    public void setMenit(int menit){
        this.menit = menit;
    }

    public void setDetik(int detik){
        this.detik = detik;
    }

    public void inputWaktu(){
        this.jam = Helper.validInputInt("Masukkan jam: ", 0, 23);
        this.menit = Helper.validInputInt("Masukkan menit: ", 0, 59);
        this.detik = Helper.validInputInt("Masukkan detik: ", 0, 59);
    }

    public int getJam(){
        return this.jam;
    }

    public int getMenit(){
        return this.menit;
    }

    public int getDetik(){
        return this.detik;
    }

    // Output
    public void printWaktu(char name){
        System.out.printf("Waktu %c: %02d:%02d:%02d\n", name, jam, menit, detik);
    }

    public Waktu hitungSelisihReturn(Waktu B){
        Waktu hasil = new Waktu();

        int awal = this.jam * 3600 + this.menit * 60 + this.detik;
        int akhir = B.getJam() * 3600 + B.getMenit() * 60 + B.getDetik();
        int selisih = Math.abs(awal - akhir);

        hasil.setJam(selisih / 3600);
        selisih %= 3600;
        hasil.setMenit(selisih / 60);
        selisih %= 60;
        hasil.setDetik(selisih);

        return hasil;
    }

    public void hitungSelisihVoid(Waktu A, Waktu B){
        int awal = A.getJam() * 3600 + A.getMenit() * 60 + A.getDetik();
        int akhir = B.getJam() * 3600 + B.getMenit() * 60 + B.getDetik();
        int selisih = Math.abs(awal - akhir);

        this.jam = (selisih / 3600);
        selisih %= 3600;
        this.menit = (selisih / 60);
        selisih %= 60;
        this.detik = (selisih);
    }
}

public class SelisihWaktu{
    public static void main(String[] args){
        Menu.runMenu();
    }
}

class Menu{
    public static void displayMenu(){
        System.out.printf("\n========== Menu ==========\n");
        System.out.printf("1. Constructor Konstanta\n");
        System.out.printf("2. Setter Konstanta \n");
        System.out.printf("3. Setter Input Luar\n");
        System.out.printf("4. Input Dalam\n");
        System.out.printf("0. Keluar\n");
        System.out.printf("==========================\n");
    }

    public static void menu(int pilihan){
        switch(pilihan){
            case 1:{
                System.out.println("\n--- 1. Constructor Konstanta ---");
                Waktu wA = new Waktu(8, 30, 0);
                Waktu wB = new Waktu(10, 45, 15);

                wA.printWaktu('A');
                wB.printWaktu('B');

                Waktu selisihReturn = wA.hitungSelisihReturn(wB);
                System.out.print("Selisih (Return): ");
                selisihReturn.printWaktu('S');

                Waktu selisihVoid = new Waktu();
                selisihVoid.hitungSelisihVoid(wA, wB);
                System.out.print("Selisih (Void)  : ");
                selisihVoid.printWaktu('S');
                break;
            }
            case 2:{
                System.out.println("\n--- 2. Setter Konstanta ---");
                Waktu wA = new Waktu();
                Waktu wB = new Waktu();

                wA.setWaktu(12, 15, 30);
                wB.setWaktu(15, 0, 45);

                wA.printWaktu('A');
                wB.printWaktu('B');

                Waktu selisihReturn = wA.hitungSelisihReturn(wB);
                System.out.print("Selisih (Return): ");
                selisihReturn.printWaktu('S');

                Waktu selisihVoid = new Waktu();
                selisihVoid.hitungSelisihVoid(wA, wB);
                System.out.print("Selisih (Void)  : ");
                selisihVoid.printWaktu('S');
                break;
            }
            case 3:{
                System.out.println("\n--- 3. Setter Input Luar ---");
                Waktu wA = new Waktu();
                Waktu wB = new Waktu();

                System.out.println("Input Waktu A:");
                wA.setJam(Helper.validInputInt("Masukkan jam: ", 0, 23));
                wA.setMenit(Helper.validInputInt("Masukkan menit: ", 0, 59));
                wA.setDetik(Helper.validInputInt("Masukkan detik: ", 0, 59));

                System.out.println("\nInput Waktu B:");
                wB.setJam(Helper.validInputInt("Masukkan jam: ", 0, 23));
                wB.setMenit(Helper.validInputInt("Masukkan menit: ", 0, 59));
                wB.setDetik(Helper.validInputInt("Masukkan detik: ", 0, 59));

                System.out.println();
                wA.printWaktu('A');
                wB.printWaktu('B');

                Waktu selisihReturn = wA.hitungSelisihReturn(wB);
                System.out.print("Selisih (Return): ");
                selisihReturn.printWaktu('S');

                Waktu selisihVoid = new Waktu();
                selisihVoid.hitungSelisihVoid(wA, wB);
                System.out.print("Selisih (Void)  : ");
                selisihVoid.printWaktu('S');
                break;
            }
            case 4:{
                System.out.println("\n--- 4. Input Dalam ---");
                Waktu wA = new Waktu();
                Waktu wB = new Waktu();

                System.out.println("Input Waktu A:");
                wA.inputWaktu();

                System.out.println("\nInput Waktu B:");
                wB.inputWaktu();

                System.out.println();
                wA.printWaktu('A');
                wB.printWaktu('B');

                Waktu selisihReturn = wA.hitungSelisihReturn(wB);
                System.out.print("Selisih (Return): ");
                selisihReturn.printWaktu('S');

                Waktu selisihVoid = new Waktu();
                selisihVoid.hitungSelisihVoid(wA, wB);
                System.out.print("Selisih (Void)  : ");
                selisihVoid.printWaktu('S');
                break;
            }
            case 0:{
                System.out.println("Terima kasih...\n\n");
                break;
            }
        }
    }

    public static void runMenu(){
        int pilih = 0;

        do{
            Menu.displayMenu();
            pilih = Helper.validInputInt("Menu: ", 0, 4);
            Menu.menu(pilih);
        } while (pilih != 0);
    }
}

class Helper{
    static Scanner in = new Scanner(System.in);

    public static int validInputInt(String pesan, int min, int maks){
        int angka = 0;
        boolean valid = false;
        do{
            System.out.printf(pesan);
            try{
                angka = in.nextInt();
                if (angka < min || angka > maks){
                    System.out.printf("Input harus angka %d - %d!!\n", min, maks);
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e){
                System.out.printf("Input harus angka %d - %d!!\n", min, maks);
                in.next();
            }
        } while (!valid);

        return angka;
    }
}