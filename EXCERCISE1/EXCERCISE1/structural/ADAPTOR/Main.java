interface MediaPlayer {
    void play(String fileType, String fileName);
}

class MP3Player {
    public void playMP3(String fileName) {
        System.out.println("Playing MP3 file: " + fileName);
    }
}

class MP4Player {
    public void playMP4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }
}

class MediaAdapter implements MediaPlayer {
    private MP3Player mp3Player;
    private MP4Player mp4Player;

    public MediaAdapter(String fileType) {
        if (fileType.equalsIgnoreCase("mp3")) {
            mp3Player = new MP3Player();
        } else if (fileType.equalsIgnoreCase("mp4")) {
            mp4Player = new MP4Player();
        }
    }

    @Override
    public void play(String fileType, String fileName) {
        if (fileType.equalsIgnoreCase("mp3")) {
            mp3Player.playMP3(fileName);
        } else if (fileType.equalsIgnoreCase("mp4")) {
            mp4Player.playMP4(fileName);
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        MediaAdapter adapter = new MediaAdapter("mp4");
        adapter.play("mp4", "my_video.mp4");

        adapter = new MediaAdapter("mp3");
        adapter.play("mp3", "my_song.mp3");
    }
}
