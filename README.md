### Les types des agents : 
- agent Virus : c'est l'agent qui répresente le virus.
- agent fixCop : c'est le policier stationnaire.
- agent mobileCop : c'est le policier mobile.
- Puis on a ajouté un agent auxiliaire dans chaque container pour gérer l'état de contamination, on l'a appelé : agentContainer-i (avec i dans [1,5]).

### Les Containers : 
- Main-Container : c'est le container du lancement.
- VirusedContainer : c'est le container qui répresente le noeud du départ pour le _VIRUS_
- Container-i : avec i dans [1,5] se sont les autres noeuds de l'arbre.

### Autres classes : 
#### PathRouter : 
C'est la classe responsable de la définition de la logique du déplacement des différents agents.

#### Graph : 
C'est l'illustation graphique du déplacement, cette illustration n'est pas encore términé, pour le moment c'est le mode console; les messages _ACL_ sont traduits par la suite vers des 
messages console.

### Les comportements : 
#### VIRUS :
Le virus se deplace d'un noeud vers l'autre selon sa position initiale qui définit les positions accessibles.
Une fois le virus est déplacé d'un container à un autre, il envoie un message _ACL_ vers 
l'agent auxiliaire afin de mettre à jour l'état de contamination à _true_ (l'état est modifié par l'agent auxiliaire "agentContainer-i").

#### fixCop : 
C'est l'agent stationnaire, il est stationnaire dans le noeud du container Main-Container, si le virus se déplace vers lui (fixCop), ce dernier va tuer le virus puis verifier si tout est décontaminé en envoyant des messages aux agents auxiliaires, si oui il va arrêter le procès sinon il fait rien.

#### mobileCop : 
C'est l'agent responsable de la décontamination des noeuds, il se déplace d'un container vers l'autre selon les règles du déplacement qui gèrent son accessibilité (ces règles sont définies par la classe support.PathRouter.java), une fois il trouve le virus, il le tue et envoie un message vers le _fixCop_ pour lui informer, sinon il continue le procès de la décontamination.

#### agentContainer-i : 
Il reçoit les messages de chaque nouveau visiteur de son noeud, si le visiteur est le _VIRUS_ il modifie donc l'état de la contamination par _true_ , sinon si c'est le visiteur est le policier mobile (mobileCop) alors il met l'état à false, et il affiche l'état après.

