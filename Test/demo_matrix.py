from Adafruit_LED_Backpack import Matrix8x16
from matrix16x16 import Matrix16x16

display = Matrix8x16.Matrix8x16(address=0x70)
display.begin()
display.set_brightness(3)

matrix = Matrix16x16(display, None)
matrix.write_string("AAABBBCCCABC=)")


