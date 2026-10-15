/***********************************************************
 *    Nama File    : operasi_matriks.cpp
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 11 Oktober 2026
 *    Deskripsi    : Program operasi matriks
 ***********************************************************/

#include "../common/cpp/helpers.hpp"
#include <string>
#include <array>

using Larik2D = int[100][100];

class Matriks {
    private:
    int baris;
    int kolom;
    Larik2D data;

    public:
    Matriks() {
        baris = 0;
        kolom = 0;
        for (size_t i = 0; i < 100; i++)
        {
            for (size_t j = 0; j < 100; j++)
            {
                data[i][j] = 0;
            }
        }
    }

    Matriks(const int& baris, const int& kolom) {
        this->baris = baris;
        this->kolom = kolom;
        for (size_t i = 0; i < 100; i++)
        {
            for (size_t j = 0; j < 100; j++)
            {
                data[i][j] = 0;
            }
        }
    }
    
    Matriks(const int& baris, const int& kolom, const Larik2D& data) {
        this->baris = baris;
        this->kolom = kolom;
        for (size_t i = 0; i < baris; i++)
        {
            for (size_t j = 0; j < kolom; j++)
            {
                this->data[i][j] = data[i][j];
            }
        }
    }

    int get_baris() const {
        return baris;
    }

    int get_kolom() const {
        return kolom;
    }

    //return by reference
    const Larik2D& get_data() const {
        return data;
    }

    void set_baris(const int& baris) {
        this->baris = baris;
    }

    void set_kolom(const int& kolom) {
        this->kolom = kolom;
    }

    void set_data(const int& baris, const int& kolom, Larik2D& data) {
        for (size_t i = 0; i < baris; i++)
        {
            for (size_t j = 0; j < kolom; j++)
            {
                this->data[i][j] = data[i][j];
            }
        }
    }

    void const print_data() {
        for (size_t i = 0; i < baris; i++)
        {
            for (size_t j = 0; j < kolom; j++)
            {
                std::cout << data[i][j] << " ";
            }
            std::cout << "\n";
        }
    }

    void input_matriks(const std::string& nama) {
        baris = Helper::inputInteger("Input baris " + nama + ": ");
        kolom = Helper::inputInteger("Input kolom " + nama + ": ");

        for (size_t i = 0; i < baris; i++)
        {
            for (size_t j = 0; j < kolom; j++)
            {
                data[i][j] = Helper::inputInteger("Input data [" +std::to_string(i+1)+"]["+std::to_string(j+1)+"]: ");
            }
        }
    }

    void ubah_entri_input(const std::string& nama) {
        Helper::print("Ubah entri matriks " + nama);
        for (size_t i = 0; i < baris; i++)
        {
            for (size_t j = 0; j < kolom; j++)
            {
                data[i][j] = Helper::inputInteger("Input data [" +std::to_string(i)+"]["+std::to_string(j)+"]: ");
            }
        }
    }

    static Matriks kali_matriks_return(const Matriks& A, const Matriks& B) {
        Matriks hasil(A.get_baris(), B.get_kolom());

        for (int i = 0; i < A.get_baris(); ++i) {
            for (int j = 0; j < B.get_kolom(); ++j) {
                for (int k = 0; k < A.get_kolom(); ++k) {
                    hasil.data[i][j] += A.data[i][k] * B.data[k][j];
                }
            }
        }

        return hasil;
    }

    void kali_matriks_void(const Matriks& matriks_lain) {
        const Matriks matriks_hasil = kali_matriks_return(*this, matriks_lain);
        *this = matriks_hasil;
    } //gak jadi buat copy cons :p

    static Matriks tambah_matriks_return(const Matriks& A, const Matriks& B) {
        Matriks hasil(A.get_baris(), A.get_kolom());

        for (int i = 0; i < A.get_baris(); ++i) {
            for (int j = 0; j < A.get_kolom(); ++j) {
                hasil.data[i][j] =
                    A.data[i][j] + B.data[i][j];
            }
        }

        return hasil;
    }

    void tambah_matriks_void(const Matriks& matriks_lain) {
        const Matriks matriks_hasil = tambah_matriks_return(*this, matriks_lain);
        *this = matriks_hasil;
    }
};

class Menu {
public:
    static void print_menu() {
        std::cout << "\n============= MENU ===============\n";
        std::cout << "1. CONSTRUCTOR KONSTANTA\n";
        std::cout << "2. SETTER KONSTANTA\n";
        std::cout << "3. SETTER INPUT LUAR\n";
        std::cout << "4. INPUT DALAM\n";
        std::cout << "0. KELUAR\n";
        std::cout << "==================================\n";
    }

