public interface Camera {
    void takePhoto();
}
public interface MusicPlayer {
    void playMusic();
}
public class SmartPhone implements Camera, MusicPlayer {
    @Override
    public void takePhoto() {
        System.out.println("Taking photo with smartphone");
    }
    
    @Override
    public void playMusic() {
        System.out.println("Playing music on smartphone");
    }
}
public class DeviceTest {
    public static void main(String[] args) {
        SmartPhone phone = new SmartPhone();
        phone.takePhoto();
        phone.playMusic();
    }
}
// Example usage
// Output:
// Taking photo with smartphone
// Playing music on smartphone  