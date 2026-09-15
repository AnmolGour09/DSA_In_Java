class Solution {
    private double radius;
    private double c1;
    private double c2;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.c1 = x_center;
        this.c2 = y_center;
    }
    
    public double[] randPoint() {
        while (true) {
            double x = (Math.random() * 2 - 1) * radius;
            double y = (Math.random() * 2 - 1) * radius;

            if (x * x + y * y <= radius * radius) {
                return new double[] {
                    c1 + x,
                    c2 + y
                };
            }
        }
    }
}
