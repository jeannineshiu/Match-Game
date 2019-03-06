package scenes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends ImageView {
	
	private boolean out = false;
	
	private Image back,front;
	public Card(Image back,Image front) {
		super(back);
		this.back = back;
		this.front = front;
		setFitWidth(64);
        setFitHeight(95);
	}
	public void flipFront() {
		setImage(front);
	}
	public void flipBack() {
		setImage(back);
	}
	public void out() {
		out = true;
	}
	public boolean isOut() {
		return out;
	}
	public void setFront(Image newfront) {
		this.front = newfront;
	}
	public void reset(Image newfront) {
		this.front = newfront;
		out = false;
		flipBack();
		
	}
}
