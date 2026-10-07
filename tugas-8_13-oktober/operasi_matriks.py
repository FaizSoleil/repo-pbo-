#
#
#
#
# kopi

class Matriks:
    def __init__(self, baris, kolom):
        self.baris = baris
        self.kolom = kolom
        self.matriks[baris][kolom] = [[0.0 for _ in range[kolom]] for _ in range[baris]]

    def set_baris(self, baris):
        self.baris = baris
        self.matriks[baris][self.kolom] = [[0.0 for _ in range[self.kolom]] for _ in range[baris]]

    def set_kolom(self, kolom):
        self.kolom = kolom
        self.matriks[self.baris][kolom] = [[0.0 for _ in range[kolom]] for _ in range[self.baris]]

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


class Menu:
    @staticmethod
    def run_menu():
        return "var"


if __name__=="__main__":
    Main.run_menu()