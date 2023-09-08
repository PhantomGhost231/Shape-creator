package com.example.assignment6;
// Assignment #: Arizona State University CSE205 #5
//         Name: Rushil Prajapati
////    StudentID: 1225139643
//      Lecture: TUESDAY && THURSDAY 10:35am
//  Description: The DrawPane class creates a canvas where we can use
//               mouse key to draw either a Rectangle, a Circle or an Arc with

//               colors. We can also use the two buttons to erase the last
//  drawn shape or clear them all.
//import any classes necessary here
//----
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.geometry.Orientation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class DrawPane extends BorderPane
{
    private Button undoBtn, eraseBtn;
    private ComboBox<String> colorCombo;
    private RadioButton rbRect, rbCircle, rbArc;
    private ArrayList<Shape> shapeList;
    private Pane canvas;
    private HBox box;
    private VBox box2;
    private String check;
    private Shape shape;
    private Circle c1;
    private Arc a1;
    private Rectangle r1;
    private double x1, y1;

    //declare any other necessary instance variables here
    //----
    //Constructor
    public DrawPane()
    {
        //Step #1: initialize each instance variable and set up layout
        undoBtn = new Button("Undo");
        eraseBtn = new Button("Erase");
        undoBtn.setMinWidth(80.0);
        eraseBtn.setMinWidth(80.0);
        //initialize instance variables and set up the layout
        //----
        undoBtn = new Button("Undo");
        eraseBtn = new Button("Erase");
        undoBtn.setMinWidth(80.0);
        eraseBtn.setMinWidth(80.0);
        colorCombo = new ComboBox<>();
        box = new HBox();
        box.setPrefWidth(200);

        box.getChildren().addAll(undoBtn, eraseBtn);
        box.setAlignment(Pos.CENTER);


        box2 = new VBox();
        colorCombo = new ComboBox<String>();
        colorCombo.getItems().add("Black");
        colorCombo.getItems().add("Red");
        colorCombo.getItems().add("Blue");
        colorCombo.getItems().add("Green");
        colorCombo.getItems().add("Yellow");
        colorCombo.setValue("Black");
        box2.getChildren().add(colorCombo);

        rbArc = new RadioButton("Arc");
        rbCircle = new RadioButton("Circle");
        rbRect = new RadioButton("Rectangle");
        box2.getChildren().addAll(rbArc, rbCircle, rbRect);

        undoBtn.setOnAction(new ButtonHandler());
        eraseBtn.setOnAction(new ButtonHandler());
        rbArc.setOnAction(new ShapeHandler());
        rbCircle.setOnAction(new ShapeHandler());
        rbRect.setOnAction(new ShapeHandler());
        colorCombo.setOnAction((new ColorHandler()));

        c1 =null;
        r1= null;
        a1=null;

        //initialize shapeList, it is a data structure we used
        shapeList = new ArrayList<>();
        //to track the shape we drew
        //----
        //canvas is a Pane where we will draw rectagles, circles and arcs on it
        canvas = new Pane();
        canvas.setStyle("-fx-background-color: Azure;");
        //Step #3: Register the source nodes with its handler objects
        canvas.setOnMousePressed(new MouseHandler());
        canvas.setOnMouseDragged(new MouseHandler());
        canvas.setOnMouseReleased(new MouseHandler());

        setCenter(canvas);
        setBottom(box);
        setLeft(box2);

        //----
        //----
    }
    //Step #2(A) - MouseHandler
    private class MouseHandler implements EventHandler<MouseEvent>
    {

        public void handle(MouseEvent event)
        {
            //handle MouseEvent here
            //----

            if (rbCircle.isSelected())//Circle
            {
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    x1 = event.getX();
                    y1 = event.getY();

                    c1 = new Circle();
                    shape= c1;
                    c1.setCenterX(x1);
                    c1.setCenterY(y1);
                    c1.setFill(Color.WHITE);
                    c1.setStroke(Color.BLACK);

                    canvas.getChildren().add(c1);
                    shapeList.add(c1);


                }
                else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    double tempX = event.getX();
                    double tempY = event.getY();

                    double dx = Math.abs(tempX - x1);
                    double dy = Math.abs(tempY - y1);
                    double radius = Math.sqrt(dx * dx + dy * dy);

                    c1.setRadius(radius);
                }
                else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {

                    String s = colorCombo.getValue();
                    if(s.equals("Red"))
                    {
                        c1.setFill(Color.RED);
                    }
                    else if(s.equals("Blue"))
                    {
                        c1.setFill(Color.BLUE);
                    }
                    else if(s.equals("Green"))
                    {
                        c1.setFill(Color.GREEN);
                    }
                    else if (s.equals("Yellow"))
                    {
                        c1.setFill(Color.YELLOW);
                    }
                    else
                    {
                        c1.setFill(Color.BLACK);
                    }

                }

            }
            else if (rbRect.isSelected())// Rectangle
            {


                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    r1 = new Rectangle();
                    r1.setX(event.getX());
                    r1.setY(event.getY());

                    r1.setFill(Color.WHITE);
                    r1.setStroke(Color.BLACK);

                    canvas.getChildren().add(r1);
                    shapeList.add(r1);


                }
                else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    r1.setWidth(event.getX() - r1.getX() );
                    r1.setHeight(event.getY() -r1.getY() );
                }
                else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    String s = colorCombo.getValue();//sets colors
                    if(s.equals("Red"))
                    {
                        r1.setFill(Color.RED);
                    }
                    else if(s.equals("Blue"))
                    {
                        r1.setFill(Color.BLUE);
                    }
                    else if(s.equals("Green"))
                    {
                        r1.setFill(Color.GREEN);
                    }
                    else if (s.equals("Yellow"))
                    {
                        r1.setFill(Color.YELLOW);
                    }
                    else
                    {
                        r1.setFill(Color.BLACK);
                    }
                }
            }


            else if( rbArc.isSelected())//arc
            {
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    x1 = event.getX();
                    y1 = event.getY();

                    a1 = new Arc();
                    a1.setCenterX(x1);
                    a1.setCenterY(y1);
                    a1.setType(ArcType.ROUND);
                    a1.setStartAngle(0);
                    a1.setFill(Color.WHITE);
                    a1.setStroke(Color.BLACK);

                    canvas.getChildren().add(a1);
                    shapeList.add(a1);


                }
                else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    double tempX = event.getX();
                    double tempY = event.getY();

                    double dx = Math.abs(tempX );
                    double dy = Math.abs(tempY );
                    double radius = Math.sqrt(dx * dx + dy * dy);


                    a1.setRadiusX(radius);
                    a1.setRadiusY(radius/2);
                    double angleInRadians = Math.atan2(-dy/a1.getRadiusY(), dx/a1.getCenterX());
                    double length = Math.toDegrees(angleInRadians);
                    a1.setLength(length);
                    a1.setStartAngle(angleInRadians);




                }
                else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    String s = colorCombo.getValue(); //sets colors
                    if(s.equals("Red"))
                    {
                        a1.setFill(Color.RED);
                    }
                    else if(s.equals("Blue"))
                    {
                        a1.setFill(Color.BLUE);
                    }
                    else if(s.equals("Green"))
                    {
                        a1.setFill(Color.GREEN);
                    }
                    else if (s.equals("Yellow"))
                    {
                        a1.setFill(Color.YELLOW);
                    }
                    else {
                        a1.setFill(Color.BLACK);
                    }
                }
            }

            else{}

        }//end handle()
    }//end MouseHandler
    //Step #2(B)- A handler class used to handle events from Undo & Erase buttons
    private class ButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            Object source= event.getSource();
            if(source==undoBtn)
            {
                canvas.getChildren().remove(shapeList.size()-1);
                shapeList.remove(shapeList.size()-1);
            }
            else if(source == eraseBtn)
            {
                canvas.getChildren().removeAll((shapeList));
                shapeList.removeAll(shapeList);


            }
            //write your codes here
            //----
        }
    }//end ButtonHandler
    //Step #2(C)- A handler class used to handle events from the three radio buttons

    private class ShapeHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            //write your own codes here

        Object source = event.getSource();

        if(rbArc.isSelected())
        {
            check ="arc";
           //shapeList.add(new Arc());
        }
        else if(rbCircle.isSelected()){
           check = "circle";
           //shapeList.add(new Circle());
        }
        else if (rbRect.isSelected())
        {
              check="rectangle";
            //shapeList.add(new Rectangle());
           //shapeList.add(new Rectangle());
        }

       //write your own codes here
       //----


            //----
        }
    }//end ShapeHandler
    //Step #2(D)- A handler class used to handle colors from the combo box
    private class ColorHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            //write your own codes here
/*
        String s = colorCombo.getValue();

            String so = colorCombo.getValue();
            if(s.equals("Red"))
            {
                shape.setFill(Color.RED);
            }
            else if(s.equals("Blue"))
            {
                shape.setFill(Color.BLUE);
            }
            else if(s.equals("Green"))
            {
                shape.setFill(Color.GREEN);
            }
            else if (s.equals("Yellow"))
            {
                shape.setFill(Color.YELLOW);
            }
            else {
                shape.setFill(Color.BLACK);
            }
            //----
            **/

        }
    }//end ColorHandler
}//end class DrawPan