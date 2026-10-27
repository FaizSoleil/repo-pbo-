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
#include <string>
#include <iomanip>
#include <cmath>

using namespace std;

class Helper {
    public:
        static int valid_input_int(string pesan, int min, int maks) {
            int angka = 0;
            bool valid = false;

            do {
                string temp;
                cout << pesan;
                getline(cin, temp); //biar konsumsi newline konsisten
                
                if (!temp.empty()) //biar gak throw invalid argument di stoi
                {

                    try
                    {
                        angka = stoi(temp); //kalo kosong invalid argument

                        if(angka < min || angka > maks) {
                            cout << "Input harus angka " << min << " - " << maks << "!!\n";
                            continue;
                        }

                        valid = true;
                    }
                    catch(const std::exception& e)
                    {
                        cout << "Input harus sebuah integer!!\n";
                        cin.clear();
                        cin.ignore(400000, '\n');
                    }
                } else { continue; }
            } while (!valid);

            return angka;
        }

        static string format_ribuan(long long n) {
            string s = to_string(n);
            int insert = s.length() - 3;
            while (insert > 0) {
                s.insert(insert, ".");
                insert -= 3;
            }
            return s;
        }

        static string input_string(string message=""){
            cout << message;

            string hasil;
            // cin.clear();
            // cin.ignore(40000, '\n'); clear dan ignore lebih baik di luar logika input biar gak double input.
            getline(cin, hasil);

            return hasil;
        }

        static void clear_buffer() {
            cin.clear();
            cin.ignore(40000, '\n');
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
            cout << right << setfill('0') << setw(2) << this->jam << ":"
                << setfill('0') << setw(2) << this->menit << ":"
                << setfill('0') << setw(2) << this->detik << setfill(' ');
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

// Biar nanti kelas anaknya bisa manggil
class Person {
    protected:
        string nama;
        string alamat;

    public:
        Person(){
            this->nama = "";
            this->alamat = "";
        }

        Person(string nama, string alamat){
            this->nama = nama;
            this->alamat = alamat;
        }

        void set_person(string nama, string alamat){
            this->nama = nama;
            this->alamat = alamat;
        }

        void set_nama(string nama){ this->nama = nama; }
        void set_alamat(string alamat){ this->alamat = alamat; }

        string get_nama(){ return this->nama; }
        string get_alamat(){ return this->alamat; }

        void input(){
            cout << "Masukkan Nama: ";
            this->nama = Helper::input_string();
            cout << "Masukkan Alamat: ";
            this->alamat = Helper::input_string();
        }

        void output(){
            cout << this->nama << this->alamat;
        }
};

// Penggunaa public agar fungsi dan atribut class parent bisa digunakan oleh class child
// Penggunaan ": public" pada cpp mirip seperti extends pada java
class Pegawai : public Person{
    private:
        string NIP;
        int gol;
        Waktu datang;
        Waktu pulang;

    public:
        Pegawai() : Person() {
            this->NIP = "";
            this->gol = 0;
            this->datang;
            this->pulang;
        }

        Pegawai(string nama, string alamat, string NIP, int gol, Waktu datang, Waktu pulang)
            : Person(nama, alamat){
            this->NIP = NIP;
            this->gol = gol;
            this->datang = datang;
            this->pulang = pulang;
        }

        void set_pegawai(string nama, string alamat, string NIP, int gol, Waktu datang, Waktu pulang) {
            Person::set_person(nama, alamat);
            this->NIP = NIP;
            this->gol = gol;
            this->datang = datang;
            this->pulang = pulang;
        }

        // setter dan getter dari class Parent akan otomatis diwarisi oleh class Child
        void set_NIP(string NIP) { this->NIP = NIP; }
        void set_gol(int gol) { this->gol = gol; }
        void set_datang(Waktu datang) { this->datang = datang; }
        void set_pulang(Waktu pulang) { this->pulang = pulang; }

        string get_NIP() { return this->NIP; }
        int get_gol() { return this->gol; }
        Waktu get_datang() { return this->datang; }
        Waktu get_pulang() { return this->pulang; }

        void input_pegawai() {
            Person::input();

            cout << "Masukkan NIP: ";
            this->NIP = Helper::input_string();

            cout << "Masukkan Golongan (1-4): ";
            gol = Helper::valid_input_int("Golongan: ", 1, 4);

            cout << "\n--- Input Waktu Datang ---" << endl;
            datang.input_waktu();

            cout << "\n--- Input Waktu Pulang ---" << endl;
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

        string get_status() {
            if (get_lama_kerja().get_jam() < 8) {
                return "peringatan";
            } else {
                return "ok";
            }
        }

        void print_pegawai(int no) {
            cout << left << setw(4) << (to_string(no) + ".") << setw(6) << get_NIP() << setw(16) << get_nama() << setw(20) << get_alamat() << setw(8) << get_gol();
            get_datang().print_waktu();
            cout << "   ";
            get_pulang().print_waktu();
            cout << "   ";
            get_lama_kerja().print_waktu();
            cout << "     ";
            get_waktu_lembur().print_waktu();
            cout << "     ";
            cout << left << setw(14) << Helper::format_ribuan(get_gaji_harian())
                << setw(13) << Helper::format_ribuan(get_uang_lembur_total())
                << setw(14) << Helper::format_ribuan(get_total_gaji())
                << setw(10) << get_status() << endl;
        }
};

class ArrayPegawai {
    string GARIS = string(152, '-');

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
                cout << "\n--- Data Pegawai ke-" << i + 1 << " ---" << endl;
                Pegawai p;
                p.input_pegawai();
                tambah_pegawai(p);
            }
        }

        void print_all_pegawai() {
            cout << "\n\t\t\t\tDaftar Gaji Harian PT Informatika\n";
            cout << GARIS << endl;
            cout << left << setw(4) << "No" << setw(6) << "NIP" << setw(16) << "Nama" << setw(20) << "Alamat" << setw(8) << "Gol"
                << setw(11) << "Datang" << setw(11) << "Pulang" << setw(13) << "Lama" << setw(13) << "Jam Lembur"
                << setw(14) << "Gaji Harian" << setw(13) << "Lembur" << setw(14) << "Total" << setw(10) << "Status" << endl;
            cout << GARIS << endl;

            for (int i = 0; i < this->kapasitas; i++) {
                data[i].print_pegawai(i + 1);
            }

            cout << GARIS << endl;
        }
};

class Menu {
    public:
        static void display_menu() {
            cout << "\n============= MENU ===============" <<
                endl << "1. CONSTRUCTOR KONSTANTA" <<
                endl << "2. SETTER KONSTANTA" <<
                endl << "3. SETTER INPUT LUAR" <<
                endl << "4. INPUT DALAM" <<
                endl << "0. KELUAR" <<
                endl << "==================================\n";
        }

