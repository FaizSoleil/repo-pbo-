#    Nama File    : SelisihWaktu.py
#    Nama Kelompok:
#      - Muhammad Athar Alfarisi (140810250005)
#      - Muhammad Faiz Hariy Nugroho (140810250029)
#      - Gibraldi Zilal Fachry (140810250038)
#    Tanggal Buat : 07 Oktober 2026
#    Deskripsi    : Program kalkulasi selisih waktu

class Matriks:
    def __init__(self, baris, kolom):
        self.baris = baris
        self.kolom = kolom
        self.matriks[baris][kolom] = [[0.0 for _ in range[kolom]]]

    def set_baris(self, baris):
        self.baris = baris

    def set_kolom(self, kolom):
        self.kolom = kolom

    def get_baris(self):
        return self.baris

    def get_kolom(self):
        return self.kolom

    def set_matriks(self):
        for i in range(self.baris):
            for j in range(self.kolom):
                self.matriks[i][j] = float(input("Meow: "))

    def get_matriks(self):
        return self.matriks