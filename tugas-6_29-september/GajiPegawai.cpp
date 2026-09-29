/***********************************************************
 *    Nama File    : GajiPegawai.cpp
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 29 September 2026
 *    Deskripsi    : Implementasi komunikasi antar class menggunakan masalah gaji pegawai
 ***********************************************************/

#include <iostream>
#include <string>
#include <iomanip>
#include <cmath>
using namespace std;

class Helper {
    public:
        static int validInputInt(string pesan, int min, int maks) {
            int angka = 0;
            bool valid = false;

            do {
                cout << pesan;
                cin >> angka;

                if (cin.fail() || angka < min || angka > maks) {
                    cout << "Input harus angka " << min << " - " << maks << "!!\n";
                    cin.clear();
                    cin.ignore(400000, '\n');
                } else {
                    valid = true;
                }
            } while (!valid);

            return angka;
        }

        static string formatRibuan(long long n) {
            string s = to_string(n);
            int insert = s.length() - 3;
            while (insert > 0) {
                s.insert(insert, ".");
                insert -= 3;
            }
            return s;
        }
};

class Waktu{
    private:
        int jam;
        int menit;
        int detik;

    public:
        Waktu(){
            this->jam = 0;
            this->menit = 0;
            this->detik = 0;
        }

        Waktu(int jam, int menit, int detik){
            this->jam = jam;
            this->menit = menit;
            this->detik = detik;
        }

        void setWaktu(int jam, int menit, int detik){
            this->jam = jam;
            this->menit = menit;
            this->detik = detik;
        }

        void setJam(int jam){
            this->jam = jam;
        }

        void setMenit(int menit){
            this->menit = menit;
        }

        void setDetik(int detik){
            this->detik = detik;
        }

        void inputWaktu(){
            this->jam = Helper::validInputInt("Masukkan jam: ", 0, 23);
            this->menit = Helper::validInputInt("Masukkan menit: ", 0, 59);
            this->detik = Helper::validInputInt("Masukkan detik: ", 0, 59);
        }

        int getJam(){
            return this->jam;
        }

        int getMenit(){
            return this->menit;
        }

        int getDetik(){
            return this->detik;
        }

        void printWaktu(){
            cout << right << setfill('0') << setw(2) << this->jam << ":"
                 << setfill('0') << setw(2) << this->menit << ":"
                 << setfill('0') << setw(2) << this->detik << setfill(' ');
        }

        void printWaktuJamLembur(){
            cout << this->jam << ":"
                 << setfill('0') << setw(2) << this->menit << ":"
                 << setfill('0') << setw(2) << this->detik << setfill(' ');
        }

        Waktu jarakWaktu(Waktu pulang){
            Waktu hasil;
            int awal = (this->jam * 3600) + (this->menit * 60) + (this->detik);
            int akhir = (pulang.getJam() * 3600) + (pulang.getMenit() * 60) + (pulang.getDetik());
            
            if (akhir < awal){
                akhir += (24 * 3600);
            }
            
            int selisih = abs(akhir - awal);

            hasil.setJam(selisih / 3600);
            selisih %= 3600;
            hasil.setMenit(selisih / 60);
            selisih %= 60;
            hasil.setDetik(selisih);

            return hasil;
        }

        void lembur(Waktu lama){
            if (lama.getJam() >= 8){
                this->jam = lama.getJam() - 8;
                this->menit = lama.getMenit();
                this->detik = lama.getDetik();
            } else {
                this->jam = 0;
                this->menit = 0;
                this->detik = 0;
            }
        }
};

class Pegawai{
    private:
        string NIP;
        string nama;
        int gol;
        Waktu datang;
        Waktu pulang;
    
    public:
        Pegawai(){}

        Pegawai(string NIP, string nama, int gol, Waktu datang, Waktu pulang){
            this->NIP = NIP;
            this->nama = nama;
            this->gol = gol;
            this->datang = datang;
            this->pulang = pulang;
        }

        void inputPegawai() {
            cout << "Masukkan NIP: ";
            cin >> NIP;
            
            cout << "Masukkan Nama: ";
            cin.ignore();
            getline(cin, nama);
            
            cout << "Masukkan Golongan (1-4): ";
            gol = Helper::validInputInt("Golongan: ", 1, 4);
            
            cout << "\n--- Input Waktu Datang ---" << endl;
            datang.inputWaktu();
            
            cout << "\n--- Input Waktu Pulang ---" << endl;
            pulang.inputWaktu();
        }

        void setPegawai(string NIP, string nama, int gol){
            this->NIP = NIP;
            this->nama = nama;
            this->gol = gol;
        }

        void setNIP(string NIP){
            this->NIP = NIP;
        }

        void setNama(string nama){
            this->nama = nama;
        }

        void setGol(int gol){
            this->gol = gol;
        }

        void setDatang(Waktu datang){
            this->datang = datang;
        }

