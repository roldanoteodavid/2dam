class Ficha:
    def __init__(self, estado='tb_a1', visible=False, x=0, y=0):
        self.estado = estado
        self.visible = visible
        self.x = x
        self.y = y

    def get_pos(self):
        return self.estado.split("_")[1]

    def get_tipo(self):
        return self.estado.split("_")[0][0]

    def get_color(self):
        return self.estado.split("_")[0][1]

    def image_location(self):
        return "images/" + self.estado.split("_")[0] + ".png"
