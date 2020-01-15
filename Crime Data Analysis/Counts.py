# -*- coding: utf-8 -*-
"""
Created on Sat Dec 15 18:49:17 2018

@author: kaushikp
"""

import pandas as pd
import matplotlib.pyplot as plt
import numpy as np


#################################################CrimeType##################################################
df = pd.read_csv('C:/Users/hp/Desktop/MyDownloads/KP_new_CT_count/crimeTypecounts.csv')
df= df.sort_values('Count', ascending=False)
#df_arrest = df.loc[df['Arrest'] == True]
#df_no_arrest = df.loc[df['Arrest'] == False]

width = 0.50
N = len(df['CrimeType'].unique())
ind = np.arange(N)

plt.bar(0.3 + np.arange(len(df)), tuple(df['Count']), width)
#plt.bar(np.arange(len(df_no_arrest)) + width, tuple(df_no_arrest['Count']), width, label="No Arrest")
plt.ylabel('Counts')
plt.title('Counts by CrimeType')
plt.xticks(ind , tuple(df['CrimeType'].unique()), rotation=70)
#plt.yticks(np.arange(0, 81, 10))
#plt.legend((p1[0], p2[0]), ('Arrest', 'No Arrest'))
plt.legend(loc="upper right")
plt.rcParams['figure.figsize'] = [35, 5]
plt.show()





df = pd.read_csv('C:/Users/hp/Desktop/MyDownloads/KPCTtop10/CTtop10.csv')
df= df.sort_values('Count', ascending=False)
#df_arrest = df.loc[df['Arrest'] == True]
#df_no_arrest = df.loc[df['Arrest'] == False]

width = 0.3
N = len(df['CrimeType'].unique())
ind = np.arange(N)

plt.bar(np.arange(len(df)), tuple(df['Count']), width)
#plt.bar(np.arange(len(df_no_arrest)) + width, tuple(df_no_arrest['Count']), width, label="No Arrest")
plt.ylabel('Counts')
plt.title('Top 10 CrimeType')
plt.xticks(ind , tuple(df['CrimeType'].unique()), rotation=70)
#plt.yticks(np.arange(0, 81, 10))
#plt.legend((p1[0], p2[0]), ('Arrest', 'No Arrest'))
plt.legend(loc="upper right")
plt.rcParams['figure.figsize'] = [15, 5]
plt.show()



###################################################################################################

#################################################CrimeType##################################################
df = pd.read_csv('C:/Users/hp/Desktop/MyDownloads/disttotalcounts_KP/disttotalcounts.csv')
df= df.sort_values('Count', ascending=False)
#df_arrest = df.loc[df['Arrest'] == True]
#df_no_arrest = df.loc[df['Arrest'] == False]

width = 0.50
N = len(df['district'].unique())
ind = np.arange(N)

plt.bar( np.arange(len(df)), tuple(df['Count']), width)
#plt.bar(np.arange(len(df_no_arrest)) + width, tuple(df_no_arrest['Count']), width, label="No Arrest")
plt.ylabel('Counts')
plt.title('Counts by district')
plt.xticks(ind , tuple(df['district'].unique()), rotation=70)
#plt.yticks(np.arange(0, 81, 10))
#plt.legend((p1[0], p2[0]), ('Arrest', 'No Arrest'))
plt.legend(loc="upper right")
plt.rcParams['figure.figsize'] = [35, 5]
plt.show()





df = pd.read_csv('C:/Users/hp/Desktop/MyDownloads/disttop10_KP/disttop10.csv')
df= df.sort_values('Count', ascending=False)
#df_arrest = df.loc[df['Arrest'] == True]
#df_no_arrest = df.loc[df['Arrest'] == False]

width = 0.3
N = len(df['district'].unique())
ind = np.arange(N)

plt.bar(np.arange(len(df)), tuple(df['Count']), width)
#plt.bar(np.arange(len(df_no_arrest)) + width, tuple(df_no_arrest['Count']), width, label="No Arrest")
plt.ylabel('Counts')
plt.title('Top 10 district by District registration')
plt.xticks(ind , tuple(df['district'].unique()), rotation=70)
#plt.yticks(np.arange(0, 81, 10))
#plt.legend((p1[0], p2[0]), ('Arrest', 'No Arrest'))
plt.legend(loc="upper right")
plt.rcParams['figure.figsize'] = [10, 5]
plt.show()


###################################################################################################