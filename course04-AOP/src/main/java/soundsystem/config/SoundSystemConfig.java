package soundsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import soundsystem.TrackCounter;
import soundsystem.album.BlankDisc;
import soundsystem.album.CompactDisc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author justZero
 * @since 2018/12/31
 */
@Configuration
@EnableAspectJAutoProxy
public class SoundSystemConfig {

    @Bean
    public CompactDisc seventeen() {
        BlankDisc cd = new BlankDisc();
        cd.setTitle("Seventeen");
        cd.setArtist("Xxxtentacion");
        List<String> tracks = new ArrayList<>();
        tracks.add("The Explannation");
        tracks.add("Jocelyn Flores");
        tracks.add("Depression & Obsession");
        tracks.add("Everybody Die In Their Nightmares");
        tracks.add("Revenge");
        tracks.add("Fuck Love");
        cd.setTracks(tracks);
        return cd;
    }

    @Bean
    public TrackCounter trackCounter() {
        return new TrackCounter();
    }

}
