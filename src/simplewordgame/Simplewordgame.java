/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplewordgame;

import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author MahiRaj Gosemath
 */
public class Simplewordgame extends Application {
    
    GridPane welcome=new GridPane();
    Text instructions=new Text();
    
    GridPane end=new GridPane();
    Label finalScore=new Label("Your Final Score");
    
    Random r=new Random();
    VBox vbox=new VBox();
    DropShadow shadow=new DropShadow();
    ImageView imageWrong;
    ImageView imageRight;
    Text text1;
    int rand, correct=0, incorrect=0, unanswered=0, total=0;
    int timeNow, timeElapsed;
    Label first =new Label("Correct");
    Label second=new Label("Wrong");
    Label third=new Label("Skipped");
    Label fourth=new Label("Total");
    Label right=new Label(Integer.toString(correct));
    Label wrong=new Label(Integer.toString(incorrect));
    Label unansweredTakes=new Label(Integer.toString(unanswered));
    Label totalScore=new Label(Integer.toString(total));
    KeyCode codes[]={ KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D, KeyCode.E,
                      KeyCode.F, KeyCode.G, KeyCode.H, KeyCode.I, KeyCode.J,
                      KeyCode.K, KeyCode.L, KeyCode.M, KeyCode.N, KeyCode.O,
                      KeyCode.P, KeyCode.Q, KeyCode.R, KeyCode.S, KeyCode.T,
                      KeyCode.U, KeyCode.V, KeyCode.W, KeyCode.X, KeyCode.Y,
                      KeyCode.Z };
    
