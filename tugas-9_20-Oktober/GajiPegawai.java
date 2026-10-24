/***********************************************************
 *    Nama File    : GajiPegawai.java
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 21 Oktober 2026
 *    Deskripsi    : Program untuk mengolah array data pegawai dan implementasi inheritence
 ***********************************************************/

import java.util.Locale;
import java.util.Scanner;
import java.lang.NumberFormatException;

class Helper {
    public static Scanner sc = new Scanner(System.in);

    public static String inputString(String pesan) {
        System.out.print(pesan);
        return sc.nextLine();
    }

    public static int validInputInt(String pesan, int min, int maks) {
        int angka = 0;
        boolean valid = false;

        String inputError = String.format("Input harus angka %d - %d !!", min, maks);

        do {

            try {
                angka = Integer.parseInt(inputString(pesan));
                if (angka < min || angka > maks)
                    System.out.println(inputError);
                else valid = true;
            } catch (NumberFormatException e) {
                System.out.println(inputError);
            }

        } while (!valid);

        return angka;
    }

    public static String formatRibuan(int n) {
        return String.format(Locale.US, "%,d", n).replace(",", ".");
    }

}

class Waktu {
    private int jam=0;
    private int menit=0;
    private int detik=0;

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

    public int getJam() { return this.jam; }
    public int getMenit() { return this.menit; }
    public int getDetik() { return this.detik; }

    public void inputWaktu() {
        this.jam = Helper.validInputInt("Masukkan jam: ", 0, 23);
        this.menit = Helper.validInputInt("Masukkan menit: ", 0, 59);
        this.detik = Helper.validInputInt("Masukkan detik: ", 0, 59);
    }

    public void printWaktu() {
        System.out.printf("%02d:%02d:%02d", this.jam, this.menit, this.detik);
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

}

class Person {
    protected String nama="";
    protected String alamat="";

    public Person() {}
    public Person(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    public void setNama(String nama) { this.nama = nama; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public void setPerson(String nama, String alamat) {
        setNama(nama);
        setAlamat(alamat);
    }

    public String getNama() { return this.nama; }
    public String getAlamat() { return this.alamat; }

    public void input() {
        this.nama = Helper.inputString("Masukkan nama: ");
        this.alamat = Helper.inputString("Masukkan alamat: ");
    }

    public void output() {
        System.out.println(this.nama + " " + this.alamat);
    }
}

class Pegawai extends Person {
    private String NIP="";
    private int gol=0;
    private Waktu datang=new Waktu();
    private Waktu pulang=new Waktu();

    public Pegawai() {}
    public Pegawai(String nama, String alamat, String NIP, int gol, Waktu datang, Waktu pulang) {
        this.nama = nama;
        this.alamat = alamat;
        this.NIP = NIP;
        this.gol = gol;
        this.datang = datang;
        this.pulang = pulang;
    }

    // public void setNama(String nama) { this.setNama(nama); }
    // public void setAlamat(String alamat) { this.setAlamat(alamat);}
    public void setNIP(String NIP) { this.NIP = NIP; }
    public void setGol(int gol) { this.gol = gol; }
    public void setDatang(Waktu datang) { this.datang = datang; }
    public void setPulang(Waktu pulang) { this.pulang = pulang; }
    
    public String getNIP() { return this.NIP; }
    public int getGol() { return this.gol; }
    public Waktu getDatang() { return this.datang; }
    public Waktu getPulang() { return this.pulang; }

    public void inputPegawai() {
        super.input();
        this.NIP = Helper.inputString("Masukkan NIP: ");
        this.gol = Helper.validInputInt("Masukkan Golongan (1-4): ", 1, 4);

        System.out.println("\n--- Input Waktu Datang ---\n");
        this.datang.inputWaktu();
        System.out.println("\n--- Input Waktu Pulang ---\n");
        this.pulang.inputWaktu();
    }

    public Waktu getLamaKerja() {
        return datang.jarakWaktu(pulang);
    }

    public Waktu getWaktuLembur() {
        Waktu lembur = getLamaKerja();
        if (lembur.getJam() > 8)
            lembur.setJam((lembur.getJam() - 8));
        else
            lembur.setWaktu(0, 0, 0);

        return lembur;
    }

    public int getGajiHarian() {
        int gaji = 0;
        switch (gol) {
                case 1: gaji = 150000; break;
                case 2: gaji = 200000; break;
                case 3: gaji = 400000; break;
                case 4: gaji = 500000; break;
            }
            return gaji;
    }

    public int getBiayaLembur() {
        int bayaran = 0;
        switch (gol) {
            case 1: bayaran = 50000; break;
            case 2: bayaran = 75000; break;
            case 3: bayaran = 150000; break;
            case 4: bayaran = 200000; break;
        }
        return bayaran;
    }
}