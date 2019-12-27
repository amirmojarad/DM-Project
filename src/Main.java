import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

<<<<<<< HEAD
public class Main {
    public static void main(String[] args) throws Exception {
        Expression expression = new Expression("(1,2),(2,1),(3,8),(4,3),(8,2)");
        System.out.println(expression.symmetric());
=======
public class Main extends Application {
    Expression a;
    Expression b;
    Insets insets = new Insets(10, 10, 10, 10);
    Button evaluate = new Button("Evaluate");
    Button create = new Button("Create Sets");
    Button relations = new Button("Relations");
    Stage mainStage;
    Scene mainScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        //Components
        create.setAlignment(Pos.CENTER);
        create.setPrefSize(150, 25);

        evaluate.setAlignment(Pos.CENTER);
        evaluate.setPrefSize(150, 25);
        evaluate.setDisable(true);

        relations.setAlignment(Pos.CENTER);
        relations.setPrefSize(150, 25);
//        relations.setDisable(true);

        //Actions
        create.setOnAction(event -> {
            evaluate.setDisable(false);
            relations.setDisable(false);
            mainStage.setScene(createScene());
        });
        evaluate.setOnAction(event -> {
            mainStage.setScene(evaluateScene());
        });
        relations.setOnAction(event -> {
            mainStage.setScene(relationScene());
        });
        VBox mainBox = new VBox(create, evaluate, relations);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(insets);
        mainBox.setSpacing(10);
        mainScene = new Scene(mainBox, 300, 200);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    private Scene createScene() {
        ///components
        Line line = new Line(300, 0, 300, 500);
        //A Expression
        Image source_A = new Image("A.jpg");
        ImageView image_A = new ImageView(source_A);
        image_A.setFitHeight(50);
        image_A.setFitWidth(50);

        Label label_A = new Label("Please Enter Your Sentence");
        label_A.setAlignment(Pos.CENTER);

        TextArea text_A = new TextArea();
        text_A.setPrefColumnCount(50);
        text_A.setPrefRowCount(50);
        text_A.setPrefSize(280, 200);

        Button done_A = new Button("Done");
        done_A.setAlignment(Pos.CENTER);
        done_A.setPrefSize(75, 25);

        VBox box_A = new VBox(image_A, label_A, text_A, done_A);
        box_A.setSpacing(10);
        box_A.setPadding(insets);
        box_A.setAlignment(Pos.TOP_CENTER);

        //B Expression
        Image source_B = new Image("B.jpg");
        ImageView image_B = new ImageView(source_B);
        image_B.setFitHeight(50);
        image_B.setFitWidth(50);

        Label label_B = new Label("Please Enter Your Sentence");
        label_B.setAlignment(Pos.CENTER);

        TextArea text_B = new TextArea();
        text_B.setPrefColumnCount(50);
        text_B.setPrefRowCount(50);
        text_B.setPrefSize(280, 200);

        Button done_B = new Button("Done");
        done_B.setAlignment(Pos.CENTER);
        done_B.setPrefSize(75, 25);

        VBox box_B = new VBox(image_B, label_B, text_B, done_B);
        box_B.setSpacing(10);
        box_B.setPadding(insets);
        box_B.setAlignment(Pos.TOP_CENTER);

        HBox mainBox = new HBox(box_A, line, box_B);
        mainBox.setPadding(insets);
        mainBox.setSpacing(10);
        mainBox.setAlignment(Pos.CENTER);

        ///Actions
        //A done Button
        done_A.setOnAction(event -> {
            a = new Expression(text_A.getText());
        });
        //B done Button
        done_B.setOnAction(event -> {
            b = new Expression(text_B.getText());
        });

        Button back = new Button("Back");
        back.setAlignment(Pos.CENTER);
        back.setPrefSize(75, 25);
        back.setLayoutX(10);
        back.setLayoutY(450);
        back.setOnAction(event -> {
            b = new Expression(text_B.getText());
            a = new Expression(text_A.getText());
            if (text_A.getText().equals("") || text_B.getText().equals("")) {
                evaluate.setDisable(true);
                relations.setDisable(true);
            }
            mainStage.setScene(mainScene);
        });
        Pane main = new Pane(mainBox, back);

        Scene createScene = new Scene(main, 640, 500);
        return createScene;
    }

    private Scene evaluateScene() {

        ComboBox<String> expressions = new ComboBox<>();
        expressions.getItems().add("A - A");
        expressions.getItems().add("A - B");
        expressions.getItems().add("B - B");
        expressions.getItems().add("B - A");
        expressions.setPrefHeight(25);
        expressions.setPrefWidth(75);

        ComboBox<String> operations = new ComboBox<>();
        operations.getItems().add("Intersection");
        operations.getItems().add("Union");
        operations.getItems().add("Subtract");
        operations.setPrefWidth(125);
        operations.setPrefHeight(25);

        Label operationLabel = new Label("Operations");
        operationLabel.setAlignment(Pos.CENTER);

        HBox combosBox = new HBox(expressions, operationLabel, operations);
        combosBox.setSpacing(10);
        combosBox.setAlignment(Pos.CENTER);
        combosBox.setPadding(insets);

        TextArea resultText = new TextArea();
        resultText.setPrefSize(680, 200);
        resultText.setPrefColumnCount(150);
        resultText.setPrefRowCount(150);
        resultText.setLayoutX(10);
        resultText.setLayoutY(100);

        Button done = new Button("Done");
        done.setPrefSize(50, 25);
        done.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(combosBox, done);
        hBox.setPadding(insets);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);


        VBox mainBox = new VBox(hBox);
        mainBox.setPadding(insets);
        mainBox.setSpacing(10);
        mainBox.setAlignment(Pos.CENTER);
        done.setOnAction(event -> {
            resultText.clear();
            Expression first = null;
            Expression second = null;
            Expression result = null;
            if (expressions.getValue().equals("A - A")) {
                first = a;
                second = a;
            } else if (expressions.getValue().equals("A - B")) {
                first = a;
                second = b;
            } else if (expressions.getValue().equals("B - B")) {
                first = b;
                second = b;
            } else if (expressions.getValue().equals("B - A")) {
                first = b;
                second = a;
            }
            if (operations.getValue().equals("Intersection")) {
                result = first.intersection(second);
            } else if (operations.getValue().equals("Union")) {
                result = first.union(second);
            } else if (operations.getValue().equals("Subtract")) {
                result = first.difference(second);
            }
            resultText.setText(result.getSentences());
        });

        Button back = new Button("Back");
        back.setAlignment(Pos.CENTER);
        back.setPrefSize(75, 25);
        back.setLayoutX(10);
        back.setLayoutY(350);
        back.setOnAction(event -> {
            mainStage.setScene(mainScene);
        });

        Pane main = new Pane(mainBox, back, resultText);
        Scene evaluateScene = new Scene(main, 700, 400);
        return evaluateScene;
    }

    private Scene relationScene() {
        Expression first;
        Expression second;
        String result;
        Label setLabel = new Label("Choose a Set");
        setLabel.setAlignment(Pos.TOP_CENTER);

        ComboBox<String> sets = new ComboBox<>();
        sets.setPrefHeight(25);
        sets.setPrefWidth(200);
        sets.getItems().add("A");
        sets.getItems().add("B");

        sets.getItems().add("A intersection B");
        sets.getItems().add("A union B");
        sets.getItems().add("A subtract B");

        sets.getItems().add("B intersection A");
        sets.getItems().add("B union A");
        sets.getItems().add("B subtract A");

        Button done = new Button("Done");
        done.setPrefSize(75, 25);
        done.setAlignment(Pos.CENTER);
        done.setDisable(true);

        VBox setsBox = new VBox();
        setsBox.setAlignment(Pos.CENTER);
        setsBox.setSpacing(10);
        setsBox.setPadding(insets);

        TextArea resultText = new TextArea();
        resultText.setPrefRowCount(10);
        resultText.setPrefColumnCount(10);
        resultText.setPrefSize(50, 50);
        resultText.setEditable(false);

        setsBox.getChildren().addAll(setLabel, sets, done, resultText);
        setsBox.setLayoutX(100);
        setsBox.setLayoutY(80);

        //Actions
        sets.setOnAction(event -> {
            if (!sets.getSelectionModel().isEmpty())
                done.setDisable(false);
        });

        done.setOnAction(event -> {
            if (sets.getValue().equals("A")) {

            } else if (sets.getValue().equals("B")) {

            } else if (sets.getValue().equals("A intersection B")) {

            } else if (sets.getValue().equals("A union B")) {

            } else if (sets.getValue().equals("A subtract B")) {

            } else if (sets.getValue().equals("B intersection A")) {

            } else if (sets.getValue().equals("B union A")) {

            } else if (sets.getValue().equals("B subtract A")) {

            }

        });

        Pane pane = new Pane(setsBox);
        Scene relationScene = new Scene(pane, 400, 250);
        return relationScene;
>>>>>>> GUI
    }
}
