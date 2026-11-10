/***********************************************************
 *    Nama File    : gaji_pegawai.cpp
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 21 Oktober 2026
 *    Deskripsi    : Program untuk mengolah array data pegawai dan implementasi inheritence
 ***********************************************************/

#include <iostream>
#include <iomanip>
#include <string>
#include <vector>

class Helper {
public:
    static int valid_input_int(std::string pesan, int min, int maks) {
        int angka = 0;
        bool valid = false;

        do {
            std::string temp;
            std::cout << pesan;
            std::getline(std::cin, temp); //biar konsumsi newline konsisten
                
            if (!temp.empty()) //biar gak throw invalid argument di stoi
            {

                try
                {
                    angka = stoi(temp); //kalo kosong invalid argument

                    if(angka < min || angka > maks) {
                        std::cout << "Input harus angka " << min << " - " << maks << "!!\n";
                        continue;
                    }

                    valid = true;
                }
                catch(const std::exception& e)
                {
                    std::cout << "Input harus sebuah integer!!\n";
                    std::cin.clear();
                    std::cin.ignore(400000, '\n');
                }
            } else { continue; }
        } while (!valid);

        return angka;
    }

    static std::string format_ribuan(long long n) {
        std::string s = std::to_string(n);
        int insert = s.length() - 3;
        while (insert > 0) {
            s.insert(insert, ".");
            insert -= 3;
        }
        return s;
    }

    static std::string input_string(std::string message=""){
        std::string hasil;
        // std::cin.clear();
        // std::cin.ignore(40000, '\n'); clear dan ignore lebih baik di luar logika input biar gak double input.

        while (true)
        {
            std::cout << message;

            std::getline(std::cin, hasil);

            if(!hasil.empty()) break;

            std::cout << "Input tidak boleh kosong!!";
        }

        return hasil;
    }

    static void clear_buffer() {
        std::cin.clear();
        std::cin.ignore(40000, '\n');
    }
};

class Waktu {
private:
    int jam;
    int menit;
    int detik;

public:
    Waktu() {
        this->jam = 0;
        this->menit = 0;
        this->detik = 0;
    }

    Waktu(int jam, int menit, int detik) {
        this->jam = jam;
        this->menit = menit;
        this->detik = detik;
    }

    void set_waktu(int jam, int menit, int detik) {
        this->jam = jam;
        this->menit = menit;
        this->detik = detik;
    }

    void set_jam(int jam) { this->jam = jam; }
    void set_menit(int menit) { this->menit = menit; }
    void set_detik(int detik) { this->detik = detik; }

    int get_jam() { return this->jam; }
    int get_menit() { return this->menit; }
    int get_detik() { return this->detik; }

    void input_waktu() {
        this->jam = Helper::valid_input_int("Masukkan jam: ", 0, 23);
        this->menit = Helper::valid_input_int("Masukkan menit: ", 0, 59);
        this->detik = Helper::valid_input_int("Masukkan detik: ", 0, 59);
    }

    void print_waktu() {
        std::cout << std::right << std::setfill('0') << std::setw(2) << this->jam << ":"
            << std::setfill('0') << std::setw(2) << this->menit << ":"
            << std::setfill('0') << std::setw(2) << this->detik << std::setfill(' ');
    }

    Waktu jarak_waktu(Waktu pulang) {
        Waktu hasil;
        int awal = (this->jam * 3600) + (this->menit * 60) + (this->detik);
        int akhir = (pulang.get_jam() * 3600) + (pulang.get_menit() * 60) + pulang.get_detik();

        if (akhir < awal) {
            akhir += (24 * 3600);
        }

        int selisih = abs(akhir - awal);

        hasil.set_jam(selisih / 3600);
        selisih %= 3600;
        hasil.set_menit(selisih / 60);
        selisih %= 60;
        hasil.set_detik(selisih);

        return hasil;
    }
};

class Person {
public:
    Person(){
        this->nama = "";
        this->alamat = "";
    }
    virtual ~Person() {}
    virtual bool is_pegawai() { return false; }

protected:
    std::string nama;
    std::string alamat;
    std::string nik;

public:
    Person(std::string nama, std::string alamat){
        this->nama = nama;
        this->alamat = alamat;
    }

    void set_person(std::string nama, std::string alamat){
        this->nama = nama;
        this->alamat = alamat;
    }

    void set_nama(std::string nama){ this->nama = nama; }
    void set_alamat(std::string alamat){ this->alamat = alamat; }

    std::string get_nama(){ return this->nama; }
    std::string get_alamat(){ return this->alamat; }