        void setPulang(Waktu pulang){
            this->pulang = pulang;
        }

        string getNIP(){
            return this->NIP;
        }

        string getNama(){
            return this->nama;
        }

        int getGol(){
            return this->gol;
        }

        Waktu getDatang(){
            return this->datang;
        }

        Waktu getPulang(){
            return this->pulang;
        }

        Waktu getLamaKerja(){
            return datang.jarakWaktu(pulang);
        }

        Waktu getWaktuLembur(){
            Waktu lama = getLamaKerja();
            Waktu lembur;
            lembur.lembur(lama);
            return lembur;
        }

        int getGajiHarian(){
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

        int getBiayaLembur(){
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

        int getUangLemburTotal(){
            return getWaktuLembur().getJam() * getBiayaLembur();
        }

        int getTotalGaji(){
            return getGajiHarian() + getUangLemburTotal();
        }

        string getStatus(){
            if (getLamaKerja().getJam() < 8){
                return "peringatan";
            } else {
                return "ok";
            }
        }
};

class GajiPegawai{
    public:
        void displayMenu(){
            cout << "\n============= MENU ===============" <<
            endl << "1. CONTRUCTOR KONSTANTA" <<
            endl << "2. SETTER KONSTANTA" <<
            endl << "3. SETTER INPUT LUAR" <<
            endl << "4. INPUT DALAM" <<
            endl << "0. KELUAR" <<
            endl << "==================================\n";
        }

        void cetakTabel(Pegawai p){
            cout << "\n\t\t\t\tDaftar Gaji Harian PT Informatika\n\n";
            cout << "------------------------------------------------------------------------------------------------------------------------------------\n";
            cout << left << setw(4) << "No" 
                 << setw(6) << "NIP" 
                 << setw(16) << "Nama" 
                 << setw(8) << "Gol" 
                 << setw(11) << "Datang" 
                 << setw(11) << "Pulang" 
                 << setw(11) << "Lama" 
                 << setw(13) << "Jam Lembur" 
                 << setw(14) << "Gaji Harian" 
                 << setw(13) << "Lembur" 
                 << setw(14) << "Total" 
                 << setw(10) << "Status" << endl;
            cout << "------------------------------------------------------------------------------------------------------------------------------------\n";

            cout << setfill(' ');
            cout << left << setw(4) << "1." 
                 << setw(6) << p.getNIP()
                 << setw(16) << p.getNama()
                 << setw(8) << p.getGol();
            
            p.getDatang().printWaktu();
            cout << "   ";
            p.getPulang().printWaktu();
            cout << "   ";
            p.getLamaKerja().printWaktu();
            cout << "   ";
            p.getWaktuLembur().printWaktuJamLembur();
            cout << "      ";

            cout << left << setw(14) << Helper::formatRibuan(p.getGajiHarian())
                 << setw(13) << Helper::formatRibuan(p.getUangLemburTotal())
                 << setw(14) << Helper::formatRibuan(p.getTotalGaji())
                 << setw(10) << p.getStatus() << endl;

            cout << "------------------------------------------------------------------------------------------------------------------------------------\n";
        }

        void menu(){
            int pilih = 0;
            do{
                displayMenu();
                pilih = Helper::validInputInt("Menu: ", 0, 4);

                switch(pilih){
                    case 1:{
                        Pegawai p("001", "Ali", 3, Waktu(8, 0, 0), Waktu(17, 15, 10));
                        cetakTabel(p);
                        break;
                    }
                    case 2:{
                        Pegawai p;
                        p.setNIP("001");
                        p.setNama("Ali");
                        p.setGol(3);
                        p.setDatang(Waktu(8, 0, 0));
                        p.setPulang(Waktu(17, 15, 10));

                        cetakTabel(p);
                        break;
                    }
                    case 3:{
                        Pegawai p;
                        string nip, nama;
                        int gol;
                        cout << "Masukkan NIP: ";
                        cin >> nip;
                        cout << "Masukkan Nama: ";
                        cin.ignore();
                        getline(cin, nama);
                        gol = Helper::validInputInt("Masukkan Golongan (1-4): ", 1, 4);

                        p.setNIP(nip);
                        p.setNama(nama);
                        p.setGol(gol);

                        cout << "\n--- Input Waktu Datang ---" << endl;
                        Waktu d;
                        d.inputWaktu();
                        p.setDatang(d);

                        cout << "\n--- Input Waktu Pulang ---" << endl;
                        Waktu pl;
                        pl.inputWaktu();
                        p.setPulang(pl);

                        cetakTabel(p);
                        break;
                    }
                    case 4:{
                        Pegawai p;
                        p.inputPegawai();
                        cetakTabel(p);
                        break;
                    }
                    case 0:{
                        cout << "Terima kasih...\n";
                        break;
                    }
                }
            } while(pilih != 0);
        }
};

int main(){
    GajiPegawai app;
    app.menu();
    return 0;
}