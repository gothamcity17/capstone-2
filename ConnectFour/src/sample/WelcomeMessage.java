package sample;

public abstract class WelcomeMessage {

    // Concrete method (not a shell)
    public String welcomeMessagePlayer1(){

        return ("Welcome ");
    }

    // Abstract method (is a shell)
    public abstract String welcomeMessagePlayer2();
}

