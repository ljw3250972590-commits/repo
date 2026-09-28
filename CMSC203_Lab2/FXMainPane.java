


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This panel is the basic panel, inside which other panels are placed.  
 * Before beginning to implement, design the structure of your GUI in order to 
 * understand what panels go inside which ones, and what buttons or other components
 * go in which panels.  
 * @author ralexander
 *
 */
//make the main panel's layout be a VBox
public class FXMainPane extends VBox {

	//student Task #2:
	//  declare five buttons, a label, and a textfield
	//  declare two HBoxes
	
	//student Task #4:
	//  declare an instance of DataManager
	/**
	 * The MainPanel constructor sets up the entire GUI in this approach.  Remember to
	 * wait to add a component to its containing component until the container has
	 * been created.  This is the only constraint on the order in which the following 
	 * statements appear.
	 */
	private Button helloButton;
	private Button howdyButton;
	private Button chineseButton;
	private Button clearButton;
	private Button exitButton;

	private Label feedbackLabel;
	private TextField feedbackTextField;

	private HBox buttonBox;
	private HBox feedbackBox;
	private DataManager dataManager;
	
	FXMainPane() {
		//student Task #2:
		//  instantiate the buttons, label, and textfield
		//  instantiate the HBoxes
		helloButton = new Button("Hello");
		howdyButton = new Button("Howdy");
		chineseButton = new Button("Chinese");
		clearButton = new Button("Clear");
		exitButton = new Button("Exit");

		feedbackLabel = new Label("Feedback:");
		feedbackTextField = new TextField();

		buttonBox = new HBox();
		feedbackBox = new HBox();
		
		dataManager = new DataManager();
		//student Task #4:
		//  instantiate the DataManager instance
		//  set margins and set alignment of the components
		
		//student Task #3:
		//  add the label and textfield to one of the HBoxes
		//  add the buttons to the other HBox
		//  add the HBoxes to this FXMainPanel (a VBox)
		feedbackBox.getChildren().addAll(feedbackLabel, feedbackTextField);

		buttonBox.getChildren().addAll(
		        helloButton,
		        howdyButton,
		        chineseButton,
		        clearButton,
		        exitButton
		);

		getChildren().addAll(feedbackBox, buttonBox);
		
		helloButton.setOnAction(new ButtonHandler());
		howdyButton.setOnAction(new ButtonHandler());
		chineseButton.setOnAction(new ButtonHandler());
		clearButton.setOnAction(new ButtonHandler());
		exitButton.setOnAction(new ButtonHandler());
		Insets inset = new Insets(10);
		HBox.setMargin(helloButton, inset);
		HBox.setMargin(howdyButton, inset);
		HBox.setMargin(chineseButton, inset);
		HBox.setMargin(clearButton, inset);
		HBox.setMargin(exitButton, inset);

		HBox.setMargin(feedbackLabel, inset);
		HBox.setMargin(feedbackTextField, inset);
		
		buttonBox.setAlignment(Pos.CENTER);
		feedbackBox.setAlignment(Pos.CENTER);
	}
	
	//Task #4:
	private class ButtonHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
		    Object target = event.getTarget();

		    if (target == helloButton)
		    {
		        feedbackTextField.setText(dataManager.getHello());
		    }
		    else if (target == howdyButton)
		    {
		        feedbackTextField.setText(dataManager.getHowdy());
		    }
		    else if (target == chineseButton)
		    {
		        feedbackTextField.setText(dataManager.getChinese());
		    }
		    else if (target == clearButton)
		    {
		        feedbackTextField.setText("");
		    }
		    else if (target == exitButton)
		    {
		        Platform.exit();
		        System.exit(0);
		    }
		}
	}
	//  create a private inner class to handle the button clicks
}
	
