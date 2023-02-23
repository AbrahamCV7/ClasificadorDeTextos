import pandas as pd
import sklearn
import matplotlib.pyplot as plt
import numpy as np
from sklearn import svm, tree, neighbors
from sklearn.model_selection import train_test_split

# lectura del archivo de caracteristicas de los textos
archivo = "textosPesadoMorfologico.csv"
conjuntoTextos = pd.read_csv(archivo,encoding='ISO-8859-1')
print(conjuntoTextos)

# Divide el conjunto en X (características) y Y (listado de clases)

# Valores para Morfologicas (3 caracteristicas)
#X = conjuntoTextos.iloc[:, :3].values
# Valores para TFIDF (5563) cantidad de palabras en el diccionario)
X = conjuntoTextos.iloc[:, :3].values
Y = conjuntoTextos['clase'].values

# Divide los conjuntos X y Y en entrenamiento y pruebas con un 80/20
(X_entrenamiento, X_prueba, Y_entrenamiento, Y_prueba) = train_test_split(X, Y, test_size=0.20)
print("Cantidad de textos de entrenamiento: ", len(Y_entrenamiento))
print("Cantidad de textos de prueba: ",len(Y_prueba))


# Entrena con los conjuntos (X,Y_entrenamiento), genera el modelo, clasifica y evalua con los conjuntos (X,Y_prueba)
# Utilizando el algoritmo K-NN con 3 vecinos
clasificadorKNN = neighbors.KNeighborsClassifier(n_neighbors=7,weights='distance')
clasificadorKNN.fit(X_entrenamiento, Y_entrenamiento)
precisionKNN = clasificadorKNN.score(X_prueba, Y_prueba)
print("Precisión del algoritmo K-NN: ",precisionKNN*100)

# Entrena, genera el modelo, clasifica y evalua con los conjuntos 
# Utilizando el algoritmo Maquinas de soporte vectorial
clasificadorSVM = svm.SVC()
clasificadorSVM.fit(X_entrenamiento, Y_entrenamiento)
precisionSVM = clasificadorSVM.score(X_prueba, Y_prueba)
print("Precisión del algoritmo SVM: ",precisionSVM*100)

# Entrena, genera el modelo, clasifica y evalua con los conjuntos 
# Utilizando el algoritmo Arboles de Decisión
clasificadorArboles = tree.DecisionTreeClassifier()
clasificadorArboles = clasificadorArboles.fit(X_entrenamiento, Y_entrenamiento)
precisionArboles = clasificadorArboles.score(X_prueba, Y_prueba)
print("Precisión del algoritmo Arboles: ",precisionArboles*100)


# Compara los resultados de los tres algoritmos mediante gráficas
plt.title("Comparacion de Algoritmos")
plt.xlabel('Algoritmos')
plt.ylabel('Precision')
plt.plot([1,2,3], [precisionKNN,precisionSVM,precisionArboles], 'r--')
plt.axis([1, 3, 0, 1])

plt.show()