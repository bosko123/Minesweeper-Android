package com.bostjan.mfc.minesweeper.game;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bostjan.mfc.minesweeper.assets_settings.ImageRegister;
import com.bostjan.mfc.minesweeper.main.MainWindow;

public class EventHandler {
	
	//declare global variables
	public static int stMines = 0;
	public static int stLeftMines = stMines;
	public static int stFlags = stMines;
	public static int stCrates = 1;
	public static int stLeftCrates = stCrates;
	public static int[][] mines = null;
	public static int[][] flags = null;
	public static int[][] rightFlags = null;
	public static boolean[][] emptyTiles = null;
	public static boolean[][] crates = null;
	public static boolean[][] readyCrates = null;
	public static boolean[][] brokenTiles = null;
	public static boolean[][] xrayTiles = null;
	public static boolean[][] nukes = null;
	public static int[][] openedTiles = null;
	public static boolean[][] noXray = null;
	
	public static String time;

	public static GameWindow gameWindow = new GameWindow();
	
	public static int rows = GameWindow.tiles.length;
	public static int columns = GameWindow.tiles[rows-1].length;
	
	public static void setupMinesAndFlags() {
		
		//set variables and arrays properties
		stLeftMines = stMines;
		stFlags = stMines;
		stLeftCrates = stCrates;
		
		rows = GameWindow.tiles.length;
		columns = GameWindow.tiles[rows-1].length;
		
		mines = new int[rows][columns];
		flags = new int[rows][columns];
		rightFlags = new int[rows][columns];
		emptyTiles = new boolean[rows][columns];
		crates = new boolean[rows][columns];
		readyCrates = new boolean[rows][columns];
		brokenTiles = new boolean[rows][columns];
		xrayTiles = new boolean[rows][columns];
		nukes = new boolean[rows][columns];
		openedTiles = new int[rows][columns];
		noXray = new boolean[rows][columns];
		
		//set arrays default values
		for (int i = 0; i < flags.length; i++) {
			
			for (int j = 0; j < flags[i].length; j++) {
				
				flags[i][j] = 0;
				mines[i][j] = 0;
				emptyTiles[i][j] = false;
				crates[i][j] = false;
				readyCrates[i][j] = false;
				brokenTiles[i][j] = false;
				xrayTiles[i][j] = false;
				nukes[i][j] = false;
				openedTiles[i][j] = -1;
				noXray[i][j] = false;
				
			}
			
		}
		
	}
	
	public static void generateMines() {
		
		//prepare temp variables
		setupMinesAndFlags();
		int xCoord = 0;
		int yCoord = 0;
		
		//repeat until all mines were placed
		while (stLeftMines != 0) {
			
			//generate random coords
			//if mine is already generated there generate new coords
			do {
				
				xCoord = (int)(Math.random() * rows + 0);
				yCoord = (int)(Math.random() * columns + 0);
				
			} while (mines[xCoord][yCoord] != 0);
			
			//place mine at coords
			mines[xCoord][yCoord] = 9;
			stLeftMines--;
			
		}
		
		for (int i = 0; i < mines.length; i++) {
			
			for (int j = 0; j < mines[i].length; j++) {
				
				System.out.print(mines[i][j] + " ");
				
			}
			
			System.out.println();
			
		}
		
		System.out.println();
		
		//set number count around the mine
		countMines();
		
		for (int i = 0; i < mines.length; i++) {
			
			for (int j = 0; j < mines[i].length; j++) {
				
				System.out.print(mines[i][j] + " ");
				
			}
			
			System.out.println();
			
		}
		
	}

