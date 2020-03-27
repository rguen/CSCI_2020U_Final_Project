==== Battlehip ====

Ryan Guenther (100702069)
- Worked on GameState Class: Initialized Scenes, created panes, initialized panes, added buttons, implemented button functions, added file I/O for instructions, implemented .csv high score file import
- Worked on User Interface and user experience: Created the start menu for the game and the start menu image
- Board Class: Display Board function
- Created the Server Class: Created Server class with server socket and used threading to connect clients
- Created the Client Class: Created Client class and used sockets to connect clients to server
- Gradle: Initialized and implemented Gradle integration

Wyatt Ritchie (100483764)
- Player interface: Created player interface for the human and cpu classes
- Human class: Created and worked on the human class, implemented functions
- CPU class: Created and worked on the cpu class, implemented functions along with cpu difficulty
- Board class: Board event handler
- GameState class: End game scene in Game State

Alper Tuna Unsal (100696076)
- Worked on importing the IntelliJ IDEA project into the Gradle project.
- Gradle: Initialized and implemented Gradle integration
- Board class: Implemented a water image to the board background
- Worked on the networking classes

Simon Lee (100700110)
- Pieces Class: Made all of piece class and edited other classes to be able to work with it. Worked on piece rotation, placing the pieces on board, and the image rotations
- Board and Pieces Class: Handled Getting and Implementing ship images


==== Instructions ====
1. Clone the git repository.
2. Open the command line and navigate to the root directory (CSCI_2020U_Final_Project).
3. Use the command "gradle build", then "gradle run" to launch the project.
4. Select either "Play CPU" or "Play Online" to play battleship.
    - Playing against CPU:
      1. Select the difficulty of the CPU.
      2. Learn how to play the game through the "options" menu after clicking "instructions".
      3. Select a tile to start the game
    
    - Playing Online
      1. This function is not fully implemented yet. At this point, we have used server sockets, sockets and threading to be able to connect two users that can chat to each other using the provided chat box.
      2. ++++ VERY IMPORTANT ++++ In order to use the chat box with another opponent, players CANNOT be on a local network (127.0.0.1). To talk to each other, players need to use their public IP address (which can be changed in the client class and the server class).
