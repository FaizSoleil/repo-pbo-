#    Nama File    : SelisihWaktu.py
#    Nama Kelompok:
#      - Muhammad Athar Alfarisi (140810250005)
#      - Muhammad Faiz Hariy Nugroho (140810250029)
#      - Gibraldi Zilal Fachry (140810250038)
#    Tanggal Buat : 07 Oktober 2026
#    Deskripsi    : Program kalkulasi selisih waktu

import copy

class Matriks:
    def __init__(self, baris, kolom):
        self.baris = baris
        self.kolom = kolom
        self.matriks = [[0.0 for _ in range(kolom)] for _ in range(baris)]

    def set_baris(self, baris):
        self.baris = baris
        self.matriks = [[0.0 for _ in range(self.kolom)] for _ in range(baris)]

    def set_kolom(self, kolom):
        self.kolom = kolom
        self.matriks = [[0.0 for _ in range(kolom)] for _ in range(self.baris)]

    def input_baris(self):
        while True:
            try:
                baris = int(input("Masukkan jumlah baris: "))
                if baris > 0:
                    break
                print("Jumlah baris harus lebih dari 0!")
            except ValueError:
                print("Masukkan bilangan bulat!")
        self.set_baris(baris)

    def input_kolom(self):
        while True:
            try:
                kolom = int(input("Masukkan jumlah kolom: "))
                if kolom > 0:
                    break
                print("Jumlah kolom harus lebih dari 0!")
            except ValueError:
                print("Masukkan bilangan bulat!")
        self.set_kolom(kolom)

    def get_baris(self):
        return self.baris

    def get_kolom(self):
        return self.kolom

    def set_matriks(self, value):
        self.matriks = value

    def input_matriks(self):
        for i in range(self.baris):
            for j in range(self.kolom):
                while True:
                    try:
                        self.matriks[i][j] = float(
                            input(f"Masukkan elemen [{i}][{j}]: ")
                        )
                        break
                    except ValueError:
                        print("Masukkan angka yang valid!")

    def get_matriks(self):
        return self.matriks

    def tampilkan(self, label="Matriks"):
        print(f"{label} ({self.baris}x{self.kolom}):")
        for row in self.matriks:
            print("  " + " ".join(f"{x:8.2f}" for x in row))

    def tambah_matriks_void(self, matriks_b):
        if self.kolom != matriks_b.get_kolom() or self.baris != matriks_b.get_baris():
            print("Baris dan kolom kedua matriks tidak sama! Operasi tidak bisa dilakukan!")
            return

        for i in range(self.baris):
            for j in range(self.kolom):
                self.matriks[i][j] += matriks_b.matriks[i][j]

    def tambah_matriks_return(self, matriks_b):
        if self.kolom != matriks_b.kolom or self.baris != matriks_b.baris:
            print("Baris dan kolom kedua matriks tidak sama! Operasi tidak bisa dilakukan!")
            return None

        hasil = Matriks(self.baris, self.kolom)
        for i in range(self.baris):
            for j in range(self.kolom):
                hasil.matriks[i][j] = self.matriks[i][j] + matriks_b.matriks[i][j]
        return hasil

    def kali_matriks_void(self, matriks_b):
        if self.kolom != matriks_b.get_baris():
            print("Kolom matriks A dan baris matriks B tidak sama! Operasi tidak bisa dilakukan!")
            return

        hasil = [[0.0 for _ in range(matriks_b.get_kolom())] for _ in range(self.baris)]

        for i in range(self.baris):
            for j in range(matriks_b.get_kolom()):
                for k in range(self.kolom):
                    hasil[i][j] += self.matriks[i][k] * matriks_b.matriks[k][j]

        self.matriks = hasil
        self.kolom = matriks_b.get_kolom()

    def kali_matriks_return(self, matriks_b):
        if self.kolom != matriks_b.get_baris():
            print("Kolom matriks A dan baris matriks B tidak sama! Operasi tidak bisa dilakukan!")
            return None

        hasil = Matriks(self.baris, matriks_b.get_kolom())

        for i in range(self.baris):
            for j in range(matriks_b.get_kolom()):
                for k in range(self.kolom):
                    hasil.matriks[i][j] += self.matriks[i][k] * matriks_b.matriks[k][j]

        return hasil


