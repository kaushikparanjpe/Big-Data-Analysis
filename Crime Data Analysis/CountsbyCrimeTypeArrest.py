# -*- coding: utf-8 -*-
"""
Created on Thu Dec 13 11:14:07 2018

@author: kaushikp
"""

import pandas as pd
import matplotlib.pyplot as plt
import numpy as np



df = pd.read_csv('C:/Users/hp/Desktop/MyDownloads/KP_arrest_ct_counts/arrest_ct_counts.csv')

df_arrest = df.loc[df['Arrest'] == True]
df_no_arrest = df.loc[df['Arrest'] == False]

width = 0.50
N = len(df['CrimeType'].unique())
ind = np.arange(N)

#plt.bar(np.arange(len(df_arrest)), tuple(df_arrest['Count']), width, label="Arrest")
#plt.bar(np.arange(len(df_no_arrest)), tuple(df_no_arrest['Count']), width, 
#        bottom=tuple(df_no_arrest['Count']), label="No Arrest")

plt.bar(0.3 + np.arange(len(df_arrest)), tuple(df_arrest['Count']), width, label="Arrest")
plt.bar(np.arange(len(df_no_arrest)) + width, tuple(df_no_arrest['Count']), width, label="No Arrest")
plt.ylabel('Counts')
plt.title('Counts by CrimeType and Arrests')
plt.xticks(ind + width /2, tuple(df['CrimeType'].unique()), rotation=70)
#plt.yticks(np.arange(0, 81, 10))
#plt.legend((p1[0], p2[0]), ('Arrest', 'No Arrest'))
plt.legend(loc="upper right")
plt.rcParams['figure.figsize'] = [35, 5]
plt.show()
