class Ficha:
    estado = 'tb_a1'
    visible = false
    x=0
    y=0

    def __init__(self, estado='tb_a1', visible):
        self.estado = estado
        self.visible = visible

    def get_pos(self):
        return estado.split("_")[1]

    def get_tipo(self):
        return estado.split("_")[0][0]

    def get_tipo(self):
        return estado.split("_")[0][1]

    def image_location():
        return "images/"+estado.split("")[0]+".png"
    