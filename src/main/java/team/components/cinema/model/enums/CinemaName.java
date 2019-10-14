package team.components.cinema.model.enums;

public enum CinemaName {
    ALPHA("Alpha"),
    OMEGA("Omega");

    private String name;

    CinemaName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
