package world.anhgelus.gamelibrary.team;

public abstract class TeamProperties {
    public final Team linkedTeam;
    private int points = 0;

    /**
     * /!\ Do not modify the constructor /!\
     * @param linkedTeam The team linked with this properties
     */
    public TeamProperties(Team linkedTeam) {
        this.linkedTeam = linkedTeam;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void removePoints(int points) {
        this.points -= points;
    }
}
