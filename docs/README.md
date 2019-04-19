# LPOO_25 Ghost Rumble (GR)

> Ghost Rumble (or GR for short) is a survival arena combat game, in which the player must defeat waves of ghosts and other mysterious enemies, in order to survive.

> The game lasts as long as the player is able to overcome all enemies, which grow in number and power with each passing wave. (Some have claimed to have seen Bosses every 5 waves 👀👀)

> The game is being developed by Eduardo Ribeiro (up201705421@fe.up.pt) and Diogo Machado (up201706832@fe.up.pt).

## Implemented Features

> This section should contain a list of implemented features and their descriptions. In the end of the section, include two or three screenshots that illustrate the most important features.

## Planned Features

> This section is similar to the previous one but should list the features that are not yet implemented. Instead of screenshots you should include GUI mock-ups for the planned features.

## Design

> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts: "Problem in Context", "The Pattern", "Implementation" and "Consequences".

> 1. Seperating the Game´s Logic and the Drawing Module
     
> 1.1 Problem in Context
     
>     One of the first decisions/problems that we had was to find a way to seperate the code for the game's logic (that is, the inner workings of the game, such as the player and enemies movement, when the game ends, etc) from the drawing module, that is in charge of transmiting the game´s logic and current state onto the screen, for the user to understand/interract. Seperating the two modules would help us in the future, if we wanted to change only the drawing module, for example: we wouldn't need to change anything in the logic module, because they are seperated and not dependent.
     
>     1.2 The Pattern
     
>     For this, we decided to implement the Factory Method design pattern. In the game class, that will have a drawing interface associated, a method will be called to decide the specific way of drawing the elements. In the concrete classes that extend the game class, we can instanciate the concrete drawing interface that we want for that specific game.
     
>     1.3 Implementation
     
     
     
>     1.4 Consequences
     
>     This procedure ensures that the first SOLID principle, the Single Responsibility Principle, is not violated, because the modules created are only in charge of one specific task, and have only "one reason" to be changed.

## Known Code Smells and Refactoring Suggestions

> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.

## Testing Results

> This section should contain screenshots of the main results of both the test coverage and mutation testing reports. It should also contain links to those reports in HTML format (you can copy the reports to the docs folder).

## Self-evaluation

> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.
