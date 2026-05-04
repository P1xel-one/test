package org.example.demo67;

import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller {

    // ==================== ПОЛЯ ====================

    double StartMouseX;
    double StartMouseY;
    double endMouseX;
    double endMouseY;
    Random random = new Random();

    @FXML
    private URL location;
    @FXML
    private Slider Slider;
    @FXML
    private Tab Tab1;
    @FXML
    private Tab Tab2;
    @FXML
    private Tab Tab3;
    @FXML
    private Box box = new Box(200, 200, 200);
    private Rotate rotateXAxis;
    private Rotate rotateYAxis;
    private Translate translate;
    private PhongMaterial material = new PhongMaterial();

    private final double mouseSensitivity = 0.1;
    private final double movementSpeed = 10.0;
    private double mouse0ldX, mouse0ldY;
    private double mouseDeltaY, mouseDeltaX;

    @FXML
    private MenuItem black;
    @FXML
    private MenuItem blue;
    @FXML
    private SplitMenuButton color;
    private Color color1;
    @FXML
    private SplitMenuButton form;
    @FXML
    private MenuItem krugs;
    @FXML
    private MenuItem line1;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private AnchorPane pane3 = new AnchorPane();
    private PerspectiveCamera camera;
    Group modelGroup = new Group();
    @FXML
    private MenuItem pryamoug;
    @FXML
    private MenuItem red;
    @FXML
    private Slider slider;
    @FXML
    private ResourceBundle resources;
    @FXML
    private Line line;
    private double startAngle;
    private double anchorX, anchorY;
    @FXML
    private Circle krug;
    @FXML
    private Rectangle kvadrat;

    // ==================== НОВОЕ: Фабрика фигур ====================
    private ShapeFactory shapeFactory = new ShapeFactory();
    private String currentShapeType = "линия";  // запоминаем текущий тип фигуры

    // ==================== 3D МЕТОДЫ ====================

    @FXML
    void SkrollPane3(ScrollEvent event) {
        double delta = event.getSceneY();
        if (delta > 0) {
            translate.setZ(translate.getZ() + movementSpeed);
        } else {
            translate.setZ(translate.getZ() - movementSpeed);
        }
        System.out.println("skroll");
    }

    @FXML
    void actionPressedPane3(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            mouse0ldX = event.getSceneX();
            mouse0ldY = event.getSceneY();
            System.out.println("press");
        }
    }

    @FXML
    void pane3Dragged(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            mouseDeltaX = event.getSceneX() - mouse0ldX;
            mouseDeltaY = event.getSceneX() - mouse0ldY;
            rotateXAxis.setAngle(rotateXAxis.getAngle() - mouseDeltaY * mouseSensitivity);
            rotateYAxis.setAngle(rotateYAxis.getAngle() - mouseDeltaX * mouseSensitivity);
        }
    }

    public void Box() {
        material.setDiffuseColor(Color.OLIVE);
        box.setMaterial(material);
        rotateXAxis = new Rotate(0, Rotate.X_AXIS);
        rotateYAxis = new Rotate(0, Rotate.Y_AXIS);
        translate = new Translate();
        modelGroup.getTransforms().addAll(translate, rotateXAxis, rotateYAxis);
        modelGroup.getChildren().add(box);
        AmbientLight ambientLight = new AmbientLight(Color.WHITE);
        PointLight pointLight = new PointLight(Color.WHITE);
        pointLight.setTranslateX(800);
        pointLight.setTranslateY(-700);
        pointLight.setTranslateZ(-300);
        pane3.getChildren().addAll(modelGroup, ambientLight, pointLight);
    }

    // ==================== ВКЛАДКА 2: РИСОВАНИЕ ====================

    @FXML
    void paneclick(MouseEvent event) {
        // можно оставить пустым или удалить
    }

    @FXML
    void panepressed(MouseEvent event) {
        // Определяем цвет
        if (color.getText().equals("красный")) {
            color1 = Color.RED;
        } else if (color.getText().equals("синий")) {
            color1 = Color.BLUE;
        } else if (color.getText().equals("черный")) {
            color1 = Color.BLACK;
        }

        StartMouseX = event.getX();
        StartMouseY = event.getY();

        // Получаем текущий тип фигуры из кнопки
        currentShapeType = form.getText();

        // Используем фабрику для создания фигуры
        switch (currentShapeType) {
            case "круг":
                System.out.println("круг");
                shapeFactory.createCircle(pane2, StartMouseX, StartMouseY, color1, Slider.getValue());
                break;
            case "линия":
                System.out.println("линия");
                shapeFactory.createLine(pane2, StartMouseX, StartMouseY, color1, Slider.getValue());
                break;
            case "прямоугольник":
                System.out.println("прямоугольник");
                shapeFactory.createRectangle(pane2, StartMouseX, StartMouseY, color1, Slider.getValue());
                break;
        }
    }

    @FXML
    void panedragged(MouseEvent event) {
        endMouseX = event.getX();
        endMouseY = event.getY();

        // Используем фабрику для обновления фигуры
        switch (currentShapeType) {
            case "круг":
                shapeFactory.updateCircle(endMouseX, endMouseY);
                break;
            case "линия":
                shapeFactory.updateLine(endMouseX, endMouseY);
                break;
            case "прямоугольник":
                shapeFactory.updateRectangle(endMouseX, endMouseY);
                break;
        }
    }

    // ==================== ВЫБОР ФИГУРЫ ====================

    @FXML
    void OnActionkrugs(ActionEvent event) {
        form.setText("круг");
        currentShapeType = "круг";
    }

    @FXML
    void OnActionline(ActionEvent event) {
        form.setText("линия");
        currentShapeType = "линия";
    }

    @FXML
    void OnActionpryamoug(ActionEvent event) {
        form.setText("прямоугольник");
        currentShapeType = "прямоугольник";
    }

    // ==================== ВЫБОР ЦВЕТА ====================

    @FXML
    void OnActonRed(ActionEvent event) {
        color.setText("красный");
    }

    @FXML
    void OnActonblack(ActionEvent event) {
        color.setText("черный");
    }

    @FXML
    void OnActonblue(ActionEvent event) {
        color.setText("синий");
    }

    // ==================== ВКЛАДКА 1: ДЕМО-ФИГУРЫ ====================

    private Color generateRandomColor(Random random) {
        this.random = random;
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    @FXML
    void OnMouseClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            System.out.println(krug.getFill());
            krug.setFill(generateRandomColor(random));
        } else if (event.getButton() == MouseButton.SECONDARY) {
            krug.setFill(Paint.valueOf("linear-gradient(from 0.0% 0.0% to 100.0% 100.0%, 0x7700ffff 0.0%, 0xada40eff 95.1219512195122%, 0xffffffff 100.0%)"));
        }
    }

    @FXML
    void OnMouseExited(MouseEvent event) {
        kvadrat.setFill(generateRandomColor(random));
    }

    @FXML
    void OnMouseMoved(MouseEvent event) {
        kvadrat.setFill(Paint.valueOf("linear-gradient(from 0.0% 0.0% to 100.0% 100.0%, 0x7700ffff 0.0%, 0xada40eff 95.1219512195122%, 0xffffffff 100.0%)"));
    }

    // ==================== ЛИНИЯ (вращение) ====================

    @FXML
    void LineMouseDragged(MouseEvent event) {
        double deltaX = event.getSceneX() - anchorX;
        double deltaY = event.getSceneY() - anchorY;
        double newAngel = Math.atan2(deltaY, deltaX) * 180 / Math.PI + 90;
        line.setRotate(startAngle + newAngel);
    }

    @FXML
    void LineMousePressed(MouseEvent event) {
        anchorX = event.getSceneX();
        anchorY = event.getSceneY();
        startAngle = line.getRotate();
    }

    // ==================== СКРОЛЛ НА ФИГУРАХ ====================

    @FXML
    void OnScroll(ScrollEvent event) {
        EventTarget objects = event.getTarget();

        if (objects instanceof Circle) {
            shapeFactory.scrollCircle(event.getDeltaY());
        } else if (objects instanceof Line) {
            shapeFactory.scrollLine(event.getDeltaY());
        } else if (objects instanceof Rectangle) {
            shapeFactory.scrollRectangle(event.getDeltaY());
        }
    }

    // ==================== INITIALIZE ====================
    @FXML
    void initialize() {
        assert Slider != null : "fx:id=\"Slider\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Tab1 != null : "fx:id=\"Tab1\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Tab2 != null : "fx:id=\"Tab2\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert black != null : "fx:id=\"black\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert blue != null : "fx:id=\"blue\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert color != null : "fx:id=\"color\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert form != null : "fx:id=\"form\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert krug != null : "fx:id=\"krug\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert krugs != null : "fx:id=\"krugs\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert kvadrat != null : "fx:id=\"kvadrat\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert line != null : "fx:id=\"line\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert line1 != null : "fx:id=\"line1\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert pane1 != null : "fx:id=\"pane1\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert pane2 != null : "fx:id=\"pane2\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert pryamoug != null : "fx:id=\"pryamoug\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert red != null : "fx:id=\"red\" was not injected: check your FXML file 'hello-view.fxml'.";

        Box();
        camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-500);
    }
}