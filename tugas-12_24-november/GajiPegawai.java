/***********************************************************
 *    Nama File    : GajiPegawai.java
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 24 November 2026
 *    Deskripsi    : Program gaji pegawai, diimplementasikan dengan struktur data queue.
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

    int getUangLemburTotal() {
        return getWaktuLembur().getJam() * getBiayaLembur();
    }

    int getTotalGaji() {
        return getGajiHarian() + getUangLemburTotal();
    }

    String getStatus() {
        return (getLamaKerja().getJam() < 8) ? "peringatan" : "ok";
    }

    public void printPegawai(int no) {
        System.out.print(String.format("%-4s%-6s%-16s%-20s%-8d", no + ".", getNIP(), getNama(), getAlamat(), getGol()));
 
        getDatang().printWaktu();
        System.out.print("   ");
        getPulang().printWaktu();
        System.out.print("   ");
        getLamaKerja().printWaktu();
        System.out.print("   ");
        getWaktuLembur().printWaktu();
        System.out.print("     ");
 
        System.out.println(String.format("%-14s%-13s%-14s%-10s",
                Helper.formatRibuan(getGajiHarian()),
                Helper.formatRibuan(getUangLemburTotal()),
                Helper.formatRibuan(getTotalGaji()),
                getStatus()));
    }
}

class NodePegawai {
    Pegawai data;
    NodePegawai next;

    NodePegawai(Pegawai data) {
        this.data = data;
        this.next = null;
    }
}

class QueuePegawai {
    String GARIS = "-".repeat(152);

    private NodePegawai depan = null;
    private NodePegawai belakang = null;
    private int jumlah = 0;

    public QueuePegawai() { }

    public boolean isEmpty() { return depan == null; }
    public int getJumlah() { return jumlah; }

    public void enqueue(Pegawai pegawai) {
        NodePegawai baru = new NodePegawai(pegawai);

        if (isEmpty()) {
            depan = baru;
            belakang = baru;
        } else {
            belakang.next = baru;
            belakang = baru;
        }
        jumlah++;
    }

    public Pegawai dequeue() {
        if (isEmpty()) return null;

        Pegawai hasil = depan.data;
        depan = depan.next;
        if (depan == null) {
            belakang = null;
        }
        jumlah--;
        return hasil;
    }

    public Pegawai peek() {
        return isEmpty() ? null : depan.data;
    }

    public void clear() {
        depan = null;
        belakang = null;
        jumlah = 0;
    }

    public void inputData(int n) {
        clear();
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Data Pegawai ke-" + (i + 1) + " ---");
            Pegawai p = new Pegawai();
            p.inputPegawai();
            enqueue(p);
        }
    }
    
    public void printAllPegawai() {
        System.out.println("\n\t\t\t\tDaftar Gaji Harian PT Informatika");
        System.out.println(GARIS);
        System.out.println(String.format("%-4s%-6s%-16s%-20s%-8s%-11s%-11s%-11s%-13s%-14s%-13s%-14s%-10s",
                "No", "NIP", "Nama", "Alamat", "Gol", "Datang", "Pulang", "Lama", "Jam Lembur",
                "Gaji Harian", "Lembur", "Total", "Status"));
        System.out.println(GARIS);

        NodePegawai bantu = depan;
        int no = 1;
        while (bantu != null) {
            bantu.data.printPegawai(no);
            bantu = bantu.next;
            no++;
        }

        System.out.println(GARIS);
    }
}

class Menu {
    public static void displayMenu() {
        System.out.println("\n============= MENU ===============");
        System.out.println("1. CONSTRUCTOR KONSTANTA");
        System.out.println("2. SETTER KONSTANTA");
        System.out.println("3. SETTER INPUT LUAR");
        System.out.println("4. INPUT DALAM");
        System.out.println("5. DEMO DEQUEUE (PROSES ANTRIAN)");
        System.out.println("0. KELUAR");
        System.out.println("==================================");
    }

    public static void menu() {
        int pilih = -1;
        do {
            displayMenu();
            pilih = Helper.validInputInt("Menu: ", 0, 5);
            switch (pilih) {
                case 1: {
                    int n = Helper.validInputInt("Masukkan jumlah pegawai: ", 1, 100);
                    QueuePegawai queue = new QueuePegawai();
                    for (int i = 0; i < n; i++) {
                        Pegawai p = new Pegawai("Pegawai " + (i + 1), "jalan Soetta" + (i + 1),
                                "00" + (i + 1), (i % 4) + 1, new Waktu(8, 0, 0), new Waktu(17, 15, 10));
                        queue.enqueue(p);
                    }
                    queue.printAllPegawai();
                    break;
                }
                case 2: {
                    int n = Helper.validInputInt("Masukkan jumlah pegawai: ", 1, 100);
                    QueuePegawai queue = new QueuePegawai();
                    for (int i = 0; i < n; i++) {
                        Pegawai p = new Pegawai();
                        p.setNIP("00" + (i + 1));
                        p.setNama("Pegawai " + (i + 1));
                        p.setAlamat("jalan Soetta" + (i + 1));
                        p.setGol((i % 4) + 1);
                        p.setDatang(new Waktu(8, 0, 0));
                        p.setPulang(new Waktu(17, 15, 10));
                        queue.enqueue(p);
                    }
                    queue.printAllPegawai();
                    break;
                }
                case 3: {
                    int n = Helper.validInputInt("Masukkan jumlah pegawai: ", 1, 100);
                    QueuePegawai queue = new QueuePegawai();
                    for (int i = 0; i < n; i++) {
                        System.out.println("\n--- Data Pegawai ke-" + (i + 1) + " ---");
                        Pegawai p = new Pegawai();
                        String nip = Helper.inputString("Masukkan NIP: ");
                        String nama = Helper.inputString("Masukkan Nama: ");
                        String alamat = Helper.inputString("Masukkan Alamat: ");
                        int gol = Helper.validInputInt("Masukkan Golongan (1-4): ", 1, 4);

                        p.setNIP(nip);
                        p.setNama(nama);
                        p.setAlamat(alamat);
                        p.setGol(gol);

                        System.out.println("\n--- Input Waktu Datang ---\n");
                        Waktu d = new Waktu();
                        d.inputWaktu();
                        p.setDatang(d);

                        System.out.println("\n--- Input Waktu Pulang ---\n");
                        Waktu pl = new Waktu();
                        pl.inputWaktu();
                        p.setPulang(pl);

                        queue.enqueue(p);
                    }
                    queue.printAllPegawai();
                    break;
                }
                case 4: {
                    int n = Helper.validInputInt("Masukkan jumlah pegawai: ", 1, 100);
                    QueuePegawai queue = new QueuePegawai();
                    queue.inputData(n);
                    queue.printAllPegawai();
                    break;
                }
                case 5: {
                    int n = Helper.validInputInt("Masukkan jumlah pegawai: ", 1, 100);
                    QueuePegawai queue = new QueuePegawai();
                    for (int i = 0; i < n; i++) {
                        Pegawai p = new Pegawai("Pegawai " + (i + 1), "jalan Soetta" + (i + 1),
                                "00" + (i + 1), (i % 4) + 1, new Waktu(8, 0, 0), new Waktu(17, 15, 10));
                        queue.enqueue(p);
                    }

                    System.out.println("\nAntrian awal (" + queue.getJumlah() + " pegawai):");
                    queue.printAllPegawai();

                    Pegawai depan = queue.peek();
                    if (depan != null) {
                        System.out.println("\nPEEK (paling depan): " + depan.getNama());
                    }

                    int k = Helper.validInputInt("Berapa pegawai yang akan di-dequeue? ", 0, n);
                    for (int i = 0; i < k; i++) {
                        Pegawai keluar = queue.dequeue();
                        if (keluar != null) {
                            System.out.println("DEQUEUE: " + keluar.getNama() + " (NIP " + keluar.getNIP() + ")");
                        }
                    }

                    if (queue.isEmpty()) {
                        System.out.println("\nAntrian sudah kosong.");
                    } else {
                        System.out.println("\nSisa antrian (" + queue.getJumlah() + " pegawai):");
                        queue.printAllPegawai();
                    }
                    break;
                }
                case 0: {
                    System.out.println("Terima kasih...");
                    break;
                }
            }
        } while (pilih != 0);
    }
}

public class GajiPegawai {
    public static void main(String[] args) {
        Menu.menu();
    }
}