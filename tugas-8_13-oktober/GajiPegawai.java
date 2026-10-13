/***********************************************************
 *    Nama File    : GajiPegawai.java
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 28 September 2026
 *    Deskripsi    : Program untuk mengolah array data pegawai
 ***********************************************************/

import java.util.Locale;
import java.util.Scanner;

public class GajiPegawai {
    public static void main(String args[]) {
        Menu.menu();
    }
}

class Helper {
    private static Scanner scanner = new Scanner(System.in);

    public static String input(String pesan) {
        System.out.print(pesan);
        return scanner.nextLine();
    }
 
    public static int validInputInt(String pesan, int minimum, int maksimum) {
        while (true) {
            try {
                int angka = Integer.parseInt(input(pesan).trim());
                if (minimum <= angka && angka <= maksimum) {
                    return angka;
                }
            } catch (NumberFormatException e) { /* pass */ }
            System.out.println("Input harus angka " + minimum + " - " + maksimum + "!!");
        }
    }
 
    public static String formatRibuan(int n) {
        return String.format(Locale.US, "%,d", n).replace(",", ".");
    }

}

class Waktu {
    private int jam = 0;
    private int menit = 0;
    private int detik = 0;

    public Waktu() { }
    public Waktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    public void setJam(int jam) { this.jam = jam; }
    public void setMenit(int menit) { this.menit = menit; }
    public void setDetik(int detik) { this.detik = detik; }
    public void setWaktu(int jam, int menit, int detik) {
        setJam(jam);
        setMenit(menit);
        setDetik(detik);
    }

    public void inputWaktu() {
        this.jam = Helper.validInputInt("Masukkan jam: ", 0, 23);
        this.menit = Helper.validInputInt("Masukkan menit: ", 0, 59);
        this.detik = Helper.validInputInt("Masukkan detik: ", 0, 59);
    }

    public int getJam() { return this.jam; }
    public int getMenit() { return this.menit; }
    public int getDetik() { return this.detik; }

    public void printWaktu() {
        System.out.printf("%02d:%02d:%02d", this.jam, this.menit, this.detik);
    }

    public void printWaktuJamLembur() {
        System.out.printf("%d:%02d:%02d", this.jam, this.menit, this.detik);
    }

    public Waktu jarakWaktu(Waktu pulang) {
        Waktu hasil = new Waktu();

                int awal = (jam * 3600) + (menit * 60) + detik;
        int akhir = (pulang.getJam() * 3600) + (pulang.getMenit() * 60) + pulang.getDetik();
 
        if (akhir < awal) {
            akhir += 24 * 3600;
        }
 
        int selisih = Math.abs(akhir - awal);
 
        hasil.setJam(selisih / 3600);
        selisih %= 3600;
        hasil.setMenit(selisih / 60);
        selisih %= 60;
        hasil.setDetik(selisih);
 
        return hasil;
    }

    public void lembur(Waktu lama) {
        if (lama.getJam() >= 8) {
            this.jam = lama.getJam() - 8;
            this.menit = lama.getMenit();
            this.detik = lama.getDetik();
        } else {
            this.jam = 0;
            this.menit = 0;
            this.detik = 0;
        }
    }
}

class Pegawai {
    private String nip="";
    private String nama="";
    private int gol=0;
    private Waktu datang=null;
    private Waktu pulang=null;

    public Pegawai() {}
    public Pegawai(String nip, String nama, int gol, Waktu datang, Waktu pulang) {
        this.nip = nip;
        this.nama = nama;
        this.gol = gol;
        this.datang = datang;
        this.pulang = pulang;
    }

    public void inputPegawai() {
        this.nip = Helper.input("Masukkan NIP: ");
        this.nama = Helper.input("Masukkan Nama: ");
        this.gol = Helper.validInputInt("Masukkan Golongan (1-4): ", 1, 4);
 
        System.out.println("\n--- Input Waktu Datang ---");
        this.datang.inputWaktu();
 
        System.out.println("\n--- Input Waktu Pulang ---");
        this.pulang.inputWaktu();
    }

    public void setPegawai(String nip, String nama, int gol) {
        this.nip = nip;
        this.nama = nama;
        this.gol = gol;
    }

    public void setNip(String nip) { this.nip = nip; }
    public void setNama(String nama) { this.nama = nama; }
    public void setGol(int gol) { this.gol = gol; }
    public void setDatang(Waktu datang) { this.datang = datang; }
    public void setPulang(Waktu pulang) { this.pulang = pulang; }

    public String getNip() { return this.nip; }
    public String getNama() { return this.nama; }
    public int getGol() { return this.gol; }
    public Waktu getDatang() { return this.datang; }
    public Waktu getPulang() { return this.pulang; }

    public Waktu getLamaKerja() { return this.datang.jarakWaktu(this.pulang); }

    public Waktu getWaktuLembur() {
        Waktu lembur = new Waktu();
        lembur.lembur(getLamaKerja());
        return lembur;
    }

    public int getGajiHarian() {
        switch (this.gol) {
            case 1: return 150000;
            case 2: return 200000;
            case 3: return 400000;
            case 4: return 500000;
            default: return 0;
        }
    }

    public int getBiayaLembur() {
        switch (this.gol) {
            case 1: return 50000;
            case 2: return 75000;
            case 3: return 150000;
            case 4: return 200000;
            default: return 0;
        }
    }

    public int getUangLemburTotal() {
        return getWaktuLembur().getJam() * getBiayaLembur();
    }

    public int getTotalGaji() {
        return getGajiHarian() + getUangLemburTotal();
    }

    public String getStatus() {
        if (getLamaKerja().getJam() < 8)
            return "peringatan";
        else
            return "ok";
    }

    public void printPegawai() {
        printPegawai(1);
    }
 
