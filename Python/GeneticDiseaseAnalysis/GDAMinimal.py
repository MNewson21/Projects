import pandas as pd
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.tree import DecisionTreeClassifier, plot_tree
from sklearn import metrics

df = pd.read_csv("Gene Expression Analysis and Disease Relationship.csv")


df = df.drop(columns=["PatientID", "TreatmentResponse"])
df_Y = df["DiseaseStatus"]

# Use only the top 2 features
df_X = df[['SmokingStatus', 'Gene_Y_Expression']]


X_train, X_test, y_train, y_test = train_test_split(df_X, df_Y, test_size=0.5, random_state=1)


t_model = DecisionTreeClassifier(max_depth=3, min_samples_leaf=5, random_state=1)
t_model.fit(X_train, y_train)


y_predict = t_model.predict(X_test)
print("Test set accuracy:", metrics.accuracy_score(y_test, y_predict))

# Cross-validation
scores = cross_val_score(t_model, df_X, df_Y, cv=5)
print("5-fold cross-validated accuracy:", scores)
print("Mean accuracy:", scores.mean())

print("Feature importances:", pd.Series(t_model.feature_importances_, index=df_X.columns))

plt.figure(figsize=(10,6))
plot_tree(
    t_model,
    filled=True,
    feature_names=df_X.columns,
    class_names=[str(c) for c in t_model.classes_],
    rounded=True
)
plt.title("Interpretable Decision Tree")
plt.show()
