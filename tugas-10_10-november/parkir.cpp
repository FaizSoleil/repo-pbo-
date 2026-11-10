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

// Biar nanti kelas anaknya bisa manggil
class Person {
protected:
    std::string nama;
    std::string alamat;

public:
    Person(){
        this->nama = "";
        this->alamat = "";
    }

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
    std::string NIP;
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

    Pegawai(std::string nama, std::string alamat, std::string NIP, int gol, Waktu datang, Waktu pulang)
        : Person(nama, alamat){
        this->NIP = NIP;
        this->gol = gol;
        this->datang = datang;
        this->pulang = pulang;
    }

    void set_pegawai(std::string nama, std::string alamat, std::string NIP, int gol, Waktu datang, Waktu pulang) {
        Person::set_person(nama, alamat);
        this->NIP = NIP;
        this->gol = gol;
        this->datang = datang;
        this->pulang = pulang;
    }

    // setter dan getter dari class Parent akan otomatis diwarisi oleh class Child
    void set_NIP(std::string NIP) { this->NIP = NIP; }
    void set_gol(int gol) { this->gol = gol; }
    void set_datang(Waktu datang) { this->datang = datang; }
    void set_pulang(Waktu pulang) { this->pulang = pulang; }

    std::string get_NIP() { return this->NIP; }
    int get_gol() { return this->gol; }
    Waktu get_datang() { return this->datang; }
    Waktu get_pulang() { return this->pulang; }

    void input_pegawai() {
        Person::input();

        std::cout << "Masukkan NIP: ";
        this->NIP = Helper::input_string("Masukkan NIP: ");

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
        std::cout << std::left << std::setw(4) << (std::to_string(no) + ".") << std::setw(6) << get_NIP() << std::setw(16) << get_nama() << std::setw(20) << get_alamat() << std::setw(8) << get_gol();
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

class Kendaraan {

};

class Mobil : public Kendaraan {

};

class Motor : public Kendaraan {

};

class Truk : public Kendaraan {

};

class ParkirKendaraan {

};

class Menu {

};

int main() {

    return 0;
}