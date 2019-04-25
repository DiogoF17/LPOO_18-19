# LPOO_25 Ghost Rumble (GR)

> Ghost Rumble (or GR for short) is a survival arena combat game, in which the player must defeat waves of ghosts and other mysterious enemies, in order to survive.

> The game lasts as long as the player is able to overcome all enemies, which grow in number and power with each passing wave. (Some have claimed to have seen Bosses every 5 waves 👀👀)

> The game is being developed by Eduardo Ribeiro (up201705421@fe.up.pt) and Diogo Machado (up201706832@fe.up.pt).

## Implemented Features

### Player and Walls Representation
> The player character is shown inside a haunted house, delimited by walls, that the player cannot trespass.

### Movement of the Monsters
> The different types os monsters and creatures each have their own way of travelling arround the map; they also have different speeds.

### HP Bar
> The player HP bar is shown in the top of the screen; it is green when the HP is medium/high, and it is red and flickering when the HP is low.

### Player Movement
> The player is able to move using the WASD keys.

## Planned Features

### Shooting/Attacking
> The player will also be able to shoot the monsters from a distance, launching a projectile/bullet in a certain direction, as well as performing a melee attack (short distance). The concept of limited ammo may also be added to the game.

### Monsters Chase the Player
> The monsters, instead of roaming around the haunted house, will actually pursue the player and try to chase him down.

### Monsters Causing Damage
> If a monster touches the player, it will die, but it will inflict damage on the player, lowering his HP.

### Coin Collecting
> In the future there may be coins scattered arround the house, for the player to collect.

### Upgrade Store
> The player would be able to use those coins in an upgrade store, that would open in the end of a wave, in order to purchase things like health regeneration, damage boost, etc.

### ... and maybe some more.

## Design
### 1. Separating the Game's Modules
#### 1.1 Problem in Context
> The first problem that we came across was to find a way to separate, in a correct way, the different aspects of our game: the logic module, that would be in charge of the mechanics of the game (the inner workings of the game, such as the player and enemies movement, when the game ends, etc); the drawing module, that would be in charge of transmiting the game´s logic and current state onto the screen, for the user to understand/interact; and others. Performing a correct separation of the game's modules would avoid the violation of the Single Responsability Principle; furthermore, doing so would make it a lot easier if we were to change just on component of the game, such as the drawing method, because we wouldn't need to change the other modules, as they are separated and not dependent.

#### 1.2 The Pattern
> In order to do this, we decided to implement a slightly different version of the MVC (Model - View - Controller) architectural pattern. The Model functions only has a data "warehouse", containing all the information about the game and its current state, but not knowing how to change it; the Controller receives the Model and the current Event, and updates the Model accordingly; the View (contrary to the standard MVC pattern) is split in two parts, one that receives the user input and generates the Event that is going to be processed by the Controller, and another one that receives the Model, and is in charge of drawing the current state of the game onto the screen. Finally, we have a Game class that connects all these modules, and contains the main game cycle.

#### 1.3 The Implementation
> Here's how we decided to implement the pattern:
(image)

> The classes can be found in the following files:
>
> [Game](../GhostRumble/src/main/java/com/aor/ghostrumble/Game.java)
>
> [GameLanterna](../GhostRumble/src/main/java/com/aor/ghostrumble/view/GameLanterna.java)
>
> [DrawingMethod](../GhostRumble/src/main/java/com/aor/ghostrumble/view/DrawingMethod.java)
>
> [DrawLanterna](../GhostRumble/src/main/java/com/aor/ghostrumble/view/DrawLanterna.java)
>
> [HauntedHouse](../GhostRumble/src/main/java/com/aor/ghostrumble/model/HauntedHouse.java)
>
> [Updater](../GhostRumble/src/main/java/com/aor/ghostrumble/controller/Updater.java)

#### 1.4 Consequences
> As said before, using the MVC design (or similar, like we did) increases the modularity of the code. It makes it easier to change only one component of the game, and to keep all the others (for example, deciding to use another way of drawing and reading inputs, other than Lanterna), because although they are linked, the code is not "mixed together" (we would need to create another subclass of Game, that uses a new way of read inputs, and another subclass of DrawingMethod, that would use a new way to draw onto the screen).

> As we also said before, it meets the requirements of the Single Responsability Principle: each module has only one reason to change. In our opinion, separating the View into two parts (one that draws, one that reads user input) contributes even more to the following of this principle.

### 2. Joining the Different View Components
#### 2.1 Problem in Context
> As we said before, when implementing the MVC pattern, we opted to separate the View module into two: one that is in charge of reading user input, and the other is in charge of drawing onto the screen. Because of this, we needed to find a way to join these two modules together (example: if we want to use Lanterna, then we should use the Game subclass GameLanterna, that reads input using Lanterna functions; the drawing aspect should also be done using Lanterna functions, so the class in charge of that should be DrawLanterna).
     
#### 2.2 The Pattern
> For this, we decided to implement the Factory Method design pattern. In the game class, that will have a drawing interface associated, a method will be called to decide the specific way of drawing the elements. In the concrete classes that extend the game class (and are in charge of reading input), we can instanciate the concrete drawing interface that we want for that specific type of game. (We can actually also consider this to be sort of a Strategy pattern too, because the way game class "draws" is to delegate the task to the drawing interface. The different interfaces are the different strategies).
> NOTE: if needed, this design pattern can later be changed to an Abstract Factory pattern; for now, we do not feel the need to do that, so we kept it has a regular Factory Method pattern.
     
#### 2.3 Implementation  
> Here's how we decided to implement the design pattern:
![Alt text](images/UML_FactoryMethod_1/UML_FactoryMethod_1.png)

> The classes can be found in the following files:
>
>[Game](../GhostRumble/src/main/java/com/aor/ghostrumble/Game.java)
>
>[GameLanterna](../GhostRumble/src/main/java/com/aor/ghostrumble/view/GameLanterna.java)
>
>[DrawingMethod](../GhostRumble/src/main/java/com/aor/ghostrumble/view/DrawingMethod.java)
>
>[DrawLanterna](../GhostRumble/src/main/java/com/aor/ghostrumble/view/DrawLanterna.java)
        
#### 2.4 Consequences
> The main Game class doesn't need to anticipate what implementation of DrawingMethod it needs to create; it just delegates that decision to the subclasses.
> In order to change the View component, we only need to change what Game subclass we use, because doing so changes what drawing interface we use, because of the Factory Method pattern.

## Known Code Smells and Refactoring Suggestions

> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.

## Testing Results

> This section should contain screenshots of the main results of both the test coverage and mutation testing reports. It should also contain links to those reports in HTML format (you can copy the reports to the docs folder).

## Self-evaluation

> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.
