/***********************************************************
 *    Nama File    : array_integer.java
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 28 September 2026
 *    Deskripsi    : Program array
 ***********************************************************/

#include <iostream>
#include <string>
#include <iomanip>

using namespace std;

class Helper {
    public:
        static int validInputInt(string pesan, int min, int maks) {
            int angka = 0;
            bool valid = false;
            do {
                cout << pesan;
                cin >> angka;
                if (cin.fail() || angka > maks || angka < min) {
                    cin.clear();
                    cin.ignore(9999999, '\n');
                    cout << "Input harus angka " << min << " - " << maks << "!!" << endl;
                } else {
                    cin.ignore(9999999, '\n');
                    valid = true;
                }
            } while (!valid);

            return angka;
        }
};

class ArrayInteger1D {
    private:
        const static int MAX_ARR_SIZE = 1000;
        int data[MAX_ARR_SIZE];
        int ukuran;
        int kapasitas;

    public:
        ArrayInteger1D() {
            this->kapasitas = 100;
            this->ukuran = 0;
        }

        ArrayInteger1D(int jumlah) {
            this->kapasitas = 100;
            this->ukuran = 0;
            for (int i = 0; i < jumlah; i++) {
                tambah_integer(i);
            }
        }

        int get_ukuran() { return this->ukuran; }
        int get_kapasitas() { return this->kapasitas; }
        int get_data(int index) {
            if (index < 0 || index >= this->ukuran) {
                cout << "Index di luar jangkauan." << endl;
                return 0;
            }
            return this->data[index];
        }
 
        void set_data(int index, int nilai) {
            if (index < 0 || index >= this->ukuran) {
                cout << "Index di luar jangkauan." << endl;
            } else {
                this->data[index] = nilai;
            }
        }
 
        void set_ukuran(int ukuran) {
            if (ukuran < 0 || ukuran > this->kapasitas) {
                cout << "Ukuran harus 0 - " << this->kapasitas << "." << endl;
            } else {
                this->ukuran = ukuran;
            }
        }
 
        void set_kapasitas(int kapasitas) {
            if (kapasitas < this->ukuran || kapasitas > MAX_ARR_SIZE) {
                cout << "Kapasitas harus " << this->ukuran << " - " << MAX_ARR_SIZE << "." << endl;
            } else {
                this->kapasitas = kapasitas;
            }
        }

        void tambah_integer(int integer) {
            if (this->ukuran >= this->kapasitas) {
                cout << "Kapasitas array sudah full." << endl;
            } else {
                this->data[this->ukuran] = integer;
                this->ukuran++;
            }
        }

        void input_data(int jumlah) {
            this->ukuran = 0;
            for (int i = 0; i < jumlah; i++) {
                cout << "\n --- Data Integer ke-" << i + 1 << " ---\n";
                int temp = Helper::validInputInt("Masukkan integer: ", -9999, 9999);
                tambah_integer(temp);
            }
        }

        float get_rata_rata() {
            if (this->ukuran == 0) return 0;

            float jumlah = 0;
            for (int i = 0; i < this->ukuran; i++) {
                jumlah += data[i];
            }
            return jumlah / this->ukuran;
        }

        int get_tertinggi() {
            if (this->ukuran == 0) return 0;

            int highest = data[0];
            for (int i = 1; i < this->ukuran; i++) {
                if (highest < data[i]) highest = data[i];
            }
            return highest;
        }

        int get_terendah() {
            if (this->ukuran == 0) return 0;

            int lowest = data[0];
            for (int i = 1; i < this->ukuran; i++) {
                if (lowest > data[i]) lowest = data[i];
            }
            return lowest;
        }

        void bubble_sort_asc() {
            for (int i = 0; i < this->ukuran - 1; i++) {
                bool swapped = false;

                for (int j = 0; j < this->ukuran - i - 1; j++) {
                    if (this->data[j] > this->data[j + 1]) {
                        int temp = this->data[j];
                        this->data[j] = this->data[j + 1];
                        this->data[j + 1] = temp;
                        swapped = true;
                    }
                }

                if (!swapped) break;
            }
        }

        void bubble_sort_desc() {
            for (int i = 0; i < this->ukuran - 1; i++) {
                bool swapped = false;

                for (int j = 0; j < this->ukuran - i - 1; j++) {
                    if (this->data[j] < this->data[j + 1]) {
                        int temp = this->data[j];
                        this->data[j] = this->data[j + 1];
                        this->data[j + 1] = temp;
                        swapped = true;
                    }
                }

                if (!swapped) break;
            }
        }

        void printData() {
            for (int i = 0; i < this->ukuran; i++) {
                cout << this->data[i] << " ";
            }
        }
};

class Menu {
    public:
        static void display_menu() {
            cout << "\n============= MENU ===============" << endl;
            cout << "1. CONSTRUCTOR KONSTANTA" << endl;
            cout << "2. SETTER KONSTANTA" << endl;
            cout << "3. SETTER INPUT LUAR" << endl;
            cout << "4. INPUT DALAM" << endl;
            cout << "0. KELUAR" << endl;
            cout << "==================================" << endl;
        }

        static int input() {
            return Helper::validInputInt("Menu: ", 0, 4);
        }

        static void print_test(int objek_nomor, ArrayInteger1D &array) {
            cout << "Objek ke-" << objek_nomor << endl;
            cout << "Rata-rata: " << fixed << setprecision(2) << array.get_rata_rata() << endl;
            cout << "Tertinggi: " << array.get_tertinggi() << endl;
            cout << "Terendah: " << array.get_terendah() << endl;
            cout << "Data awal:" << endl;
            array.printData();

            array.bubble_sort_asc();
            cout << "\nData sorted asc:" << endl;
            array.printData();

            array.bubble_sort_desc();
            cout << "\nData sorted desc:" << endl;
            array.printData();

            cout << endl;
        }

        static void runMenu() {
            int pilihan = -1;

            while (pilihan != 0) {
                display_menu();
                pilihan = input();
                switch (pilihan) {
                    case 1: {
                        int n = Helper::validInputInt("Masukkan jumlah integer: ", 1, 100);
                        ArrayInteger1D array_integer1;

                        for (int i = 0; i < n; i++) {
                            array_integer1.tambah_integer(i);
                        }

                        print_test(1, array_integer1);
                        break;
                    }
                    case 2: {
                        int n = Helper::validInputInt("Masukkan jumlah integer: ", 1, 100);
                        ArrayInteger1D array_integer2(n);

                        print_test(2, array_integer2);
                        break;
                    }
                    case 3: {
                        int n = Helper::validInputInt("Masukkan jumlah integer: ", 1, 100);
                        ArrayInteger1D array_integer3;

                        for (int i = 0; i < n; i++) {
                            cout << "\n --- Data Integer ke-" << i + 1 << " ---\n";
                            int temp = Helper::validInputInt("Masukkan integer: ", -9999, 9999);
                            array_integer3.tambah_integer(temp);
                        }

                        print_test(3, array_integer3);
                        break;
                    }
                    case 4: {
                        int n = Helper::validInputInt("Masukkan jumlah integer: ", 1, 100);
                        ArrayInteger1D array_integer4;

                        array_integer4.input_data(n);

                        print_test(4, array_integer4);
                        break;
                    }
                    case 0: {
                        cout << "Terima kasih sudah menggunakan program ini." << endl;
                        break;
                    }
                    default: {
                        cout << "Pilihan tidak valid." << endl;
                        break;
                    }
                }
            }
        }
};

int main() {
    Menu::runMenu();
    return 0;
}