class Menu:
    @staticmethod
    def print_menu():
        print("=== MENU ===")
        print("1. Constructor konstanta")
        print("2. Setter konstanta")
        print("3. Setter input luar")
        print("4. Input dalam")
        print("0. Berhenti")

    @staticmethod
    def run_menu():
        choice = -1

        while choice != 0:
            Menu.print_menu()
            try:
                choice = int(input(": "))
            except ValueError:
                print("Masukkan angka pilihan menu!\n")
                choice = -1
                continue

            a = None
            b = None

            match choice:
                case 1:
                    a = Matriks(2, 3)
                    b = Matriks(3, 2)
                    for i in range(a.get_baris()):
                        for j in range(a.get_kolom()):
                            a.get_matriks()[i][j] = i + j + 1.0
                    for i in range(b.get_baris()):
                        for j in range(b.get_kolom()):
                            b.get_matriks()[i][j] = (i + 1) * (j + 2.0)

                case 2:
                    a = Matriks(1, 1)
                    a.set_baris(2)
                    a.set_kolom(3)
                    a.set_matriks([[1.0, 2.0, 3.0],
                                   [4.0, 5.0, 6.0]])

                    b = Matriks(1, 1)
                    b.set_baris(3)
                    b.set_kolom(2)
                    b.set_matriks([[7.0, 8.0],
                                   [9.0, 10.0],
                                   [11.0, 12.0]])

                case 3:
                    daftar = []
                    for nama in ("A", "B"):
                        print(f"\n-- Input matriks {nama} --")
                        m = Matriks(1, 1)

                        while True:
                            try:
                                baris = int(input("Masukkan jumlah baris: "))
                                if baris > 0:
                                    break
                                print("Jumlah baris harus lebih dari 0!")
                            except ValueError:
                                print("Masukkan bilangan bulat!")

                        while True:
                            try:
                                kolom = int(input("Masukkan jumlah kolom: "))
                                if kolom > 0:
                                    break
                                print("Jumlah kolom harus lebih dari 0!")
                            except ValueError:
                                print("Masukkan bilangan bulat!")

                        m.set_baris(baris)
                        m.set_kolom(kolom)

                        data = []
                        for i in range(baris):
                            satu_baris = []
                            for j in range(kolom):
                                while True:
                                    try:
                                        satu_baris.append(
                                            float(input(f"Masukkan elemen [{i}][{j}]: "))
                                        )
                                        break
                                    except ValueError:
                                        print("Masukkan angka yang valid!")
                            data.append(satu_baris)
                        m.set_matriks(data)
                        daftar.append(m)
                    a, b = daftar

                case 4:
                    daftar = []
                    for nama in ("A", "B"):
                        print(f"\n-- Input matriks {nama} --")
                        m = Matriks(1, 1)
                        m.input_baris()
                        m.input_kolom()
                        m.input_matriks()
                        daftar.append(m)
                    a, b = daftar

                case 0:
                    print("Terimakasih")

                case _:
                    print("Pilihan tidak valid!\n")

            if a is not None and b is not None:
                print()
                a.tampilkan("Matriks A")
                b.tampilkan("Matriks B")

                print("\n--- A + B (return) ---")
                hasil = a.tambah_matriks_return(b)
                if hasil is not None:
                    hasil.tampilkan("Hasil")

                print("\n--- A + B (void, pada salinan A) ---")
                salinan = copy.deepcopy(a) 
                salinan.tambah_matriks_void(b)
                if salinan.get_matriks() != a.get_matriks():
                    salinan.tampilkan("Hasil")

                print("\n--- A x B (return) ---")
                hasil = a.kali_matriks_return(b)
                if hasil is not None:
                    hasil.tampilkan("Hasil")

                print("\n--- A x B (void, pada salinan A) ---")
                salinan = copy.deepcopy(a)
                salinan.kali_matriks_void(b)
                if salinan.get_kolom() == b.get_kolom():
                    salinan.tampilkan("Hasil")
                print()


if __name__ == "__main__":
    Menu.run_menu()