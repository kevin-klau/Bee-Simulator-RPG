// Program: Bee Simulator
// Created By: Kevin Lau
// Creation Date: March 1st, 2022
// Version 2.0 
// None of the Photos are owned by the creator, credits given to their rightful owners

import arc.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class RPGWorld{
	public static void main (String[]args){
		Console con = new Console("Bee Simulator",900,600);
		
		int intPlayerHealth = 50;
		char chrPlayerMenuChoice='0';
		
		while (chrPlayerMenuChoice != ('q') && intPlayerHealth > 0){
			// Reset Player Health if They PLay Again
			intPlayerHealth = 50;
			
			// Show Main Menu, Ask If They Want To Play, Go To Help Screen, or Just Quit
			
			// Title Page - Formatting (Fonts/Background)
			con.setBackgroundColor(new Color (255, 234, 128));
			
			con.setDrawColor (new Color (245,162,20));
			con.fillRect (0,0,1000,175);
			
			con.setDrawColor (Color.BLACK);
			con.fillRect (0,165,1000,10);
			
			Font TitleFont;
			Font TextFont;
			
			TitleFont = con.loadFont ("impact.ttf",125);
			con.setDrawFont(TitleFont);
			
			TextFont = con.loadFont ("Exo2.0.otf",38);
			con.setTextFont (TextFont);
			con.setTextColor (Color.BLACK);
			
			Font TitleFontSmall = con.loadFont ("impact.ttf", 100);

			
			// Title Page - Bee Photo
			BufferedImage Bee = con.loadImage ("Bee.png");
			con.drawImage (Bee, 555, 250);
			con.repaint();
			
			// Title Page - Title		
			con.setDrawColor (Color.BLACK);
			con.drawString ("BEE SIMUATOR", 85,-17);
			
			// Title Page - Text!!
			con.clear();
			con.println ();
			con.println ();
			con.println ();
			con.println ();
			
			con.println ("  What Would You Like To Do??? Press:");
			con.println ();
			con.println ("  (P) to Play");
			con.println ("  (H) for Help Screen");
			con.println ("  (Q) to Quit");
			con.println ();
			con.println ();
			
			con.print ("   Good Luck Being a Bee!!!");
			
			chrPlayerMenuChoice = con.getChar();
			
			// Load Tiles For Game and Help Screen
			BufferedImage Bird = con.loadImage("Bird.png");
			BufferedImage Building = con.loadImage("building.png");
			BufferedImage Flower = con.loadImage("Flower.png");
			BufferedImage Grass = con.loadImage("grass.png");
			BufferedImage Honey = con.loadImage("Honey.png");
			BufferedImage Ladybug = con.loadImage("Ladybug.png");
			BufferedImage Tree = con.loadImage("tree.png");
			BufferedImage Wasp = con.loadImage("Wasp.png");
			BufferedImage Water = con.loadImage("water.png");
			BufferedImage Wings = con.loadImage("Wings.png");
				
			// Route To Play, Help Menu, or Quit!
			if (chrPlayerMenuChoice == 'p' || chrPlayerMenuChoice == 'P'){
				// Play Menu
				con.clear();
				
				// Menu To Ask Which Map To Choose
				// Setup Background and Titles
				con.setBackgroundColor(new Color (245,162,20));
				con.setDrawColor (Color.BLACK);
				con.setDrawFont (TitleFontSmall);
				con.setDrawColor (Color.BLACK);
				con.fillRect (0,0,900,140);
				con.setDrawColor (Color.WHITE);
				con.drawString ("Map Selector!!!",140, -15);
				
				con.repaint();
				
				// Display Map 1 and 2
				// Map 1
				BufferedImage Map1Photo;
				Map1Photo = con.loadImage("Map1.png");
				con.setDrawColor (Color.BLACK);
				con.fillRect(40,160,380,380);
				con.drawImage (Map1Photo, 50, 170);
				con.setDrawFont (TextFont);
				con.setDrawColor (Color.BLACK);
				con.drawString ("Map (1)", 165, 535);
						
				// Map 2
				BufferedImage Map2Photo;
				Map2Photo = con.loadImage("Map2.png");
				con.setDrawColor (Color.BLACK);
				con.fillRect(480,160,380,380);
				con.drawImage (Map2Photo, 490, 170);
				con.setDrawFont (TextFont);
				con.drawString ("Map (2)", 600, 535);
				
				con.repaint(); 
				
				// Get Map Input
				char chrMapChoice = '0';
				int intMapChoice=0; 
				
				// If they don't enter 1 or 2, the program will wait until they do
				while (chrMapChoice != '1' && chrMapChoice != '2'){
					chrMapChoice = con.getChar();
				}
				
				if (chrMapChoice == '1'){
					intMapChoice = 1;
				}else if (chrMapChoice == '2'){
					intMapChoice = 2;
				}
				
				// Making the intCount Variable
				int intCount;
				
				// Open Map File and Input Info Into Array
				// Setting Map Array Boundaries
				String strMap[][];
				strMap = new String [20][20];
				
				// Setting CSV Array 
				String strCsvSplit[];
				strCsvSplit = new String [20];
				
				// Opening File
				TextInputFile txtMap;
				txtMap = new TextInputFile (("Map"+intMapChoice+".csv"));
			
				// Inputting CSV into Map Array
				String strCsvInput; 
				int intCount1;
				
				for (intCount = 0; intCount < 20; intCount++){
					strCsvInput = txtMap.readLine();
					strCsvSplit = strCsvInput.split(",");
					
					for (intCount1 = 0; intCount1 < 20; intCount1++){
						strMap[intCount][intCount1] = strCsvSplit[intCount1];
					}
				}
				
				// Close File
				txtMap.close();
				
				// Find How Many Enemies In Total
				int intTotalEnemies=0; 
				for (intCount = 0; intCount < 20; intCount++){
					for (intCount1 = 0; intCount1 < 20; intCount1++){
						if (strMap[intCount][intCount1].equalsIgnoreCase("Wasp")||strMap[intCount][intCount1].equalsIgnoreCase("Ladybug")||strMap[intCount][intCount1].equalsIgnoreCase("Bird")){
							intTotalEnemies++;
						}
					}
				}

				// Set Player Health Cap, Defence, and Attack
				int intPlayerHealthCap = 50;
				int intPlayerDefence = 10;
				int intPlayerAttack = 10;
				
				// Initialize Enemy Stats
				boolean blnFight = false;
				String strEnemyName = "";
				int intEnemyAttack = 0;
				int intEnemyDefence = 0;
				int intEnemyHealth = 0;
					
				// Set Boolean So The Game Knows If You Killed All The Enemies
				boolean blnCompleteGame = false;
				
				// Set Boolean So The Game Knows If You Drowned
				boolean blnDrown = false;
				
				// Set Player Position For The 2 Maps
				int intPlayerPositionX = 0;
				int intPlayerPositionY = 0;
				
				if (intMapChoice == 1){
					intPlayerPositionX = 18;
					intPlayerPositionY = 14;
				} else if(intMapChoice == 2){
					intPlayerPositionX = 1;
					intPlayerPositionY = 13;
				}
				// Intiializing Variables For HUD
				int intHudCount = 0;
				String strHudText = "";
				
				// Variable for Character Movement
				String strBeeCharacterDirection = "Down";
				
				// Loop Everything Until Player Dies or Game Is Completed
				while (intPlayerHealth > 0 && blnCompleteGame == false){
					// Add a sleep function (so the player can control)
					con.sleep (30);
					
					// Clear Everything
					con.clear();
					con.setDrawColor (new Color(255, 234, 128));
					con.fillRect (610,0,300,600);
					con.setDrawColor (Color.BLACK);
					con.fillRect (600,0,10,600);
					con.fillRect (610, 430, 300, 8);
						
					// Draw The Map
					for (intCount = 0; intCount < 20; intCount++){
						for (intCount1 = 0; intCount1 < 20; intCount1++){
							if (strMap[intCount][intCount1].equalsIgnoreCase("Bird")){
								con.drawImage(Bird,intCount1*30,intCount*30);
							} else if (strMap[intCount][intCount1].equalsIgnoreCase("Building")){
								con.drawImage(Building,intCount1*30,intCount*30);
							} else if (strMap[intCount][intCount1].equalsIgnoreCase("Flower")){
								con.drawImage(Flower,intCount1*30,intCount*30);
							} else if (strMap[intCount][intCount1].equalsIgnoreCase("Grass")){
								con.drawImage(Grass,intCount1*30,intCount*30);
							} else if (strMap[intCount][intCount1].equalsIgnoreCase("Honey")){
								con.drawImage(Honey,intCount1*30,intCount*30);
							} else if (strMap[intCount][intCount1].equalsIgnoreCase("Ladybug")){
								con.drawImage(Ladybug,intCount1*30,intCount*30);
							} else if (strMap[intCount][intCount1].equalsIgnoreCase("Tree")){
								con.drawImage(Tree,intCount1*30,intCount*30);
							} else if (strMap[intCount][intCount1].equalsIgnoreCase("Wasp")){
								con.drawImage(Wasp,intCount1*30,intCount*30);
							} else if (strMap[intCount][intCount1].equalsIgnoreCase("Water")){
								con.drawImage(Water,intCount1*30,intCount*30);
							} else if (strMap[intCount][intCount1].equalsIgnoreCase("Wings")){
								con.drawImage(Wings,intCount1*30,intCount*30);
							}
						}
					}
					
					// Draw The Player
					BufferedImage Player;
					Player = con.loadImage("BeeCharacter" + strBeeCharacterDirection +".png");
					con.drawImage (Player, intPlayerPositionX*30, intPlayerPositionY*30);
					
					// HUD
					// Photo of Bee
					BufferedImage BeeImage;
					BeeImage = con.loadImage ("MinecraftBee.png");
					con.drawImage (BeeImage, 630,0);
					
					// Set Font
					con.setDrawFont(TextFont);
					
					// Health, Attack, and Defence Display
					con.drawString (("Health: "+intPlayerHealth), 630,260);
					con.drawString (("Attack: "+intPlayerAttack), 630, 310);
					con.drawString (("Defence: "+intPlayerDefence), 630, 360);
					
					// HUD Text Display
					con.setDrawFont (TextFont);
					if (intHudCount < 5){
						con.drawString (strHudText, 620, 500);
						intHudCount++;
					}
									
					// Screen If Player Drowned
					if (blnDrown == true){
						intPlayerHealth = 0;
						con.setDrawColor (new Color (225,119,51));
						con.fillRect (165,140,590, 280);
						con.setDrawColor (new Color (255,212,128));
						con.fillRect (195, 170, 530, 220);
						
						con.setDrawFont (TextFont);
						con.setDrawColor (Color.BLACK);
						con.drawString ("YOU DIED BY DROWNING!!", 216, 175);
						con.drawString ("Bees can't swim if you", 250, 280);
						con.drawString ("didn't know....", 340, 325);
						con.repaint();
						
						con.sleep (5000);
						con.closeConsole();
					}
					
					// Repaint
					con.repaint();
					
					
					// FIGHT SCENE
					if (blnFight == true){
						// Fight Popup
						con.setDrawColor (new Color(240,37,22));
						con.fillRect (165,140,590, 240);
						con.setDrawColor (new Color (10,157,226));
						con.fillRect (195, 170, 530, 180);
						
						Font TitleFontLarge = con.loadFont ("impact.ttf", 150);
						con.setDrawFont (TitleFontLarge);
						con.setDrawColor (Color.BLACK);
						con.drawString ("FIGHT!!!", 230, 135);
						con.repaint();
						con.sleep (1000);
						
						// New Console
						Console Fight = new Console ("Fight!", 900, 635);
						
						// Add Background Photos
						BufferedImage BackgroundFight  = Fight.loadImage ("Backgroundfight.png");
						BufferedImage BackgroundFightLeft = Fight.loadImage("Backgroundfightleft.png");
						BufferedImage BackgroundFightRight = Fight.loadImage ("Backgroundfightright.png");
						while (intPlayerHealth > 0 && intEnemyHealth > 0){
							// Background
							Fight.drawImage (BackgroundFight, 0,0);
							Fight.setDrawColor (Color.BLACK);
							Fight.fillRect (0,300,900,5);
							
							// Draw Player and Enemy 
							BufferedImage PlayerFight = Fight.loadImage ("Beefight.png");
							BufferedImage EnemyFight = Fight.loadImage (strEnemyName + "fight.png");
							
							Fight.drawImage (PlayerFight, 75,25);
							Fight.drawImage (EnemyFight, 525, 25);
							
							// Background for HUD
							Fight.setDrawColor (new Color(255, 234, 128));
							Fight.fillRect (0,305,900,700);
							Fight.setDrawColor (Color.BLACK);
							Fight.fillRect (450,305,5,238);
							Fight.fillRect (0,543,900,5);
							
							// Player and Enemy Stats
							Font TextFontSmall = con.loadFont ("Exo2.0.otf",35);
							Fight.setDrawFont (TextFontSmall);
							Fight.setDrawColor (Color.BLACK);
							
							// Player
							Fight.drawString ("Player Stats:", 125, 300);
							Fight.drawString ("Health: "+intPlayerHealth, 125, 345);
							Fight.drawString ("Attack: "+intPlayerAttack, 125, 385);
							Fight.drawString ("Defence: "+intPlayerDefence, 125, 425);
							
							// Enemy
							Fight.drawString (strEnemyName + " Stats:", 550, 300);
							Fight.drawString ("Health: "+intEnemyHealth, 550, 345);
							Fight.drawString ("Attack: "+intEnemyAttack, 550, 385);
							Fight.drawString ("Defence: "+intEnemyDefence, 550, 425);
							
							// Add Health Bar
							int intPlayerHealthBarX;
							int intPlayerHealthColor;
							int intPlayerHealthColor2=255;
							
							// Change Health Bar if they take damage
							// Player Bar
							intPlayerHealthBarX = 336 * intPlayerHealth/intPlayerHealthCap;						
							// Repaint Health Bar
							Fight.setDrawColor (Color.BLACK);
							Fight.fillRect (50, 485,350,40);
							// Change Color
							intPlayerHealthColor = (255 - (255*intPlayerHealth/intPlayerHealthCap))*2;
							if ((intPlayerHealth*100/intPlayerHealthCap)<50){
								intPlayerHealthColor = 255;
								intPlayerHealthColor2 = 255*2*intPlayerHealth/intPlayerHealthCap;
							}
							// Prevent the bug where the program stops when it hits 266 for intPlayerHealthColor and intPlayerHealthColor2
							if (intPlayerHealthColor > 255){
								intPlayerHealthColor = 255;
							}
							if (intPlayerHealthColor2 > 255){
								intPlayerHealthColor2 = 255;
							}
							
							// Draw Health Bar
							Fight.setDrawColor (new Color (intPlayerHealthColor, intPlayerHealthColor2,0));
							Fight.fillRect (57, 493, intPlayerHealthBarX, 24);
							
							
							// Enemy Bar
							int intEnemyHealthBarX;
							int intEnemyHealthColor;
							int intEnemyHealthColor2=255;
							int intEnemyHealthCap=0;
							// Set Enemy Max
							if (strEnemyName.equalsIgnoreCase ("Ladybug")){
								intEnemyHealthCap = 50;
							} else if (strEnemyName.equalsIgnoreCase ("Wasp")){
								intEnemyHealthCap = 100;
							} else if (strEnemyName.equalsIgnoreCase ("Bird")){
								intEnemyHealthCap = 200;
							}
							intEnemyHealthBarX = 336 * intEnemyHealth/intEnemyHealthCap;
													
							
							// Enemy Bar
							intEnemyHealthBarX = 336 * intEnemyHealth/intEnemyHealthCap;
													
							// Repaint Health Bar
							Fight.setDrawColor (Color.BLACK);
							Fight.fillRect (500, 485,350,40);
							// Change Color
							intEnemyHealthColor = (255 - (255*intEnemyHealth/intEnemyHealthCap))*2;
							if ((intEnemyHealth*100/intEnemyHealthCap)<50){
								intEnemyHealthColor = 255;
								intEnemyHealthColor2 = 255*2*intEnemyHealth/intEnemyHealthCap;
							}
							
							// Prevent the bug where the program stops when it hits 266 for intPlayerHealthColor and intPlayerHealthColor2
							if (intPlayerHealthColor > 255){
								intPlayerHealthColor = 255;
							}
							if (intPlayerHealthColor2 > 255){
								intPlayerHealthColor2 = 255;
							}
							Fight.setDrawColor (new Color (intEnemyHealthColor, intEnemyHealthColor2,0));
							
							Fight.fillRect (507, 493, intEnemyHealthBarX, 24);
							Fight.repaint();
							Fight.println ();
							
							
							// Fight Slide Thingy
							boolean blnFightDone = false; 						
							int intXFight = 50;
							int intAdder = (int)(Math.random()*15+10);
							char chrStop = 'a';
							int intTotalPlayerDamage = 0;
							while (blnFightDone == false){							
								// Display The Slider
								Fight.setDrawColor (new Color (255, 234, 128));
								Fight.fillRect (0,560,900,100);
								Fight.setDrawColor (Color.BLACK);
								Fight.fillRect (45,570,810,40);
								Fight.setDrawColor (Color.RED);
								Fight.fillRect (50,575,200,30);
								Fight.fillRect (650,575,200,30);
								Fight.setDrawColor (Color.YELLOW);
								Fight.fillRect (250,575,150,30);
								Fight.fillRect (500,575,150,30);
								Fight.setDrawColor (Color.GREEN);
								Fight.fillRect (400,575,100,30);
								
								//System.out.println (intXFight);
								Fight.setDrawColor(Color.BLACK);
								Fight.fillRect (intXFight, 562, 3, 60);
								intXFight = intXFight + intAdder;
								
								if (intXFight >= (850-intAdder) || intXFight <= 50){
									intAdder = -intAdder;
								}
								
								//Stop When Player Hits Any Key
								chrStop = Fight.currentChar();
								if (chrStop == (' ')){
									intXFight = intXFight - intAdder;
									// Damage Calculations With Slider
									int intDamageMultiplier = 0;
									if (intXFight < 250 || intXFight > 650){
										// If they are in the red, they do half their attack damage, when calculating damage applied, if statement to divide their attack by 2
										intDamageMultiplier = 3;
									}else if (intXFight <= 500 && intXFight >= 400){
										// If they are in the green, they do double their attack damage
										intDamageMultiplier = 2;
									} else if (intXFight >= 250 || intXFight < 400 || intXFight > 500 || intXFight <= 650){
										// If they are in the yellow, their attack = damage
										intDamageMultiplier = 1;
									}
									
									// Damage the Enemy
									// Damage Calculation = PlayerAttack * Damage Multiplier / (EnemyDefence/100)
									intTotalPlayerDamage = ((intPlayerAttack * intDamageMultiplier) * (100-intEnemyDefence))/100;
									// To account for the player getting red (as their current multiplier is 3 (since it is an int, it couldn't be 0.5)
									if (intDamageMultiplier == 3){
										intTotalPlayerDamage = ((intPlayerAttack / 2) * (100-intEnemyDefence))/100;
									}
									blnFightDone = true;
									
									// Different Damage Animations
									boolean blnPlayerAnimationDone = false;
									if (intDamageMultiplier == 3){
										// Lowest Damage Animation (Headbutt)
										int intBeeHeadbuttX = 75;
										int intBeeHeadbuttXAdd = 6;
										int intHeadbuttCount = 1;
										
										boolean blnLadybugBlink = false;
										// Forward Headbutt
										while (blnPlayerAnimationDone == false){
											if (intBeeHeadbuttX >= 465){
												blnPlayerAnimationDone = true;
											}
											Fight.drawImage (BackgroundFight,0,0);
											Fight.drawImage (EnemyFight, 525,25);
											Fight.drawImage (PlayerFight, intBeeHeadbuttX, 25);
											Fight.repaint();
											Fight.sleep (1000/60);
											intBeeHeadbuttX = intBeeHeadbuttX + intBeeHeadbuttXAdd;
											if (intHeadbuttCount == 2){
												intBeeHeadbuttXAdd = intBeeHeadbuttXAdd+1;
												intHeadbuttCount = 0;
											}
											intHeadbuttCount++;
											
										}
										Fight.sleep (100);
										blnPlayerAnimationDone = false;
										intBeeHeadbuttX = intBeeHeadbuttX - intBeeHeadbuttXAdd;
										
										// Send bee to Beginning
										while (blnPlayerAnimationDone == false){
											if (intBeeHeadbuttX <= 75){
												blnPlayerAnimationDone = true;
												intBeeHeadbuttX = 75;
											}
											
											Fight.drawImage (BackgroundFight,0,0);
											// Enemy Takes Damage Animation
											if (intBeeHeadbuttX > 465 || intBeeHeadbuttX < 365){
												Fight.drawImage (EnemyFight, 525,25);
											}
											
											Fight.drawImage (PlayerFight, intBeeHeadbuttX, 25);
											Fight.repaint();
											intBeeHeadbuttX = intBeeHeadbuttX - 8;
											Fight.sleep (1000/60);
											
										}
										
									}else if (intDamageMultiplier == 1){
										con.sleep (300);
										// Middle Damage Animation (Bee turns aruond and fires 3 stingers)
										BufferedImage BeeBackwards = Fight.loadImage ("Beefightback.png");
										BufferedImage BeeStinger = Fight.loadImage ("Beefightstinger.png");
										int intBeeStingerX = 300;
										int intBeeStingCount = 0;
										int intEnemyBlinkCount = 0;
										
										// Flip bee and pause
										Fight.drawImage (BackgroundFight,0,0);
										Fight.drawImage (EnemyFight, 525,25);
										Fight.drawImage (BeeStinger, intBeeStingerX, 115);
										Fight.drawImage (BeeBackwards, 75,25);
										Fight.repaint();
										Fight.sleep (300);
										
										while (blnPlayerAnimationDone == false){		
											// Repaint Enemies and Player										
											Fight.drawImage (BackgroundFight,0,0);
											Fight.drawImage (EnemyFight, 525,25);
											if (intBeeStingerX >= 300){
												Fight.drawImage (BeeStinger, intBeeStingerX, 115);
											}
											Fight.drawImage (BeeBackwards, 75,25);
											
											// Enemy Taking Damage Animation
											if (intBeeStingCount == 1 || intBeeStingCount == 2){
												if (intBeeStingerX >=200 && intBeeStingerX <= 300){
													Fight.drawImage (BackgroundFightRight,450,0);
												}
											}
											
											// Stinger Moving
											intBeeStingerX = intBeeStingerX + 8;
											Fight.repaint();
											Fight.sleep (1000/60);
											
											// Repeat over and over
											if (intBeeStingerX >= 570){
												intBeeStingCount++;
												intBeeStingerX = 150;
												Fight.sleep (100);
											}
											
											// Stop after 3 Times
											if (intBeeStingCount == 3){
												blnPlayerAnimationDone = true;
											}
											
										}
										
										// Reprint Bee looking forwards	
										Fight.drawImage (BackgroundFight,0,0);
										Fight.drawImage (PlayerFight, 75,25);
										Fight.drawImage (EnemyFight, 525,25);
										Fight.repaint();
										
										// Reprint Enemy Blinking
										Fight.drawImage (EnemyFight, 525,25);
										Fight.repaint();
										Fight.sleep (100);
										Fight.drawImage (BackgroundFightRight, 450,0);
										Fight.repaint();
										Fight.sleep (200);
										Fight.drawImage (EnemyFight, 525,25);
										Fight.repaint();
										Fight.sleep (300);
										
									}else if (intDamageMultiplier == 2){
										// Highest Damage Animation (The Assasination)
										// Making Bee Dissapear
										Fight.sleep (500);
										Fight.drawImage (BackgroundFightLeft, 0,0);
										Fight.repaint();
										Fight.sleep (1000);
										
										// Making Bee Run from Right side to Left
										BufferedImage BeeLeft = Fight.loadImage ("Beefightslash.png");
										BufferedImage Slash = Fight.loadImage("Beefightslasheffect.png");
										int intBeeSlashX = 915;
										blnPlayerAnimationDone = false;
										
										while (blnPlayerAnimationDone == false){
											if (intBeeSlashX <= 75){
												blnPlayerAnimationDone = true;
												intBeeSlashX = 75;
											} 
											Fight.drawImage (BackgroundFight,0,0);
											Fight.drawImage (EnemyFight, 525,25);
											Fight.drawImage (BeeLeft, intBeeSlashX, 25);
											if (intBeeSlashX <= 300){
												Fight.drawImage (Slash, 510,10);
											}
											Fight.repaint();
											Fight.sleep (1000/60);
											
											intBeeSlashX = intBeeSlashX - 60;
											
										}
										
										// Slash Animation and Dissapear
										Fight.sleep (1050);
										Fight.drawImage (BackgroundFightRight,450,0);
										Fight.drawImage (EnemyFight,525,25);
										//Fight.repaint();
										//Fight.sleep (300);
										
										// Enemy Taking Damage
										Fight.drawImage (BackgroundFightRight,450,0);
										Fight.repaint();
										Fight.sleep (300);
										Fight.drawImage (EnemyFight,525,25);
										Fight.repaint ();
										Fight.sleep (300);
										
										// Bee Turning Around
										Fight.drawImage (BackgroundFightLeft,0,0);
										Fight.drawImage (PlayerFight,75,25);
										Fight.repaint ();
										
									}	
								}
								// Making The Repaint
								Fight.sleep (1000/60);
								Fight.repaint();
							}
							
							// Damaging the Enemy
							intEnemyHealth = intEnemyHealth - intTotalPlayerDamage;
							
							// Turn Enemy Negative Health To 0
							if (intEnemyHealth < 0){
								intEnemyHealth = 0;
							}			
										
							Fight.setDrawColor (new Color(255, 234, 128));
							Fight.fillRect (460,310,450,170);
							
							Fight.setDrawFont (TextFontSmall);
							Fight.setDrawColor (Color.BLACK);
							
							Fight.drawString (strEnemyName + " Stats:", 550, 300);
							Fight.drawString ("Health: "+intEnemyHealth, 550, 345);
							Fight.drawString ("Attack: "+intEnemyAttack, 550, 385);
							Fight.drawString ("Defence: "+intEnemyDefence, 550, 425);
							
							// Updating Stats and Bar
							// Enemy Bar
							intEnemyHealthBarX = 336 * intEnemyHealth/intEnemyHealthCap;
													
							// Repaint Health Bar
							Fight.setDrawColor (Color.BLACK);
							Fight.fillRect (500, 485,350,40);
							// Change Color
							intEnemyHealthColor = (255 - (255*intEnemyHealth/intEnemyHealthCap))*2;
							if ((intEnemyHealth*100/intEnemyHealthCap)<50){
								intEnemyHealthColor = 255;
								intEnemyHealthColor2 = 255*2*intEnemyHealth/intEnemyHealthCap;
							}
							// Prevent the bug where the program stops when it hits 266 for intPlayerHealthColor and intPlayerHealthColor2
							if (intPlayerHealthColor > 255){
								intPlayerHealthColor = 255;
							}
							if (intPlayerHealthColor2 > 255){
								intPlayerHealthColor2 = 255;
							}
							
							Fight.setDrawColor (new Color (intEnemyHealthColor, intEnemyHealthColor2,0));
							
							Fight.fillRect (507, 493, intEnemyHealthBarX, 24);
							
							Fight.repaint();
							Fight.sleep (1000);
							
							// Enemy Damaging Player and Animation
							int intTotalEnemyDamage = 0;
							// Make the Birds damage increase each round
							if (strEnemyName.equalsIgnoreCase ("Bird")){
								intEnemyAttack = intEnemyAttack + 5;
							}
							if (intEnemyHealth > 0){
								// Damage
								intTotalEnemyDamage = (intEnemyAttack * (100-intPlayerDefence))/100;
								intPlayerHealth = intPlayerHealth -intTotalEnemyDamage;			
								
								// Enemy Animation
								BufferedImage EnemyAction;
								int intAttackX=450;
								if (strEnemyName.equalsIgnoreCase ("Ladybug")){
									// Bees
									EnemyAction = Fight.loadImage ("Ladybugaction.png");
									while(intAttackX > 160){
										Fight.drawImage (BackgroundFight, 0,0);
													
										Fight.drawImage (PlayerFight, 75,25);
										Fight.drawImage (EnemyFight, 525, 25);
										
										Fight.drawImage (EnemyAction, intAttackX, 100);
										intAttackX = intAttackX - 10;
										Fight.repaint();
											
										Fight.sleep (1000/60);
									}
									// Player Takes Damage Animation
									for (intCount = 0; intCount < 3; intCount++){
										if (intCount == 0 || intCount == 2){
											Fight.drawImage (BackgroundFightLeft, 0,0);
											Fight.drawImage (PlayerFight, 75,25);
											Fight.drawImage (EnemyAction, intAttackX, 100);
											Fight.repaint(); 
											Fight.sleep (100);
										} else if (intCount == 1){										
											Fight.drawImage (BackgroundFightLeft, 0,0);
											Fight.drawImage (EnemyAction, intAttackX, 100);
											Fight.repaint();
											Fight.sleep (200);
										}
									}
									con.sleep(400);
									
								} else if (strEnemyName.equalsIgnoreCase ("Wasp")){
									boolean blnEnemyAnimationDone = false;
									int intWaspY1 = -0;
									int intWaspY2 = -500;
									// Wasp
									BufferedImage WaspAction1 = Fight.loadImage ("WaspAction1.png");
									BufferedImage WaspAction2 = Fight.loadImage ("WaspAction2.png");
									while(blnEnemyAnimationDone == (false)){
										Fight.drawImage (BackgroundFight, 0,0);
													
										Fight.drawImage (PlayerFight, 75,25);
										Fight.drawImage (EnemyFight, 525, 25);
										
										if (intWaspY1 > -250){
											Fight.drawImage (WaspAction1, 550, intWaspY1);
											intWaspY1 = intWaspY1 -10;
										} else if (intWaspY1 <= -250){
											Fight.drawImage (WaspAction2, 100, intWaspY2);
											intWaspY2 = intWaspY2 +10;
										}
										
										if (intWaspY2 >= 70){
											blnEnemyAnimationDone = true;
										}
											
										Fight.repaint();
										
										Fight.sleep (1000/60);
									}
									// Player Takes Damage Animation
									for (intCount = 0; intCount < 3; intCount++){
										if (intCount == 0 || intCount == 2){
											Fight.drawImage (BackgroundFightLeft, 0,0);
											Fight.drawImage (PlayerFight, 75,25);
											Fight.drawImage (WaspAction2, 100, intWaspY2);
											Fight.repaint(); 
											Fight.sleep (100);
										} else if (intCount == 1){										
											Fight.drawImage (BackgroundFightLeft, 0,0);
											Fight.drawImage (WaspAction2, 100, intWaspY2);
											Fight.repaint();
											Fight.sleep (200);
										}
									}
									Fight.sleep (400);
									
								} else if (strEnemyName.equalsIgnoreCase ("Bird")){
									// Bird Fight Animations
									EnemyAction = Fight.loadImage ("Birdaction.png");
									while(intAttackX > 160){
										Fight.drawImage (BackgroundFight, 0,0);
													
										Fight.drawImage (PlayerFight, 75,25);
										Fight.drawImage (EnemyFight, 525, 25);
										
										Fight.drawImage (EnemyAction, intAttackX, 50);
										intAttackX = intAttackX - 10;
										Fight.repaint();
											
										Fight.sleep (1000/60);
									}
									BufferedImage Explosion = Fight.loadImage ("BirdExplosion.png");
									Fight.drawImage (Explosion,25,0);
									Fight.repaint();
									Fight.sleep (1500);
								}
							}
							
							// Turn Negative Player Health To 0
							if (intPlayerHealth < 0){
								intPlayerHealth = 0;
							}
							
							// Record Enemy Death and add 20 health cap per enemy killed
							if (intEnemyHealth == 0){
								intTotalEnemies --;
								intPlayerHealthCap = intPlayerHealthCap + 5;
							}
						}
											
						// Reprint the Health of Enemies and Players When They Die
						Fight.setDrawColor (new Color(255, 234, 128));
						Fight.fillRect (20,310,850,170);
						
						Fight.setDrawColor (Color.BLACK);
						Fight.fillRect (450,310,5,170);
							
						Fight.drawString ("Player Stats:", 125, 300);
						Fight.drawString ("Health: "+intPlayerHealth, 125, 345);
						Fight.drawString ("Attack: "+intPlayerAttack, 125, 385);
						Fight.drawString ("Defence: "+intPlayerDefence, 125, 425);
						
						Fight.drawString (strEnemyName + " Stats:", 550, 300);
						Fight.drawString ("Health: "+intEnemyHealth, 550, 345);
					    Fight.drawString ("Attack: "+intEnemyAttack, 550, 385);
						Fight.drawString ("Defence: "+intEnemyDefence, 550, 425);
						
						Fight.repaint();
						
						// Dying Animations
						if (intEnemyHealth == 0){
							//Enemy
							BufferedImage EnemyFight = Fight.loadImage (strEnemyName+"fight.png");
							for (intCount = 0; intCount < 4; intCount++){
								Fight.drawImage (EnemyFight, 525,25);
								Fight.repaint(); 
								Fight.sleep (250);
								Fight.drawImage (BackgroundFightRight, 450,0);
								Fight.repaint();
								Fight.sleep (250);
							}
						} else if (intPlayerHealth == 0){
							//Player
							BufferedImage PlayerFight = Fight.loadImage ("Beefight.png");

							Fight.drawImage (BackgroundFightLeft, 0,0);
							// Player Bar
							Fight.setDrawColor (Color.BLACK);
							Fight.fillRect (50, 485,350,40);
							
							for (intCount = 0; intCount < 4; intCount++){
								Fight.drawImage (PlayerFight, 75 ,25);
								Fight.repaint();
								Fight.sleep (250);
								Fight.drawImage (BackgroundFightLeft, 0,0);
								Fight.repaint();
								Fight.sleep (250);
							}
						}
						Fight.sleep (1000);
						
						// Close Fight Scene
						Fight.closeWindow();
						blnFight = false;
						
						// Update the Console So The Popup dissapears
						con.clear();
						con.setDrawColor (new Color(255, 234, 128));
						con.fillRect (610,0,300,600);
						con.setDrawColor (Color.BLACK);
						con.fillRect (600,0,10,600);
						con.fillRect (610, 430, 300, 8);
							
						// Draw The Map
						for (intCount = 0; intCount < 20; intCount++){
							for (intCount1 = 0; intCount1 < 20; intCount1++){
								if (strMap[intCount][intCount1].equalsIgnoreCase("Bird")){
									con.drawImage(Bird,intCount1*30,intCount*30);
								} else if (strMap[intCount][intCount1].equalsIgnoreCase("Building")){
									con.drawImage(Building,intCount1*30,intCount*30);
								} else if (strMap[intCount][intCount1].equalsIgnoreCase("Flower")){
									con.drawImage(Flower,intCount1*30,intCount*30);
								} else if (strMap[intCount][intCount1].equalsIgnoreCase("Grass")){
									con.drawImage(Grass,intCount1*30,intCount*30);
								} else if (strMap[intCount][intCount1].equalsIgnoreCase("Honey")){
									con.drawImage(Honey,intCount1*30,intCount*30);
								} else if (strMap[intCount][intCount1].equalsIgnoreCase("Ladybug")){
									con.drawImage(Ladybug,intCount1*30,intCount*30);
								} else if (strMap[intCount][intCount1].equalsIgnoreCase("Tree")){
										con.drawImage(Tree,intCount1*30,intCount*30);
								} else if (strMap[intCount][intCount1].equalsIgnoreCase("Wasp")){
									con.drawImage(Wasp,intCount1*30,intCount*30);
								} else if (strMap[intCount][intCount1].equalsIgnoreCase("Water")){
									con.drawImage(Water,intCount1*30,intCount*30);
								} else if (strMap[intCount][intCount1].equalsIgnoreCase("Wings")){
									con.drawImage(Wings,intCount1*30,intCount*30);
								}
							}
						}
						
						// Draw The Player
						con.drawImage (Player, intPlayerPositionX*30, intPlayerPositionY*30);
							
						// HUD
						// Photo of Bee
						con.drawImage (BeeImage, 630,0);
						
						// Set Font
						con.setDrawFont(TextFont);
						
						// Health, Attack, and Defence Display
						con.drawString (("Health: "+intPlayerHealth), 630,260);
						con.drawString (("Attack: "+intPlayerAttack), 630, 310);
						con.drawString (("Defence: "+intPlayerDefence), 630, 360);
						con.repaint();
							
						// If Player Dies Popup
						if (intPlayerHealth == 0){
							con.setDrawColor (Color.BLACK);
							con.fillRect (165,140,590, 280);
							con.setDrawColor (new Color (153,0,0));
							con.fillRect (195, 170, 530, 220);
							
							con.setDrawFont (TextFont);
							con.setDrawColor (Color.WHITE);
							con.drawString ("YOU DIEDDDDDDDDDDDD!", 210, 175);
							con.drawString ("You suck....", 360, 250);
							con.drawString ("Get better stats next time", 210, 325);
							
							con.repaint();
							con.sleep (5000);
							con.closeConsole();
						}
						
						// Make the Boolean below = true to break out of the loop
						if (intTotalEnemies == 0){
							blnCompleteGame = true;
						}
					}
					// Player Movement
					char chrMovement;
					int intPlayerMovementX = 0;
					int intPlayerMovementY = 0;
					
					chrMovement = con.getChar();
					
					
					// Transfer Their WASD into either +- 1 in PlayerMovement X/Y and Change their Facing
					if (chrMovement == 'w'){
						intPlayerMovementY--;
						strBeeCharacterDirection = "Up";
					} else if (chrMovement == 's'){
						intPlayerMovementY++;
						strBeeCharacterDirection = "Down";
					} else if (chrMovement == 'a'){
						intPlayerMovementX--;
						strBeeCharacterDirection = "Left";
					} else if (chrMovement == 'd'){
						intPlayerMovementX++;
						strBeeCharacterDirection = "Right";
					}
					
					// Add Player Movement to Position
					intPlayerPositionX = intPlayerPositionX + intPlayerMovementX;
					intPlayerPositionY = intPlayerPositionY + intPlayerMovementY;
					
					// Add Object Interactions
					// Check current tile for the type of interaction			
					if (strMap[intPlayerPositionY][intPlayerPositionX].equalsIgnoreCase("Tree")){
						//Tree
						intPlayerPositionX = intPlayerPositionX - intPlayerMovementX;
						intPlayerPositionY = intPlayerPositionY - intPlayerMovementY; 
						intHudCount = 0;
						strHudText = "Not That Way!";
					} else if (strMap[intPlayerPositionY][intPlayerPositionX].equalsIgnoreCase("Flower")){
						// Flower
						strMap[intPlayerPositionY][intPlayerPositionX] = ("Grass");
						intPlayerAttack = intPlayerAttack + 5;
						intHudCount = 0;
						strHudText = "Gained Attack!";
					} else if (strMap[intPlayerPositionY][intPlayerPositionX].equalsIgnoreCase("Wings")){
						// Wings
						strMap[intPlayerPositionY][intPlayerPositionX] = ("Grass");
						intPlayerDefence = intPlayerDefence + 5;
						intHudCount = 0;
						strHudText = "DEFENSE!!!";
					} else if (strMap[intPlayerPositionY][intPlayerPositionX].equalsIgnoreCase("Honey")){
						// Honey (CHANGE TO HALF IF TOO OP)
						strMap[intPlayerPositionY][intPlayerPositionX] = ("Grass");
						intPlayerAttack = intPlayerAttack + 20;
						intPlayerDefence = intPlayerDefence + 20;
						intPlayerHealth = intPlayerHealth + 50;
						intPlayerHealthCap = intPlayerHealthCap + 50;
						intHudCount = 0;
						strHudText = "OP LOOT";
					} else if (strMap[intPlayerPositionY][intPlayerPositionX].equalsIgnoreCase("Building")){
						// Building
						//int intBuildingHeal = 0;
						if (intPlayerMovementX == 0 && intPlayerMovementY == 0){
							// If the player is stationary, he doesn't automatically just heal all up by standing on the building (this was when I used con.currentChar)
							
						} else if (intPlayerHealth <= intPlayerHealthCap - 10){
							// If Player is normally healing
							intPlayerHealth = intPlayerHealth + 10;
							strHudText = ("Heal!!! HEAL!");
						} else if (intPlayerHealth < intPlayerHealthCap){
							// If Player Healed up to Health Cap
							intPlayerHealth = intPlayerHealthCap;
							strHudText = ("HEALTH UP!!!");
						} else if (intPlayerHealth == intPlayerHealthCap){
							// If Player Is At Max Health
							strHudText = ("MAX HEALTH");	
						}	
						
						intHudCount = 0;
					} else if (strMap[intPlayerPositionY][intPlayerPositionX].equalsIgnoreCase("LadyBug")){
						// LadyBug (Stats)
						strEnemyName = "Ladybug";
						blnFight = true;
						intEnemyAttack = 10;
						intEnemyDefence = 10;
						intEnemyHealth = 50;
						strMap[intPlayerPositionY][intPlayerPositionX] = ("Grass");
					} else if (strMap[intPlayerPositionY][intPlayerPositionX].equalsIgnoreCase("Wasp")){
						// Wasp (Stats)
						strEnemyName = "Wasp";
						blnFight = true;
						intEnemyAttack = 20;
						intEnemyDefence = 15;
						intEnemyHealth = 100;
						strMap[intPlayerPositionY][intPlayerPositionX] = ("Grass");
					} else if (strMap[intPlayerPositionY][intPlayerPositionX].equalsIgnoreCase("Bird")){
						// Bird (Stats)
						strEnemyName = "Bird";
						blnFight = true;
						intEnemyAttack = 50;
						intEnemyDefence = 30;
						intEnemyHealth = 200;
						strMap[intPlayerPositionY][intPlayerPositionX] = ("Grass");
					} else if (strMap[intPlayerPositionY][intPlayerPositionX].equalsIgnoreCase("Water")){
						// Water
						blnDrown = true;
					}
					// Set Win Screen	
					if (blnCompleteGame == true){
						con.setDrawColor (new Color(204,34,0));
						con.fillRect (50,50,800, 500);
						con.setDrawColor (new Color (255,215,0));
						con.fillRect (80, 80, 740, 440);
						
						Font TitleFontLarge = con.loadFont ("impact.ttf", 85);
						con.setDrawFont (TitleFontLarge);
						con.setDrawColor (new Color (204,34,0));
						con.drawString ("CONGRATULATIONS!", 120, 105);
						con.setDrawColor (Color.BLACK);
						con.drawString ("YOU ARE THE...", 250, 230);
						con.drawString ("MOST DOMINANT BEE!", 100, 310);
						con.repaint();
						
						char chrAnything = '1';
						Font TitleFontSmaller = con.loadFont ("impact.ttf", 40);
						intCount = 0;

						// Do the blinking animation until they click something
						while (chrAnything != ' '){
							if (intCount <= 60 && intCount >= 30){
								con.setDrawColor(new Color (255,215,0));
								con.fillRect (90,420,500,100);
								if (intCount == 60){
									intCount = 1;
								}
								con.repaint();
							} else {
								con.setDrawFont (TitleFontSmaller);
								con.setDrawColor (new Color (50,50,50));
								con.drawString ("Press space to continue!!", 90, 455);
								con.repaint();
								
							}
							intCount++;
							chrAnything = con.currentChar();
							con.sleep (1000/30);
						}
					}
				}  
				
			}else if (chrPlayerMenuChoice == 'h' || chrPlayerMenuChoice == 'H'){
				// Help Screen
				String strContinue;
				// Background
				con.setBackgroundColor (new Color (204, 230, 255));
				con.setDrawColor (Color.WHITE);
				con.fillRect (0,125,900,10);
				con.clear();
				// Title
				con.setDrawFont (TitleFontSmall);
				con.setDrawColor (Color.BLACK);
				con.drawString ("Help Screen!", 20,-20);
				//Page 1
				Font TextFontSmall = con.loadFont ("Exo2.0.otf",29);
				con.setTextFont (TextFontSmall);
				con.println ();
				con.println ();
				con.println ();
				con.println ();
				con.println ("   Page 1 (Player Movement):");
				con.println ();
				con.println ("   To move up, press 'w'");
				con.println ("   To move down, press 's'");
				con.println ("   To move left, press 'a'");
				con.println ("   To move right, press 'd'");
				con.println ();
				con.println ("                                                                                       You can't walk past trees!");
				con.println (); 
				con.println ("                                  You can walk in water... however, you'll drown!!!");
				con.println ();
				
				// Draw Images
				BufferedImage Player = con.loadImage("BeeCharacterUp.png");
				con.drawImage (Player, 650,250);
				Player = con.loadImage("BeeCharacterLeft.png");
				con.drawImage (Player, 600,300);
				Player = con.loadImage("BeeCharacterDown.png");
				con.drawImage (Player, 650,300);
				Player = con.loadImage("BeeCharacterRight.png");
				con.drawImage (Player, 700,300);
				
				// Trees
				int intHelpX = 470;
				while (intHelpX >=320){
					con.drawImage (Tree, intHelpX, 385);
					con.drawImage (Tree, intHelpX, 415);
					intHelpX = intHelpX-30;
				}
				
				// Water
				intHelpX = 150;
				while (intHelpX >= 30){
					con.drawImage (Water, intHelpX, 457);
					con.drawImage (Water, intHelpX, 487);
					intHelpX = intHelpX-30;
				}
				
				char chrContinue;
				con.print ("   Enter SPACE to continue!");
				chrContinue = con.getChar();
				
				// Page 2
				con.clear();
				con.setDrawColor (new Color (204, 230, 255));
				con.fillRect(0,200,900,600);
				con.println ();
				con.println ();
				con.println ();
				con.println ();
				con.println ("   Page 2 (Stats):");
				con.println ();
				con.println ("   You can pick up various items to increase your health,");
				con.println ("   defence, attack, and healthcap!!!");
				con.println ();
				con.println ("   Go into buildings to increase your health!!!");
				con.println ("   There is a health cap, so don't spam!");
				con.println ();
				con.println ("   Defeat enemies to raise your health cap!!!");
				con.println ();
				con.println ();
				con.print ("   Enter SPACE to continue!");
				
				// Draw Images
				con.drawImage (Wings,550,260);
				con.drawImage (Honey, 650,260);
				con.drawImage (Flower, 750,260);
				con.drawImage (Building, 660, 330);
				con.drawImage (Ladybug, 650, 435);
				con.drawImage (Bird, 740, 435);
				con.drawImage (Wasp, 830, 435);
				chrContinue = con.getChar();
				
				// Page 3
				con.clear();
				con.setDrawColor (new Color (204, 230, 255));
				con.fillRect(0,200,900,600);
				con.println ();
				con.println ();
				con.println ();
				con.println ();
				con.println ("   Page 3 (Battle Help)");
				con.println ();
				con.println ("   There will be a slider bouncing left and right!!!");
				con.println ("   Hit 'SPACE' to stop it!! The closer to the centre, the more");
				con.println ("   damage you do!!! Like this:");
				con.println ("");
				con.println ("");
				con.println ("");
				con.println ("   You have to kill all the enemies in order to win the game!!!");
				con.println ("   Good luck have fun!!!");
				con.println ("");
				con.print ("   Enter SPACE to continue!");
				
				// Slider 
				con.setDrawColor (new Color (204, 230, 255));
				con.fillRect (40,300,900,120);
				con.setDrawColor (Color.BLACK);
				con.fillRect (45,355,810,40);
				con.setDrawColor (Color.RED);
				con.fillRect (50,360,200,30);
				con.fillRect (650,360,200,30);
				con.setDrawColor (Color.YELLOW);
				con.fillRect (250,360,150,30);
				con.fillRect (500,360,150,30);
				con.setDrawColor (Color.GREEN);
				con.fillRect (400,360,100,30);
				
				con.repaint();
				
				// Prevent the last key to hit the current key command in the while loop
				con.sleep (200);
				
				// Set Varaibles
				int intXFight = 50;
				int intAdder = 20;
				boolean blnHelpDone = false;
				chrContinue = '~';
				
				// Slider Bouncing
				while (blnHelpDone == false){
					// Slider Colors
					con.setDrawColor (new Color (204, 230, 255));
					con.fillRect (40,300,900,120);
					con.setDrawColor (Color.BLACK);
					con.fillRect (45,355,810,40);
					con.setDrawColor (Color.RED);
					con.fillRect (50,360,200,30);
					con.fillRect (650,360,200,30);
					con.setDrawColor (Color.YELLOW);
					con.fillRect (250,360,150,30);
					con.fillRect (500,360,150,30);
					con.setDrawColor (Color.GREEN);
					con.fillRect (400,360,100,30);

					// Slider Bouncing Left and Right
					con.setDrawColor (Color.BLACK);
					intXFight = intXFight + intAdder;
					
					if (intXFight >= (850) || intXFight <= 50){
						intAdder = -intAdder;
					}
					con.fillRect (intXFight, 345, 3, 60);
					con.repaint();
					con.sleep (1000/60);
					
					// Continue Key
					chrContinue = con.currentChar();
					
					// Quit when they hit space
					if (chrContinue == ' '){
						blnHelpDone = true;
					}
				}
		
				con.clear();
			}else if (chrPlayerMenuChoice == 'q' || chrPlayerMenuChoice == 'Q'){
				// Quit Console			
				con.closeConsole();
			}
		}			
	}
}

