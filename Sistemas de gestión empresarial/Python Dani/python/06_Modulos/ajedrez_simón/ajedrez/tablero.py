from ficha import Ficha
from PIL import Image

class Tablero:
    def __init__(self, inicio):

        self.posfichas = []
        

        columnas = {'a': 0, 'b': 45, 'c': 90, 'd': 135, 'e': 180, 'f': 225, 'g': 270, 'h': 315}
        filas = {'1': 315, '2': 270, '3': 225, '4': 180, '5': 135, '6': 90, '7': 45, '8': 0}

        for posicion in inicio:

            pieza, posicion_tablero = posicion.split('_')
            columna, fila = posicion_tablero
            

            x = columnas[columna]
            y = filas[fila]


            imagen = f'{pieza}.png' 
            self.posfichas.append(Ficha(x, y, imagen))

    def pintar_tablero(self):

        tablero_img = Image.open('images/tablero.png').convert("RGBA")


        for ficha in self.posfichas:
            if ficha.visible:

                aux_img = Image.open(ficha.image_location()).convert("RGBA")
                tablero_img.alpha_composite(aux_img, (ficha.X, ficha.Y))


        tablero_img.save("jugada.png")

    def cambiar_posicion_ficha(self, posicion_actual, nueva_posicion):

        columnas = {'a': 0, 'b': 45, 'c': 90, 'd': 135, 'e': 180, 'f': 225, 'g': 270, 'h': 315}
        filas = {'1': 315, '2': 270, '3': 225, '4': 180, '5': 135, '6': 90, '7': 45, '8': 0}


        pieza_actual, posicion_tablero_actual = posicion_actual.split('_')
        columna_actual, fila_actual = posicion_tablero_actual


        _, posicion_tablero_nueva = nueva_posicion.split('_')
        columna_nueva, fila_nueva = posicion_tablero_nueva


        x_actual = columnas[columna_actual]
        y_actual = filas[fila_actual]
        x_nueva = columnas[columna_nueva]
        y_nueva = filas[fila_nueva]

        for ficha in self.posfichas:
            if ficha.X == x_actual and ficha.Y == y_actual and ficha.imagen.startswith(pieza_actual):
                ficha.X = x_nueva
                ficha.Y = y_nueva
                break
        else:
            print(f"No se encontró la ficha en la posición {posicion_actual}")

    def comer_ficha(self, posicion_actual, nueva_posicion):

        columnas = {'a': 0, 'b': 45, 'c': 90, 'd': 135, 'e': 180, 'f': 225, 'g': 270, 'h': 315}
        filas = {'1': 315, '2': 270, '3': 225, '4': 180, '5': 135, '6': 90, '7': 45, '8': 0}

        pieza_actual, posicion_tablero_actual = posicion_actual.split('_')
        columna_actual, fila_actual = posicion_tablero_actual


        _, posicion_tablero_nueva = nueva_posicion.split('_')
        columna_nueva, fila_nueva = posicion_tablero_nueva


        x_actual = columnas[columna_actual]
        y_actual = filas[fila_actual]
        x_nueva = columnas[columna_nueva]
        y_nueva = filas[fila_nueva]


        ficha_a_mover = None
        for ficha in self.posfichas:
            if ficha.X == x_actual and ficha.Y == y_actual and ficha.imagen.startswith(pieza_actual):
                ficha_a_mover = ficha
                break

        if ficha_a_mover:

            self.posfichas = [ficha for ficha in self.posfichas if not (ficha.X == x_nueva and ficha.Y == y_nueva)]

            ficha_a_mover.X = x_nueva
            ficha_a_mover.Y = y_nueva
        else:
            print(f"No se encontró la ficha en la posición {posicion_actual}")

    def pintar_reina(self, x):

        x = min(x, 8)

        columnas = {'a': 0, 'b': 45, 'c': 90, 'd': 135, 'e': 180, 'f': 225, 'g': 270, 'h': 315}
        filas = {'1': 315, '2': 270, '3': 225, '4': 180, '5': 135, '6': 90, '7': 45, '8': 0}


        posiciones_reinas = [
            ('d', 1), ('b', 2), ('e', 3), ('h', 4),
            ('f', 5), ('a', 6), ('c', 7), ('g', 8)
        ]

        self.posfichas.clear()
        for i in range(x):
            columna, fila = posiciones_reinas[i]
            self.posfichas.append(Ficha(columnas[columna], filas[str(fila)], 'db.png'))