    void input(){
        this->nama = Helper::input_string("Masukkan Nama: ");
        this->alamat = Helper::input_string("Masukkan Alamat: ");
    }

    void output(){
        std::cout << this->nama << this->alamat;
    }
};

// Penggunaa public agar fungsi dan atribut class parent bisa digunakan oleh class child
// Penggunaan ": public" pada cpp mirip seperti extends pada java
class Pegawai : public Person{
private:
    std::string nip;
    int gol;
    Waktu datang;
    Waktu pulang;

public:
    Pegawai() : Person() {
        this->nip = "";
        this->gol = 0;
        this->datang;
        this->pulang;
    }

    Pegawai(std::string nama, std::string alamat, std::string nip, int gol, Waktu datang, Waktu pulang)
        : Person(nama, alamat){
        this->nip = nip;
        this->gol = gol;
        this->datang = datang;
        this->pulang = pulang;
    }

    bool is_pegawai() override { return true; }

    void set_pegawai(std::string nama, std::string alamat, std::string nip, int gol, Waktu datang, Waktu pulang) {
        Person::set_person(nama, alamat);
        this->nip = nip;
        this->gol = gol;
        this->datang = datang;
        this->pulang = pulang;
    }

    // setter dan getter dari class Parent akan otomatis diwarisi oleh class Child
    void set_nip(std::string nip) { this->nip = nip; }
    void set_gol(int gol) { this->gol = gol; }
    void set_datang(Waktu datang) { this->datang = datang; }
    void set_pulang(Waktu pulang) { this->pulang = pulang; }

    std::string get_nip() { return this->nip; }
    int get_gol() { return this->gol; }
    Waktu get_datang() { return this->datang; }
    Waktu get_pulang() { return this->pulang; }

    void input_pegawai() {
        Person::input();

        std::cout << "Masukkan NIP: ";
        this->nip = Helper::input_string("Masukkan NIP: ");

        gol = Helper::valid_input_int("Masukkan Golongan (1-4): ", 1, 4);

        std::cout << "\n--- Input Waktu Datang ---" << '\n';
        datang.input_waktu();

        std::cout << "\n--- Input Waktu Pulang ---" << '\n';
        pulang.input_waktu();
    }

    Waktu get_lama_kerja() {
        return datang.jarak_waktu(this->pulang);
    }

    Waktu get_waktu_lembur() {
        Waktu lembur = get_lama_kerja();
        if (lembur.get_jam() > 8){
            lembur.set_jam(lembur.get_jam() - 8);
        } else {
            lembur.set_waktu(0,0,0);
        }
        return lembur;
    }

    int get_gaji_harian() {
        int gaji = 0;
        switch (gol) {
            case 1: gaji = 150000; break;
            case 2: gaji = 200000; break;
            case 3: gaji = 400000; break;
            case 4: gaji = 500000; break;
        }
        return gaji;
    }

    int get_biaya_lembur() {
        int bayaran = 0;
        switch (gol) {
            case 1: bayaran = 50000; break;
            case 2: bayaran = 75000; break;
            case 3: bayaran = 150000; break;
            case 4: bayaran = 200000; break;
        }
        return bayaran;
    }

    int get_uang_lembur_total() {
        return get_waktu_lembur().get_jam() * get_biaya_lembur();
    }

    int get_total_gaji() {
        return get_gaji_harian() + get_uang_lembur_total();
    }

    std::string get_status() {
        if (get_lama_kerja().get_jam() < 8) {
            return "peringatan";
        } else {
            return "ok";
        }
    }

    void print_pegawai(int no) {
        std::cout << std::left << std::setw(4) << (std::to_string(no) + ".") << std::setw(6) << get_nip() << std::setw(16) << get_nama() << std::setw(20) << get_alamat() << std::setw(8) << get_gol();
        get_datang().print_waktu();
        std::cout << "   ";
        get_pulang().print_waktu();
        std::cout << "   ";
        get_lama_kerja().print_waktu();
        std::cout << "     ";
        get_waktu_lembur().print_waktu();
        std::cout << "     ";
        std::cout << std::left << std::setw(14) << Helper::format_ribuan(get_gaji_harian())
            << std::setw(13) << Helper::format_ribuan(get_uang_lembur_total())
            << std::setw(14) << Helper::format_ribuan(get_total_gaji())
            << std::setw(10) << get_status() << '\n';
    }
};

class ArrayPegawai {
    std::string GARIS = std::string(152, '-');

    private:
        static const int MAX = 100;

        Pegawai data[MAX];
        int kapasitas;

    public:
        ArrayPegawai() {
            this->kapasitas = 0;
            this->data[MAX];
        }

