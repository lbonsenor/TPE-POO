package backend.model;

public enum FigureButtonEnum {
    RECTANGLE("Rectangulo"){
        @Override
        public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
            return new Rectangle(startPoint, endPoint);
        }
    },
    CIRCLE("Círculo"){
        @Override
        public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
            double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
			return new Circle(startPoint, circleRadius);
        }
    },
    ELLIPSE("Elipse"){
        @Override
        public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
            Point centerPoint = new Point(Math.abs(endPoint.x + startPoint.x) / 2, (Math.abs((endPoint.y + startPoint.y)) / 2));
            double sMayorAxis = Math.abs(endPoint.x - startPoint.x);
            double sMinorAxis = Math.abs(endPoint.y - startPoint.y);
            return new Ellipse(centerPoint, sMayorAxis, sMinorAxis);
        }
    },
    SQUARE("Cuadrado"){
        @Override
        public Figure getFigureBasedOnPoints(Point startPoint, Point endPoint){
            double size = Math.abs(endPoint.getX() - startPoint.getX());
			return new Square(startPoint, size);
        }
    };

    private String name;

    FigureButtonEnum(String name){
        this.name = name;
    }

    public abstract Figure getFigureBasedOnPoints(Point startPoint, Point endPoint);
    
    @Override
    public String toString(){
        return this.name;
    }
}