        static void menu() {
            int pilih = -1;
            do {
                Menu::display_menu();
                pilih = Helper::valid_input_int("Menu: ", 0, 4);
                switch (pilih) {
                    case 1: {
                        int n = Helper::valid_input_int("Masukkan jumlah pegawai: ", 1, 100);
                        ArrayPegawai array_pegawai1;
                        for (int i = 0; i < n; i++) {
                            Pegawai p("Pegawai " + to_string(i + 1), "jalan Soetta" + to_string(i + 1), "00" + to_string(i + 1), (i % 4) + 1, Waktu(8, 0, 0), Waktu(17, 15, 10));
                            array_pegawai1.tambah_pegawai(p);
                        }
                        array_pegawai1.print_all_pegawai();
                        break;
                    }
                    case 2: {
                        int n = Helper::valid_input_int("Masukkan jumlah pegawai: ", 1, 100);
                        ArrayPegawai array_pegawai2;
                        for (int i = 0; i < n; i++) {
                            Pegawai p;
                            p.set_NIP("00" + to_string(i + 1));
                            p.set_nama("Pegawai " + to_string(i + 1));
                            p.set_alamat("jalan soetta" + to_string(i+1));
                            p.set_gol((i % 4) + 1);
                            p.set_datang(Waktu(8, 0, 0));
                            p.set_pulang(Waktu(17, 15, 10));
                            array_pegawai2.tambah_pegawai(p);
                        }
                        array_pegawai2.print_all_pegawai();
                        break;
                    }
                    case 3: {
                        int n = Helper::valid_input_int("Masukkan jumlah pegawai: ", 1, 100);
                        ArrayPegawai array_pegawai3;
                        for (int i = 0; i < n; i++) {
                            cout << "\n--- Data Pegawai ke-" << i + 1 << " ---" << endl;
                            Pegawai p;
                            string nip, nama, alamat;
                            cout << "Masukkan NIP: ";
                            nip = Helper::input_string();
                            cout << "Masukkan Nama: ";
                            nama = Helper::input_string();
                            cout << "Masukkan Alamat: ";
                            alamat = Helper::input_string();
                            int gol = Helper::valid_input_int("Masukkan Golongan (1-4): ", 1, 4);

                            p.set_NIP(nip);
                            p.set_nama(nama);
                            p.set_alamat(alamat);
                            p.set_gol(gol);

                            cout << "\n--- Input Waktu Datang ---" << endl;
                            Waktu d;
                            d.input_waktu();
                            p.set_datang(d);

                            cout << "\n--- Input Waktu Pulang ---" << endl;
                            Waktu pl;
                            pl.input_waktu();
                            p.set_pulang(pl);

                            array_pegawai3.tambah_pegawai(p);
                        }
                        array_pegawai3.print_all_pegawai();
                        break;
                    }
                    case 4: {
                        int n = Helper::valid_input_int("Masukkan jumlah pegawai: ", 1, 100);
                        ArrayPegawai array_pegawai4;
                        array_pegawai4.input_data(n);
                        array_pegawai4.print_all_pegawai();
                        break;
                    }
                    case 0: {
                        cout << "Terima kasih...\n";
                        break;
                    }
                }
            } while (pilih != 0);
        }
};

int main() {
    Menu::menu();
    return 0;
}