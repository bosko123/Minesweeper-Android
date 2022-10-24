package com.bostjan.mfc.minesweeper.assets_settings;

import android.content.Context;

import com.bostjan.mfc.minesweeper.R;
import com.bostjan.mfc.minesweeper.game.GameWindow;

public class ImageRegister {
	
	public static int[] images = new int[12];
	public static int[] imagesXRay = new int[2];
	public static int imageNuke = -1;
	public static int[] imagesCrates = new int[9];
	public static int[] imagesBroken = new int[10];
	public static int[] flagImages = new int[3];
	//public static String assets = "/assets/textures/tiles/";
	public static int tile_no_xray = -1;

	public static int[] crate = new int[60];

	public static GameWindow gameWindow = new GameWindow();
	
	public static void imageLoader() {
		
		try {
			
			//normal icons
			int tile_closed = R.drawable.tile_closed;
			int flag_0 = R.drawable.flag_0;
			int flag_1 = R.drawable.flag_1;

			int tile_open = R.drawable.tile_open;
			int tile_1 = R.drawable.tile_1;
			int tile_2 = R.drawable.tile_2;
			int tile_3 = R.drawable.tile_3;
			int tile_4 = R.drawable.tile_4;
			int tile_5 = R.drawable.tile_5;
			int tile_6 = R.drawable.tile_6;
			int tile_7 = R.drawable.tile_7;
			int tile_8 = R.drawable.tile_8;
			int tile_mine_on = R.drawable.tile_mine_on;
			int tile_mine_off = R.drawable.tile_mine_off;
			int tile_mine_flag = R.drawable.tile_mine_flag;
			
			images[0] = tile_open;
			images[1] = tile_1;
			images[2] = tile_2;
			images[3] = tile_3;
			images[4] = tile_4;
			images[5] = tile_5;
			images[6] = tile_6;
			images[7] = tile_7;
			images[8] = tile_8;
			images[9] = tile_mine_on;
			images[10] = tile_mine_off;
			images[11] = tile_mine_flag;
			
			flagImages[0] = tile_closed;
			flagImages[1] = flag_0;
			flagImages[2] = flag_1;

            int crate0 = R.drawable.crate_1;
            int crate1 = R.drawable.crate_2;
            int crate2 = R.drawable.crate_3;
            int crate3 = R.drawable.crate_4;
            int crate4 = R.drawable.crate_5;
            int crate5 = R.drawable.crate_6;
            int crate6 = R.drawable.crate_7;
            int crate7 = R.drawable.crate_8;
            int crate8 = R.drawable.crate_9;
            int crate9 = R.drawable.crate_10;
            int crate10 = R.drawable.crate_11;
            int crate11 = R.drawable.crate_12;
            int crate12 = R.drawable.crate_13;
            int crate13 = R.drawable.crate_14;
            int crate14 = R.drawable.crate_15;
            int crate15 = R.drawable.crate_16;
            int crate16 = R.drawable.crate_17;
            int crate17 = R.drawable.crate_18;
            int crate18 = R.drawable.crate_19;
            int crate19 = R.drawable.crate_20;
            int crate20 = R.drawable.crate_21;
            int crate21 = R.drawable.crate_22;
            int crate22 = R.drawable.crate_23;
            int crate23 = R.drawable.crate_24;
            int crate24 = R.drawable.crate_25;
            int crate25 = R.drawable.crate_26;
            int crate26 = R.drawable.crate_27;
            int crate27 = R.drawable.crate_28;
            int crate28 = R.drawable.crate_29;
            int crate29 = R.drawable.crate_30;
            int crate30 = R.drawable.crate_31;
            int crate31 = R.drawable.crate_32;
            int crate32 = R.drawable.crate_33;
            int crate33 = R.drawable.crate_34;
            int crate34 = R.drawable.crate_35;
            int crate35 = R.drawable.crate_36;
            int crate36 = R.drawable.crate_37;
            int crate37 = R.drawable.crate_38;
            int crate38 = R.drawable.crate_39;
            int crate39 = R.drawable.crate_40;
            int crate40 = R.drawable.crate_41;
            int crate41 = R.drawable.crate_42;
            int crate42 = R.drawable.crate_43;
            int crate43 = R.drawable.crate_44;
            int crate44 = R.drawable.crate_45;
            int crate45 = R.drawable.crate_46;
            int crate46 = R.drawable.crate_47;
            int crate47 = R.drawable.crate_48;
            int crate48 = R.drawable.crate_49;
            int crate49 = R.drawable.crate_50;
            int crate50 = R.drawable.crate_51;
            int crate51 = R.drawable.crate_52;
            int crate52 = R.drawable.crate_53;
            int crate53 = R.drawable.crate_54;
            int crate54 = R.drawable.crate_55;
            int crate55 = R.drawable.crate_56;
            int crate56 = R.drawable.crate_57;
            int crate57 = R.drawable.crate_58;
            int crate58 = R.drawable.crate_59;
            int crate59 = R.drawable.crate_60;

            crate[0] = crate0;
            crate[1] = crate1;
            crate[2] = crate2;
            crate[3] = crate3;
            crate[4] = crate4;
            crate[5] = crate5;
            crate[6] = crate6;
            crate[7] = crate7;
            crate[8] = crate8;
            crate[9] = crate9;
            crate[10] = crate10;
            crate[11] = crate11;
            crate[12] = crate12;
            crate[13] = crate13;
            crate[14] = crate14;
            crate[15] = crate15;
            crate[16] = crate16;
            crate[17] = crate17;
            crate[18] = crate18;
            crate[19] = crate19;
            crate[20] = crate20;
            crate[21] = crate21;
            crate[22] = crate22;
            crate[23] = crate23;
            crate[24] = crate24;
            crate[25] = crate25;
            crate[26] = crate26;
            crate[27] = crate27;
            crate[28] = crate28;
            crate[29] = crate29;
            crate[30] = crate30;
            crate[31] = crate31;
            crate[32] = crate32;
            crate[33] = crate33;
            crate[34] = crate34;
            crate[35] = crate35;
            crate[36] = crate36;
            crate[37] = crate37;
            crate[38] = crate38;
            crate[39] = crate39;
            crate[40] = crate40;
            crate[41] = crate41;
            crate[42] = crate42;
            crate[43] = crate43;
            crate[44] = crate44;
            crate[45] = crate45;
            crate[46] = crate46;
            crate[47] = crate47;
            crate[48] = crate48;
            crate[49] = crate49;
            crate[50] = crate50;
            crate[51] = crate51;
            crate[52] = crate52;
            crate[53] = crate53;
            crate[54] = crate54;
            crate[55] = crate55;
            crate[56] = crate56;
            crate[57] = crate57;
            crate[58] = crate58;
            crate[59] = crate59;

		} catch (Exception e) {
			
		}
		
	}
	
