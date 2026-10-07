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
        self.matriks = [[0.0 for _ in range(kolom)] for _ in range(baris)]

    def set_baris(self, baris):
        self.baris = baris
        self.matriks= [[0.0 for _ in range(self.kolom)] for _ in range(baris)]

    def set_kolom(self, kolom):
        self.kolom = kolom
        self.matriks = [[0.0 for _ in range(kolom)] for _ in range(self.baris)]

    def get_baris(self):
        return self.baris

    def get_kolom(self):
        return self.kolom

    def set_matriks(self, value):
        self.matriks = value

    def get_matriks(self):
        return self.matriks

    def tambah_matriks_void(self, matriks_b):
        if self.kolom != matriks_b.getks_kolom() or self.baris != matriks_b.get_baris():
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
    def run_menu():
        return "var"


if __name__=="__main__":
    Menu.run_menu()