        ArrayPegawai(int kapasitas) {
            this->kapasitas = kapasitas;
            this->data[this->kapasitas];
        }

        void tambah_pegawai(Pegawai pegawai) {
            if (this->kapasitas < this->MAX) {
                this->data[this->kapasitas] = pegawai;
                this->kapasitas++;
            }
        }

        Pegawai get_data() {
            return this->data[kapasitas];
        }

        int get_jumlah() {
            return this->kapasitas;
        }

        void input_data(int n) {
            this->kapasitas = 0;
            for (int i = 0; i < n; i++) {
                std::cout << "\n--- Data Pegawai ke-" << i + 1 << " ---" << '\n';
                Pegawai p;
                p.input_pegawai();
                tambah_pegawai(p);
            }
        }

        void print_all_pegawai() {
            std::cout << "\n\t\t\t\tDaftar Gaji Harian PT Informatika\n";
            std::cout << GARIS << '\n';
            std::cout << std::left << std::setw(4) << "No" << std::setw(6) << "NIP" << std::setw(16) << "Nama" << std::setw(20) << "Alamat" << std::setw(8) << "Gol"
                << std::setw(11) << "Datang" << std::setw(11) << "Pulang" << std::setw(13) << "Lama" << std::setw(13) << "Jam Lembur"
                << std::setw(14) << "Gaji Harian" << std::setw(13) << "Lembur" << std::setw(14) << "Total" << std::setw(10) << "Status" << '\n';
            std::cout << GARIS << '\n';

            for (int i = 0; i < this->kapasitas; i++) {
                data[i].print_pegawai(i + 1);
            }

            std::cout << GARIS << '\n';
        }
};

class Kendaraan {
protected:
    int nomor_polisi;
    Person* pemilik;    // nullptr = anonim maka tidak dimiliki sama Kendaraan.
    Waktu datang;   // see note 1 below
    Waktu pulang;

    bool adalah_pegawai() {
        return pemilik != nullptr && pemilik->is_pegawai();
    }

    // minimum 1 jam, kalau kurang bulatkan ke atas
    int get_jam_tagihan() {
        Waktu lama = datang.jarak_waktu(pulang);
        int detik = lama.get_jam() * 3600 + lama.get_menit() * 60 + lama.get_detik();
        int jam = (detik + 3599) / 3600;
        if (jam < 1) jam = 1;
        return jam;
    }

    int biaya_per_jam(int tarif) { return get_jam_tagihan() * tarif; }

public:
        Kendaraan(int nomor_polisi, Person* pemilik, Waktu datang, Waktu pulang) {
            this->nomor_polisi = nomor_polisi;
            this->pemilik = pemilik;
            this->datang = datang;
            this->pulang = pulang;
        }
        virtual ~Kendaraan() {}

        int get_nomor_polisi() { return nomor_polisi; }
        Person* get_pemilik() { return pemilik; }
        void ganti_pemilik(Person* pemilik_baru) { this->pemilik = pemilik_baru; }

        std::string get_nama_pemilik() {
            return pemilik == nullptr ? "null" : pemilik->get_nama();
        }

        std::string get_status() {
            if (adalah_pegawai()) return "pegawai";
            if (pemilik == nullptr) return "umum (anonim)";
            return "umum";
        }

        // virtual xxx fungsi() = 0 menandakan abstract function()
        virtual int hitung_biaya() = 0;
        virtual std::string get_jenis() = 0;
        virtual std::string get_detail() = 0;

        void print_kendaraan(int no) {
            std::cout << std::left << std::setw(4) << (std::to_string(no) + ".")
                << std::setw(8) << get_jenis()
                << std::setw(9) << get_nomor_polisi()
                << std::setw(16) << get_nama_pemilik()
                << std::setw(16) << get_status()
                << std::setw(12) << get_detail();
            datang.print_waktu();
            std::cout << "   ";
            pulang.print_waktu();
            std::cout << "   ";
            datang.jarak_waktu(pulang).print_waktu();
            std::cout << "   " << std::left << std::setw(12) << Helper::format_ribuan(hitung_biaya()) << '\n';
        }
};

class Mobil : public Kendaraan {
private:
    int jumlah_pintu;
public:
    Mobil(int nomor_polisi, Person* pemilik, Waktu datang, Waktu pulang, int jumlah_pintu)
    : Kendaraan(nomor_polisi, pemilik, datang, pulang) {
    this->jumlah_pintu = jumlah_pintu;
    }
    int get_jumlah_pintu() { return jumlah_pintu; }
    void set_jumlah_pintu(int j) { jumlah_pintu = j; }

