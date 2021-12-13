package src.gui.game;

import javafx.scene.image.Image;
import src.graphics.Sprite;

public class GetImage {
        // player
        public Image player_up1, player_up2, player_down1, player_down2, player_left1, player_left2, player_right1,
                        player_right2, player_dead1, player_dead2, player_dead3, player_stand_up, player_stand_down,
                        player_stand_left, player_stand_right;

        // bolloom
        public Image balloom_up1, balloom_up2, balloom_down1, balloom_down2, balloom_left1, balloom_left2,
                        balloom_right1,
                        balloom_right2, balloom_dead1, balloom_dead2, balloom_dead3;

        // oneal
        public Image oneal_up1, oneal_up2, oneal_down1, oneal_down2, oneal_left1, oneal_left2, oneal_right1,
                        oneal_right2,
                        oneal_dead1, oneal_dead2, oneal_dead3;

        // tiles
        public Image grass, wall, wall1;
        public Image brick, brick_exploded, brick_exploded1, brick_exploded2;

        // objects
        public Image portal, powerup_bombs, powerup_flames, powerup_speed, powerup_wallpass, powerup_detonator,
                        powerup_bombpass,
                        powerup_flamepass;

        // bomb
        public Image bomb, bomb1, bomb2;
        // bomb explode
        public Image bomb_exploded, bomb_exploded1, bomb_exploded2;
        // flame
        public Image flame_mid_ver, flame_mid_ver1, flame_mid_ver2;
        public Image flame_mid_hor, flame_mid_hor1, flame_mid_hor2;
        public Image flame_left_hor, flame_left_hor1, flame_left_hor2;
        public Image flame_right_hor, flame_right_hor1, flame_right_hor2;
        public Image flame_top_ver, flame_top_ver1, flame_top_ver2;
        public Image flame_bot_ver, flame_bot_ver1, flame_bot_ver2;

        public GetImage() {
                getImageFromSprite();
        }

        public void getImageFromSprite() {

                // player
                player_up1 = Sprite.player_up_1.getFxImage();
                player_up2 = Sprite.player_up_2.getFxImage();
                player_down1 = Sprite.player_down_1.getFxImage();
                player_down2 = Sprite.player_down_2.getFxImage();
                player_left1 = Sprite.player_left_1.getFxImage();
                player_left2 = Sprite.player_left_2.getFxImage();
                player_right1 = Sprite.player_right_1.getFxImage();
                player_right2 = Sprite.player_right_2.getFxImage();
                player_dead1 = Sprite.player_dead1.getFxImage();
                player_dead2 = Sprite.player_dead2.getFxImage();
                player_dead3 = Sprite.player_dead3.getFxImage();
                player_stand_up = Sprite.player_up.getFxImage();
                player_stand_down = Sprite.player_down.getFxImage();
                player_stand_left = Sprite.player_left.getFxImage();
                player_stand_right = Sprite.player_right.getFxImage();

                // balloom
                balloom_up1 = Sprite.balloom_left1.getFxImage();
                balloom_up2 = Sprite.balloom_right1.getFxImage();
                balloom_down1 = Sprite.balloom_right1.getFxImage();
                balloom_down2 = Sprite.balloom_left1.getFxImage();
                balloom_left1 = Sprite.balloom_left2.getFxImage();
                balloom_left2 = Sprite.balloom_left3.getFxImage();
                balloom_right1 = Sprite.balloom_right2.getFxImage();
                balloom_right2 = Sprite.balloom_right3.getFxImage();
                balloom_dead1 = Sprite.balloom_dead.getFxImage();
                balloom_dead2 = Sprite.mob_dead1.getFxImage();
                balloom_dead3 = Sprite.mob_dead3.getFxImage();

                // oneal
                oneal_up1 = Sprite.oneal_left1.getFxImage();
                oneal_up2 = Sprite.oneal_right1.getFxImage();
                oneal_down1 = Sprite.oneal_right1.getFxImage();
                oneal_down2 = Sprite.oneal_left1.getFxImage();
                oneal_left1 = Sprite.oneal_left2.getFxImage();
                oneal_left2 = Sprite.oneal_left3.getFxImage();
                oneal_right1 = Sprite.oneal_right2.getFxImage();
                oneal_right2 = Sprite.oneal_right3.getFxImage();
                oneal_dead1 = Sprite.oneal_dead.getFxImage();
                oneal_dead2 = Sprite.mob_dead1.getFxImage();
                oneal_dead3 = Sprite.mob_dead3.getFxImage();

                // tiles
                grass = Sprite.grass.getFxImage();
                wall = Sprite.wall.getFxImage();
                wall1 = Sprite.wall1.getFxImage();
                brick = Sprite.brick.getFxImage();
                brick_exploded = Sprite.brick_exploded.getFxImage();
                brick_exploded1 = Sprite.brick_exploded1.getFxImage();
                brick_exploded2 = Sprite.brick_exploded2.getFxImage();

                // objects
                portal = Sprite.portal.getFxImage();
                powerup_bombs = Sprite.powerup_bombs.getFxImage();
                powerup_speed = Sprite.powerup_speed.getFxImage();
                powerup_flames = Sprite.powerup_flames.getFxImage();

                // bomb
                bomb = Sprite.bomb.getFxImage();
                bomb1 = Sprite.bomb_1.getFxImage();
                bomb2 = Sprite.bomb_2.getFxImage();
                // bomb explode
                bomb_exploded = Sprite.bomb_exploded.getFxImage();
                bomb_exploded1 = Sprite.bomb_exploded1.getFxImage();
                bomb_exploded2 = Sprite.bomb_exploded2.getFxImage();

                // flame
                flame_mid_ver = Sprite.explosion_vertical.getFxImage();
                flame_mid_ver1 = Sprite.explosion_vertical1.getFxImage();
                flame_mid_ver2 = Sprite.explosion_vertical2.getFxImage();
                flame_mid_hor = Sprite.explosion_horizontal.getFxImage();
                flame_mid_hor1 = Sprite.explosion_horizontal1.getFxImage();
                flame_mid_hor2 = Sprite.explosion_horizontal2.getFxImage();
                flame_left_hor = Sprite.explosion_horizontal_left_last.getFxImage();
                flame_left_hor1 = Sprite.explosion_horizontal_left_last1.getFxImage();
                flame_left_hor2 = Sprite.explosion_horizontal_left_last2.getFxImage();
                flame_right_hor = Sprite.explosion_horizontal_right_last.getFxImage();
                flame_right_hor1 = Sprite.explosion_horizontal_right_last1.getFxImage();
                flame_right_hor2 = Sprite.explosion_horizontal_right_last2.getFxImage();
                flame_top_ver = Sprite.explosion_vertical_top_last.getFxImage();
                flame_top_ver1 = Sprite.explosion_vertical_top_last1.getFxImage();
                flame_top_ver2 = Sprite.explosion_vertical_top_last2.getFxImage();
                flame_bot_ver = Sprite.explosion_vertical_down_last.getFxImage();
                flame_bot_ver1 = Sprite.explosion_vertical_down_last1.getFxImage();
                flame_bot_ver2 = Sprite.explosion_vertical_down_last2.getFxImage();
        }
}
