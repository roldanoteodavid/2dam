class Ficha:
    def __init__(self, x, y, imagen, visible=True):
        self.X = x
        self.Y = y
        self.imagen = imagen
        self.visible = visible
    
    def image_location(self):
        # Retorna la ruta relativa a la imagen de la ficha
        return f'../images/{self.imagen}'