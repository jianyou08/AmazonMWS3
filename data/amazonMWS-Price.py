# -*- coding=utf-8 -*-
from datetime import datetime
from datetime import timedelta
import string
import numpy as np
import pylab as pl
import matplotlib.pyplot as plt

def LoadPrice(filename, asin):
    tm = []
    datalist = []
    fin = open(filename, 'r')
    for line in fin:
        vs = line.split(',')
        if len(vs) < 24:
            print line
            continue
        if vs[1] != asin:
            continue
        #v = (float(vs[1]), float(vs[2]), float(vs[3].strip('\n')))
        dt = datetime.strptime(vs[0], "%Y-%m-%d %H:%M:%S")
        tm.append(dt)
        datalist.append(vs)
    return tm,datalist
    
def DrawPicture(tm,datalist):
    x = tm
    y1=[]
    y2=[]
    y3=[]
    for row in datalist:
        #y1.append(float(row[2]))
        #y2.append(float(row[11]))
        #y3.append(float(row[16]))
        
        y1.append(float(row[3]))
        y2.append(float(row[12]))
        y3.append(float(row[17]))
    
    py1 = pl.plot(x, y1, 'k', label='MyPrice')
    py2 = pl.plot(x, y2, 'r', label='Competitive')
    py3 = pl.plot(x, y3, 'b', label='Lowest')
    #pl.xlim(min(x), max(x))
    pl.ylim(min(min(y1),min(y2),min(y3)) - 1.0, max(max(y1),max(y2),max(y3))+1.0)
    pl.xlabel('time')  
    pl.ylabel('Price')     
    #pl.legend([py1,py2,py3], ['MyPrice, Competitive, Lowest'], 'best', numpoints=1)
    pl.legend()
    pl.show()
    
def DrawPicture1(tm,datalist):
    x = tm
    t_y1=[]
    t_y2=[]
    t_y3=[]
    p_y1=[]
    p_y2=[]
    p_y3=[]
    for row in datalist:
        t_y1.append(float(row[2]))
        t_y2.append(float(row[11]))
        t_y3.append(float(row[16]))
        
        p_y1.append(float(row[3]))
        p_y2.append(float(row[12]))
        p_y3.append(float(row[17]))
    
    #plt.figure(1)
    plt.figure(2)
    ax1 = plt.subplot(211)
    ax2 = plt.subplot(212)
    
    plt.sca(ax1)
    pl.title('landed Price')
    plt.plot(x, t_y1, 'k', label='MyPrice')
    plt.plot(x, t_y2, 'r', label='Competitive')
    plt.plot(x, t_y3, 'b', label='Lowest')
    plt.ylim(min(min(t_y1),min(t_y2),min(t_y3)) - 0.5, max(max(t_y1),max(t_y2),max(t_y3))+0.5)
    plt.xlabel('time')  
    plt.ylabel('Price')     
    plt.legend()
    
    plt.sca(ax2)
    pl.title('listing Price')
    plt.plot(x, p_y1, 'k', label='MyPrice')
    plt.plot(x, p_y2, 'r', label='Competitive')
    plt.plot(x, p_y3, 'b', label='Lowest')
    plt.ylim(min(min(p_y1),min(p_y2),min(p_y3)) - 0.5, max(max(p_y1),max(p_y2),max(p_y3))+0.5)
    plt.xlabel('time')  
    plt.ylabel('Price')     
    plt.legend()
    
    plt.show()


#tm,datalist = LoadPrice('imemory_price.txt', 'B00L21BZ14')
tm,datalist = LoadPrice('alwayfresh_price.txt', 'B00K547ZW2')

DrawPicture1(tm,datalist)