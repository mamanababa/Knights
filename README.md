# Knights
Knights Templar Fuzzy Logic Grail Quest
=======================================

Step 1
---------------------------------------
Execute a command “java –jar fuzzy.jar Play” to run the programme.The “information.xml” contains all information about locations and game characters.

Step 2
---------------------------------------
Input some verbs to play the game:
---LOOK to get information of this location
---CHECK to check all your items and life force
---EAT to use supplies to increase life force
---PICK to pick items of this location
---DROP to drop items
---GET to get supplies from friends
---EXCHANGE to use your weapon to exchange supplies from neutral people
---FIGHT to fight with enemies
---MOVE then choose N/W/S/E/NW/NE/SW/SE to go to different locations
---HELP to check all commands again

Step 3
---------------------------------------
1.The START location(first location Player at) is Jerusalem and the END location is Cyprus-Nicosia. 

2.Player is the Hero, all game characters’s initial life force are 100(Integer). Player needs to pick up the “Holy Grail” item from Jerusalem then bring it to Cyprus-Nicosia safely.

3.Enemies include Saracen and Brigands. 
Saracen army are only in Jerusalem and have no movements. Brigands are in 5 different locations and also have no movements

4.Other game characters are Friends and Neutral people. 
Friends use Best-First search algorithm to move through the map every 20 seconds. Player can get supplies(medicine and food) from friends. And Neutral people use Hill-Climbing search algorithm to move through the map every 30 seconds. Player has to use weapon to exchange supplies from neutral people.

5.Player can picks each item of current location once(type PICK then choose item) or drops them(type DROP then choose item) or use supply items(type EAT then choose MEDICINE or FOOD to increase life force). Player cannot EAT when life force is 100%.

6.Before moves to next location, player has to defeat all enemies of current location(type FIGHT and choose enemy) or cannot move, also can equip a weapon after typing FIGHT.
According to the result of fight by using Fuzzy Logic, player’s life force will decreases 30 or 40. Once the life force falls to 0 then GAME OVER, player loses.
After player defeat the Brigands of a location, there is no Brigands at here any more. But after defeat the Saracen army of Jerusalem, they are still in Jerusalem because you cannot kill the whole army in this game, player has to defeat Saracen at least one time before move.

7.Player input MOVE then choose direction(N/W/S/E/NW/NE/SW/SE) to uses Beam search algorithm to move through the map. If the direction that player chosen is too dangerous and player still decides to go this direction, then player will lose 70 life force. Once the life force falls to 0 then GAME OVER, player loses.

8.When player reach the END location Cyprus-Nicosia with the “Holy Grail” item then player wins.
