package org.example.demo67;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Фабрика для создания и управления фигурами
 */
public class ShapeFactory {

    // Текущие созданные фигуры
    private Circle currentCircle;
    private Line currentLine;
    private Rectangle currentRectangle;

    // Начальные координаты для расчета размеров
    private double startX;
    private double startY;

    /**
     * Создать круг
     */
    public void createCircle(AnchorPane pane, double x, double y, Color color, double strokeWidth) {
        this.startX = x;
        this.startY = y;
        currentCircle = new Circle(x, y, 0);
        currentCircle.setFill(color);
        currentCircle.setStroke(Color.RED);
        currentCircle.setStrokeWidth(strokeWidth);
        pane.getChildren().add(currentCircle);
    }

    /**
     * Обновить круг при перетаскивании
     */
    public void updateCircle(double endX, double endY) {
        if (currentCircle != null) {
            double radius = Math.sqrt(
                    Math.pow((startX - endX), 2) +
                            Math.pow((startY - endY), 2)
            );
            currentCircle.setRadius(radius);
        }
    }

    /**
     * Создать линию
     */
    public void createLine(AnchorPane pane, double x, double y, Color color, double strokeWidth) {
        currentLine = new Line(x, y, x + 1, y + 1);
        currentLine.setStrokeWidth(strokeWidth);
        currentLine.setStroke(color);
        pane.getChildren().add(currentLine);
    }

    /**
     * Обновить линию при перетаскивании
     */
    public void updateLine(double endX, double endY) {
        if (currentLine != null) {
            currentLine.setEndX(endX);
            currentLine.setEndY(endY);
        }
    }

    /**
     * Создать прямоугольник
     */
    public void createRectangle(AnchorPane pane, double x, double y, Color color, double strokeWidth) {
        this.startX = x;
        this.startY = y;
        currentRectangle = new Rectangle(x, y, 0, 0);
        currentRectangle.setFill(color);
        currentRectangle.setStroke(Color.RED);
        currentRectangle.setStrokeWidth(strokeWidth);
        pane.getChildren().add(currentRectangle);
    }

    /**
     * Обновить прямоугольник при перетаскивании
     */
    public void updateRectangle(double endX, double endY) {
        if (currentRectangle != null) {
            currentRectangle.setWidth(Math.abs(startX - endX));
            currentRectangle.setHeight(Math.abs(startY - endY));
            currentRectangle.setX(Math.min(startX, endX));
            currentRectangle.setY(Math.min(startY, endY));
        }
    }

    /**
     * Изменить размер круга при скролле
     */
    public void scrollCircle(double deltaY) {
        if (currentCircle != null) {
            if (deltaY > 0) {
                currentCircle.setRadius(currentCircle.getRadius() + 5);
            } else if (deltaY < 0) {
                currentCircle.setRadius(Math.max(0, currentCircle.getRadius() - 5));
            }
        }
    }

    /**
     * Изменить толщину линии при скролле
     */
    public void scrollLine(double deltaY) {
        if (currentLine != null) {
            if (deltaY > 0) {
                currentLine.setStrokeWidth(currentLine.getStrokeWidth() * 1.05);
            } else if (deltaY < 0) {
                currentLine.setStrokeWidth(currentLine.getStrokeWidth() / 1.05);
            }
        }
    }

    /**
     * Изменить размер прямоугольника при скролле
     */
    public void scrollRectangle(double deltaY) {
        if (currentRectangle != null) {
            if (deltaY > 0) {
                currentRectangle.setWidth(currentRectangle.getWidth() * 1.05);
                currentRectangle.setHeight(currentRectangle.getHeight() * 1.05);
            } else if (deltaY < 0) {
                currentRectangle.setWidth(Math.max(1, currentRectangle.getWidth() / 1.05));
                currentRectangle.setHeight(Math.max(1, currentRectangle.getHeight() / 1.05));
            }
        }
    }

    /**
     * Очистить текущие фигуры (если нужно)
     */
    public void clear() {
        currentCircle = null;
        currentLine = null;
        currentRectangle = null;
    }
}