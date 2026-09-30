/***********************************************************
 *    Nama File    : SelisihWaktu.cpp
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 28 September 2026
 *    Deskripsi    : Program kalkulasi selisih waktu
 ***********************************************************/

#include <iostream>
#include <string>

using namespace std;

class Helper{
public:
    static int validInputInt(string pesan, int min, int maks) {
        int angka = 0;
        bool valid = false;
        do {
            cout << pesan;
            if (cin >> angka) {
                if (angka < min || angka > maks)
                    cout << "Input harus angka " << min << " - " << maks << endl;
                else 
                    valid = true;
                
            } else {
                cout << "Input harus integer!" << endl;
                cin.clear();
                cin.ignore(9999999999999, '\n');
            }
        } while (!valid);

        return angka;
    }
    
};

class Waktu{
private:
    int jam = 0;
    int menit = 0;
    int detik = 0;

public:
    // Construtor
    Waktu() {}

    Waktu(int jam, int menit, int detik){
        this->jam = jam;
        this->menit = menit;
        this->detik = detik;
    }

    // Setter Getter
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

    int getJam() const{
        return this->jam;
    }

    int getMenit() const{
        return this->menit;
    }

    int getDetik() const{
        return this->detik;
    }

    // Output
    void printWaktu(char name){
        printf("Waktu %c: %02d:%02d:%02d\n", name, jam, menit, detik);
    }

    Waktu hitungSelisihReturn(const Waktu& B){
        Waktu hasil;

        int awal = this->jam * 3600 + this->menit * 60 + this->detik;
        int akhir = B.getJam() * 3600 + B.getMenit() * 60 + B.getDetik();
        int selisih = abs(awal - akhir);

        hasil.setJam(selisih / 3600);
        selisih %= 3600;
        hasil.setMenit(selisih / 60);
        selisih %= 60;
        hasil.setDetik(selisih);

        return hasil;
    }

    void hitungSelisihVoid(const Waktu& A, const Waktu& B){
        int awal = A.getJam() * 3600 + A.getMenit() * 60 + A.getDetik();
        int akhir = B.getJam() * 3600 + B.getMenit() * 60 + B.getDetik();
        int selisih = abs(awal - akhir);

        this->jam = (selisih / 3600);
        selisih %= 3600;
        this->menit = (selisih / 60);
        selisih %= 60;
        this->detik = (selisih);
    }
};

class Menu{
public:
    static void displayMenu(){
        printf("\n========== Menu ==========\n");
        printf("1. Constructor Konstanta\n");
        printf("2. Setter Konstanta \n");
        printf("3. Setter Input Luar\n");
        printf("4. Input Dalam\n");
        printf("0. Keluar\n");
        printf("==========================\n");
    }

    static void menu(int pilihan){
        switch(pilihan) {
            case 1: {
                printf("\n--- 1. Constructor Konstanta ---\n");
                Waktu wA(8, 30, 0);
                Waktu wB(10, 45, 15);

                wA.printWaktu('A');
                wB.printWaktu('B');

                Waktu selisihReturn = wA.hitungSelisihReturn(wB);
                printf("Selisih (Return): ");
                selisihReturn.printWaktu('S');

                Waktu selisihVoid;
                selisihVoid.hitungSelisihVoid(wA, wB);
                printf("Selisih (Void)  : ");
                selisihVoid.printWaktu('S');
                break;
            }
            case 2: {
                printf("\n--- 2. Setter Konstanta ---\n");
                Waktu wA;
                Waktu wB;

                wA.setWaktu(12, 15, 30);
                wB.setWaktu(15, 0, 45);

                wA.printWaktu('A');
                wB.printWaktu('B');

                Waktu selisihReturn = wA.hitungSelisihReturn(wB);
                printf("Selisih (Return): ");
                selisihReturn.printWaktu('S');

                Waktu selisihVoid;
                selisihVoid.hitungSelisihVoid(wA, wB);
                printf("Selisih (Void)  : ");
                selisihVoid.printWaktu('S');
                break;
            }
            case 3: {
                printf("\n--- 3. Setter Input Luar ---\n");
                Waktu wA;
                Waktu wB;

                printf("Input Waktu A:\n");
                wA.setJam(Helper::validInputInt("Masukkan jam: ", 0, 23));
                wA.setMenit(Helper::validInputInt("Masukkan menit: ", 0, 59));
                wA.setDetik(Helper::validInputInt("Masukkan detik: ", 0, 59));

                printf("\nInput Waktu B:\n");
                wB.setJam(Helper::validInputInt("Masukkan jam: ", 0, 23));
                wB.setMenit(Helper::validInputInt("Masukkan menit: ", 0, 59));
                wB.setDetik(Helper::validInputInt("Masukkan detik: ", 0, 59));

                printf("\n");
                wA.printWaktu('A');
                wB.printWaktu('B');

                Waktu selisihReturn = wA.hitungSelisihReturn(wB);
                printf("Selisih (Return): ");
                selisihReturn.printWaktu('S');

                Waktu selisihVoid;
                selisihVoid.hitungSelisihVoid(wA, wB);
                printf("Selisih (Void)  : ");
                selisihVoid.printWaktu('S');
                break;
            }
            case 4: {
                printf("\n--- 4. Input Dalam ---\n");
                Waktu wA;
                Waktu wB;

                printf("Input Waktu A:\n");
                wA.inputWaktu();

                printf("\nInput Waktu B:\n");
                wB.inputWaktu();

                printf("\n");
                wA.printWaktu('A');
                wB.printWaktu('B');

                Waktu selisihReturn = wA.hitungSelisihReturn(wB);
                printf("Selisih (Return): ");
                selisihReturn.printWaktu('S');

                Waktu selisihVoid;
                selisihVoid.hitungSelisihVoid(wA, wB);
                printf("Selisih (Void)  : ");
                selisihVoid.printWaktu('S');
                break;
            }
            case 0:
                printf("Terima kasih...\n\n\n");
                break;

            default:
                printf("Input (0-4)!\n");
                break;
        }
    }

    static void runMenu() {
        int pilih = -1;
        do {
            displayMenu();
            pilih = Helper::validInputInt(": ", 0, 4);
            menu(pilih);
        } while (pilih != 0);
    }
};

int main(){
    
    Menu::runMenu();

    return 0;
}