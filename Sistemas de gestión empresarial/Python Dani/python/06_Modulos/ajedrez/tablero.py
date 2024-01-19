class Tablero:

    def __init__(self):


    def pintar_tablero(self):
        tablero_img = Image.open('images/tablero.png').convert("RGBA")
        for ficha in self.posficha:
            if ficha.visible:
                aux_img = Image.open(ficha.image_location()).convert("RGBA")
                tablero_img.alpha_composite(aux_img, (ficha.X, ficha.Y))
            tablero_img.save("jugada.png")
    