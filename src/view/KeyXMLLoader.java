package view;

import java.io.Serializable;

import javafx.scene.input.KeyCode;

public class KeyXMLLoader implements Serializable {
	private KeyCode up;
	private KeyCode down;
	private KeyCode right;
	private KeyCode left;

	public KeyXMLLoader(){
		this.up=KeyCode.UP;
		this.down=KeyCode.DOWN;
		this.right=KeyCode.LEFT;
		this.left=KeyCode.RIGHT;

	}

	public KeyCode getUp() {
		return up;
	}

	public KeyCode getDown() {
		return down;
	}

	public KeyCode getRight() {
		return right;
	}

	public KeyCode getLeft() {
		return left;
	}

	public void setUp(KeyCode up) {
		this.up = up;
	}

	public void setDown(KeyCode down) {
		this.down = down;
	}

	public void setRight(KeyCode right) {
		this.right = right;
	}

	public void setLeft(KeyCode left) {
		this.left = left;
	}


}
