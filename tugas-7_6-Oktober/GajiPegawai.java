/*
Nama File: GajiPegawai.java
Nama Anggota: - Muhammad Athar Alfarisi (250005)
              - Muhammad Faiz Hariy Nugroho (250029)
              - Gibraldi Zilal Fachry (250038)
Tanggal buat: 29 September 2026
Deskripsi: Implementasi komunikasi antar class menggunakan masalah gaji pegawai
*/

import java.util.Scanner;
import java.lang.Math;

class Helper {
    public static Scanner sc = new Scanner(System.in);

    public static int validInputInt(String pesan, int min, int maks) {
        int angka = 0;
        boolean valid = false;

        do {
            System.out.print(pesan);

            if (sc.hasNextInt()) {
                angka = Integer.parseInt(sc.nextLine());
                
                if (angka >= min && angka <= maks) {
                    valid = true;
                } else {
                    System.out.printf("Error: Input harus angka diantara %d dan %d.%n", min, maks);

                }
            } else {
                String invalidInput = sc.nextLine();
                System.out.printf("Error: Input %s bukan angka integer.%n", invalidInput);
            }
        } while (!valid);

        return angka;
    }

    public static String formatRibuan(long n) {
        StringBuilder strBld = new StringBuilder(Long.toString(n));
        int insert = strBld.length() - 3;
        while (insert > 0) {
            strBld.insert(insert, '.');
            insert -= 3;
        }

        return strBld.toString();
    }
}

class Waktu {
    private int jam;
    private int menit;
    private int detik;

