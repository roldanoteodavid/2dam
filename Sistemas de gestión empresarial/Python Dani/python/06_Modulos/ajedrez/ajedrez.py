import sys
from tablero import Tablero

def print_help():
    print("ajedrez.py -help print this help")
    print("ajedrez.py pinta el tablero inicial")
    print("ajedrez.py -n X pinta X damas en el tablero sin darse jaque")

if __name__ == "__main__":
    if len(sys.argv) == 2 and sys.argv[1] == "-help":
        print_help()
    elif len(sys.argv) == 1:
        tablero = Tablero()
        tablero.pintar_tablero()
    elif len(sys.argv) == 3 and sys.argv[1] == "-n":
        try:
            num_damas = int(sys.argv[2])
            tablero = Tablero()
            tablero.pintar_tablero(num_damas)
        except ValueError:
            print("Error: El argumento después de -n debe ser un número entero.")
    else:
        print("Error: Comando no reconocido. Ejecuta 'python3 ajedrez.py -help' para obtener ayuda.")