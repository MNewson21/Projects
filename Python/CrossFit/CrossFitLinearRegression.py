import pandas as pd
import matplotlib.pyplot as plt
from sklearn import preprocessing
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn import metrics
import numpy as np
df = pd.read_csv("CrossFit3.csv", encoding="ISO-8859-1")
errors = df.isnull().sum()
#print(errors)
#df.info()

df2 = df.copy()
df2['overallscore'] = df2['overallscore'].ffill()
min_max_scaler = preprocessing.MinMaxScaler()
df2 = df2.drop(columns=['gender','division','overallscore'])
df2_scaled = df2.copy()
df2_scaled[df2.columns] = min_max_scaler.fit_transform(df2)
y_f = df2_scaled['overallrank']
X_f = df2_scaled.drop(columns=['overallrank'])
x_train,x_test,y_train,y_test = train_test_split(X_f, y_f, test_size=0.3,random_state=1)
lr = LinearRegression()
lr.fit(x_train,y_train)


print("Linear coefficient", lr.coef_)
y_pred = lr.predict(x_test)
test1mse = metrics.mean_squared_error(y_test, y_pred)
print(test1mse)
plt.scatter(x_train['weight'], y_train, marker='x', s=20, alpha=0.7)
plt.scatter(x_train['height'], y_train, marker='x', s=20, alpha=0.7)

plt.plot(lr.coef_[2] * x_test['age'] + lr.coef_[1] * x_test['weight'] + lr.coef_[0] * x_test['height'] + lr.intercept_, y_pred, color="black")
plt.legend(['weight', 'height'])
plt.xlabel('weight')
plt.ylabel('rank')
plt.title("Weight and height vs overall rank")
plt.show()


#The most important factor in determining the rank of the player in the race is the age
#This means that out of the increase of any of these factors, the increase in 1 age unit has the worst impact
#Then closely followed by the weight, and the increase in height has a slightly positive impact on it

#Analysis made by Miles Newson
