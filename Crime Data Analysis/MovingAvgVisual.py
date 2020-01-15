# -*- coding: utf-8 -*-
"""
Created on Sat Dec 15 19:23:32 2018

@author: kaushikp
"""

import pandas as pd
import matplotlib.pyplot as plt
import numpy as np


df = pd.read_csv('C:/Users/hp/Desktop/MyDownloads/MovingAvg.csv')
df= df.loc[df['CrimeType']=='ARSON'].sort_values('Year', ascending=True)

#df_arrest = df.loc[df['Arrest'] == True]
#df_no_arrest = df.loc[df['Arrest'] == False]

width = 0.3
N = len(df['Year'].unique())
ind = np.arange(N)

plt.bar(np.arange(len(df)), tuple(df['MovingAvg']), width)
#plt.bar(np.arange(len(df_no_arrest)) + width, tuple(df_no_arrest['Count']), width, label="No Arrest")
plt.ylabel('MovingAvg')
plt.title('Moving Average For Crimetype=ARSON')
plt.xticks(ind , tuple(df['Year'].unique()), rotation=70)
#plt.yticks(np.arange(0, 81, 10))
#plt.legend((p1[0], p2[0]), ('Arrest', 'No Arrest'))
plt.legend(loc="upper right")
plt.rcParams['figure.figsize'] = [10, 5]
plt.show()