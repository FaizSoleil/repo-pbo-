/*
 *    Nama File    : parkir.java
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 10 November 2026
 *    Deskripsi    : Program untuk mengolah array parkir menggunakan inheritance dan polimorfisme
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Helper {
    private static final Scanner INPUT = new Scanner(System.in);

    static int valid_input_int(String pesan, int min, int maks) {
        while (true) {
            System.out.print(pesan);
            // Membaca satu baris penuh mencegah newline tersisa mengganggu input berikutnya.
            String temp = INPUT.nextLine();

            try {
                int angka = Integer.parseInt(temp);
                if (angka < min || angka > maks) {
                    System.out.println("Input harus angka " + min + " - " + maks + "!!");
                    continue;
                }
                return angka;
            } catch (NumberFormatException e) {
                System.out.println("Input harus sebuah integer!!");
            }
        }
    }

    static String format_ribuan(long angka) {
        String hasil = Long.toString(angka);
        int posisi = hasil.length() - 3;
        while (posisi > 0) {
            hasil = hasil.substring(0, posisi) + "." + hasil.substring(posisi);
            posisi -= 3;
        }
        return hasil;
    }

    static String buat_garis(int panjang) {
        StringBuilder garis = new StringBuilder(panjang);
        for (int i = 0; i < panjang; i++) {
            garis.append('-');
        }
        return garis.toString();
    }

    static String input_string() {
        return input_string("");
    }

    static String input_string(String message) {
        while (true) {
            System.out.print(message);
            String hasil = INPUT.nextLine();
            if (!hasil.isEmpty()) {
                return hasil;
            }
            System.out.println("Input tidak boleh kosong!!");
        }
    }
}

class Waktu {
    private int jam;
    private int menit;
    private int detik;

    Waktu() {
        this.jam = 0;
        this.menit = 0;
        this.detik = 0;
    }

    Waktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    Waktu(Waktu waktu) {
        this(waktu.jam, waktu.menit, waktu.detik);
    }

    void set_waktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    void set_jam(int jam) {
        this.jam = jam;
    }

    void set_menit(int menit) {
        this.menit = menit;
    }

    void set_detik(int detik) {
        this.detik = detik;
    }

    int get_jam() {
        return jam;
    }

    int get_menit() {
        return menit;
    }

    int get_detik() {
        return detik;
    }

    void input_waktu() {
        jam = Helper.valid_input_int("Masukkan jam: ", 0, 23);
        menit = Helper.valid_input_int("Masukkan menit: ", 0, 59);
        detik = Helper.valid_input_int("Masukkan detik: ", 0, 59);
    }

    void print_waktu() {
        System.out.printf("%02d:%02d:%02d", jam, menit, detik);
    }

    Waktu jarak_waktu(Waktu pulang) {
        int awal = jam * 3600 + menit * 60 + detik;
        int akhir = pulang.get_jam() * 3600
                + pulang.get_menit() * 60
                + pulang.get_detik();

        // Jika jam pulang lebih kecil, waktu pulang dianggap pada hari berikutnya.
        if (akhir < awal) {
            akhir += 24 * 3600;
        }

        int selisih = Math.abs(akhir - awal);
        int hasilJam = selisih / 3600;
        selisih %= 3600;
        int hasilMenit = selisih / 60;
        selisih %= 60;
        return new Waktu(hasilJam, hasilMenit, selisih);
    }
}

class Person {
    protected String nama;
    protected String alamat;

    Person() {
        this("", "");
    }

    Person(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    boolean is_pegawai() {
        return false;
    }

    void set_person(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    void set_nama(String nama) {
        this.nama = nama;
    }

    void set_alamat(String alamat) {
        this.alamat = alamat;
    }

    String get_nama() {
        return nama;
    }

    String get_alamat() {
        return alamat;
    }

    void input() {
        nama = Helper.input_string("Masukkan Nama: ");
        alamat = Helper.input_string("Masukkan Alamat: ");
    }

    void output() {
        System.out.print(nama + alamat);
    }
}

// Pewarisan di Java menggunakan extends; setter dan getter Person ikut diwarisi Pegawai.
class Pegawai extends Person {
    private String nip;
    private int gol;
    private Waktu datang;
    private Waktu pulang;

    Pegawai() {
        this("", "", "", 0, new Waktu(), new Waktu());
    }

    Pegawai(String nama, String alamat, String nip, int gol, Waktu datang, Waktu pulang) {
        super(nama, alamat);
        this.nip = nip;
        this.gol = gol;
        this.datang = new Waktu(datang);
        this.pulang = new Waktu(pulang);
    }

    @Override
    boolean is_pegawai() {
        return true;
    }

    void set_pegawai(String nama, String alamat, String nip, int gol, Waktu datang, Waktu pulang) {
        set_person(nama, alamat);
        this.nip = nip;
        this.gol = gol;
        this.datang = new Waktu(datang);
        this.pulang = new Waktu(pulang);
    }

    void set_nip(String nip) {
        this.nip = nip;
    }

    void set_gol(int gol) {
        this.gol = gol;
    }

    void set_datang(Waktu datang) {
        this.datang = new Waktu(datang);
    }

    void set_pulang(Waktu pulang) {
        this.pulang = new Waktu(pulang);
    }

    String get_nip() {
        return nip;
    }

    int get_gol() {
        return gol;
    }

    Waktu get_datang() {
        return new Waktu(datang);
    }

    Waktu get_pulang() {
        return new Waktu(pulang);
    }

    void input_pegawai() {
        input();
        nip = Helper.input_string("Masukkan NIP: ");
        gol = Helper.valid_input_int("Masukkan Golongan (1-4): ", 1, 4);

        System.out.println("\n--- Input Waktu Datang ---");
        datang.input_waktu();
        System.out.println("\n--- Input Waktu Pulang ---");
        pulang.input_waktu();
    }

    Waktu get_lama_kerja() {
        return datang.jarak_waktu(pulang);
    }

    Waktu get_waktu_lembur() {
        Waktu lembur = get_lama_kerja();
        if (lembur.get_jam() > 8) {
            lembur.set_jam(lembur.get_jam() - 8);
        } else {
            lembur.set_waktu(0, 0, 0);
        }
        return lembur;
    }

    int get_gaji_harian() {
        switch (gol) {
            case 1:
                return 150000;
            case 2:
                return 200000;
            case 3:
                return 400000;
            case 4:
                return 500000;
            default:
                return 0;
        }
    }

    int get_biaya_lembur() {
        switch (gol) {
            case 1:
                return 50000;
            case 2:
                return 75000;
            case 3:
                return 150000;
            case 4:
                return 200000;
            default:
                return 0;
        }
    }

    int get_uang_lembur_total() {
        return get_waktu_lembur().get_jam() * get_biaya_lembur();
    }

    int get_total_gaji() {
        return get_gaji_harian() + get_uang_lembur_total();
    }

    String get_status() {
        return get_lama_kerja().get_jam() < 8 ? "peringatan" : "ok";
    }

    void print_pegawai(int no) {
        System.out.printf("%-4s%-6s%-16s%-20s%-8d", no + ".", get_nip(), get_nama(), get_alamat(), get_gol());
        get_datang().print_waktu();
        System.out.print("   ");
        get_pulang().print_waktu();
        System.out.print("   ");
        get_lama_kerja().print_waktu();
        System.out.print("     ");
        get_waktu_lembur().print_waktu();
        System.out.printf("     %-14s%-13s%-14s%-10s%n",
                Helper.format_ribuan(get_gaji_harian()),
                Helper.format_ribuan(get_uang_lembur_total()),
                Helper.format_ribuan(get_total_gaji()),
                get_status());
    }
}

class ArrayPegawai {
    private static final int MAX = 100;
    private static final String GARIS = Helper.buat_garis(152);
    private final List<Pegawai> data = new ArrayList<>();

    ArrayPegawai() {
    }

    ArrayPegawai(int kapasitas) {
        if (kapasitas < 0 || kapasitas > MAX) {
            throw new IllegalArgumentException("Kapasitas harus 0 - " + MAX);
        }
    }

    void tambah_pegawai(Pegawai pegawai) {
        if (data.size() < MAX) {
            data.add(pegawai);
        }
    }

    Pegawai get_data(int index) {
        return data.get(index);
    }

    int get_jumlah() {
        return data.size();
    }

    void input_data(int n) {
        if (n < 0 || n > MAX) {
            throw new IllegalArgumentException("Jumlah pegawai harus 0 - " + MAX);
        }
        data.clear();
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Data Pegawai ke-" + (i + 1) + " ---");
            Pegawai pegawai = new Pegawai();
            pegawai.input_pegawai();
            tambah_pegawai(pegawai);
        }
    }

    void print_all_pegawai() {
        System.out.println("\n\t\t\t\tDaftar Gaji Harian PT Informatika");
        System.out.println(GARIS);
        System.out.printf("%-4s%-6s%-16s%-20s%-8s%-11s%-11s%-13s%-13s%-14s%-13s%-14s%-10s%n",
                "No", "NIP", "Nama", "Alamat", "Gol", "Datang", "Pulang", "Lama", "Jam Lembur",
                "Gaji Harian", "Lembur", "Total", "Status");
        System.out.println(GARIS);
        for (int i = 0; i < data.size(); i++) {
            data.get(i).print_pegawai(i + 1);
        }
        System.out.println(GARIS);
    }
}

abstract class Kendaraan {
    protected int nomor_polisi;
    // pemilik == null menandakan kendaraan anonim.
    protected Person pemilik;
    protected Waktu datang;
    protected Waktu pulang;

    Kendaraan(int nomor_polisi, Person pemilik, Waktu datang, Waktu pulang) {
        this.nomor_polisi = nomor_polisi;
        this.pemilik = pemilik;
        this.datang = new Waktu(datang);
        this.pulang = new Waktu(pulang);
    }

    protected boolean adalah_pegawai() {
        return pemilik != null && pemilik.is_pegawai();
    }

    protected int get_jam_tagihan() {
        Waktu lama = datang.jarak_waktu(pulang);
        int detik = lama.get_jam() * 3600 + lama.get_menit() * 60 + lama.get_detik();
        // Tagihan minimal satu jam; kelebihan menit atau detik dibulatkan ke atas.
        return Math.max(1, (detik + 3599) / 3600);
    }

    protected int biaya_per_jam(int tarif) {
        return get_jam_tagihan() * tarif;
    }

    int get_nomor_polisi() {
        return nomor_polisi;
    }

    Person get_pemilik() {
        return pemilik;
    }

    void ganti_pemilik(Person pemilik_baru) {
        pemilik = pemilik_baru;
    }

    String get_nama_pemilik() {
        return pemilik == null ? "null" : pemilik.get_nama();
    }

    String get_status() {
        if (adalah_pegawai()) {
            return "pegawai";
        }
        return pemilik == null ? "umum (anonim)" : "umum";
    }

    // Setiap subclass menyediakan implementasi melalui polymorphism.
    abstract int hitung_biaya();

    abstract String get_jenis();

    abstract String get_detail();

    void print_kendaraan(int no) {
        System.out.printf("%-4s%-8s%-9d%-16s%-16s%-12s",
                no + ".", get_jenis(), get_nomor_polisi(), get_nama_pemilik(), get_status(), get_detail());
        datang.print_waktu();
        System.out.print("   ");
        pulang.print_waktu();
        System.out.print("   ");
        datang.jarak_waktu(pulang).print_waktu();
        System.out.printf("   %-12s%n", Helper.format_ribuan(hitung_biaya()));
    }
}

class Mobil extends Kendaraan {
    private int jumlah_pintu;

    Mobil(int nomor_polisi, Person pemilik, Waktu datang, Waktu pulang, int jumlah_pintu) {
        super(nomor_polisi, pemilik, datang, pulang);
        this.jumlah_pintu = jumlah_pintu;
    }

    int get_jumlah_pintu() {
        return jumlah_pintu;
    }

    void set_jumlah_pintu(int jumlah_pintu) {
        this.jumlah_pintu = jumlah_pintu;
    }

    @Override
    int hitung_biaya() {
        // Mobil pegawai dikenai tarif harian; mobil umum dikenai tarif per jam.
        return adalah_pegawai() ? 6000 : biaya_per_jam(3000);
    }

    @Override
    String get_jenis() {
        return "Mobil";
    }

    @Override
    String get_detail() {
        return "Pintu: " + jumlah_pintu;
    }
}

class Truk extends Kendaraan {
    private int jumlah_roda;

    Truk(int nomor_polisi, Person pemilik, Waktu datang, Waktu pulang, int jumlah_roda) {
        super(nomor_polisi, pemilik, datang, pulang);
        this.jumlah_roda = jumlah_roda;
    }

    int get_jumlah_roda() {
        return jumlah_roda;
    }

    void set_jumlah_roda(int jumlah_roda) {
        this.jumlah_roda = jumlah_roda;
    }

    @Override
    int hitung_biaya() {
        // Tarif truk berlaku per jam, termasuk untuk pegawai.
        return biaya_per_jam(5000);
    }

    @Override
    String get_jenis() {
        return "Truk";
    }

    @Override
    String get_detail() {
        return "Roda: " + jumlah_roda;
    }
}

class Motor extends Kendaraan {
    private int jumlah_tak;

    Motor(int nomor_polisi, Person pemilik, Waktu datang, Waktu pulang, int jumlah_tak) {
        super(nomor_polisi, pemilik, datang, pulang);
        this.jumlah_tak = jumlah_tak;
    }

    int get_jumlah_tak() {
        return jumlah_tak;
    }

    void set_jumlah_tak(int jumlah_tak) {
        this.jumlah_tak = jumlah_tak;
    }

    @Override
    int hitung_biaya() {
        // Motor pegawai dikenai tarif harian; motor umum dikenai tarif per jam.
        return adalah_pegawai() ? 3000 : biaya_per_jam(2000);
    }

    @Override
    String get_jenis() {
        return "Motor";
    }

    @Override
    String get_detail() {
        return "Tak: " + jumlah_tak;
    }
}

class ArrayParkir {
    private static final String GARIS = Helper.buat_garis(110);
    // Koleksi menyimpan referensi polymorphic; Java mengelola memori objek secara otomatis.
    private final List<Kendaraan> data = new ArrayList<>();

    void tambah_kendaraan(Kendaraan kendaraan) {
        data.add(kendaraan);
    }

    Kendaraan get_kendaraan(int index) {
        if (index < 0 || index >= data.size()) {
            return null;
        }
        return data.get(index);
    }

    int get_jumlah() {
        return data.size();
    }

    void print_all_parkir() {
        System.out.println("\n\t\t\t\tLaporan Parkir Gedung");
        System.out.println(GARIS);
        System.out.printf("%-4s%-8s%-9s%-16s%-16s%-12s%-11s%-11s%-11s%-12s%n",
                "No", "Jenis", "No.Pol", "Pemilik", "Status", "Detail",
                "Datang", "Pulang", "Lama", "Biaya");
        System.out.println(GARIS);

        for (int i = 0; i < data.size(); i++) {
            // Satu loop memanggil implementasi cetak dari tipe kendaraan masing-masing.
            data.get(i).print_kendaraan(i + 1);
        }
        System.out.println(GARIS);
    }
}

class MenuParkir {
    private static void display_menu() {
        System.out.println("\n========== MENU PARKIR =========="
                + "\n1. Tambah kendaraan (Input dalam)"
                + "\n2. Tambah kendaraan (Input luar)"
                + "\n3. Tampilkan laporan parkir"
                + "\n4. Ganti pemilik kendaraan"
                + "\n5. Isi data contoh"
                + "\n0. Keluar"
                + "\n=================================");
    }

    private static Person pilih_pemilik(List<Person> list_orang) {
        System.out.println("\n--- Pilih Pemilik ---");
        System.out.println("0. Anonim (tanpa identitas)");
        for (int i = 0; i < list_orang.size(); i++) {
            Person orang = list_orang.get(i);
            System.out.println((i + 1) + ". " + orang.get_nama()
                    + " (" + (orang.is_pegawai() ? "pegawai" : "umum") + ")"); // operator ternary
        }

        int pilihan = Helper.valid_input_int("Pemilik: ", 0, list_orang.size());
        return pilihan == 0 ? null : list_orang.get(pilihan - 1);
    }

    private static Kendaraan buat_kendaraan(List<Person> list_orang) {
        System.out.println("\n1. Mobil\n2. Truk\n3. Motor");
        int jenis = Helper.valid_input_int("Jenis kendaraan: ", 1, 3);
        int plat = Helper.valid_input_int("Nomor polisi (angka): ", 1, 999999);
        Person pemilik = pilih_pemilik(list_orang);

        System.out.println("\n--- Waktu Datang ---");
        Waktu datang = new Waktu();
        datang.input_waktu();
        System.out.println("\n--- Waktu Pulang ---");
        Waktu pulang = new Waktu();
        pulang.input_waktu();

        switch (jenis) {
            case 1:
                int pintu = Helper.valid_input_int("Jumlah pintu: ", 2, 6);
                return new Mobil(plat, pemilik, datang, pulang, pintu);
            case 2:
                int roda = Helper.valid_input_int("Jumlah roda: ", 4, 18);
                return new Truk(plat, pemilik, datang, pulang, roda);
            case 3:
                int tak = Helper.valid_input_int("Jumlah tak (2/4): ", 2, 4);
                return new Motor(plat, pemilik, datang, pulang, tak);
            default:
                throw new IllegalStateException("Jenis kendaraan tidak dikenal.");
        }
    }

    private static void isi_contoh(ArrayParkir parkir, List<Person> list_orang) {
        // Urutan pemilik: Budi, Ani, Siti, lalu Joko.
        parkir.tambah_kendaraan(new Mobil(1234, list_orang.get(2),
                new Waktu(8, 0, 0), new Waktu(17, 30, 0), 4));
        parkir.tambah_kendaraan(new Motor(5678, list_orang.get(3),
                new Waktu(8, 0, 0), new Waktu(16, 0, 0), 2));
        parkir.tambah_kendaraan(new Truk(9012, list_orang.get(2),
                new Waktu(7, 0, 0), new Waktu(10, 10, 0), 6));
        parkir.tambah_kendaraan(new Mobil(3456, list_orang.get(0),
                new Waktu(9, 0, 0), new Waktu(11, 0, 0), 4));
        parkir.tambah_kendaraan(new Motor(7890, null,
                new Waktu(10, 0, 0), new Waktu(10, 20, 0), 2));
        parkir.tambah_kendaraan(new Mobil(1111, null,
                new Waktu(22, 0, 0), new Waktu(2, 30, 0), 4));
        System.out.println("6 data contoh ditambahkan.");
    }

    static void menu() {
        // Kendaraan menyimpan referensi pemilik, sehingga daftar pemilik dibuat lebih dahulu.
        Person budi = new Person("Budi", "Jl. Merdeka");
        Person ani = new Person("Ani", "Jl. Sudirman");
        Pegawai siti = new Pegawai("Siti", "Jl. Soetta", "001", 2,
                new Waktu(8, 0, 0), new Waktu(17, 0, 0));
        Pegawai joko = new Pegawai("Joko", "Jl. Diponegoro", "002", 3,
                new Waktu(8, 0, 0), new Waktu(17, 0, 0));
        List<Person> list_orang = List.of(budi, ani, siti, joko);

        ArrayParkir parkir = new ArrayParkir();
        int pilihan;
        do {
            display_menu();
            pilihan = Helper.valid_input_int("Menu: ", 0, 5);

            switch (pilihan) {
                case 1:
                    parkir.tambah_kendaraan(buat_kendaraan(list_orang));
                    System.out.println("Kendaraan ditambahkan.");
                    break;
                case 2: {
                    // Alur input ditulis di menu, berbeda dari opsi 1 yang memakai buat_kendaraan().
                    System.out.println("\n1. Mobil\n2. Truk\n3. Motor");
                    int jenis = Helper.valid_input_int("Jenis kendaraan: ", 1, 3);
                    int plat = Helper.valid_input_int("Nomor polisi (angka): ", 1, 999999);
                    Person pemilik = pilih_pemilik(list_orang);

                    System.out.println("\n--- Waktu Datang ---");
                    Waktu datang = new Waktu();
                    datang.input_waktu();
                    System.out.println("\n--- Waktu Pulang ---");
                    Waktu pulang = new Waktu();
                    pulang.input_waktu();

                    Kendaraan kendaraan;
                    switch (jenis) {
                        case 1: {
                            int pintu = Helper.valid_input_int("Jumlah pintu: ", 2, 6);
                            kendaraan = new Mobil(plat, pemilik, datang, pulang, pintu);
                            break;
                        }
                        case 2: {
                            int roda = Helper.valid_input_int("Jumlah roda: ", 4, 18);
                            kendaraan = new Truk(plat, pemilik, datang, pulang, roda);
                            break;
                        }
                        case 3: {
                            int tak = Helper.valid_input_int("Jumlah tak (2/4): ", 2, 4);
                            kendaraan = new Motor(plat, pemilik, datang, pulang, tak);
                            break;
                        }
                        default:
                            throw new IllegalStateException("Jenis kendaraan tidak dikenal.");
                    }
                    parkir.tambah_kendaraan(kendaraan);
                    System.out.println("Kendaraan ditambahkan.");
                    break;
                }
                case 3:
                    if (parkir.get_jumlah() == 0) {
                        System.out.println("Belum ada data.");
                    } else {
                        parkir.print_all_parkir();
                    }
                    break;
                case 4:
                    if (parkir.get_jumlah() == 0) {
                        System.out.println("Belum ada data.");
                        break;
                    }
                    parkir.print_all_parkir();
                    int nomor = Helper.valid_input_int("Nomor kendaraan: ", 1, parkir.get_jumlah());
                    Kendaraan kendaraan = parkir.get_kendaraan(nomor - 1);
                    if (kendaraan == null) {
                        throw new IllegalStateException("Kendaraan tidak ditemukan.");
                    }
                    kendaraan.ganti_pemilik(pilih_pemilik(list_orang));
                    System.out.println("Pemilik diganti.");
                    break;
                case 5:
                    isi_contoh(parkir, list_orang);
                    break;
                case 0:
                    System.out.println("Terima kasih...");
                    break;
                default:
                    throw new IllegalStateException("Pilihan menu tidak dikenal.");
            }
        } while (pilihan != 0);
    }
}

public class parkir {
    public static void main(String[] args) {
        MenuParkir.menu();
    }
}