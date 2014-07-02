load("fx:base.js");
load("fx:controls.js");
load("fx:graphics.js");

var alphabets = [KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D, KeyCode.E,
                      KeyCode.F, KeyCode.G, KeyCode.H, KeyCode.I, KeyCode.J,
                      KeyCode.K, KeyCode.L, KeyCode.M, KeyCode.N, KeyCode.O,
                      KeyCode.P, KeyCode.Q, KeyCode.R, KeyCode.S, KeyCode.T,
                      KeyCode.U, KeyCode.V, KeyCode.W, KeyCode.X, KeyCode.Y,
                      KeyCode.Z];
var random;

function start() {
    $STAGE.title = "Typing Game";
    var labelMessage = new Label("Type the character");
    var label = new Label();
    label.setText('Lets start');
    var labelScore = new Label("Your Score: ");

    var root = new StackPane();
    
    var grid = new GridPane();
    grid.padding = new Insets(10, 10, 10, 10);
    grid.Vgap = 10;
    grid.setAlignment(Pos.CENTER);

    grid.addRow(1, labelMessage);
    grid.addRow(4, label);
    grid.addRow(7, labelScore);
    root.children.addAll(grid);
    
    var scene = new Scene(root, 150, 100);
    var score = 0, total = 0;

    label.setText(generateRandom());
    scene.onKeyPressed = function(e) {
    	total++;
    	if(e.getCode() === alphabets[random]) {
    		labelMessage.setText('Correct');
    		score++;
    		labelScore.setText("Your score: " + score + " / " + total);
    		label.setText(generateRandom());
    	} else {
    		labelMessage.setText('Wrong');
    		labelScore.setText("Your score: " + score + " / " + total);
    		label.setText(generateRandom());
    	}
    };
    $STAGE.scene = scene;
    $STAGE.show();
}

function generateRandom() {
    random = Math.floor((Math.random() * 26));
    return alphabets[random];
}

