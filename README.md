# Android Memory Game

## Introduction
This Android Memory Game is a simple and fun card-matching game that tests your memory. The game consists of flipped cards that a player turns over two at a time with the goal of finding a matching pair. The game continues until all pairs are found, and the objective is to achieve this in the fewest flips possible.

## Features
- **Simple and Intuitive UI**: Easy to navigate and play the game with a user-friendly interface.
- **Score Tracking**: Keeps track of the number of pairs each player has found.
- **Turn Indication**: Clearly indicates which player's turn it is.
- **Help Section**: Provides game rules and instructions.
- **Dynamic Grid**: Supports different grid sizes for various difficulties (currently set to 4x4).

## How to Play
- Start the game with all cards face down.
- Tap on two cards to flip them over.
- If the cards match, they remain flipped, and you score a point.
- If they don't match, they flip back over after a short delay.
- Keep playing until all cards have been matched.
- Try to match all pairs with the fewest flips to win.

## Technical Details
- Built using Android Studio.
- Utilizes Fragments to manage different sections of the game.
- Implements RecyclerView for efficient card display and flipping logic.
- MVC architectural pattern for separation of concerns.

## Installation
Clone this repository and import it into **Android Studio**.
```bash
git clone https://github.com/BrendanoElTaco/CSCI3660_Memory_Game.git
```
Build and run the project using the Gradle build tool.

## Screenshots
![image](https://github.com/BrendanoElTaco/CSCI3660_Memory_Game/assets/33934145/ff4327cf-1895-41b0-835b-0bb522fa3681)
![image](https://github.com/BrendanoElTaco/CSCI3660_Memory_Game/assets/33934145/4c9e45ee-c71b-479e-91a2-2add5c8019d7)
![image](https://github.com/BrendanoElTaco/CSCI3660_Memory_Game/assets/33934145/a8c33c38-38cb-4fb4-9838-b60b4e517f92)

## Contributions
Contributions are welcome! Please fork this repository and contribute back using [pull requests](https://github.com/BrendanoElTaco/CSCI3660_Memory_Game/pulls).

## License
This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/BrendanoElTaco/CSCI3660_Memory_Game/blob/9754fe3815f454371cd14a58effb6e040a5b4072/LICENSE) file for details.