    int hitung_biaya() override {
        if (adalah_pegawai()) return 6000;  // per hari
            return biaya_per_jam(3000); // per jam
    }
    std::string get_jenis() override { return "Mobil"; }
    std::string get_detail() override { return "Pintu: " + std::to_string(jumlah_pintu); }
};

class Truk : public Kendaraan {
private:
    int jumlah_roda;
public:
    Truk(int nomor_polisi, Person* pemilik, Waktu datang, Waktu pulang, int jumlah_roda)
    : Kendaraan(nomor_polisi, pemilik, datang, pulang) {
    this->jumlah_roda = jumlah_roda;
    }
    int get_jumlah_roda() { return jumlah_roda; }
    void set_jumlah_roda(int j) { jumlah_roda = j; }

    int hitung_biaya() override {
        return biaya_per_jam(5000); // employees bayar biaya yang person juga
    }
    std::string get_jenis() override { return "Truk"; }
    std::string get_detail() override { return "Roda: " + std::to_string(jumlah_roda); }
};

class Motor : public Kendaraan {
private:
    int jumlah_tak;
public:
    Motor(int nomor_polisi, Person* pemilik, Waktu datang, Waktu pulang, int jumlah_tak)
        : Kendaraan(nomor_polisi, pemilik, datang, pulang) {
        this->jumlah_tak = jumlah_tak;
    }
    int get_jumlah_tak() { return jumlah_tak; }
    void set_jumlah_tak(int j) { jumlah_tak = j; }

    int hitung_biaya() override {
        if (adalah_pegawai()) return 3000;  // per hari
        return biaya_per_jam(2000); // per jam
    }
    std::string get_jenis() override { return "Motor"; }
    std::string get_detail() override { return "Tak: " + std::to_string(jumlah_tak); }
};

class ArrayParkir {
private:
    std::string GARIS = std::string(110, '-');
    std::vector<Kendaraan*> data;

public:
    ArrayParkir() {}

    // Destructor agar tidak ada memory leak
    ~ArrayParkir() {
        for (Kendaraan* k : data) delete k;
    }
    ArrayParkir(const ArrayParkir&) = delete; //copy constructor-nya disable
    ArrayParkir& operator=(const ArrayParkir&) = delete; //copy assignment operator-nya disable
        
    //maksudnya dijelasin di bawah

    Kendaraan* get_kendaraan(int index) {
        if (index < 0 || index >= (int)data.size()) return nullptr;
        return data[index];
    }

    void tambah_kendaraan(Kendaraan* k) { data.push_back(k); }
    int get_jumlah() { return data.size(); }

    void print_all_parkir() {
        std::cout << "\n\t\t\t\tLaporan Parkir Gedung\n";
        std::cout << GARIS << '\n';
        std::cout << std::left << std::setw(4) << "No" << std::setw(8) << "Jenis" << std::setw(9) << "No.Pol"
            << std::setw(16) << "Pemilik" << std::setw(16) << "Status" << std::setw(12) << "Detail"
            << std::setw(11) << "Datang" << std::setw(11) << "Pulang" << std::setw(11) << "Lama"
            << std::setw(12) << "Biaya" << '\n';
        std::cout << GARIS << '\n';

        for (int i = 0; i < (int)data.size(); i++) {
            data[i]->print_kendaraan(i + 1);    // satu loop, panggil secara polimorfisme
        }
        std::cout << GARIS << '\n';
    }
};

// disable ini (copy constructor)
// ArrayParkir a;
// ArrayParkir b(a); 
// ArrayParkir c = a;

//dan disable ini (copy assignment operator)
// ArrayParkir a;
// ArrayParkir b;
// b = a;

class MenuParkir {
private:
        static void display_menu() {
            std::cout << "\n========== MENU PARKIR ==========" << '\n'
                << "1. Tambah kendaraan" << '\n'
                << "2. Tampilkan laporan parkir" << '\n'
                << "3. Ganti pemilik kendaraan" << '\n'
                << "4. Isi data contoh" << '\n'
                << "0. Keluar" << '\n'
                << "=================================\n";
        }

        // Pemiliknya hard-coded
        static Person* pilih_pemilik(std::vector<Person*>& list_orang) {
            std::cout << "\n--- Pilih Pemilik ---\n";
            std::cout << "0. Anonim (tanpa identitas)\n";
            for (int i = 0; i < (int)list_orang.size(); i++) {
                std::cout << (i + 1) << ". " << list_orang[i]->get_nama()
                    << " (" << (list_orang[i]->is_pegawai() ? "pegawai" : "umum") << ")\n"; //ternary operator
            }
            int p = Helper::valid_input_int("Pemilik: ", 0, list_orang.size());
            return (p == 0) ? nullptr : list_orang[p - 1];
        }