    public void printPegawai(int no) {
        System.out.print(String.format("%-4s%-6s%-16s%-8d", no + ".", getNip(), getNama(), getGol()));
 
        getDatang().printWaktu();
        System.out.print("   ");
        getPulang().printWaktu();
        System.out.print("   ");
        getLamaKerja().printWaktu();
        System.out.print("   ");
        getWaktuLembur().printWaktuJamLembur();
        System.out.print("      ");
 
        System.out.println(String.format("%-14s%-13s%-14s%-10s",
                Helper.formatRibuan(getGajiHarian()),
                Helper.formatRibuan(getUangLemburTotal()),
                Helper.formatRibuan(getTotalGaji()),
                getStatus()));
    }
}

class ArrayPegawai {
    static final String GARIS = "-".repeat(132);

    private Pegawai[] data;
    private int kapasitas;
    private int jumlah=0;

    public ArrayPegawai() { this(100); }
    public ArrayPegawai(int kapasitas) {
        this.kapasitas = kapasitas;
        this.data = new Pegawai[kapasitas];
    }

    public void tambahPegawai(Pegawai pegawai) {
        if (jumlah < kapasitas) {
            data[jumlah] = pegawai;
            jumlah++;
        }
    }

    public Pegawai[] getData() { return this.data; }
    public int getJumlah() { return this.jumlah; }

    public void inputData(int n) {
        data = new Pegawai[kapasitas];
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Data Pegawai ke-" + (i+1) + " ---");
            Pegawai p = new Pegawai();
            p.inputPegawai();
            tambahPegawai(p);
        }
    }

    public void printAllPegawai() {
        System.out.println("\n\t\t\t\tDaftar Gaji Harian PT Informatika\n");
        System.out.println(GARIS);
        System.out.println(String.format("%-4s%-6s%-16s%-8s%-11s%-11s%-11s%-13s%-14s%-13s%-14s%-10s",
                "No", "NIP", "Nama", "Gol", "Datang", "Pulang",
                "Lama", "Jam Lembur", "Gaji Harian", "Lembur",
                "Total", "Status"));
        System.out.println(GARIS);
 
        for (int i = 0; i < this.jumlah; i++) {
            data[i].printPegawai(i + 1);
        }
 
        System.out.println(GARIS);
    }
}

class Menu {
    public static void display_menu() {
        System.out.println("\n============= MENU ===============");
        System.out.println("1. CONSTRUCTOR KONSTANTA");
        System.out.println("2. SETTER KONSTANTA");
        System.out.println("3. SETTER INPUT LUAR");
        System.out.println("4. INPUT DALAM");
        System.out.println("0. KELUAR");
        System.out.println("==================================");
    }
 
    public static int input() {
        return Helper.validInputInt("Menu: ", 0, 4);
    }

    public static void menu() {
        int pilih = -1;
        while (pilih != 0) {
            display_menu();
            pilih = input();
 
            switch (pilih) {
                case 1: {
                    int n = Helper.validInputInt("Masukkan jumlah pegawai: ", 1, 100);
                    ArrayPegawai array_pegawai1 = new ArrayPegawai();
                    for (int i = 0; i < n; i++) {
                        Pegawai p = new Pegawai("00" + (i + 1), "Pegawai " + (i + 1), (i % 4) + 1,
                                new Waktu(8, 0, 0), new Waktu(17, 15, 10));
                        array_pegawai1.tambahPegawai(p);
                    }
                    array_pegawai1.printAllPegawai();
                    break;
                }

                case 2: {
                    int n = Helper.validInputInt("Masukkan jumlah pegawai: ", 1, 100);
                    ArrayPegawai array_pegawai2 = new ArrayPegawai();
                    for (int i = 0; i < n; i++) {
                        Pegawai p = new Pegawai();
                        p.setNip("00" + (i + 1));
                        p.setNama("Pegawai " + (i + 1));
                        p.setGol((i % 4) + 1);
                        p.setDatang(new Waktu(8, 0, 0));
                        p.setPulang(new Waktu(17, 15, 10));
                        array_pegawai2.tambahPegawai(p);
                    }
                    array_pegawai2.printAllPegawai();
                    break;
                }

                case 3: {
                    int n = Helper.validInputInt("Masukkan jumlah pegawai: ", 1, 100);
                    ArrayPegawai array_pegawai3 = new ArrayPegawai();
                    for (int i = 0; i < n; i++) {
                        System.out.println("\n--- Data Pegawai ke-" + (i + 1) + " ---");
                        Pegawai p = new Pegawai();
                        String nip = Helper.input("Masukkan NIP: ");
                        String nama = Helper.input("Masukkan Nama: ");
                        int gol = Helper.validInputInt("Masukkan Golongan (1-4): ", 1, 4);

                        p.setNip(nip);
                        p.setNama(nama);
                        p.setGol(gol);

                        System.out.println("\n--- Input Waktu Datang ---");
                        Waktu d = new Waktu();
                        d.inputWaktu();
                        p.setDatang(d);

                        System.out.println("\n--- Input Waktu Pulang ---");
                        Waktu pl = new Waktu();
                        pl.inputWaktu();
                        p.setPulang(pl);

                        array_pegawai3.tambahPegawai(p);
                    }
                    array_pegawai3.printAllPegawai();
                    break;
                }

                case 4: {
                    int n = Helper.validInputInt("Masukkan jumlah pegawai: ", 1, 100);
                    ArrayPegawai array_pegawai4 = new ArrayPegawai();
                    array_pegawai4.inputData(n);
                    array_pegawai4.printAllPegawai();
                    break;
                }

                case 0:
                    System.out.println("Terima kasih...");
                    break;

                default:
                    // pass
                    break;
            }
        }
    }
}