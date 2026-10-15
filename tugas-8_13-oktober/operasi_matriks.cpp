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

class Matriks {
    private:
    static const int KAPASITAS = 100;

    int baris;
    int kolom;
    int data[KAPASITAS][KAPASITAS];

    public:
    Matriks(int baris, int kolom, int data[KAPASITAS][KAPASITAS]) {
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

    int getBaris() {
        return baris;
    }

    int getKolom() {
        return kolom;
    }

    void setBaris(int baris) {
        this->baris = baris;
    }

    void setKolom(int kolom) {
        this->kolom = kolom;
    }

    void setBaris(int baris, int kolom, int data[KAPASITAS][KAPASITAS]) {
        for (size_t i = 0; i < baris; i++)
        {
            for (size_t j = 0; j < kolom; j++)
            {
                this->data[i][j] = data[i][j];
            }
        }
    }

    void printData() {
        for (size_t i = 0; i < baris; i++)
        {
            for (size_t j = 0; j < kolom; j++)
            {
                std::cout << data[i][j] << " ";
            }
        }
    }
};

int main() {
    int data[100][100] = {{1,2,3},{4,5,6},{7,8,9}};

    Matriks test = Matriks(3, 3, data);
    test.printData();
}