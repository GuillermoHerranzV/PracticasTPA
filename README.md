# PracticasTPA
# Wilderness
## Juego
Hemos decidido realizar un juego tipo RPG en 2D en el que se elegirá un personaje al principio de la partida y habrá que ir avanzando por caminos cogiendo objetos y enfrentándote a enemigos y jefes.
Los combates serán encuentros prefijados contra enemigos que nos encontraremos explorando. Serán por turnos, en los cuales se podrán realizar varias acciones como por ejemplo atacar, utilizar objeto, utilizar la habilidad de personaje o huir del combate (a excepción de los jefes).
El personaje mejorara sus atributos al vencer al jefe de cada nivel para poder enfrentarse a los enemigos del siguiente nivel.
El plan inicial es construir dos niveles con un jefe cada uno (cada nivel más difícil que el anterior), pero esta idea se podría expandir a más niveles.
## Interfaz
Respecto a la interfaz de usuario mientras el jugador este andando por los niveles, el personaje elegido aparecerá siempre en el centro de la pantalla y la vida en la esquina superior izquierda mientras el resto del escenario se mueve a medida que el jugador avanza.
Cuando el jugador entre en combate, la interfaz cambiará y el jugador verá a su personaje en la parte izquierda con la barra de vida encima de su cabeza, el enemigo se situará en la parte arriba derecha con la barra de vida debajo. También habrá una caja en la parte inferior de la pantalla donde se podrán ver las opciones que tiene el jugador para combatir.
### Estado actual
En su estado actual el juego permite mover un personaje en una ventana con un mapa cargado que se va generando segun el personaje se mueve por el.
El personaje para cuando colisiona con una casilla que no permite ser atravesada.
Los sprites de los objetos estan implementados pero no se muestran todavia ya que no tienen uso.

![image](https://github.com/GuillermoHerranzV/PracticasTPA/assets/149266597/195ad3fc-82bf-4993-abf7-b06c94934086)

### Esquema UML actual
El esquema UML actualizado:
![image](https://github.com/GuillermoHerranzV/PracticasTPA/assets/149266597/ccbc848f-dd4b-46c2-a52b-0d0060ffaea9)