	public static void imageCrateLoader() {
		
		try {
			
			//crazy mode icons
			imageNuke = R.drawable.tile_mine_nuke;
			
			int tile_0_crate = R.drawable.tile_0_crate;
			int tile_1_crate = R.drawable.tile_1_crate;
			int tile_2_crate = R.drawable.tile_2_crate;
			int tile_3_crate = R.drawable.tile_3_crate;
			int tile_4_crate = R.drawable.tile_4_crate;
			int tile_5_crate = R.drawable.tile_5_crate;
			int tile_6_crate = R.drawable.tile_6_crate;
			int tile_7_crate = R.drawable.tile_7_crate;
			int tile_8_crate = R.drawable.tile_8_crate;
			
			imagesCrates[0] = tile_0_crate;
			imagesCrates[1] = tile_1_crate;
			imagesCrates[2] = tile_2_crate;
			imagesCrates[3] = tile_3_crate;
			imagesCrates[4] = tile_4_crate;
			imagesCrates[5] = tile_5_crate;
			imagesCrates[6] = tile_6_crate;
			imagesCrates[7] = tile_7_crate;
			imagesCrates[8] = tile_8_crate;
			
			int tile_0_cracked = R.drawable.tile_0_cracked;
			int tile_1_cracked = R.drawable.tile_1_cracked;
			int tile_2_cracked = R.drawable.tile_2_cracked;
			int tile_3_cracked = R.drawable.tile_3_cracked;
			int tile_4_cracked = R.drawable.tile_4_cracked;
			int tile_5_cracked = R.drawable.tile_5_cracked;
			int tile_6_cracked = R.drawable.tile_6_cracked;
			int tile_7_cracked = R.drawable.tile_7_cracked;
			int tile_8_cracked = R.drawable.tile_8_cracked;
			int tile_mine_cracked = R.drawable.tile_mine_cracked;
			
			imagesBroken[0] = tile_0_cracked;
			imagesBroken[1] = tile_1_cracked;
			imagesBroken[2] = tile_2_cracked;
			imagesBroken[3] = tile_3_cracked;
			imagesBroken[4] = tile_4_cracked;
			imagesBroken[5] = tile_5_cracked;
			imagesBroken[6] = tile_6_cracked;
			imagesBroken[7] = tile_7_cracked;
			imagesBroken[8] = tile_8_cracked;
			imagesBroken[9] = tile_mine_cracked;
			
			int tile_0_xray = R.drawable.tile_0_xray;
			int tile_9_xray = R.drawable.tile_9_xray;
			tile_no_xray = R.drawable.tile_no_xray;
			
			imagesXRay[0] = tile_0_xray;
			imagesXRay[1] = tile_9_xray;
			imagesXRay[2] = tile_no_xray;

			int crate0 = R.drawable.crate_1;
            int crate1 = R.drawable.crate_2;
            int crate2 = R.drawable.crate_3;
            int crate3 = R.drawable.crate_4;
            int crate4 = R.drawable.crate_5;
            int crate5 = R.drawable.crate_6;
            int crate6 = R.drawable.crate_7;
            int crate7 = R.drawable.crate_8;
            int crate8 = R.drawable.crate_9;
            int crate9 = R.drawable.crate_10;
            int crate10 = R.drawable.crate_11;
            int crate11 = R.drawable.crate_12;
            int crate12 = R.drawable.crate_13;
            int crate13 = R.drawable.crate_14;
            int crate14 = R.drawable.crate_15;
            int crate15 = R.drawable.crate_16;
            int crate16 = R.drawable.crate_17;
            int crate17 = R.drawable.crate_18;
            int crate18 = R.drawable.crate_19;
            int crate19 = R.drawable.crate_20;
            int crate20 = R.drawable.crate_21;
            int crate21 = R.drawable.crate_22;
            int crate22 = R.drawable.crate_23;
            int crate23 = R.drawable.crate_24;
            int crate24 = R.drawable.crate_25;
            int crate25 = R.drawable.crate_26;
            int crate26 = R.drawable.crate_27;
            int crate27 = R.drawable.crate_28;
            int crate28 = R.drawable.crate_29;
            int crate29 = R.drawable.crate_30;
            int crate30 = R.drawable.crate_31;
            int crate31 = R.drawable.crate_32;
            int crate32 = R.drawable.crate_33;
            int crate33 = R.drawable.crate_34;
            int crate34 = R.drawable.crate_35;
            int crate35 = R.drawable.crate_36;
            int crate36 = R.drawable.crate_37;
            int crate37 = R.drawable.crate_38;
            int crate38 = R.drawable.crate_39;
            int crate39 = R.drawable.crate_40;
            int crate40 = R.drawable.crate_41;
            int crate41 = R.drawable.crate_42;
            int crate42 = R.drawable.crate_43;
            int crate43 = R.drawable.crate_44;
            int crate44 = R.drawable.crate_45;
            int crate45 = R.drawable.crate_46;
            int crate46 = R.drawable.crate_47;
            int crate47 = R.drawable.crate_48;
            int crate48 = R.drawable.crate_49;
            int crate49 = R.drawable.crate_50;
            int crate50 = R.drawable.crate_51;
            int crate51 = R.drawable.crate_52;
            int crate52 = R.drawable.crate_53;
            int crate53 = R.drawable.crate_54;
            int crate54 = R.drawable.crate_55;
            int crate55 = R.drawable.crate_56;
            int crate56 = R.drawable.crate_57;
            int crate57 = R.drawable.crate_58;
            int crate58 = R.drawable.crate_59;
            int crate59 = R.drawable.crate_60;

            crate[0] = crate0;
            crate[1] = crate1;
            crate[2] = crate2;
            crate[3] = crate3;
            crate[4] = crate4;
            crate[5] = crate5;
            crate[6] = crate6;
            crate[7] = crate7;
            crate[8] = crate8;
            crate[9] = crate9;
            crate[10] = crate10;
            crate[11] = crate11;
            crate[12] = crate12;
            crate[13] = crate13;
            crate[14] = crate14;
            crate[15] = crate15;
            crate[16] = crate16;
            crate[17] = crate17;
            crate[18] = crate18;
            crate[19] = crate19;
            crate[20] = crate20;
            crate[21] = crate21;
            crate[22] = crate22;
            crate[23] = crate23;
            crate[24] = crate24;
            crate[25] = crate25;
            crate[26] = crate26;
            crate[27] = crate27;
            crate[28] = crate28;
            crate[29] = crate29;
            crate[30] = crate30;
            crate[31] = crate31;
            crate[32] = crate32;
            crate[33] = crate33;
            crate[34] = crate34;
            crate[35] = crate35;
            crate[36] = crate36;
            crate[37] = crate37;
            crate[38] = crate38;
            crate[39] = crate39;
            crate[40] = crate40;
            crate[41] = crate41;
            crate[42] = crate42;
            crate[43] = crate43;
            crate[44] = crate44;
            crate[45] = crate45;
            crate[46] = crate46;
            crate[47] = crate47;
            crate[48] = crate48;
            crate[49] = crate49;
            crate[50] = crate50;
            crate[51] = crate51;
            crate[52] = crate52;
            crate[53] = crate53;
            crate[54] = crate54;
            crate[55] = crate55;
            crate[56] = crate56;
            crate[57] = crate57;
            crate[58] = crate58;
            crate[59] = crate59;
			
		} catch (Exception e) {
			
		}
		
	}
	
}
