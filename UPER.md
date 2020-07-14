<h1>The Problem Solving Framework : 'UPER'</h1>

* U = "Understand"
* P = "Plan"
* E = "Execute"
* R = "Reflect" / "Refactor"

<h2>1. Understanding the Problem</h2>

- Need to create a text based terminal game.
- Need to includes some core functionality as described in the directions.

<h2>
    2. Planning the Solution
</h2>

- First, need to brainstorm some ideas.
- Since I am new to Java, a game like Chess would be challenging but time consuming for me to understand what exactly is going on.
- Would be better to focus something that's more manageable and understandable.

- Some options are: Checkers or Connect Four.
- I know how to play these games, and they're also pretty fun.

- Also can't forget the core requirements, such as:

- The game must be modelled after Java classes.
- Have to implement an interface.
- Have to implement one abstract class.
- Have to implement one lambda expression/stream.
- Have to adhere to two or more pillars of OOP.

- Furthermore:

- The code should use be generally organized.
- And uses logical and expected code structure.
- Abd has an organized and a logical project structure with at least one package.

- And most importantly:

- The game must work.

- For Connect Four I would need:

- A Main class for the board and some functionality
- A Players class
- A Pieces class

<h2>
    3. Executing the Plan

- Although a terminal game would have been simpler to implement, I felt as though a GUI (or graphical user interface) would really help in constructing the game.
- Game play would be way easier, as opposed to the terminal.

- So first I watched some of the JavaFX videos found on the course Java Masterclass by Tim Buchalka.
- From his videos, I was able to create a simple HelloWorld through JavaFX, this ended up being the base for my project.

- After the introductory videos on JavaFX, I was able to watch a JavaFX tutorial on how to construct a simple board (or grid) for Connect Four.
- The video was very comprehensive, so I made sure to take a lot notes.
- As well as recreate the board many many times, slowly solidying my understanding of how JavaFX works in creating GUIs.

- The board (or grid in this case) was constrcuted in the Main class.
- Within the Main class, I needed to create a method to pass the disks over the board.
- This was implemented through move functions that created a diskRoot, another Pane over the grid Pane.
- Therefore, when you click on a column, the disks would be passed and show on the new Pane, which overlapped the grid Pane.

- Next, I had to figure out a way to assort colors for the disk.
- This was made through a boolean, which stated that if the disk was not RED, then it was YELLOW.
- When the mouse is clicked over a column, a disk drops, then switches to the next colored disk.
- Therefore, if the value was not returned as RED, then it will be returned as YELLOW.

- Next, I had to create intances where the game would end, and then declare a winner.
- Through lambda streams I was able to determine the instances needed to declare a winner.
- The lambda streams would hold the points on an array (or list in this case).
- Therefore, when the row has collected four disks, the game would end.
- These instances were the same for column, top left diagonal, and bottom left diagonal.
- This was returned in checkRange, which determined if the four disk were actually connected and passed onto redMove.
- The result from redMove was passed onto gameOver which then printed a statement stating that the game was over.

- Now, basic game functionality had been reached, so now I needed to implement an interface and abstract class.

- For an interface, I created PlayerNames, which was implemented in PlayerNamesImp (stands for implementation) which captures the names of players and prints out a unqiue string message as well.
- The string for the players were returned in PlayersNameImp, and used to create a new object in the Main arguments execution (or launch).
- Here, it prints the name of the players, that were captured in PlayerNamesImp, along with the unqiue string message from PlayersNamesImp.

- For abstract class, I chose something simple like a welcome message for each player.
- The welcome messages were returned from each, the concrete class found in WelcomeMessage and the abstract class found in WelcomeMessageImp, and were printed in the Main arguments execution (or launch).

- With that, I was able to finally complete my project, and continue onwards to clean up some of the code.

</h2>


<h2>
    4. Reflection / Refactor
</h2>

- Here all I did was continue to clean up the code, add comments, and worked on separation of responsibility with some aspects of the code.

