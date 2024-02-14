from PIL import Image
from ficha import Ficha

class Tablero:

    def __init__(self):
        self.inicio = ['tb_a1', 'cb_b1', 'ab_c1', 'rb_d1', 'db_e1', 'ab_f1', 'cb_g1', 'tb_h1',
                       'pb_a2', 'pb_b2', 'pb_c2', 'pb_d2', 'pb_e2', 'pb_f2', 'pb_g2', 'pb_h2',
                       'pn_a7', 'pn_b7', 'pn_c7', 'pn_d7', 'pn_e7', 'pn_f7', 'pn_g7', 'pn_h7',
                       'tn_a8', 'cn_b8', 'an_c8', 'rn_d8', 'dn_e8', 'an_f8', 'cn_g8', 'tn_h8']
        self.posficha = []
        self.cargar_fichas()

    def cargar_fichas(self):
        for i, posicion in enumerate(self.inicio):
            fila = int(posicion[4]) - 1
            columna = ord(posicion[3].lower()) - ord('a')
            ficha = Ficha(estado=posicion, visible=True, x=columna * 45, y=fila * 45)
            self.posficha.append(ficha)

    def pintar_tablero(self):
        tablero_img = Image.open('images/tablero.png').convert("RGBA")
        for ficha in self.posficha:
            if ficha.visible:
                aux_img = Image.open(ficha.image_location()).convert("RGBA")
                tablero_img.paste(aux_img, (ficha.x, ficha.y), aux_img)
        tablero_img.save("jugada.png")

    def cambiar_posicion_ficha(self, ficha_actual, nueva_posicion):
        for ficha in self.posficha:
            if ficha.estado == ficha_actual:
                ficha.estado = nueva_posicion
                fila = 8 - int(nueva_posicion[4])
                columna = ord(nueva_posicion[3].lower()) - ord('a')
                ficha.x = columna * self.tamaño_casilla
                ficha.y = fila * self.tamaño_casilla
                break