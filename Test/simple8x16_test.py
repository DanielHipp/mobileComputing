import time
import numpy as np

from PIL import Image
from PIL import ImageDraw	
from Adafruit_LED_Backpack import Matrix8x16




def draw_img(display, np_array):
	assert(np_array.shape == (8,16))
	display.clear()
	# Run through each pixel individually and turn it on.
	for x in range(8):
		for y in range(16):
			display.set_pixel(x, y, np_array[x,y])
	display.write_display()
	return

def draw_periodic(display,full_array,times=200):
	max = full_array.shape[1]
	array = np.zeros((8,16))
	for i in range(times):
		for k in range(16):
			array[:,k] = full_array[:,(k+i) % max]
		draw_img(display, array)
		time.sleep(0.125)
	return

	
display = Matrix8x16.Matrix8x16(address=0x70)
display.begin()

testIcon = np.array([[0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0],
					 [0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0],
					 [0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0],
					 [1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0],
					 [0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0],
					 [0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0],
					 [0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0],
					 [0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1]])

#draw_img(display, testIcon)

full_sine = np.array([[0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1],
			[0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1],
			[0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0],
			[0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,1,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0]])

draw_periodic(display, full_sine)
