# LDTS_<10><03> - RETRO CENTIPEDE

## Game Description

Retro Centipede is a Lanterna interpretation of the classic game by Atari Centipede. The game takes players to a pixelated world where they must slay monsters such as giant spiders and centipedes. Players experience different levels, that progressively gain difficulty and test their ability to survive and slay the monsters that come their way. The base of the game is the hero project that was done in class. This is a project by Rodrigo Araújo (up202205515), Francisco Bettencourt (up202105288) and Miguel Mateus (up202206944), made for LDTS 2023/24.


### IMPLEMENTED FEATURES

- **Moving** - The game character will move up down, right or left when corresponding arrow keys are pressed and isn't able to move past a certain area, beyond map limits (walls) or over mushrooms.
- **Game updates in real time** - Monsters constantly move and game logic is constantly applied even if user doesn't interact with the game while running.
- **Loading Screen and Replay Screen** - When the game opens, a loading screen can be seen and navigated with up and down arrows to select and the enter button to either start game or quit. When the game ends, a similar screen is shown, asking if you want to play again or quit.
- **Arrows** - When the space key is pressed, hero shoots an arrow that moves up from where the hero is. The game keeps track of the arrows shot and updates their position. When an arrow hits a target, it disappears. If it is a mushroom or a monster they also disappear along with the arrow.
- **Monster** - monster resembles a spider, that moves diagonally and has an 80% chance of maintaining its trajectory when path is clear. When it hits a wall, it changes direction. It spawns and moves in the same area as the hero.
- **Walls** - they outline the map.
- **Mushrooms** - Spawn randomly in the upper area of the arena. If the mushroom is hit 3 times, it self-destructs, meaning it disappears.
- **Levels** - Whenever the player kills the centipede and the spider (monster), both respawn and their speed increases as well.
- **Collisions** -  Collisions between different objects are verified. Simple example, the player cannot pass through the mushroom.

#### Menu Screen
<img width="507" alt="Captura de ecrã 2023-12-22, às 14 57 20" src="https://github.com/FEUP-LDTS-2023/project-l10gr03/assets/94064066/f544c363-4741-4d52-bd85-14c3e3bf4697">

#### Game Running
<img width="507" alt="Captura de ecrã 2023-12-22, às 14 57 29" src="https://github.com/FEUP-LDTS-2023/project-l10gr03/assets/94064066/22d630d8-13fa-4798-9e4a-fcde05186c35">

#### Losing Screen
<img width="507" alt="Captura de ecrã 2023-12-22, às 15 22 12" src="https://github.com/FEUP-LDTS-2023/project-l10gr03/assets/94064066/e61eb713-1d24-4f75-9754-0eaa753a5990">


### PLANNED FEATURES
All the planned features wrote here were successfully implemented.


### DESIGN

#### REFACTORING ARROW SHOOTING TO SUPPORT EXTENSIBILITY

##### **Problem in Context**

Originally, the arrow shooting logic was directly embedded within the input handling code of the ‘Game’ class. As new features such as power-ups or special arrows are planned, this tight coupling would lead to a more complex ‘Game’’ class and violate the Single Responsibility Principle. We needed a way to separate concerns and make the shooting mechanism more maintainable and easily extendable.

**The Pattern**

The **Command** pattern was identified as a suitable solution. This pattern encapsulates a request as an object, thereby allowing for parameterization of clients with different requests, queuing of requests, and logging. It also supports the extension of the system by adding new commands without changing the existing code.

**Implementation**

We created a ‘Command’ interface with an ‘execute’ method and a concrete ‘ShootArrowCommand’ class that implements this interface. This class encapsulates all the actions related to shooting an arrow. The ‘Hero’ class was given a ‘shoot’ method that creates an arrow, and the ‘Game’ class uses the ‘ShootArrowCommand’ within its input handling logic to execute the shooting action.

**Consequences**

Applying the Command pattern has several benefits:

- It separates the request for an action (shooting an arrow) from the classes that perform the action (‘Hero’), which simplifies the ‘Game’ class's input handling.
- It makes it easy to add new commands for different types of shooting actions, such as shooting multiple arrows or special power shots, without modifying the ‘Hero’ or ‘Game’ class.
- The encapsulation of the shooting action into a command object opens the door for additional features, such as undoable actions or macro commands (sequence of commands executed together).


### KNOWN CODE SMELLS

#### [Data Clumps](https://refactoring.guru/smells/data-clumps)
- On Arena, the fields walls, monsters, mushrooms, centipede, and arrows are all lists of different elements in the game. This data could be grouped into a more complex data structure or a separate class.

- On RandomArenaBuilder, the fields width, height, numberOfMonsters, numberOfMushrooms, and numberOfCentipedes are all passed together to the constructor and used together. This could indicate that this data could be grouped into a more complex data structure or a separate class.

#### [Long Parameter List](https://refactoring.guru/smells/long-parameter-list)
- The RandomArenaBuilder constructor has five parameters. This could make the code more difficult to understand and maintain. 

The rest of the code does not seem to have obvious code smells. We can conclude that the code is well-written and well-organized.

### TESTING

Due to an error that happened, which caused so that we could not open the index on the browser we send this as proof of our test in pitest.

![Screenshot_from_2023-12-23_23-50-54](https://github.com/FEUP-LDTS-2023/project-l10gr03/assets/131923186/fcdb1a02-2684-4aac-9980-7c6bc8e46376)


### SELF-EVALUATION

The distribution of labor in our project and the contribution each member gave was equal. Given we kept laying out tasks for each member and worked while on call helping each other out.

Rodrigo Araújo – 33,3%

Francisco Bettencourt – 33,3%

Miguel Mateus – 33,3%
