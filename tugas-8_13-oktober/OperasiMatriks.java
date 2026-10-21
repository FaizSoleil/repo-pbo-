/***********************************************************
 *    Nama File    : OperasiMatriks.java
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 15 Oktober 2026
 *    Deskripsi    : Program Operasi Matriks
 ***********************************************************/

import java.util.Scanner;
import java.util.InputMismatchException;

class Helper {
    private static final Scanner sc = new Scanner(System.in);

    public static int validInputInt(String pesan){
        int min = Integer.MIN_VALUE;
        int maks = Integer.MAX_VALUE;

        int angka = 0;
        boolean valid = false;
        do{
            System.out.print(pesan);
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

    public static int validInputInt(String pesan, int min, int maks){
        int angka = 0;
        boolean valid = false;
        do{
            System.out.print(pesan);
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

    public static void print(String text) {
        System.out.println(text);
    }
}

class Matriks {
    private int baris;
    private int kolom;
    private int[][] data;

    public Matriks() {
        baris = 0;
        kolom = 0;
        data = new int[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                data[i][j] = 0;
            }
        }
    }

    public Matriks(int baris, int kolom) {
        this.baris = baris;
        this.kolom = kolom;
        data = new int[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                data[i][j] = 0;
            }
        }
    }

    public Matriks(int baris, int kolom, int[][] data) {
        this.baris = baris;
        this.kolom = kolom;
        this.data = new int[100][100];

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    public int get_baris() {
        return baris;
    }

    public int get_kolom() {
        return kolom;
    }

    public int[][] get_data() {
        return data;
    }

    public void set_baris(int baris) {
        this.baris = baris;
    }

    public void set_kolom(int kolom) {
        this.kolom = kolom;
    }

    public void set_data(int baris, int kolom, int[][] data) {
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    public void print_data() {
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void input_matriks(String nama) {
        baris = Helper.validInputInt("Input baris " + nama + ": ", 0, Integer.MAX_VALUE);
        kolom = Helper.validInputInt("Input kolom " + nama + ": ",  0, Integer.MAX_VALUE);

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                data[i][j] = Helper.validInputInt(
                    "Input data [" + (i + 1) + "][" + (j + 1) + "]: "
                );
            }
        }
    }

    public void ubah_entri_input(String nama) {
        Helper.print("Ubah entri matriks " + nama);

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                data[i][j] = Helper.validInputInt(
                    "Input data [" + i + "][" + j + "]: "
                );
            }
        }
    }

    public static Matriks kali_matriks_return(Matriks A, Matriks B) {
        if (A.get_kolom() != B.get_kolom()) {
            throw new RuntimeException("Jumlah kolom A harus sama dengan jumlah baris B");
        }

        Matriks hasil = new Matriks(A.get_baris(), B.get_kolom());

        for (int i = 0; i < A.get_baris(); i++) {
            for (int j = 0; j < B.get_kolom(); j++) {
                for (int k = 0; k < A.get_kolom(); k++) {
                    hasil.data[i][j] += A.data[i][k] * B.data[k][j];
                }
            }
        }

        return hasil;
    }

    public void kali_matriks_void(Matriks matriks_lain) {
        Matriks matriks_hasil = kali_matriks_return(this, matriks_lain);
        this.baris = matriks_hasil.baris;
        this.kolom = matriks_hasil.kolom;
        this.data = matriks_hasil.data;
    }

    public static Matriks tambah_matriks_return(Matriks A, Matriks B) {
        Matriks hasil = new Matriks(A.get_baris(), A.get_kolom());

        for (int i = 0; i < A.get_baris(); i++) {
            for (int j = 0; j < A.get_kolom(); j++) {
                hasil.data[i][j] = A.data[i][j] + B.data[i][j];
            }
        }

        return hasil;
    }

    public void tambah_matriks_void(Matriks matriks_lain) {
        Matriks matriks_hasil = tambah_matriks_return(this, matriks_lain);
        this.baris = matriks_hasil.baris;
        this.kolom = matriks_hasil.kolom;
        this.data = matriks_hasil.data;
    }
}

class Menu {
    public static void print_menu() {
        System.out.println("\n============= MENU ===============");
        System.out.println("1. CONSTRUCTOR KONSTANTA");
        System.out.println("2. SETTER KONSTANTA");
        System.out.println("3. SETTER INPUT LUAR");
        System.out.println("4. INPUT DALAM");
        System.out.println("0. KELUAR");
        System.out.println("==================================");
    }