    @Override
    public void start(Stage primaryStage) {
        
        vbox.setVisible(false);
        
        //taking system time for creating counter
        final Timeline time=new Timeline();     
        time.setCycleCount(Timeline.INDEFINITE);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(47), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    checkTime();
                }
            });
            time.getKeyFrames().add(keyFrame);
        
        String insts="1. The game is meant for testing your typing skills.\n\n"+
                "2. You have to press the key that is appearing on the screen.\n\n"+
                "3. Pressing correct screen will increase your accuracy.\n\n"+
                "4. Every appeared character will last for 3 secs on the screen \n"+
                    " \tif you fail to type the key the character will be skipped.\n\n"+
                "5. Pressing wrong character will decrease your accuracy\n\n"+
                "Good Luck!";
        
        instructions.setText(insts);
        instructions.setFont(new Font(14));
        //instructions.setWrappingWidth(200);
        
        Button enter=new Button("Enter");
        enter.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ae)
            {
                welcome.setVisible(false);
                vbox.setVisible(true);
                time.play();
                timeElapsed=(int)System.currentTimeMillis();
            }
        });
        
        welcome.addRow(1, instructions);
        welcome.addRow(2, enter);
        welcome.setAlignment(Pos.CENTER);
        welcome.setVgap(5);
        welcome.setVisible(true);
        
        final Label accuracy=new Label();
        final Label finalRight=new Label();
        final Label finalWrong=new Label();
        final Label finalSkipped=new Label();
        final Label finalTotal=new Label();
        finalScore.setFont(Font.font("Times New Roman", 30));
        finalScore.setTextFill(Color.web("#464646"));
        end.addRow(0, finalScore);
        end.addRow(1, accuracy);
        end.addRow(2, finalRight);
        end.addRow(3, finalWrong);
        end.addRow(4, finalSkipped);
        end.addRow(5, finalTotal);
        end.setVisible(false);
        end.setAlignment(Pos.CENTER);
        
        //image attributes for displaying wrong answer
        imageWrong=new ImageView(new Image(getClass().getResourceAsStream("wrong.png")));
        imageWrong.setFitHeight(50);
        imageWrong.setFitWidth(50);
        imageWrong.setVisible(false);
        
        //image attributes for displaying right answer
        imageRight=new ImageView(new Image(getClass().getResourceAsStream("right.png")));
        imageRight.setFitHeight(50);
        imageRight.setFitWidth(50);
        imageRight.setVisible(false);
        
        //adding shadow to displayed character
        shadow.setOffsetX(0.5);
        shadow.setOffsetY(0.5);
        
        //generating random characters for displaying
        rand=r.nextInt(26);
        text1=new Text(codes[rand].toString());
        text1.setFont(Font.font("MS Sans Serif", 80));
        text1.setFill(Color.BLUE);
        text1.setEffect(shadow);
        
        Button btn=new Button("Finish");
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ae)
            {
                vbox.setVisible(false);
                end.setVisible(true);
                int accurarcy=correct/total;
                accuracy.setText("Accurarcy \t "+accurarcy);
                finalRight.setText("Correct \t "+correct);
                finalWrong.setText("Incorrect \t "+incorrect);
                finalSkipped.setText("Skipped \t "+unanswered);
                finalTotal.setText("Total \t "+total);
            }
        });
        
        StackPane screen = new StackPane();
        screen.setAlignment(Pos.CENTER);
        screen.getChildren().addAll(text1,imageWrong,imageRight);
        
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setId("grid");
        grid.setHgap(10);
        grid.setVgap(5);
        grid.addRow(1, first, right);
        grid.addRow(2, second, wrong);
        grid.addRow(3, third, unansweredTakes);
        grid.addRow(4, fourth, totalScore);
        
        StackPane bottom=new StackPane();
        bottom.setAlignment(Pos.CENTER);
        bottom.getChildren().add(btn);
        
        vbox.setSpacing(15);
        vbox.getChildren().add(screen);
        vbox.getChildren().add(grid);
        vbox.getChildren().add(bottom);
        
        GridPane root=new GridPane();
        root.getChildren().add(welcome);
        root.getChildren().add(vbox);
        root.getChildren().add(end);
        root.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(root, 400, 400);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
        
            @Override
            public void handle(KeyEvent t)
            {
                FadeTransition imageFade=new FadeTransition(Duration.millis(1000),imageRight);
                if(t.getCode()==codes[rand])
                {
                    correct++;
                    total++;
                    right.setText(Integer.toString(correct));
                    totalScore.setText(Integer.toString(total));
                    text1.setFill(Color.GREEN);
                    imageRight.setVisible(true);                    
                    imageFade.setFromValue(0.0);
                    imageFade.setToValue(1.0);
                    imageFade.setCycleCount(1);
                    imageFade.setFromValue(1.0);
                    imageFade.setToValue(0.0);
                    imageFade.play();
                    timeElapsed=(int)System.currentTimeMillis();
                    imageFade.setOnFinished(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent ae)
                        {
                            imageRight.setVisible(false);
                            rand=r.nextInt(26);
                            FadeTransition fade=new FadeTransition(Duration.millis(1000),text1);
                            text1.setFill(Color.BLUE);
                            text1.setText(codes[rand].toString());
                            fade.setCycleCount(1);
                            fade.setFromValue(1.0);
                            fade.setToValue(0.0);
                            fade.setFromValue(0.0);
                            fade.setToValue(1.0);
                            timeElapsed=(int)System.currentTimeMillis();
                            fade.play();
                        }
                    });
                }
                else
                {
                    incorrect++;
                    total++;
                    wrong.setText(Integer.toString(incorrect));
                    totalScore.setText(Integer.toString(total));
                    text1.setFill(Color.RED);
                    imageWrong.setVisible(true);                    
                    imageFade.setFromValue(0.0);
                    imageFade.setToValue(1.0);
                    imageFade.setCycleCount(1);
                    imageFade.setFromValue(1.0);
                    imageFade.setToValue(0.0);
                    imageFade.play();
                    imageFade.setOnFinished(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent ae)
                        {
                            imageWrong.setVisible(false);
                            rand=r.nextInt(8);
                            FadeTransition fade=new FadeTransition(Duration.millis(1000),text1);
                            text1.setFill(Color.BLUE);
                            text1.setText(codes[rand].toString());
                            fade.setCycleCount(1);
                            fade.setFromValue(1.0);
                            fade.setToValue(0.0);
                            fade.setFromValue(0.0);
                            fade.setToValue(1.0);
                            fade.play();
                            timeElapsed=(int)System.currentTimeMillis();
                        }
                    });
                }            
            }
        });
     
        primaryStage.setTitle("Simple Typing Game");
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
           
    public void checkTime()
    {
        timeNow=(int)System.currentTimeMillis();
        if((timeNow-timeElapsed)>3000)
        {
            unanswered++;
            unansweredTakes.setText(Integer.toString(unanswered));
            total++;
            totalScore.setText(Integer.toString(total));
            imageWrong.setVisible(false);
            rand=r.nextInt(8);
            FadeTransition fade=new FadeTransition(Duration.millis(1000),text1);
            text1.setFill(Color.BLUE);
            text1.setText(codes[rand].toString());
            fade.setCycleCount(1);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            timeElapsed=(int)System.currentTimeMillis();
            fade.play();
        }
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);      
    }
}
