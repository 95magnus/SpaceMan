package me.solhaug.input;

import me.solhaug.entities.Mob;
import me.solhaug.entities.Player;
import me.solhaug.level.Level;

public class Controller extends Input {

	private Player player;
	private Input input;

	public Controller(Input input, Level level) {
		this.input = input;
		player = level.player;
	}

	public void update() {
		if (input.left) {
			// player.accelerate(0, -1.2f);
			player.move(-player.walkingSpeed, 0);
			player.setState(Mob.State.MOVING_LEFT);
		} else if (input.right) {
			// player.accelerate(0, 1.2f);
			player.move(player.walkingSpeed, 0);
			player.setState(Mob.State.MOVING_RIGHT);
		} else {
			player.setState(Mob.State.IDLE);
		}
		if (input.up) {
			// player.accelerate(0, -0.2f);
			player.move(0, -player.playerSpeed);
			// player.stopMoving();
		} else if (input.down) {
			// player.accelerate(0, 0.2f);
			player.move(0, player.playerSpeed);
			// player.stopMoving();
		} 
	}
}