    static void run_menu() {
        int choice = -1;

        // Emulasi do-while
        while (true) {
            Menu::print_menu();
            choice = Helper::inputInteger(": ");

            switch (choice) {
                case 1: {
                    // Constructor dengan parameter
                    int baris = 3;
                    int kolom = 3;

                    Larik2D data_A = {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                    };

                    Larik2D data_B = {
                        {1, 0, 0},
                        {0, 1, 0},
                        {0, 0, 1}
                    };

                    try {
                        Matriks A(baris, kolom, data_A);
                        Matriks B(baris, kolom, data_B);

                        Menu::demo_matriks(A, B);
                    }
                    catch (const std::invalid_argument& e) {
                        std::cout << "Error: " << e.what() << '\n';
                    }
                    catch (const std::out_of_range& e) {
                        std::cout << "Error: " << e.what() << '\n';
                    }

                    break;
                }

                case 2: {
                    // Setter dengan data konstanta
                    Matriks A;
                    Matriks B;

                    int baris = 3;
                    int kolom = 3;

                    Larik2D data_A = {
                        {3, 8, 11},
                        {0, 9, 2},
                        {4, 5, 14}
                    };

                    Larik2D data_B = {
                        {8, 9, 1},
                        {0, 3, 5},
                        {0, 0, 20}
                    };

                    try {
                        A.set_baris(baris);
                        A.set_kolom(kolom);
                        A.set_data(baris, kolom, data_A);

                        B.set_baris(baris);
                        B.set_kolom(kolom);
                        B.set_data(baris, kolom, data_B);

                        Menu::demo_matriks(A, B);
                    }
                    catch (const std::invalid_argument& e) {
                        std::cout << "Error: " << e.what() << '\n';
                    }
                    catch (const std::out_of_range& e) {
                        std::cout << "Error: " << e.what() << '\n';
                    }

                    break;
                }

                case 3: {
                    // Input matriks melalui class Helper
                    try {
                        Matriks A; A.input_matriks("A");
                        Matriks B; B.input_matriks("B");

                        Menu::demo_matriks(A, B);
                    }
                    catch (const std::invalid_argument& e) {
                        std::cout << "Error: " << e.what() << '\n';
                    }
                    catch (const std::out_of_range& e) {
                        std::cout << "Error: " << e.what() << '\n';
                    }

                    break;
                }

                case 4: {
                    // Input matriks melalui method class Matriks
                    try {
                        Matriks A;
                        A.set_baris(Helper::inputInteger("Input baris A: "));
                        A.set_kolom(Helper::inputInteger("Input kolom A: "));

                        Larik2D data_baru;
                        for (size_t i = 0; i < A.get_baris(); i++)
                        {
                            for (size_t j = 0; j < A.get_kolom();j++)
                            {
                                data_baru[i][j] = Helper::inputInteger("Input data [" +std::to_string(i)+"]["+std::to_string(j)+"]: ");
                            }
                        }
                        A.set_data(A.get_baris(), A.get_kolom(), data_baru);

                        Matriks B;
                        B.set_baris(Helper::inputInteger("Input baris B: "));
                        B.set_kolom(Helper::inputInteger("Input kolom B: "));

                        for (size_t i = 0; i < B.get_baris(); i++)
                        {
                            for (size_t j = 0; j < B.get_kolom();j++)
                            {
                                data_baru[i][j] = Helper::inputInteger("Input data [" +std::to_string(i)+"]["+std::to_string(j)+"]: ");
                            }
                        }
                        B.set_data(B.get_baris(), B.get_kolom(), data_baru);

                        Menu::demo_matriks(A, B);
                    }
                    catch (const std::invalid_argument& e) {
                        std::cout << "Error: " << e.what() << '\n';
                    }
                    catch (const std::out_of_range& e) {
                        std::cout << "Error: " << e.what() << '\n';
                    }

                    break;
                }

                case 0:
                    std::cout << "Terimakasih\n";
                    break;

                default:
                    break;
            }

            if (choice == 0) {
                break;
            }
        }
    }

    static void demo_matriks(Matriks& A, Matriks& B) {
        std::cout << "Matriks A:\n";
        A.print_data();
        std::cout << '\n';

        std::cout << "Matriks B:\n";
        B.print_data();
        std::cout << '\n';

        // Salin A agar dapat digunakan kembali untuk pengujian
        Matriks A2(
            A.get_baris(),
            A.get_kolom(),
            A.get_data()
        );

        // PENJUMLAHAN TES

        try {
            Matriks C = Matriks::tambah_matriks_return(A, B);

            std::cout << "Matriks C = A + B:\n";
            C.print_data();
            std::cout << '\n';
        }
        catch (const std::invalid_argument& e) {
            std::cout << "Error: " << e.what() << '\n';
        }
        catch (const std::out_of_range& e) {
            std::cout << "Error: " << e.what() << '\n';
        }

        try {
            A.tambah_matriks_void(B);

            std::cout << "Matriks A = A + B:\n";
            A.print_data();
            std::cout << '\n';
        }
        catch (const std::invalid_argument& e) {
            std::cout << "Error: " << e.what() << '\n';
        }
        catch (const std::out_of_range& e) {
            std::cout << "Error: " << e.what() << '\n';
        }

        std::cout << "=================\n";

        // PERKALIAN TES

        try {
            Matriks D = Matriks::kali_matriks_return(A2, B);

            std::cout << "Matriks D = A * B:\n";
            D.print_data();
            std::cout << '\n';
        }
        catch (const std::invalid_argument& e) {
            std::cout << "Error: " << e.what() << '\n';
        }
        catch (const std::out_of_range& e) {
            std::cout << "Error: " << e.what() << '\n';
        }

        try {
            A2.kali_matriks_void(B);

            std::cout << "Matriks A = A * B:\n";
            A2.print_data();
            std::cout << '\n';
        }
        catch (const std::invalid_argument& e) {
            std::cout << "Error: " << e.what() << '\n';
        }
        catch (const std::out_of_range& e) {
            std::cout << "Error: " << e.what() << '\n';
        }
    }
};

int main() {
    Menu::run_menu();
}