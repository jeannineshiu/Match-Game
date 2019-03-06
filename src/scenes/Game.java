package scenes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Game extends Scene{
	Button back,restart;
	Main m;
	GridPane gp;
	AnchorPane ap;
	Label target,hit;
	
	Image[] ims = {
			new Image(Game.class.getResourceAsStream("KemBack_.jpg")),
			new Image(Game.class.getResourceAsStream("01.jpg")),
			new Image(Game.class.getResourceAsStream("02.jpg")),
			new Image(Game.class.getResourceAsStream("03.jpg")),
			new Image(Game.class.getResourceAsStream("04.jpg")),
			new Image(Game.class.getResourceAsStream("05.jpg"))
	};
	Card previous_card = null;
	Card toflip0 =null,toflip1 = null;
	int target_n = 0,hit_n = 0;
	VBox finishedBox;
	List<Image> imList;
    List<Card> cardList = new ArrayList<>();
	public Game(Parent root,Main m) {
		super(root);
		ap = (AnchorPane) root;
		this.m = m;
		back = (Button) lookup("#back");
		back.setOnMouseClicked(event->{
			m.toMenu();
			reset();
		});
		restart = (Button) lookup("#restart");
		restart.setOnMouseClicked(event->reset());
		gp = (GridPane) lookup("#grid");
		target = (Label) lookup("#target");
		hit =  (Label) lookup("#hit");
		finishedBox = (VBox) lookup("#finished");
		finishedBox.setVisible(false);
		reset();
		gp.setPadding(new Insets(30, 0, 0, 0));
		gp.setHgap(5); gp.setVgap(5);
		for (int i = 0;i<5;i++)
			for(int j = 0;j<4;j++)
				gp.add(newCard(imList.get(i*4+j)), i, j);
	}
	private void updateTarget(int amount) {
		target_n+=amount;
		target.setText(""+target_n+"/10");
		if(target_n >= 10) {
			finishedBox.setVisible(true);
			((Label) lookup("#finished_text")).setText("You Hit:"+hit_n+"!");
		}
	}
	private void updateHit(int amount) {
		if(amount<0) {
			hit_n = 0;
			hit.setText(""+hit_n);
			return;
		}
		hit_n+=amount;
		hit.setText(""+hit_n);
	}
	private void flipBack() {
		if(toflip0!=null&&toflip1!=null) {
			toflip0.flipBack();
			toflip1.flipBack();
			System.out.println("\nDid flipBlack.");
		}
		toflip0 = null;
		toflip1 = null;
	}
	private void reset() {
		if(imList==null) {
			imList= new ArrayList<>();
	        for (int i = 1;i<6;i++) {
	        	imList.add(ims[i]);
	        	imList.add(ims[i]);
	        	imList.add(ims[i]);
	        	imList.add(ims[i]);
	        }
		}
		target_n = 0;
		hit_n = 0;
		toflip0 = null;
		toflip1 = null;
		previous_card = null;
		updateHit(-1);
		updateTarget(0);
		finishedBox.setVisible(false);
        Collections.shuffle(imList);
		for (int i = 0;i<cardList.size();i++){
			cardList.get(i).reset(imList.get(i));
		}
	}
	private ImageView newCard(Image front) {
		Card card = new Card(ims[0],front);
		GridPane.setHalignment(card, HPos.CENTER);
		cardList.add(card);
		card.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					Card c = (Card)event.getTarget();
					flipBack();
					System.out.println("\nClicked.");
					if(previous_card!=c&&!c.isOut()) {
						updateHit(1);
						System.out.println("Hitted.");
						card.flipFront();
						if (previous_card == null) {
							System.out.println("Previous_card null.");
							previous_card = c;
						}
						else if (previous_card.getImage() == c.getImage()){
							System.out.println("Same im.");
							updateTarget(1);
							c.out();
							previous_card.out();
							previous_card= null;
						}else {
							System.out.println("Different im.");
							toflip0 = previous_card;
							toflip1 = c;
							previous_card=null;
						}
					}
					
				}
			});
	        return card;
	}
}
