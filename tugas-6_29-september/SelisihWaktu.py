"""
 *    Nama File    : SelisihWaktu.py
 *    Nama Kelompok:
 *      - Muhammad Athar Alfarisi (140810250005)
 *      - Muhammad Faiz Hariy Nugroho (140810250029)
 *      - Gibraldi Zilal Fachry (140810250038)
 *    Tanggal Buat : 28 September 2026
 *    Deskripsi    : Program kalkulasi selisih waktu
"""

class Waktu:
    def __init__(self, jam=0, menit=0, detik=0):
        self.__jam = int(jam)
        self.__menit = int(menit)
        self.__detik = int(detik)

    def setJam(self, jam):
        self.__jam = jam

    def setMenit(self, menit):
        self.__menit = menit

    def setDetik(self, detik):
        self.__detik = detik

    def setWaktu(self, jam, menit, detik):
        self.__jam = jam
        self.__menit = menit
        self.__detik = detik

    def inputWaktu(self):
        self.__jam = Helper.getValidInteger("Masukkan jam: ", 0, 23)
        self.__menit = Helper.getValidInteger("Masukkan menit: ", 0, 59)
        self.__detik = Helper.getValidInteger("Masukkan detik: ", 0, 59)

    def getJam(self):
        return self.__jam

    def getMenit(self):
            return self.__menit

    def getDetik(self):
            return self.__detik

    #output
    def printWaktu(self, name):
        print(f"Waktu {name}: {self.__jam:02d}:{self.__menit:02d}:{self.__detik:02d}")

    def hitungSelisihReturn(self, baru):
        hasil = Waktu()

        awal = self.__jam * 3600 + self.__menit * 60 + self.__detik
        akhir = baru.getJam() * 3600 + baru.getMenit() * 60 + baru.getDetik()
        selisih = abs(awal-akhir)

        # / -> floating point //-> integer
        hasil.setJam(selisih//3600)
        selisih %= 3600
        hasil.setMenit(selisih//60)
        selisih %= 60
        hasil.setDetik(selisih)

        return hasil

    def hitungSelisihVoid(self, waktuA, waktuB):
        awal = waktuA.getJam() * 3600 + waktuA.getMenit() * 60 + waktuA.getDetik();
        akhir = waktuB.getJam() * 3600 + waktuB.getMenit() * 60 + waktuB.getDetik();
        selisih = abs(awal - akhir);

        self.__jam = (selisih // 3600);
        selisih %= 3600;
        self.__menit = (selisih // 60);
        selisih %= 60;
        self.__detik = (selisih);


class Menu:
    @staticmethod
    def displayMenu():
        print("\n========== Menu ==========")
        print("1. Constructor Konstanta")
        print("2. Setter Konstanta ")
        print("3. Setter Input Luar")
        print("4. Input Dalam")
        print("0. Keluar")
        print("==========================")

    @staticmethod
    def menu(pilihan):
        if pilihan == 1:
            print("\n--- 1. Constructor Konstanta ---")
            wA = Waktu(8, 30, 0)
            wB = Waktu(10, 45, 15)

            wA.printWaktu('A')
            wB.printWaktu('B')

            selisihReturn = wA.hitungSelisihReturn(wB)
            print("Selisih (Return): ", end='')
            selisihReturn.printWaktu('S')

            selisihVoid = Waktu()
            selisihVoid.hitungSelisihVoid(wA, wB)
            print("Selisih (Void)  : ", end='')
            selisihVoid.printWaktu('S')
        
        elif pilihan == 2:
            print("\n--- 2. Setter Konstanta ---")
            wA = Waktu()
            wB = Waktu()

            wA.setWaktu(12, 15, 30)
            wB.setWaktu(15, 0, 45)

            wA.printWaktu('A')
            wB.printWaktu('B')

            selisihReturn = wA.hitungSelisihReturn(wB)
            print("Selisih (Return): ", end='')
            selisihReturn.printWaktu('S')

            selisihVoid = Waktu()
            selisihVoid.hitungSelisihVoid(wA, wB)
            print("Selisih (Void)  : ", end='')
            selisihVoid.printWaktu('S')

        elif pilihan == 3:
            print("\n--- 3. Setter Input Luar ---")
            wA = Waktu()
            wB = Waktu()

            print("Input Waktu A:")
            wA.setJam(Helper.getValidInteger("Masukkan jam: ", 0, 23))
            wA.setMenit(Helper.getValidInteger("Masukkan menit: ", 0, 59))
            wA.setDetik(Helper.getValidInteger("Masukkan detik: ", 0, 59))

            print("\nInput Waktu B:")
            wB.setJam(Helper.getValidInteger("Masukkan jam: ", 0, 23))
            wB.setMenit(Helper.getValidInteger("Masukkan menit: ", 0, 59))
            wB.setDetik(Helper.getValidInteger("Masukkan detik: ", 0, 59))

            print()
            wA.printWaktu('A')
            wB.printWaktu('B')

            selisihReturn = wA.hitungSelisihReturn(wB)
            print("Selisih (Return): ", end='')
            selisihReturn.printWaktu('S')

            selisihVoid = Waktu()
            selisihVoid.hitungSelisihVoid(wA, wB)
            print("Selisih (Void)  : ", end='')
            selisihVoid.printWaktu('S')

        elif pilihan == 4:
            print("\n--- 4. Input Dalam ---")
            wA = Waktu()
            wB = Waktu()

            print("Input Waktu A:")
            wA.inputWaktu()

            print("\nInput Waktu B:")
            wB.inputWaktu()

            print()
            wA.printWaktu('A')
            wB.printWaktu('B')

            selisihReturn = wA.hitungSelisihReturn(wB)
            print("Selisih (Return): ", end='')
            selisihReturn.printWaktu('S')

            selisihVoid = Waktu()
            selisihVoid.hitungSelisihVoid(wA, wB)
            print("Selisih (Void)  : ", end='')
            selisihVoid.printWaktu('S')

        elif pilihan == 0:
            print("Terima kasih...\n\n")

        else:
            print("Pilihan tidak valid, silakan coba lagi.")

    def runMenu():
        pilih = -1

        while pilih != 0:
            Menu.displayMenu()
            pilih = Helper.getValidInteger("Menu (pilih 0-4): ", 0, 4)
            Menu.menu(pilih)

class Helper:
    @staticmethod
    def getValidInteger(prompt, minValue=None, maxValue=None):
        while True:
            try:
                value = int(input(prompt))

                if minValue is not None and value < minValue:
                    print(f"Invalid input: Value must at least be {minValue}.")
                    continue

                if maxValue is not None and value > maxValue:
                    print(f"Invalid input: Value must at most be {maxValue}.")

                return value
        
            except ValueError:
                print("Invalid input: please enter a valid number")

def main():
    Menu.runMenu()



if __name__ == "__main__":
    main()