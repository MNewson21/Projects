import pandas as pd
import matplotlib.pyplot as plt
import sklearn
import numpy as np
from sklearn import preprocessing
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.tree import DecisionTreeClassifier
from sklearn.tree import plot_tree
from sklearn import metrics
from sklearn.model_selection import cross_val_score

df = pd.read_csv("Gene Expression Analysis and Disease Relationship.csv")


#data error analysis
errors = df.isnull().sum()
#print(errors)
#print(df.describe())

#Data Preprocessing .....
df = df.drop(columns=["PatientID"])
df_Y = df["DiseaseStatus"]
df_X = df.drop(columns=["DiseaseStatus", "TreatmentResponse"])


#Training
X_train, X_test, y_train, y_test = train_test_split(df_X, df_Y, test_size=0.5)
t_model = DecisionTreeClassifier(max_depth=3, min_samples_leaf=5, random_state=1)
t_model.fit(X_train,y_train)

print(pd.Series(t_model.feature_importances_, index=df_X.columns))




#Testing
y_predict = t_model.predict(X_test) # testing the trained decision tree model
print(metrics.accuracy_score(y_test, y_predict)) # evaluate the trained model on testing data


scores = cross_val_score(t_model, df_X, df_Y, cv=5)
print("5-fold cross-validated accuracy:", scores)
print("Mean accuracy:", scores.mean())

plt.figure(figsize=(10,6))
plot_tree(
    t_model,
    filled=True,
    feature_names=df_X.columns,
    class_names=[str(c) for c in t_model.classes_],
    rounded=True
)
plt.title("GDA using Decision tree")
plt.show()

