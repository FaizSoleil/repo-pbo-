"""
*   Nama file : KoordinatKartesian.py
*   Nama Kelompok:
*     - Muhammad Athar Alfarisi (140810250005)
*     - Muhammad Faiz Hariy (140810250029)
*     - Gibraldi Zilal Fachry (140810250038)
*   Tanggal buat : 28 September 2026
*   Deskripsi : Implementasi komunikasi antar objek kasus Koordinat Kartesian.
"""

class Koordinat :
    def __init__(self, absis=0, ordinat=0):
        self.__absis = absis
        self.__ordinat = ordinat

    def inputKoordinat(self):
        self.__absis = float(input("Nilai Absis: "))
        self.__ordinat = float(input("Nilai Ordinat: "))

    def setKoordinat(self, absis, ordinat):
        self.__absis = absis
        self.__ordinat = ordinat

    def setAbsis(self, absis):
        self.__absis = absis

    def setOrdinat(self, ordinat):
        self.__ordinat = ordinat

    def getAbsis(self):
        return self.__absis

    def getOrdinat(self):
        return self.__ordinat

    def printKoordinat(self, nama_titik):
        print(f"Koordinat {nama_titik} = ({self.__absis:.2f}, {self.__ordinat:.2f})")

    def titikTengahVoid(self, p1, p2):
        self.__absis = (p1.getAbsis() + p2.getAbsis()) / 2
        self.__ordinat = (p1.getOrdinat() + p2.getOrdinat()) / 2

    def titikTengahReturn(self, p):
        hasil = Koordinat()
        hasil.setAbsis((p.getAbsis() + self.__absis) / 2)
        hasil.setOrdinat((p.getOrdinat() + self.__ordinat) / 2)
        return hasil

    def cerminSumbuXVoid(self, p):
        self.__absis = p.getAbsis()
        self.__ordinat = -(p.getOrdinat())

    def cerminSumbuXReturn(self):
        hasil = Koordinat()
        hasil.setAbsis(self.__absis)
        hasil.setOrdinat(-(self.__ordinat))
        return hasil

    def cerminSumbuYVoid(self, p):
        self.__absis = -(p.getAbsis())
        self.__ordinat = p.getOrdinat()

    def cerminSumbuYReturn(self):
        hasil = Koordinat()
        hasil.setAbsis(-(self.__absis))
        hasil.setOrdinat(self.__ordinat)
        return hasil

    def jarakTitikVoid(self, p1, p2, jarak):
        jarak = ((p2.getAbsis() - p1.getAbsis()) ** 2 + (p2.getOrdinat() - p1.getOrdinat()) ** 2) ** 0.5
        print(f"Jarak antara 2 titik: {jarak:.2f}")

    def jarakTitikReturn(self, p):
        jarak = ((p.getAbsis() - self.__absis) ** 2 + (p.getOrdinat() - self.__ordinat) ** 2) ** 0.5
        return jarak

class Menu:
    @staticmethod
    def displayMenu():
        print("\n==================================================")
        print("   PENGOLAHAN KOORDINAT KARTESIUS (VOID & RETURN)   ")
        print("==================================================")
        print("1. Constuctor, Mencari Titik Tengah (Void & Return)")
        print("2. Input Dalam, Mencari Jarak Titik (Void & Return)")
        print("3. Input Luar, Mencari Cermin Sumbu X (Void & Return)")
        print("4. Input Luar, Mencari Cermin Sumbu Y (Void & Return)")
        print("5. Keluar")
        print("==================================================")

    @staticmethod
    def menu():

        while True:
            Menu.displayMenu()
            pilihan = input("Pilih menu (1-5): ")

            if pilihan == "1":
                p1 = Koordinat(5, 3)
                p2 = Koordinat(9, 7)
                p3 = Koordinat()
                p4 = Koordinat()

                print("== CONTRUCTOR KONSTANTA ==\n")
                p1.printKoordinat("A")
                p2.printKoordinat("B")

                print("\nKoordinat Titik Tengah: \n")
                print("(VOID): ")
                p3.titikTengahVoid(p1, p2)
                p3.printKoordinat("T")

                print("\n(RETURN): ")
                p4 = p1.titikTengahReturn(p2)
                p4.printKoordinat("T")

            elif pilihan == "2":
                p1 = Koordinat()
                p2 = Koordinat()
                p3 = Koordinat()
                jarak = 0.0

                print("== INPUT DALAM ==\n")
                p1.inputKoordinat()
                p2.inputKoordinat()
                p1.printKoordinat("A")
                p2.printKoordinat("B")

                print("\nJarak Titik: \n")
                print("(VOID): ")
                p3.jarakTitikVoid(p1, p2, jarak)

                print("\n(RETURN): ")
                jarak = p1.jarakTitikReturn(p2)
                print(f"Jarak antara 2 titik: {jarak:.2f}\n")

            elif pilihan == "3":
                p1 = Koordinat()
                p2 = Koordinat()
                p3 = Koordinat()

                print("== INPUT LUAR ==\n")
                p1.setAbsis(float(input("Nilai Absis A: ")))
                p1.setOrdinat(float(input("Nilai Ordinat A: ")))
                p2.setAbsis(float(input("Nilai Absis B: ")))
                p2.setOrdinat(float(input("Nilai Ordinat B: ")))
                p1.printKoordinat("A")
                p2.printKoordinat("B")
                
                print("\nCermin Sumbu X: \n")
                print("(VOID): ")
                p3.cerminSumbuXVoid(p1)
                p3.printKoordinat("A'")
                
                print("\n(RETURN): ")
                p4 = p2.cerminSumbuXReturn()
                p4.printKoordinat("B'")

            elif pilihan == "4":
                p1 = Koordinat()
                p2 = Koordinat()
                p3 = Koordinat()

                print("== INPUT LUAR ==\n")
                p1.setAbsis(float(input("Nilai Absis A: ")))
                p1.setOrdinat(float(input("Nilai Ordinat A: ")))
                p2.setAbsis(float(input("Nilai Absis B: ")))
                p2.setOrdinat(float(input("Nilai Ordinat B: ")))
                p1.printKoordinat("A")
                p2.printKoordinat("B")
                
                print("\nCermin Sumbu Y: \n")
                print("(VOID):")
                p3.cerminSumbuYVoid(p1)
                p3.printKoordinat("A'")
                
                print("\n(RETURN): ")
                p4 = p2.cerminSumbuYReturn()
                p4.printKoordinat("B'")

            elif pilihan == "5":
                print("Terima kasih telah menggunakan program ini!")
                break

            else:
                print("Pilihan tidak valid, silakan coba lagi.")


if __name__ == "__main__":
    Menu.menu()