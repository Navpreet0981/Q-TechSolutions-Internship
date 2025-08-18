package Railway_Reservation_System;

public class Passenger {
    private String name;
    private int age;
    private String route;

    public Passenger(String name, int age, String route) {
        this.name = name;
        this.age = age;
        this.route = route;
    }
    // Getters ...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