	public static void countMines() {
		
		for (int i = 0; i < mines.length; i++) {
			
			for (int j = 0; j < mines[i].length; j++) {
				
				//if current array index has a mine (number 9)
				if (mines[i][j] == 9) {
					
					//add 1 around all mines
					try {
						
						if (mines[i-1][j-1] != 9) mines[i-1][j-1]++;
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i-1][j] != 9) mines[i-1][j]++;
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i-1][j+1] != 9) mines[i-1][j+1]++;
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i][j-1] != 9) mines[i][j-1]++;
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i][j+1] != 9) mines[i][j+1]++;
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i+1][j-1] != 9) mines[i+1][j-1]++;
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i+1][j] != 9) mines[i+1][j]++;
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i+1][j+1] != 9) mines[i+1][j+1]++;
						
					} catch (Exception e) {
						
					}
					
				}
				
			}
			
		}
		
	}

	public static void openTile(ImageView tile, Context applicationContext) {
		
		int number = 0;
		
		for (int i = 0; i < mines.length; i++) {
			
			for (int j = 0; j < mines[i].length; j++) {
				
				if (GameWindow.tiles[i][j] == tile && flags[i][j] != 1 && !brokenTiles[i][j]) {
					
					//if clicked on mine
					if (mines[i][j] == 9) {
						
						//play explosion sound
						//TODO SOUND!!!
						//gameWindow.playExplosion();
						//SoundRegister.playSound("/assets/explosion.wav", -12f);
						
						//open all tiles with mines
						showMines();
						
						//depending on what mine set different image icon to the tile
						number = mines[i][j];
						if (!nukes[i][j])
							GameWindow.tiles[i][j].setImageResource(ImageRegister.images[number]);
						else
							GameWindow.tiles[i][j].setImageResource(ImageRegister.imageNuke);
						//show game over message and end game
						GameWindow.gameOver = true;
						
						if (GameWindow.crazyModeOn) {
							
							GameWindow.health--;
							GameWindow.lblHealth.setText("Lives " + GameWindow.health + "/10");
							LevelSaveAndLoad.savePowerUps(MainWindow.username);
							
						}
						
						/*final ImageIcon icon = new ImageIcon(EventHandler.class.getResource("/assets/textures/gui/over.gif"));
						JOptionPane.showMessageDialog(GameWindow.frame, "Oops!\r\nYou stepped on the mine", "Game Over", JOptionPane.DEFAULT_OPTION, icon);*/
						Toast.makeText(applicationContext, "You stepped on the mine", Toast.LENGTH_LONG).show();

						if (GameWindow.health == 0) {

							//JOptionPane.showMessageDialog(GameWindow.frame, "You lost all lives", "Start Again", JOptionPane.DEFAULT_OPTION, icon);
							Toast.makeText(applicationContext, "You lost all lives", Toast.LENGTH_LONG).show();
							GameWindow.btnReset.setText("Restart");
							GameWindow.level = 0;
							GameWindow.minePercentage = 10;
							GameWindow.health = 10;
							GameWindow.allPowerUps[0] = 3;
							GameWindow.allPowerUps[1] = 3;
							
							LevelSaveAndLoad.saveLevel(MainWindow.username);
							LevelSaveAndLoad.savePowerUps(MainWindow.username);
							
						}
						
					}
					else {
						
						//depending tile has a number or is empty display different image icon
						number = mines[i][j];
						if (!crates[i][j])
							GameWindow.tiles[i][j].setImageResource(ImageRegister.images[number]);
						else
							GameWindow.tiles[i][j].setImageResource(ImageRegister.imagesCrates[number]);
						
						if (mines[i][j] == 0) {
							
							emptyTiles[i][j] = true;
							
						}
						
						openedTiles[i][j] = mines[i][j];
						
						prepareToOpenTiles();
						openCrate(tile);
						readyCrates(tile);
                        openTiles();
						//izpisperp();
						//openAroundTheTile();
						
					}
					
				}
				
			}
			
		}
		
	}
	
	public static void izpisperp() {
		
		for (int i = 0; i < openedTiles.length; i++) {
			
			for (int j = 0; j < openedTiles[i].length; j++) {
				
				System.out.print(openedTiles[i][j]);
				
			}
			
			System.out.println();
			
		}
		
	}

	public static void readyCrates(ImageView tile) {
		
		for (int i = 0; i < readyCrates.length; i++) {
			
			for (int j = 0; j < readyCrates[i].length; j++) {
				
				if (GameWindow.tiles[i][j] == tile) {
					
					readyCrates[i][j] = true;
					
				}
				
			}
			
		}
		
	}

	public static void openCrate(ImageView tile) {
		
		for (int i = 0; i < crates.length; i++) {
			
			for (int j = 0; j < crates[i].length; j++) {
				
				if (GameWindow.tiles[i][j] == tile) {
					
					if (crates[i][j] /*&& readyCrates[i][j]*/) {
						
						crates[i][j] = false;
						//readyCrates[i][j] = false;
						int number = mines[i][j];
						GameWindow.tiles[i][j].setImageResource(ImageRegister.images[number]);
						GameWindow.openingCrate = true;
						GameWindow.frames = 1;
						//PowerUpsEvents.getPowerUp();
						
					}
					
				}
				
			}
			
		}
		
	}

	public static void showMines() {
		
		for (int i = 0; i < mines.length; i++) {
			
			for (int j = 0; j < mines[i].length; j++) {
				
				if (mines[i][j] == 9) {
					
					int number = mines[i][j];
					if (flags[i][j] != 1 && !nukes[i][j])
						GameWindow.tiles[i][j].setImageResource(ImageRegister.images[number+1]);
					else if (!nukes[i][j])
						GameWindow.tiles[i][j].setImageResource(ImageRegister.images[number+2]);
					else
						GameWindow.tiles[i][j].setImageResource(ImageRegister.imageNuke);
					
				}
				
			}
			
		}
		
	}

	public static void openAroundTheTile() {
		
		for (int i = 0; i < emptyTiles.length; i++) {
			
			for (int j = 0; j < emptyTiles[i].length; j++) {
				
				openTiles();
				
			}
			
		}
		
	}

	public static void openTiles() {
		
		int number = 0;
		
		for (int i = 0; i < emptyTiles.length; i++) {
			
			for (int j = 0; j < emptyTiles[i].length; j++) {
				
				//if tile is empty
				if (emptyTiles[i][j] && !brokenTiles[i][j] && !xrayTiles[i][j]) {
					
					//open tiles around opened tile
					try {
						
						//if flag is not placed on the tile
						if (flags[i-1][j-1] != 1) {
							
							//display icon depending on what is under the tile
							number = mines[i-1][j-1];
							
							if (!brokenTiles[i-1][j-1]) {
								
								if (!crates[i-1][j-1])
									gameWindow.updateImages(GameWindow.tiles[i-1][j-1], ImageRegister.images[number]);
									//GameWindow.tiles[i-1][j-1].setImageResource(ImageRegister.images[number]);
								else
									gameWindow.updateImages(GameWindow.tiles[i-1][j-1], ImageRegister.imagesCrates[number]);
									//GameWindow.tiles[i-1][j-1].setImageResource(ImageRegister.imagesCrates[number]);
								openedTiles[i-1][j-1] = number;
								
							}
							else {

								gameWindow.updateImages(GameWindow.tiles[i-1][j-1], ImageRegister.imagesBroken[number]);
								//GameWindow.tiles[i-1][j-1].setImageResource(ImageRegister.imagesBroken[number]);
								
							}
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (flags[i][j-1] != 1) {
							
							number = mines[i][j-1];
							
							if (!brokenTiles[i][j-1]) {
								
								if (!crates[i][j-1])
									gameWindow.updateImages(GameWindow.tiles[i][j-1], ImageRegister.images[number]);
									//GameWindow.tiles[i][j-1].setImageResource(ImageRegister.images[number]);
								else
									gameWindow.updateImages(GameWindow.tiles[i][j-1], ImageRegister.imagesCrates[number]);
									//GameWindow.tiles[i][j-1].setImageResource(ImageRegister.imagesCrates[number]);
								openedTiles[i][j-1] = number;
								
							}
							else {

								gameWindow.updateImages(GameWindow.tiles[i][j-1], ImageRegister.imagesBroken[number]);
								//GameWindow.tiles[i][j-1].setImageResource(ImageRegister.imagesBroken[number]);
								
							}
							
						}
						
					} catch (Exception e) {

						System.out.println("napaka");

					}
					
					try {
						
						if (flags[i+1][j-1] != 1) {
							
							number = mines[i+1][j-1];
							
							if (!brokenTiles[i+1][j-1]) {
								
								if (!crates[i+1][j-1])
									gameWindow.updateImages(GameWindow.tiles[i+1][j-1], ImageRegister.images[number]);
									//GameWindow.tiles[i+1][j-1].setImageResource(ImageRegister.images[number]);
								else
									gameWindow.updateImages(GameWindow.tiles[i+1][j-1], ImageRegister.imagesCrates[number]);
									//GameWindow.tiles[i+1][j-1].setImageResource(ImageRegister.imagesCrates[number]);
								openedTiles[i+1][j-1] = number;
								
							}
							else {

								gameWindow.updateImages(GameWindow.tiles[i+1][j-1], ImageRegister.imagesBroken[number]);
								//GameWindow.tiles[i+1][j-1].setImageResource(ImageRegister.imagesBroken[number]);
								
							}
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (flags[i-1][j] != 1) {
							
							number = mines[i-1][j];
							
							if (!brokenTiles[i-1][j]) {
								
								if (!crates[i-1][j])
									gameWindow.updateImages(GameWindow.tiles[i-1][j], ImageRegister.images[number]);
									//GameWindow.tiles[i-1][j].setImageResource(ImageRegister.images[number]);
								else
									gameWindow.updateImages(GameWindow.tiles[i-1][j], ImageRegister.imagesCrates[number]);
									//GameWindow.tiles[i-1][j].setImageResource(ImageRegister.imagesCrates[number]);
								openedTiles[i-1][j] = number;
								
							}
							else {

								gameWindow.updateImages(GameWindow.tiles[i-1][j], ImageRegister.imagesBroken[number]);
								//GameWindow.tiles[i-1][j].setImageResource(ImageRegister.imagesBroken[number]);
								
							}
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (flags[i+1][j] != 1) {
							
							number = mines[i+1][j];
							
							if (!brokenTiles[i+1][j]) {
								
								if (!crates[i+1][j])
									gameWindow.updateImages(GameWindow.tiles[i+1][j], ImageRegister.images[number]);
									//GameWindow.tiles[i+1][j].setImageResource(ImageRegister.images[number]);
								else
									gameWindow.updateImages(GameWindow.tiles[i+1][j], ImageRegister.imagesCrates[number]);
									//GameWindow.tiles[i+1][j].setImageResource(ImageRegister.imagesCrates[number]);
								openedTiles[i+1][j] = number;
								
							}
							else {

								gameWindow.updateImages(GameWindow.tiles[i+1][j], ImageRegister.imagesBroken[number]);
								//GameWindow.tiles[i+1][j].setImageResource(ImageRegister.imagesBroken[number]);
								
							}
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (flags[i-1][j+1] != 1) {
							
							number = mines[i-1][j+1];
							
							if (!brokenTiles[i-1][j+1]) {
								
								if (!crates[i-1][j+1])
									gameWindow.updateImages(GameWindow.tiles[i-1][j+1], ImageRegister.images[number]);
									//GameWindow.tiles[i-1][j+1].setImageResource(ImageRegister.images[number]);
								else
									gameWindow.updateImages(GameWindow.tiles[i-1][j+1], ImageRegister.imagesCrates[number]);
									//GameWindow.tiles[i-1][j+1].setImageResource(ImageRegister.imagesCrates[number]);
								openedTiles[i-1][j+1] = number;
								
							}
							else {

								gameWindow.updateImages(GameWindow.tiles[i-1][j+1], ImageRegister.imagesBroken[number]);
								//GameWindow.tiles[i-1][j+1].setImageResource(ImageRegister.imagesBroken[number]);
								
							}
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (flags[i][j+1] != 1) {
							
							number = mines[i][j+1];
							
							if (!brokenTiles[i][j+1]) {
								
								if (!crates[i][j+1])
									gameWindow.updateImages(GameWindow.tiles[i][j+1], ImageRegister.images[number]);
									//GameWindow.tiles[i][j+1].setImageResource(ImageRegister.images[number]);
								else
									gameWindow.updateImages(GameWindow.tiles[i][j+1], ImageRegister.imagesCrates[number]);
									//GameWindow.tiles[i][j+1].setImageResource(ImageRegister.imagesCrates[number]);
								openedTiles[i][j+1] = number;
								
							}
							else {

								gameWindow.updateImages(GameWindow.tiles[i][j+1], ImageRegister.imagesBroken[number]);
								//GameWindow.tiles[i][j+1].setImageResource(ImageRegister.imagesBroken[number]);
								
							}
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {

                        if (flags[i+1][j+1] != 1) {

                            number = mines[i+1][j+1];

                            if (!brokenTiles[i+1][j+1]) {

                                if (!crates[i+1][j+1])
                                    gameWindow.updateImages(GameWindow.tiles[i+1][j+1], ImageRegister.images[number]);
                                    //GameWindow.tiles[i+1][j+1].setImageResource(ImageRegister.images[number]);
                                else
                                    gameWindow.updateImages(GameWindow.tiles[i+1][j+1], ImageRegister.imagesCrates[number]);
                                //GameWindow.tiles[i+1][j+1].setImageResource(ImageRegister.imagesCrates[number]);
                                openedTiles[i+1][j+1] = number;

                            }
                            else {

                                gameWindow.updateImages(GameWindow.tiles[i+1][j+1], ImageRegister.imagesBroken[number]);
                                //GameWindow.tiles[i+1][j+1].setImageResource(ImageRegister.imagesBroken[number]);

                            }

                        }
						
					} catch (Exception e) {
						
					}
					
				}
				
			}
			
		}
		
	}

	public static void izpis() {
		
		for (int i = 0; i < emptyTiles.length; i++) {
			
			for (int j = 0; j < emptyTiles[i].length; j++) {
				
				System.out.print(rightFlags[i][j]);
				
			}
			
			System.out.println();
			
		}
		
	}

	public static void prepareToOpenTiles() {
		
		for (int i = 0; i < emptyTiles.length; i++) {
			
			for (int j = 0; j < emptyTiles[i].length; j++) {
				
				setEmpty();
				
			}
			
		}
		
	}

	public static void setEmpty() {
		
		for (int i = 0; i < emptyTiles.length; i++) {
			
			for (int j = 0; j < emptyTiles[i].length; j++) {
				
				//if tile is empty set other empty tiles next to it to true (prepare to be open)
				if (emptyTiles[i][j] && !brokenTiles[i][j]) {
					
					try {
						
						if (mines[i-1][j-1] == 0 && flags[i-1][j-1] != 1 && !brokenTiles[i-1][j-1]) {
							
							emptyTiles[i-1][j-1] = true;
							openedTiles[i-1][j-1] = 0;
							flags[i-1][j-1] = 0;
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i][j-1] == 0 && flags[i][j-1] != 1 && !brokenTiles[i][j-1]) {
							
							emptyTiles[i][j-1] = true;
							openedTiles[i][j-1] = 0;
							flags[i][j-1] = 0;
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i+1][j-1] == 0 && flags[i+1][j-1] != 1 && !brokenTiles[i+1][j-1]) {
							
							emptyTiles[i+1][j-1] = true;
							openedTiles[i+1][j-1] = 0;
							flags[i+1][j-1] = 0;
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i-1][j] == 0 && flags[i-1][j] != 1 && !brokenTiles[i-1][j]) {
							
							emptyTiles[i-1][j] = true;
							openedTiles[i-1][j] = 0;
							flags[i-1][j] = 0;
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i+1][j] == 0 && flags[i+1][j] != 1 && !brokenTiles[i+1][j]) {
							
							emptyTiles[i+1][j] = true;
							openedTiles[i+1][j] = 0;
							flags[i+1][j] = 0;
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i-1][j+1] == 0 && flags[i-1][j+1] != 1 && !brokenTiles[i-1][j+1]) {
							
							emptyTiles[i-1][j+1] = true;
							openedTiles[i-1][j+1] = 0;
							flags[i-1][j+1] = 0;
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i][j+1] == 0 && flags[i][j+1] != 1 && !brokenTiles[i][j+1]) {
							
							emptyTiles[i][j+1] = true;
							openedTiles[i][j+1] = 0;
							flags[i][j+1] = 0;
							
						}
						
					} catch (Exception e) {
						
					}
					
					try {
						
						if (mines[i+1][j+1] == 0 && flags[i+1][j+1] != 1 && !brokenTiles[i+1][j+1]) {
							
							emptyTiles[i+1][j+1] = true;
							openedTiles[i+1][j+1] = 0;
							flags[i+1][j+1] = 0;
							
						}
						
					} catch (Exception e) {
						
					}
					
				}
				
			}
			
		}
		
	}

	public static void placeFlags(ImageView tile, Context applicationContext) {
		
		int number = 0;
		
		for (int i = 0; i < flags.length; i++) {
			
			for (int j = 0; j < flags[i].length; j++) {
				
				if (GameWindow.tiles[i][j] == tile) {
					
					//if tile is closed and is not broken
					if (openedTiles[i][j] == -1 && !brokenTiles[i][j]) {
						
						//place and remove flag
						//display icon depending type of flag
						if (flags[i][j] == 0 || flags[i][j] == 1) {
							
							flags[i][j]++;
							number = flags[i][j];
							GameWindow.tiles[i][j].setImageResource(ImageRegister.flagImages[number]);
							
						}
						else {
							
							flags[i][j] = 0;
							number = flags[i][j];
							GameWindow.tiles[i][j].setImageResource(ImageRegister.flagImages[number]);
							
						}
						
						if (flags[i][j] == 2 && rightFlags[i][j] != 1) {
							
							rightFlags[i][j] = 2;
							
						}
						else if (flags[i][j] == 0 && rightFlags[i][j] != 1) {
							
							rightFlags[i][j] = 0;
							
						}
						
						//test if flags are placed correctly
						GameWindow.gameOver = hasComplited();
						
						int stPlacedFlags = getPlacedFlags();
						int mineMinusFlags = stMines - stPlacedFlags;
						GameWindow.lblMines.setText(mineMinusFlags + "");
						
						if (hasComplited()) {

							//final ImageIcon icon = new ImageIcon(EventHandler.class.getResource("/assets/textures/gui/win.gif"));
							//JOptionPane.showMessageDialog(GameWindow.frame, "Congratulation!\r\nYou defused all mines\r\nYour Time:\r\n" + GameWindow.lblTimer.getText(), "Game Won", JOptionPane.DEFAULT_OPTION, icon);
							Toast.makeText(applicationContext, "You defused all mines Your Time:" + GameWindow.lblTimer.getText(), Toast.LENGTH_LONG).show();

							if (GameWindow.crazyModeOn) {
								
								LevelSaveAndLoad.saveBestScoreCrazy(MainWindow.username);
								
								if (GameWindow.minePercentage == 25) {
									
									GameWindow.level++;
									GameWindow.minePercentage = 10;
									
								}
								else {
									
									GameWindow.minePercentage += 3;
									
								}
								
								GameWindow.btnReset.setText("Next");
								LevelSaveAndLoad.saveLevel(MainWindow.username);
								LevelSaveAndLoad.savePowerUps(MainWindow.username);
								
							}
							else {
								
								LevelSaveAndLoad.saveBestScoreClassic(MainWindow.username);
								
							}
							
						}
						
						//izpis();
						
					}
					
				}
				
			}
			
		}
		
	}

	public static int getPlacedFlags() {
		
		int stPlacedFlags = 0;
		
		for (int i = 0; i < flags.length; i++) {
			
			for (int j = 0; j < flags[i].length; j++) {
				
				//if flags equals to 1
				if (flags[i][j] == 1) {
					
					stPlacedFlags++;
					
				}
				
			}
			
		}
		
		return stPlacedFlags;
		
	}

	public static boolean hasComplited() {
		
		//if placed flags equals to correct flags
		if (flagsToString().equals(rightFlagsToString())) {
			
			return true;
			
		}
		else {
			
			return false;
			
		}
		
	}

	public static Object rightFlagsToString() {
		
		String string = "";
		
		for (int i = 0; i < rightFlags.length; i++) {
			
			for (int j = 0; j < rightFlags[i].length; j++) {
				
				string = string + rightFlags[i][j];
				
			}
			
		}
		
		return string;
		
	}

	public static Object flagsToString() {
		
		String string = "";
		
		for (int i = 0; i < flags.length; i++) {
			
			for (int j = 0; j < flags[i].length; j++) {
				
				string = string + flags[i][j];
				
			}
			
		}
		
		return string;
		
	}

	public static void reset() {
		
		//set all global variables to default state
		stLeftMines = stMines;
		stFlags = stMines;
		mines = null;
		flags = null;
		rightFlags = null;
		emptyTiles = null;
		crates = null;
		readyCrates = null;
		xrayTiles = null;
		nukes = null;
		openedTiles = null;
		GameWindow.tiles = null;
		GameWindow.gameOver = false;
		GameWindow.width = 0;
		GameWindow.height = 0;
		GameWindow.timeInSec = -1;
		GameWindow.secunds = -1;
		GameWindow.minutes = 0;
		GameWindow.hours = 0;
		
	}

	public static void rightFlags() {
		
		for (int i = 0; i < mines.length; i++) {
			
			for (int j = 0; j < mines[i].length; j++) {
				
				//if tile has mine set right flags array index to 1;
				if (mines[i][j] == 9) {
					
					rightFlags[i][j] = 1;
					
				}
				else {
					
					rightFlags[i][j] = 0;
					
				}
				
			}
			
		}
		
	}
	
	public static void crazyMode() {
		
		if (GameWindow.crazyModeOn) {
			
			ImageRegister.imageCrateLoader();
			placeCrates();
			
		}
		
	}

	public static void placeCrates() {
		
		//same as generation of mines
		int xCoord = 0;
		int yCoord = 0;
		
		while (stLeftCrates != 0) {
			
			do {
				
				xCoord = (int)(Math.random() * rows + 0);
				yCoord = (int)(Math.random() * columns + 0);
				
			} while (mines[xCoord][yCoord] == 9);
			
			crates[xCoord][yCoord] = true;
			stLeftCrates--;
			
		}
		
		for (int i = 0; i < crates.length; i++) {
			
			for (int j = 0; j < crates[i].length; j++) {
				
				System.out.print(crates[i][j]);
				
			}
			
			System.out.println();
			
		}
		
	}

	public static void breakTile(ImageView tile, Context applicationContext) {
		
		//same as open tile but only end game as game over when nuke is under the tile
		for (int i = 0; i < brokenTiles.length; i++) {
			
			for (int j = 0; j < brokenTiles[i].length; j++) {
				
				if (GameWindow.tiles[i][j] == tile && flags[i][j] != 1 && GameWindow.uses > 0) {
					
					int number = mines[i][j];
					GameWindow.tiles[i][j].setImageResource(ImageRegister.imagesBroken[number]);
					brokenTiles[i][j] = true;
					crates[i][j] = false;
					
					if (mines[i][j] == 9 && !nukes[i][j]) {
						
						flags[i][j] = 1;
						
						int stPlacedFlags = getPlacedFlags();
						int mineMinusFlags = stMines - stPlacedFlags;
						GameWindow.lblMines.setText(mineMinusFlags + "");
						
					}
					else if (mines[i][j] == 9 && nukes[i][j]) {
						
						showMines();
						
						number = mines[i][j];
						GameWindow.tiles[i][j].setImageResource(ImageRegister.imageNuke);
						GameWindow.gameOver = true;

						//final ImageIcon icon = new ImageIcon(EventHandler.class.getResource("/assets/textures/gui/over.gif"));
						//JOptionPane.showMessageDialog(GameWindow.frame, "Oops!\r\nYou stepped on the mine", "Game Over", JOptionPane.DEFAULT_OPTION, icon);
						Toast.makeText(applicationContext, "You stepped on the mine", Toast.LENGTH_LONG).show();
						
					}
					
					GameWindow.uses--;
					
					if (GameWindow.uses == 0) GameWindow.drillSelected = false;
					
				}
				
			}
			
		}
		
	}

	public static void xray(ImageView tile) {
		
		//prepare X-Ray for display
		for (int i = 0; i < mines.length; i++) {
			
			for (int j = 0; j < mines.length; j++) {
				
				if(GameWindow.tiles[i][j] == tile && GameWindow.xraySelected) {
					
					xrayTiles[i][j] = true;
					GameWindow.xrayActive = true;
					
				}
				else {
					
					xrayTiles[i][j] = false;
					
				}
				
				//System.out.print(xrayTiles[i][j]);
				
			}
			
			//System.out.println();
			
		}
		
		setXRay();
		
	}

	public static void setXRay() {
		
		//set X-Ray icon depending on if there is mine or not
		for (int i = 0; i < xrayTiles.length; i++) {
			
			for (int j = 0; j < xrayTiles.length; j++) {
				
				if (openedTiles[i][j] == -1 && !GameWindow.openingCrate && !GameWindow.gameOver) {
					
					if (xrayTiles[i][j] == true) {
						
						if (!noXray[i][j]) {
							
							if (mines[i][j] == 9) {
								
								gameWindow.updateImages(gameWindow.tiles[i][j], ImageRegister.imagesXRay[1]);
								
							}
							else {

								gameWindow.updateImages(gameWindow.tiles[i][j], ImageRegister.imagesXRay[0]);
								
							}
							
						}
						else {

							gameWindow.updateImages(gameWindow.tiles[i][j], ImageRegister.tile_no_xray);
							
						}
						
					}
					else {

						gameWindow.updateImages(gameWindow.tiles[i][j], ImageRegister.flagImages[flags[i][j]]);
						
					}
					
				}
				
			}
			
		}
		
	}
	
	public static void xRayCooldown() {
		
		//Cooldown for X-Ray
		if (!GameWindow.openingCrate && GameWindow.xraySelected) {
			
			if (GameWindow.xrayActive) {
				
				GameWindow.time--;
				
			}
			
			if (GameWindow.time == 0) {
				
				GameWindow.xraySelected = false;
				GameWindow.xrayActive = false;
				
			}
			
		}
		
	}

	public static void timer() {
		
		if (!GameWindow.openingCrate) {
			
			GameWindow.timeInSec++;
			GameWindow.secunds++;
			
			if (GameWindow.secunds >= 60) {
				
				GameWindow.secunds = GameWindow.secunds - 60;
				GameWindow.minutes++;
				
			}
			
			if (GameWindow.minutes >= 60) {
				
				GameWindow.minutes = GameWindow.minutes - 60;
				GameWindow.hours++;
				
			}
			
			time = "";
			
			if (GameWindow.hours < 10) {
				
				time = time + 0 + GameWindow.hours + ":";
				
			}
			else {
				
				time = time + GameWindow.hours + ":";
				
			}
			
			if (GameWindow.minutes < 10) {
				
				time = time + 0 + GameWindow.minutes + ":";
				
			}
			else {
				
				time = time + GameWindow.minutes + ":";
				
			}
			
			if (GameWindow.secunds < 10) {
				
				time = time + 0 + GameWindow.secunds;
				
			}
			else {
				
				time = time + GameWindow.secunds;
				
			}
			
			//GameWindow.lblTimer.setText(time);
			
		}
		
	}
	
}
