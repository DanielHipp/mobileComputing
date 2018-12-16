import time
import numpy as np

from PIL import Image
from PIL import ImageDraw	
from Adafruit_LED_Backpack import Matrix8x16




def draw_img(display, np_array):
	if display is None:
		return
	assert(np_array.shape == (8,16))
	display.clear()
	# Run through each pixel individually and turn it on.
	for x in range(8):
		for y in range(16):
			display.set_pixel(x, y, np_array[x,y])
	display.write_display()
	return

def draw_periodic(display,full_array,times=200):
	if display is None:
		return
	max = full_array.shape[1]
	array = np.zeros((8,16))
	for i in range(times):
		for k in range(16):
			array[:,k] = full_array[:,(k+i) % max]
		draw_img(display, array)
		time.sleep(0.125)
	return


def matrix16x16simulation(data, topdisplay=None, botdisplay=None, times=1, flip=True):
	print("shape of inputdata:",data.shape)
	if topdisplay is None or botdisplay is None:
		print("displays ot spezified, just usefull for testings!")
	if flip:
		data = np.flip(data, axis=1)
	print("times not implemented Yet!")
	draw_img(topdisplay, data[:8])
	draw_img(botdisplay, data[8:])
	
display = Matrix8x16.Matrix8x16(address=0x70)
display.begin()
display.set_brightness(3)

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

#draw_periodic(display, full_sine)
topBotIcon = np.array([[0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1],
					 [1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1],
					 [0,0,1,0,0,1,1,0,0,1,1,1,0,0,1,1],
					 [0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,1],
					 [0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,1],
					 [0,0,1,0,0,1,1,0,0,1,1,1,0,0,1,1],
					 [0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1],
					 [0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1],
					 [0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1],
					 [0,1,1,1,0,0,0,0,0,0,0,1,0,0,1,1],
					 [0,1,0,0,1,0,0,0,0,0,1,1,1,0,1,1],
					 [0,1,1,1,0,0,0,1,1,0,0,1,0,0,1,1],
					 [0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,1],
					 [0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,1],
					 [0,1,1,1,0,0,0,1,1,0,0,0,1,0,1,1],
					 [0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1]])


matrix16x16simulation(topBotIcon,display,None)
a = input("now it will change to Bot:")
matrix16x16simulation(topBotIcon,None,display)

