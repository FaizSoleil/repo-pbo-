// Nama file : KoordinatKartesian.cpp
// Nama Kelompok:
// - Muhammad Athar Alfarisi (140810250005)
// - Muhammad Faiz Hariy (140810250029)
// - Gibraldi Zilal Fachry (140810250038)
// Tanggal buat : 28 September 2026
// Deskripsi : Implementasi komunikasi antar objek kasus Koordinat Kartesian.

#include <iostream>
#include <limits>
#include <cstdio>
#include <cmath>
#include <string>

class InputHelper;
class Koordinat;

class InputHelper
{
    public:
    static float inputFloat(std::string message) {
        float input;

        while (true)
        {
            std::cout << message;
            std::cin >> input;
            
            if (std::cin.fail())
            {
                std::cout << "Invalid input, please enter a number.\n\n";
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

                std::cin >> input;
            }
            
            break;
        }
        
        return input;
    }

    static int inputInteger(std::string message) {
        int input;

        while (true)
        {
            std::cout << message;
            std::cin >> input;
            
            if (std::cin.fail())
            {
                std::cout << "Invalid input, please enter a number.\n\n";
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

                continue;
            }
            break;
        }
        
        return input;
    }

    static void print(std::string message) {std::cout << message << "\n";};
};

class Koordinat
{
private:
    float absis;
    float ordinat;
public:

    Koordinat() = default;
    Koordinat(float absis, float ordinat) {
        this->absis = absis;
        this->ordinat = ordinat;
    }

    void inputKoordinat() {
        absis = InputHelper::inputFloat("Nilai Absis: ");
        ordinat = InputHelper::inputFloat("Nilai Ordinat: ");
    }

    void setKoordinat(float absis, float ordinat) {
        this->absis = absis;
        this->ordinat = ordinat;
    }

    void setAbsis(float absis) {
        this->absis = absis;
    }

    void setOrdinat(float ordinat) {
        this->ordinat = ordinat;
    }

    float getAbsis() const {
        return this->absis;
    }

    float getOrdinat() const {
        return this->ordinat;
    }

    void printKoordinat(std::string nama) {
        printf("Koordinat %s = (%.2f, %.2f)\n", nama.c_str(), this->absis, this->ordinat);
    }

    void titikTengahVoid (Koordinat& p1, Koordinat& p2) {
        this->absis = (p1.getAbsis() + p2.getAbsis()) / 2.0f;
        this->ordinat = (p1.getOrdinat() + p2.getOrdinat()) / 2.0f;
    }

    Koordinat titikTengahReturn (Koordinat& p) {
        Koordinat hasil;

        hasil.absis = (p.getAbsis() + this->absis) / 2.0f;
        hasil.ordinat = (p.getOrdinat() + this->ordinat) / 2.0f;

        return hasil;
    }

    void cerminSumbuXVoid(Koordinat& p) {
        this->absis = p.getAbsis();
        this->ordinat = -(p.getOrdinat());
    }

    Koordinat cerminSumbuXReturn() {
        Koordinat hasil;
        
        hasil.absis = this->absis;
        hasil.ordinat = -(this->ordinat);
        return hasil;
    }

    void cerminSumbuYVoid(Koordinat& p) {
        this->absis = -(p.getAbsis());
        this->ordinat = p.getOrdinat();
    }

    Koordinat cerminSumbuYReturn() {
        Koordinat hasil;
        
        hasil.absis = -(this->ordinat);
        hasil.ordinat = this->absis;
        return hasil;
    }

    void jarakTitikVoid(Koordinat& p1, Koordinat& p2, float& jarak) {
        float dx = p2.getAbsis() - p1.getAbsis();
        float dy = p2.getOrdinat() - p1.getOrdinat();

        jarak = sqrt(dx*dx + dy*dy);
        printf("Jarak antara 2 titik: %.2f", jarak);
    }

    float jarakTitikReturn(Koordinat p) {
        float jarak = sqrt(pow((p.getAbsis() - this->absis), 2) + pow(p.getOrdinat() - this->ordinat, 2));
        return jarak;
    }

