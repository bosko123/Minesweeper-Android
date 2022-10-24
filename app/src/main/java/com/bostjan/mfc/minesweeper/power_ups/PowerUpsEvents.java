package com.bostjan.mfc.minesweeper.power_ups;

import com.bostjan.mfc.minesweeper.R;
import com.bostjan.mfc.minesweeper.game.GameWindow;
import com.bostjan.mfc.minesweeper.power_up_objects.MinusTime;
import com.bostjan.mfc.minesweeper.power_up_objects.PlusTime;

public class PowerUpsEvents {
	
	public static PlusTime plusTime = new PlusTime(30);
	public static MinusTime minusTime = new MinusTime(30);
	
	public static final int DRILL_USES = 7;
	public static final int XRAY_TIME = 3;
	
	public static Drill drill = new Drill(DRILL_USES);
	public static XRay xRay = new XRay(XRAY_TIME);
	
	public static int xNukes = 0;

	public static int powerupImage = 0;

	public static void getPowerUp() {
		
		//generate number to select random powerup
		int x = RandomPowerUp.randomPowerUp();
		//int x = 5;
		
		switch (x) {
		case 0:
			RandomPowerUp.minusTime();

			powerupImage = R.drawable.minus_time;
			/*try {
				Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/powerups/minusTime.png"));
				GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
			break;
			
		case 1:
			GameWindow.timeInSec += plusTime.getTimeInSec();
			GameWindow.secunds += plusTime.getTimeInSec();

			powerupImage = R.drawable.plus_time;
			/*try {
				Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/powerups/plusTime.png"));
				GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
			break;
			
		case 3:
			RandomPowerUp.placeNuke();

			powerupImage = R.drawable.tile_mine_nuke;
			/*try {
				Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/tiles/tile_mine_nuke.png"));
				GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
				PowerUpsEvents.xNukes++;
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
			break;
			
		/*case 5:
			RandomPowerUp.setNoXray();

			powerupImage = R.drawable.no_xray_icon;
			try {
				Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/powerups/no_xray_icon.png"));
				GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;*/
			
		default:
			//int randomPowerUp = (int)(Math.random()*2+0);
			int randomPowerUp = 0;
			GameWindow.allPowerUps[randomPowerUp]++;
			
			switch (randomPowerUp) {
			case 0:
				GameWindow.lblDrillsCount.setText(GameWindow.allPowerUps[0] + "");

				powerupImage = R.drawable.drill_icon;
				/*try {
					Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/powerups/drill_icon.png"));
					GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;*/
				
			/*case 1:
				GameWindow.lblXRaysCount.setText(GameWindow.allPowerUps[1] + "");

				powerupImage = R.drawable.xray_icon;
				try {
					Image image = ImageIO.read(GameWindow.class.getResource("/assets/textures/powerups/xray_icon.png"));
					GameWindow.lblPowerup.setIcon(new ImageIcon(Responsive.resizeImage(image, 150, 150)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;*/
				
			default:
				break;
			}
			
			break;
		
		}
		
		System.out.println(x);
		
	}
	
}