    public Waktu(){};
    public Waktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    };

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

    public int getDetik() {
        return detik;
    }

    void printWaktu() {
        System.out.printf("%02d:%02d:%02d", jam, menit, detik);
    }

    Waktu jarakWaktu(Waktu pulang) {
        Waktu hasil = new Waktu();
        int awal = (this.jam * 3600) + (this.menit * 60) + (this.detik);
        int akhir = (pulang.getJam() * 3600) + (pulang.getMenit() * 60) + (pulang.getDetik());
            
        if (akhir < awal){
            akhir += (24 * 3600);
        }
            
        int selisih = Math.abs(akhir - awal);

        hasil.setJam(selisih / 3600);
        selisih %= 3600;
        hasil.setMenit(selisih / 60);
        selisih %= 60;
        hasil.setDetik(selisih);

        return hasil;
    }

    void getLembur(Waktu lama){
        if (lama.getJam() >= 8){
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
    private String nip;
    private String nama;
    private int gol;
    private Waktu datang;
    private Waktu pulang;

    public Pegawai(){
        this.nip = null;
        this.nama = null;
        this.gol = 0;
        Waktu datang = new Waktu();
        this.datang = datang;
        Waktu pulang = new Waktu();
        this.pulang = pulang;
    }
    public Pegawai(String nip, String nama, int gol, Waktu datang, Waktu pulang){
        this.nip = nip;
        this.nama = nama;
        this.gol = gol;
        this.datang = datang;
        this.pulang = pulang;
    }

    public void inputPegawai() {
        System.out.print("Masukkan NIP: ");
        nip = Helper.sc.nextLine();

        System.out.print("Masukkan Nama: ");
        nama = Helper.sc.nextLine();

        System.out.println("Masukkan Golongan (1-4)");
        gol = Helper.validInputInt("Golongan: ", 1, 4);

        System.out.println("\n--- Input Waktu Datang ---");
        datang.inputWaktu();

        System.out.println("\n--- Input Waktu Pulang ---");
        pulang.inputWaktu();
    }

    public void setPegawai(String nip, String nama, int gol){
        this.nip = nip;
        this.nama = nama;
        this.gol = gol;
    }

    public void setNip(String nip){
        this.nip = nip;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setGol(int gol){
        this.gol = gol;
    }

    public void setDatang(Waktu datang){
        this.datang = datang;
    }

    public void setPulang(Waktu pulang){
        this.pulang = pulang;
    }

    public String getNip(){
        return this.nip;
    }

    public String getNama(){
        return this.nama;
    }

    public int getGol(){
        return this.gol;
    }

    public Waktu getDatang(){
        return this.datang;
    }

    public Waktu getPulang(){
        return this.pulang;
    }

    public Waktu getLamaKerja(){
        return datang.jarakWaktu(pulang);
    }

    public Waktu getWaktuLembur(){
        Waktu lama = getLamaKerja();
        Waktu lembur = new Waktu();
        lembur.getLembur(lama);
        return lembur;
    }

    public int getGajiHarian(){
        int gaji = 0;
        switch(gol){
            case 1:
                gaji = 150000;
                break;
            case 2:
                gaji = 200000;
                break;
            case 3:
                gaji = 400000;
                break;
            case 4:
                gaji = 500000;
                break;
        }
        return gaji;
    }

    public int getBiayaLembur(){
        int bayaran = 0;
        switch(gol){
            case 1:
                bayaran = 50000;
                break;
            case 2:
                bayaran = 75000;
                break;
            case 3:
                bayaran = 150000;
                break;
            case 4:
                bayaran = 200000;
                break;
        }
        return bayaran;
    }

    public int getUangLemburTotal(){
        return getWaktuLembur().getJam() * getBiayaLembur();
    }

    public int getTotalGaji(){
        return getGajiHarian() + getUangLemburTotal();
    }

    public String getStatus(){
        if (getLamaKerja().getJam() < 8) {
            return "peringatan";
        } else {
            return "ok";
        }
    }

    public void cetakTabel(Pegawai p) {
        System.out.println("\n\t\t\t\tDaftar Gaji Harian PT Informatika\n");

        System.out.println(
            "------------------------------------------------------------------------------------------------------------------------------------"
        );

        System.out.printf(
            "%-4s%-6s%-16s%-8s%-11s%-11s%-11s%-13s%-14s%-13s%-14s%-10s%n",
            "No",
            "NIP",
            "Nama",
            "Gol",
            "Datang",
            "Pulang",
            "Lama",
            "Jam Lembur",
            "Gaji Harian",
            "Lembur",
            "Total",
            "Status"
        );

        System.out.println(
            "------------------------------------------------------------------------------------------------------------------------------------"
        );

        System.out.printf(
            "%-4s%-6s%-16s%-8s",
            "1.",
            p.getNip(),
            p.getNama(),
            p.getGol()
        );

        p.getDatang().printWaktu();
        System.out.print("   ");

        p.getPulang().printWaktu();
        System.out.print("   ");

        p.getLamaKerja().printWaktu();
        System.out.print("   ");

        p.getWaktuLembur().printWaktu();
        System.out.print("      ");

        System.out.printf(
            "%-14s%-13s%-14s%-10s%n",
            Helper.formatRibuan(p.getGajiHarian()),
            Helper.formatRibuan(p.getUangLemburTotal()),
            Helper.formatRibuan(p.getTotalGaji()),
            p.getStatus()
        );

        System.out.println(
            "------------------------------------------------------------------------------------------------------------------------------------"
        );
    }
}

class Menu {
    public static void displayMenu(){
        System.out.println("\n============= MENU ===============");
        System.out.println("1. CONTRUCTOR KONSTANTA");
        System.out.println("2. SETTER KONSTANTA");
        System.out.println("3. SETTER INPUT LUAR");
        System.out.println("4. INPUT DALAM");
        System.out.println("0. KELUAR");
        System.out.println("==================================");
    }



    public static void menu(){
        int pilih = 0;
        do {
            displayMenu();
            pilih = Helper.validInputInt("Menu: ", 0, 4);

            switch(pilih){
                case 1: {
                    Pegawai p = new Pegawai("001", "Ali", 3, new Waktu(8, 0, 0), new Waktu(17, 15, 10));
                    p.cetakTabel(p);
                    break;
                }
                case 2:{
                    Pegawai p = new Pegawai();
                    p.setNip("001");
                    p.setNama("Ali");
                    p.setGol(3);
                    p.setDatang(new Waktu(8, 0, 0));
                    p.setPulang(new Waktu(17, 15, 10));

                    p.cetakTabel(p);
                    break;
                }
                case 3:{
                    Pegawai p = new Pegawai();
                    String nip, nama;
                    int gol;
                    System.out.print("Masukkan NIP: ");
                    nip = Helper.sc.nextLine();

                    System.out.print("Masukkan Nama: ");
                    nama = Helper.sc.nextLine();

                    gol = Helper.validInputInt("Masukkan Golongan (1-4): ", 1, 4);

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

                    p.cetakTabel(p);
                    break;
                }
                case 4:{
                    Pegawai p = new Pegawai();
                    p.inputPegawai();
                    p.cetakTabel(p);
                    break;
                }
                case 0:{
                    System.out.println("Terima kasih...\n");
                    break;
                }
            }
        } while(pilih != 0);
    }
}

public class GajiPegawai {
    public static void main(String[] args) {
        Menu.menu();
    }
}