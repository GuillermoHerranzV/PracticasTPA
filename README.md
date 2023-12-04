# PracticasTPA
# Wilderness
## Juego
Hemos decidido realizar un juego tipo RPG en 2D en el que se elegirá un personaje al principio de la partida y habrá que ir avanzando por caminos cogiendo objetos y enfrentándote a enemigos y jefes.
Los combates serán encuentros prefijados contra enemigos que nos encontraremos explorando. Serán por turnos, en los cuales se podrán realizar varias acciones como por ejemplo atacar, utilizar objeto, utilizar la habilidad de personaje o huir del combate (a excepción de los jefes).
El plan inicial es construir un nivele con un jefe y 3 monstruos inferiores, pero esta idea se podría expandir a más niveles.
## Interfaz
Respecto a la interfaz de usuario mientras el jugador este andando por los niveles, el personaje elegido aparecerá siempre en el centro de la pantalla y la vida en la esquina superior izquierda mientras el resto del escenario se mueve a medida que el jugador avanza.
![image](https://github.com/GuillermoHerranzV/PracticasTPA/assets/149266597/94075291-1a0c-466a-977e-9d4f667ed5c1)

Cuando el jugador entre en combate, la interfaz cambiará y el jugador verá a su personaje en la parte izquierda con la vida y el mana encima de su cabeza, el enemigo se situará en la parte derecha con la barra de vida encima. También habrá una caja en la parte inferior de la pantalla donde se podrán ver las opciones que tiene el jugador para combatir.
![image](https://github.com/GuillermoHerranzV/PracticasTPA/assets/149266597/c9845c24-e1c4-4b56-b5ef-d7cfcfbf1318)

## Estado actual
En su estado actual el juego permite mover un personaje en una ventana con un mapa cargado que se va generando segun el personaje se mueve por el.

### NPC
Hay un NPC el cual interactuas pulsando el enter a su lado y el te explica el objetivo del juego.
![image](https://github.com/GuillermoHerranzV/PracticasTPA/assets/149266597/9c23d7c6-50fa-4c4c-8dec-45a084c34d45)

### Personaje y enemigos
El personaje para cuando colisiona con una casilla que no permite ser atravesada.
Cuando haces contacto con un enemigo entras en el combate y puedes salir dandole a huir (a excepcion del boss), matando al enemigo o cuando tu personaje muere.

### Items
Hay items por el mapa que recoges y te permiten hacer diferentes cosas. Una pocion para curarte y aumentar tu velocidad y llaves para abrir las puertas que bloquean tu camino.
![image](https://github.com/GuillermoHerranzV/PracticasTPA/assets/149266597/40e2f719-f992-4be4-b1df-4895c090f833)
![image](https://github.com/GuillermoHerranzV/PracticasTPA/assets/149266597/0ddecf8f-e38b-4d64-ab57-44a847de4d46)

### Final
Cuando matas al jefe (contra el que no puedes combatir sin haber derrotado al resto de enemigos) podras abrir la ultima puerta y acceder al cofre con el que ganaras la partida.

### Esquema UML actual
El esquema UML actualizado:
![image](https://github.com/GuillermoHerranzV/PracticasTPA/assets/149266597/5670ba03-3987-4fae-84b1-ad8dfccf2e79)