    void displayMenu() {
        InputHelper::print("\n==================================================");
        InputHelper::print("   PENGOLAHAN KOORDINAT KARTESIUS (VOID & RETURN)   ");
        InputHelper::print("==================================================");
        InputHelper::print("1. Constuctor, Mencari Titik Tengah (Void & Return)");
        InputHelper::print("2. Input Dalam, Mencari Jarak Titik (Void & Return)");
        InputHelper::print("3. Input Luar, Mencari Cermin Sumbu X (Void & Return)");
        InputHelper::print("4. Input Luar, Mencari Cermin Sumbu Y (Void & Return)");
        InputHelper::print("5. Keluar");
        InputHelper::print("==================================================");
    }

    void menu() {
        int pilihan = -1;
        while (pilihan != 5)
        {
            displayMenu();
            pilihan = InputHelper::inputInteger("Pilih menu (1-5): ");

            switch (pilihan)
            {
            case 1:{

            
                Koordinat p1 = Koordinat(5, 3);
                Koordinat p2 = Koordinat(9, 7);
                Koordinat p3 = Koordinat();
                Koordinat p4 = Koordinat();

                InputHelper::print("== CONTRUCTOR KONSTANTA ==\n");
                p1.printKoordinat("A");
                p2.printKoordinat("B");

                InputHelper::print("\nKoordinat Titik Tengah: \n");
                InputHelper::print("(VOID): ");
                p3.titikTengahVoid(p1, p2);
                p3.printKoordinat("T");

                InputHelper::print("\n(RETURN): ");
                p4 = p1.titikTengahReturn(p2);
                p4.printKoordinat("T");
                break;
            }
            case 2: {
                Koordinat p1 = Koordinat();
                Koordinat p2 = Koordinat();
                Koordinat p3 = Koordinat();
                float jarak = 0.0;

                InputHelper::print("== INPUT DALAM ==\n");
                p1.inputKoordinat();
                p2.inputKoordinat();
                p1.printKoordinat("A");
                p2.printKoordinat("B");

                InputHelper::print("\nJarak Titik: \n");
                InputHelper::print("(VOID): ");
                p3.jarakTitikVoid(p1, p2, jarak);

                InputHelper::print("\n(RETURN): ");
                jarak = p1.jarakTitikReturn(p2);
                printf("Jarak antara 2 titik: %.2f\n", jarak);
                break;
            }
            case 3: {
                Koordinat p1  = Koordinat();
                Koordinat p2 = Koordinat();
                Koordinat p3 = Koordinat();

                InputHelper::print("== INPUT LUAR ==\n");
                p1.setAbsis(InputHelper::inputFloat("Nilai Absis A: "));
                p1.setOrdinat(InputHelper::inputFloat("Nilai Ordinat A: "));
                p2.setAbsis(InputHelper::inputFloat("Nilai Absis B: "));
                p2.setOrdinat(InputHelper::inputFloat("Nilai Ordinat B: "));
                p1.printKoordinat("A");
                p2.printKoordinat("B");
                
                InputHelper::print("\nCermin Sumbu X: \n");
                InputHelper::print("(VOID): ");
                p3.cerminSumbuXVoid(p1);
                p3.printKoordinat("A'");
                
                InputHelper::print("\n(RETURN): ");
                Koordinat p4 = p2.cerminSumbuXReturn();
                p4.printKoordinat("B'");
                break;
            }
            case 4: {
                Koordinat p1 = Koordinat();
                Koordinat p2 = Koordinat();
                Koordinat p3 = Koordinat();

                InputHelper::print("== INPUT LUAR ==\n");
                p1.setAbsis(InputHelper::inputFloat("Nilai Absis A: "));
                p1.setOrdinat(InputHelper::inputFloat("Nilai Ordinat A: "));
                p2.setAbsis(InputHelper::inputFloat("Nilai Absis B: "));
                p2.setOrdinat(InputHelper::inputFloat("Nilai Ordinat B: "));
                p1.printKoordinat("A");
                p2.printKoordinat("B");
                
                InputHelper::print("\nCermin Sumbu Y: \n");
                InputHelper::print("(VOID):");
                p3.cerminSumbuYVoid(p1);
                p3.printKoordinat("A'");
                
                InputHelper::print("\n(RETURN): ");
                Koordinat p4 = p2.cerminSumbuYReturn();
                p4.printKoordinat("B'");
                break;
            }
            case 5:
                InputHelper::print("Terima kasih telah menggunakan program ini!");
                break;

            default:
                InputHelper::print("Pilihan tidak valid, silakan coba lagi.");
                break;
            }
        }
    }
};

int main() {
    Koordinat koordinat;
    koordinat.menu();
}