    public static void run_menu() {
        int choice = -1;

        while (true) {
            Menu.print_menu();
            choice = Helper.validInputInt(": ", 0, 4);

            switch (choice) {
                case 1: {
                    int baris = 3;
                    int kolom = 3;

                    int[][] data_A = {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                    };

                    int[][] data_B = {
                        {1, 0, 0},
                        {0, 1, 0},
                        {0, 0, 1}
                    };

                    Matriks A = new Matriks(baris, kolom, data_A);
                    Matriks B = new Matriks(baris, kolom, data_B);

                    Menu.demo_matriks(A, B);
                    break;
                }

                case 2: {
                    Matriks A = new Matriks();
                    Matriks B = new Matriks();

                    int baris = 3;
                    int kolom = 3;

                    int[][] data_A = {
                        {3, 8, 11},
                        {0, 9, 2},
                        {4, 5, 14}
                    };

                    int[][] data_B = {
                        {8, 9, 1},
                        {0, 3, 5},
                        {0, 0, 20}
                    };

                    A.set_baris(baris);
                    A.set_kolom(kolom);
                    A.set_data(baris, kolom, data_A);

                    B.set_baris(baris);
                    B.set_kolom(kolom);
                    B.set_data(baris, kolom, data_B);

                    Menu.demo_matriks(A, B);
                    break;
                }

                case 3: {
                    Matriks A = new Matriks();
                    A.input_matriks("A");

                    Matriks B = new Matriks();
                    B.input_matriks("B");

                    Menu.demo_matriks(A, B);
                    break;
                }

                case 4: {
                    Matriks A = new Matriks();
                    A.set_baris(Helper.validInputInt("Input baris A: ",  0, Integer.MAX_VALUE));
                    A.set_kolom(Helper.validInputInt("Input kolom A: ",  0, Integer.MAX_VALUE));

                    int[][] data_baru = new int[100][100];

                    for (int i = 0; i < A.get_baris(); i++) {
                        for (int j = 0; j < A.get_kolom(); j++) {
                            data_baru[i][j] = Helper.validInputInt(
                                "Input data [" + i + "][" + j + "]: "
                            );
                        }
                    }

                    A.set_data(A.get_baris(), A.get_kolom(), data_baru);

                    Matriks B = new Matriks();
                    B.set_baris(Helper.validInputInt("Input baris B: ",  0, Integer.MAX_VALUE));
                    B.set_kolom(Helper.validInputInt("Input kolom B: ",  0, Integer.MAX_VALUE));

                    for (int i = 0; i < B.get_baris(); i++) {
                        for (int j = 0; j < B.get_kolom(); j++) {
                            data_baru[i][j] = Helper.validInputInt(
                                "Input data [" + i + "][" + j + "]: "
                            );
                        }
                    }

                    B.set_data(B.get_baris(), B.get_kolom(), data_baru);

                    Menu.demo_matriks(A, B);
                    break;
                }

                case 0:
                    System.out.println("Terimakasih");
                    break;

                default:
                    break;
            }

            if (choice == 0) {
                break;
            }
        }
    }

    public static void demo_matriks(Matriks A, Matriks B) {
        System.out.println("Matriks A:");
        A.print_data();
        System.out.println();

        System.out.println("Matriks B:");
        B.print_data();
        System.out.println();

        Matriks A2 = new Matriks(
            A.get_baris(),
            A.get_kolom(),
            A.get_data()
        );

        // PENJUMLAHAN TES

        try {
            Matriks C = Matriks.tambah_matriks_return(A, B);

            System.out.println("Matriks C = A + B:");
            C.print_data();
            System.out.println();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            A.tambah_matriks_void(B);

            System.out.println("Matriks A = A + B:");
            A.print_data();
            System.out.println();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("=================");

        // PERKALIAN TES

        try {
            Matriks D = Matriks.kali_matriks_return(A2, B);

            System.out.println("Matriks D = A * B:");
            D.print_data();
            System.out.println();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            A2.kali_matriks_void(B);

            System.out.println("Matriks A = A * B:");
            A2.print_data();
            System.out.println();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

public class OperasiMatriks {
    public static void main(String[] args) {
        Menu.run_menu();
    }
}