        static Kendaraan* buat_kendaraan(std::vector<Person*>& list_orang) {
            std::cout << "\n1. Mobil\n2. Truk\n3. Motor\n";
            int jenis = Helper::valid_input_int("Jenis kendaraan: ", 1, 3);
            int plat = Helper::valid_input_int("Nomor polisi (angka): ", 1, 999999);
            Person* pemilik = pilih_pemilik(list_orang);

            std::cout << "\n--- Waktu Datang ---" << '\n';
            Waktu datang;
            datang.input_waktu();
            std::cout << "\n--- Waktu Pulang ---" << '\n';
            Waktu pulang;
            pulang.input_waktu();

            switch (jenis) {
                case 1: {
                    int pintu = Helper::valid_input_int("Jumlah pintu: ", 2, 6);
                    return new Mobil(plat, pemilik, datang, pulang, pintu);
                }
                case 2: {
                    int roda = Helper::valid_input_int("Jumlah roda: ", 4, 18);
                    return new Truk(plat, pemilik, datang, pulang, roda);
                }
                case 3: {
                    int tak = Helper::valid_input_int("Jumlah tak (2/4): ", 2, 4);
                    return new Motor(plat, pemilik, datang, pulang, tak);
                }
            }
            return nullptr;
        }

        static void isi_contoh(ArrayParkir& parkir, std::vector<Person*>& list_orang) {
            // list_orang order: 0 Budi (umum), 1 Ani (umum), 2 Siti (pegawai), 3 Joko (pegawai)
            parkir.tambah_kendaraan(new Mobil(1234, list_orang[2],  Waktu(8,0,0),  Waktu(17,30,0), 4));
            parkir.tambah_kendaraan(new Motor(5678, list_orang[3],  Waktu(8,0,0),  Waktu(16,0,0),  2));
            parkir.tambah_kendaraan(new Truk (9012, list_orang[2],  Waktu(7,0,0),  Waktu(10,10,0), 6));
            parkir.tambah_kendaraan(new Mobil(3456, list_orang[0],  Waktu(9,0,0),  Waktu(11,0,0),  4));
            parkir.tambah_kendaraan(new Motor(7890, nullptr,  Waktu(10,0,0), Waktu(10,20,0), 2));
            parkir.tambah_kendaraan(new Mobil(1111, nullptr,  Waktu(22,0,0), Waktu(2,30,0),  4));
            std::cout << "6 data contoh ditambahkan.\n";
        }

    public:
        static void menu() {
            // Hard-coded owners. Declared BEFORE parkir so they outlive it.
            Person budi = Person("Budi", "Jl. Merdeka");
            Person ani = Person("Ani", "Jl. Sudirman");
            Pegawai siti = Pegawai("Siti", "Jl. Soetta", "001", 2, Waktu(8,0,0), Waktu(17,0,0));
            Pegawai joko = Pegawai("Joko", "Jl. Diponegoro", "002", 3, Waktu(8,0,0), Waktu(17,0,0));
            std::vector<Person*> list_orang = { &budi, &ani, &siti, &joko };

            ArrayParkir parkir;
            int pilih = -1;

            do {
                display_menu();
                pilih = Helper::valid_input_int("Menu: ", 0, 4);

                switch (pilih) {
                    case 1: {
                        parkir.tambah_kendaraan(buat_kendaraan(list_orang));
                        std::cout << "Kendaraan ditambahkan.\n";
                        break;
                    }
                    case 2: {
                        if (parkir.get_jumlah() == 0) std::cout << "Belum ada data.\n";
                        else parkir.print_all_parkir();
                        break;
                    }
                    case 3: {
                        if (parkir.get_jumlah() == 0) { std::cout << "Belum ada data.\n"; break; }
                        parkir.print_all_parkir();
                        int no = Helper::valid_input_int("Nomor kendaraan: ", 1, parkir.get_jumlah());
                        Kendaraan* k = parkir.get_kendaraan(no - 1);
                        k->ganti_pemilik(pilih_pemilik(list_orang));
                        std::cout << "Pemilik diganti.\n";
                        break;
                    }
                    case 4: {
                        isi_contoh(parkir, list_orang);
                        break;
                    }
                    case 0: {
                        std::cout << "Terima kasih...\n";
                        break;
                    }
                }
            } while (pilih != 0);
        }
};

int main() {
    MenuParkir::menu();
    return 0;
}