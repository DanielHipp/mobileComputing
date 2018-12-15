import time

from PIL import Image
from PIL import ImageDraw	
from Adafruit_LED_Backpack import Matrix8x16




def draw_img(display, np_array):
	print(np_array.shape)
	display.clear()
	# Run through each pixel individually and turn it on.
	for x in range(8):
		for y in range(16):
			display.set_pixel(x, y, np_array[x,y])
	display.write_display()
	return
	
	
display = Matrix8x16.Matrix8x16(adress=0x70)
display.begin()

testIcon = np.array([[0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0],
					 [0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0],
					 [0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0],
					 [1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0],
					 [0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0],
					 [0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0],
					 [0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0],
					 [0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1]])

draw_img(display, testIcon)