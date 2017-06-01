**Nom/Prénom Etudiant 1 :** LATHUILIERE Yoann

**Nom/Prénom Etudiant 2 :** MONTES Yannick

# Rapport TP4

## Question 1
La première chose que nous avons faite a été de tester le code, c'est à dire lancer le programme et découvrir les fonctionnalités.
Nous avons pu voir que tout fonctionnais parfaitemnt et qu'il n'y avait à prioris aucun bug critique.

Nous nous sommes ensuite concentrés sur la conception du programme en générant grâce à notre IDE un diagramme de classe.
Nous avonns alors constaté que **le programme ne respectait pas le modèle MVC**. Ainsi, les classes du programmes avaient plusieurs rôles : définir le modèle de l'objet, l'afficher... Ceci ne respectant pas un principe S.O.L.I.D : Single Class Responsability
  
Voici ci-dessous le diagramme de classes généré par notre IDE :
<p align="center"> 
<img src="images/original_diagram.png" height="800">
</p>

## Question 2

Nous avons décidé d'implémenter un pattern MVC de la manière suivante:

![Wanted_Conception](images/OurConception.png)

Cela permet de séparer les traitements de manière plus cloisonnée, 
afin de respecter l'idée qu'aucun code concenant l'affichage se mélanger avec le modèle. 

De la même manière, le controlleur permet de gérer les appuis utilisateur et de communiquer entre la vue et le modèle. 

Nous sommes partis de ce diagrame pour finalement arriver à ceci:

![Model](images/model.png)
![View](images/view.png)
![Controller](images/controller.png)
![Services](images/services.png)

Ici, les seuls modèles que nous avons sont les tortues. 
Ces dernières communiquement immédiatement avec leur vue respective, représentée par TurtleView.
A chaque changement d'une tortue, la vue est notifiée, ce qui a pour effet de redessiner cette dernière en fonction des
changements, sur la feuille de dessin. 

Nous n'avons pu vu l'utilité de détailler les controlleurs. En effet, l'utilisateur peut utiliser une seule tortue à la fois, 
par conséquent le controlleur ne concerne qu'une seule tortue. 

Nous avons simplement déplacer le code de gestion de cette dernière au sein de ce controlleur. 

En ce qui concerne le package Services, c'est un package qui permet de stocker différents comportement pour les tortues (ex: flocking)


## Question 3
*Rien à rédiger*

## Question 4
*Rien à rédiger*

## Question 5
*Expliquer le code ajouté et représenter le patron de conception*

## Question 6
*Rien à rédiger*

## Question 7
*Rien à rédiger*

## Question 8
*Expliquer l'intérêt du mock*

## Question 9
*Montrer les résultats de vos rapports d'analyse*

## Question 10
*Rien à rédiger*
