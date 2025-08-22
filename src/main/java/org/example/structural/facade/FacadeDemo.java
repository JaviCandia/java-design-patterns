package org.example.structural.facade;

class Projector {
    void turnOn() {
        System.out.println("Projector turned on");
    }

    void turnOff() {
        System.out.println("Projector turned off");
    }
}

class SoundSystem {
    void on() {
        System.out.println("Sound system turned on");
    }

    void off() {
        System.out.println("Sound system turned off");
    }
}

class VideoPlayer {
    void on() {
        System.out.println("Video player turned on");
    }

    void play(String movie) {
        System.out.println("Playing " + movie);
    }

    void stop() {
        System.out.println("Movie stopped");
    }

    void off() {
        System.out.println("Video player turned off");
    }
}

class PopcornMaker {
    void startPopping() {
        System.out.println("Making popcorn");
    }

    void stopPopping() {
        System.out.println("Stopping popcorn");
    }
}

class HomeTheaterFacade {
    private Projector projector;
    private SoundSystem soundSystem;
    private VideoPlayer videoPlayer;
    private PopcornMaker popcornMaker;

    public HomeTheaterFacade(Projector projector, SoundSystem soundSystem,
                             VideoPlayer videoPlayer, PopcornMaker popcornMaker) {
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.videoPlayer = videoPlayer;
        this.popcornMaker = popcornMaker;
    }

    public void watchMovie(String movie) {
        System.out.println("Preparing to watch the movie");
        projector.turnOn();
        soundSystem.on();
        popcornMaker.startPopping();
        videoPlayer.on();
        videoPlayer.play(movie);

        System.out.println("Enjoy the movie!");
    }

    public void endWatchingMovie() {
        System.out.println("\n\nPreparing to stop the movie");
        projector.turnOff();
        soundSystem.off();
        popcornMaker.stopPopping();
        videoPlayer.stop();
        videoPlayer.off();

        System.out.println("System turned off\n");
    }
}


public class FacadeDemo {
    public static void main(String[] args) {
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();
        VideoPlayer videoPlayer = new VideoPlayer();
        PopcornMaker popcornMaker = new PopcornMaker();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(projector, soundSystem, videoPlayer, popcornMaker);

        homeTheater.watchMovie("The Avengers");

        homeTheater.endWatchingMovie();
    }
}